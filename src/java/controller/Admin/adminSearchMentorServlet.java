/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Admin;

import DAO.CVDAO;
import DAO.MentorDAO;
import DAO.RateDAO;
import DAO.RequestDAO;
import DAO.SlotDAO;
import Model.CV;
import Model.Mentor;
import Model.Rate;
import Model.Request;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author tuong
 */
@WebServlet(name = "adminSearchMentorServlet", urlPatterns = {"/adminSearchMentor"})
public class adminSearchMentorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet adminSearchMentorServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminSearchMentorServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MentorDAO actMent = new MentorDAO();
        CVDAO actCV = new CVDAO();
        RateDAO actRate = new RateDAO();
        SlotDAO actSlot = new SlotDAO();
        RequestDAO actRequest = new RequestDAO();
        String search = request.getParameter("search");
        if (search == null || search.trim() == "") {
            response.sendRedirect("mentorListAdmin");
        }
        List<Mentor> mentorList = actMent.searchAllMentor(search);
        //get pagination
        String page_raw = request.getParameter("page");
        String numDis_raw = request.getParameter("numDis");
        int page, numDis;
        if (page_raw != null) {
            page = Integer.parseInt(page_raw);
        } else {
            page = 1;
        }
        if (numDis_raw != null) {
            numDis = Integer.parseInt(numDis_raw);
        } else {
            numDis = 10;
        }
        int stt = (page - 1) * numDis;
        int numMent = mentorList.size();
        int numOfPage = (numMent % numDis == 0 ? numMent / numDis : (numMent / numDis + 1));

        //Set pagination
        request.setAttribute("stt", stt);
        request.setAttribute("numOfPage", numOfPage);
        request.setAttribute("indexPage", page);
        request.setAttribute("numDis", numDis);

        //
        mentorList = actMent.searchListMentorPagiantion(search, page, numDis);
        List<Request> curProcessRequestt = actRequest.getAllRequestByStatus("Processing");
        List<Request> curComplete = actRequest.getAllCompleteRequest();
        List<Rate> listAllRate = actRate.getAllRates();
        //Create needed data
        String[] listProfess = new String[mentorList.size()];
        int[] curRequest = new int[mentorList.size()];
        float[] percenComplete = new float[mentorList.size()];
        float[] rate = new float[mentorList.size()];
        boolean[] checkValidate = new boolean[mentorList.size()];
        List<CV> listCV = actCV.getMostEficientCV();

        //Loop all mentor list
        for (int i = 0; i < mentorList.size(); i++) {
            int mentorID = mentorList.get(i).getMentorId();
            //Professtion
            boolean availProfess = false;
            for (int j = 0; j < listCV.size(); j++) {
                if (mentorID == listCV.get(j).getMentorId()) {
                    listProfess[i] = listCV.get(j).getJobProfession();
                    availProfess = true;
                    listCV.remove(j);
                    j = listCV.size();
                }
            }
            if (availProfess == false) {
                listProfess[i] = "Not Available";
            }
            //Current request
            int countRequest = 0;
            for (int r = 0; r < curProcessRequestt.size(); r++) {
                if (mentorID == curProcessRequestt.get(r).getMentorId()) {
                    countRequest++;
                    curProcessRequestt.remove(r);
                }
            }
            curRequest[i] = countRequest;
            //Percen complete
            int countComplete = 0;
            for (int j = 0; j < curComplete.size(); j++) {
                if (mentorID == curComplete.get(j).getMentorId()) {
                    countComplete++;
                    curComplete.remove(j);
                }
            }
            int countAllValid = actRequest.countValidRequestMentorID(mentorID);
            if (countAllValid != 0) {
                percenComplete[i] = (float) countComplete / countAllValid;
                BigDecimal bd = new BigDecimal(percenComplete[i]);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                percenComplete[i] = bd.floatValue() * 100;
            } else {
                percenComplete[i] = 0;
            }
            //Rate
            int sumRateMentor = actRate.sumRateMentor(mentorID);
            int countRateMentor = actRate.CountRateMentor(mentorID);
            if (countRateMentor != 0) {
                rate[i] = (float) sumRateMentor / countRateMentor;
                BigDecimal bd = new BigDecimal(rate[i]);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                rate[i] = bd.floatValue();
            }
            //Update status avalable
            CV mentorCV = actCV.getCVbyMentorId(mentorID);
            if (mentorCV == null) {
                checkValidate[i] = false;
            } else {
                checkValidate[i] = (!actSlot.getListofActiveSlotsByMentorId(mentorID).isEmpty()) ? true : false;
            }
        }
        //Set data
        request.setAttribute("search", search);
        request.setAttribute("listMent", mentorList);
        request.setAttribute("profess", listProfess);
        request.setAttribute("curRe", curRequest);
        request.setAttribute("percComplete", percenComplete);
        request.setAttribute("rate", rate);
        request.setAttribute("validChange", checkValidate);
        request.getRequestDispatcher("/Admin/adminSearchMentor.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MentorDAO actMent = new MentorDAO();
        CVDAO actCV = new CVDAO();
        RateDAO actRate = new RateDAO();
        SlotDAO actSlot = new SlotDAO();
        RequestDAO actRequest = new RequestDAO();
        String search = request.getParameter("search");
        if (search == null || search.trim() == "") {
            response.sendRedirect("mentorListAdmin");
            return;
        }
        List<Mentor> mentorList = actMent.searchAllMentor(search);
        //Update status
        String id_raw = request.getParameter("mentorId");
        int id = Integer.parseInt(id_raw);
        String status = request.getParameter("status");
        if (status != null) {
            if (status.equalsIgnoreCase("inactive")) {
                boolean checkUpdateStatus = actMent.changeStatusMentorById(id, "active");
            } else {
                boolean checkUpdateStatus = actMent.changeStatusMentorById(id, "inactive");
            }
        } else {
            boolean checkUpdateStatus = actMent.changeStatusMentorById(id, "inactive");
        }

        //get pagination
        String page_raw = request.getParameter("page");
        String numDis_raw = request.getParameter("numDis");
        int page, numDis;
        if (page_raw != null) {
            page = Integer.parseInt(page_raw);
        } else {
            page = 1;
        }
        if (numDis_raw != null) {
            numDis = Integer.parseInt(numDis_raw);
        } else {
            numDis = 10;
        }
        int stt = (page - 1) * numDis;
        int numMent = mentorList.size();
        int numOfPage = (numMent % numDis == 0 ? numMent / numDis : (numMent / numDis + 1));

        //Set pagination
        request.setAttribute("stt", stt);
        request.setAttribute("numOfPage", numOfPage);
        request.setAttribute("indexPage", page);
        request.setAttribute("numDis", numDis);

        //
        mentorList = actMent.searchListMentorPagiantion(search, page, numDis);
        List<Request> curProcessRequestt = actRequest.getAllRequestByStatus("Processing");
        List<Request> curComplete = actRequest.getAllCompleteRequest();
        List<Rate> listAllRate = actRate.getAllRates();
        //Create needed data
        String[] listProfess = new String[mentorList.size()];
        int[] curRequest = new int[mentorList.size()];
        float[] percenComplete = new float[mentorList.size()];
        float[] rate = new float[mentorList.size()];
        boolean[] checkValidate = new boolean[mentorList.size()];
        List<CV> listCV = actCV.getMostEficientCV();

        //Loop all mentor list
        for (int i = 0; i < mentorList.size(); i++) {
            int mentorID = mentorList.get(i).getMentorId();
            //Professtion
            boolean availProfess = false;
            for (int j = 0; j < listCV.size(); j++) {
                if (mentorID == listCV.get(j).getMentorId()) {
                    listProfess[i] = listCV.get(j).getJobProfession();
                    availProfess = true;
                    listCV.remove(j);
                    j = listCV.size();
                }
            }
            if (availProfess == false) {
                listProfess[i] = "Not Available";
            }
            //Current request
            int countRequest = 0;
            for (int r = 0; r < curProcessRequestt.size(); r++) {
                if (mentorID == curProcessRequestt.get(r).getMentorId()) {
                    countRequest++;
                    curProcessRequestt.remove(r);
                }
            }
            curRequest[i] = countRequest;
            //Percen complete
            int countComplete = 0;
            for (int j = 0; j < curComplete.size(); j++) {
                if (mentorID == curComplete.get(j).getMentorId()) {
                    countComplete++;
                    curComplete.remove(j);
                }
            }
            int countAllValid = actRequest.countValidRequestMentorID(mentorID);
            if (countAllValid != 0) {
                percenComplete[i] = (float) countComplete / countAllValid;
                BigDecimal bd = new BigDecimal(percenComplete[i]);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                percenComplete[i] = bd.floatValue() * 100;
            } else {
                percenComplete[i] = 0;
            }
            //Rate
            int sumRateMentor = actRate.sumRateMentor(mentorID);
            int countRateMentor = actRate.CountRateMentor(mentorID);
            if (countRateMentor != 0) {
                rate[i] = (float) sumRateMentor / countRateMentor;
                BigDecimal bd = new BigDecimal(rate[i]);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                rate[i] = bd.floatValue();
            }
            //Update status avalable
            CV mentorCV = actCV.getCVbyMentorId(mentorID);
            if (mentorCV == null) {
                checkValidate[i] = false;
            } else {
                checkValidate[i] = (!actSlot.getListofActiveSlotsByMentorId(mentorID).isEmpty()) ? true : false;
            }
        }
        //Set data
        request.setAttribute("search", search);
        request.setAttribute("listMent", mentorList);
        request.setAttribute("profess", listProfess);
        request.setAttribute("curRe", curRequest);
        request.setAttribute("percComplete", percenComplete);
        request.setAttribute("rate", rate);
        request.setAttribute("validChange", checkValidate);
        request.getRequestDispatcher("/Admin/adminSearchMentor.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
