/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Admin;

import DAO.AttendanceDAO;
import DAO.MenteeDAO;
import DAO.RequestDAO;
import DAO.SlotDAO;
import Model.Attendance;
import Model.Mentee;
import Model.Request;
import Model.Slot;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author tuong
 */
@WebServlet(name = "MenteeListAdminServlet", urlPatterns = {"/menteeListAdmin"})
public class MenteeListAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet MenteeListAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MenteeListAdminServlet at " + request.getContextPath() + "</h1>");
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
        MenteeDAO actMentee = new MenteeDAO();
        RequestDAO actRequest = new RequestDAO();
        List<Mentee> listMentee = actMentee.getAllMentee();
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
        request.setAttribute("stt", stt);
        int numMent = listMentee.size();
        int numOfPage = (numMent % numDis == 0 ? numMent / numDis : (numMent / numDis + 1));
        request.setAttribute("numOfPage", numOfPage);
        listMentee = actMentee.getListMenteePagination(page, numDis);
        request.setAttribute("indexPage", page);
        request.setAttribute("numDis", numDis);
        request.setAttribute("listMent", listMentee);

        int[] arrayNumMentor = getNumOfMentorOfMentee(listMentee);
        request.setAttribute("numMentor", arrayNumMentor);
        int[] arrayNumSkill = getNumOfSkillEachMentee(listMentee);
        request.setAttribute("numSkill", arrayNumSkill);
        float[] arrayHour = getTotalHourOfMentee(listMentee);
        request.setAttribute("numHour", arrayHour);
        request.getRequestDispatcher("/Admin/menteeListAdmin.jsp").forward(request, response);
    }

    public int[] getNumOfMentorOfMentee(List<Mentee> listMentee) {
        RequestDAO actReq = new RequestDAO();
        int[] arrayCount = new int[listMentee.size()];
        for (int i = 0; i < listMentee.size(); i++) {
            int menteeId = listMentee.get(i).getMenteeId();
            arrayCount[i] = actReq.getNumOfValidMentorEachMentee(menteeId);
        }
        return arrayCount;
    }

    public int[] getNumOfSkillEachMentee(List<Mentee> listMentee) {
        RequestDAO actReq = new RequestDAO();
        int[] arrayCount = new int[listMentee.size()];
        for (int i = 0; i < listMentee.size(); i++) {
            int menteeId = listMentee.get(i).getMenteeId();
            arrayCount[i] = actReq.getNumOfValidSkillEachMentee(menteeId);
        }
        return arrayCount;
    }

    public float[] getTotalHourOfMentee(List<Mentee> listMentee) {
        RequestDAO actReq = new RequestDAO();
        AttendanceDAO actAtt = new AttendanceDAO();
        float[] arraySum = new float[listMentee.size()];

        //Loop for each mentee
        for (int i = 0; i < listMentee.size(); i++) {
            int menteeId = listMentee.get(i).getMenteeId();
            List<Attendance> listAttendOfMentee = actAtt.getAttendedByMenteeID(menteeId);
            float hourOfMentee = 0;
            //Loop for each request of mentee
            for (int j = 0; j < listAttendOfMentee.size(); j++) {
                Attendance attend = listAttendOfMentee.get(j);
                LocalTime startTime = attend.getStartTime();
                LocalTime endTime = attend.getEndTime();
                float durationInHours = (float) Duration.between(startTime, endTime).toMinutes() / 60;
                //getTotal hour each request
                hourOfMentee += durationInHours;
            }
            arraySum[i] = (float) Math.round(hourOfMentee * 100) / 100;
        }

        return arraySum;
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
        MenteeDAO actMentee = new MenteeDAO();
        RequestDAO actRequest = new RequestDAO();
        List<Mentee> listMentee = actMentee.getAllMentee();
        String page_raw = request.getParameter("page");
        String numDis_raw = request.getParameter("numDis");
        String search = request.getParameter("search");
        if (search == null || search == "") {
            response.sendRedirect("menteeListAdmin");
            return;
        }
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
        request.setAttribute("stt", stt);
        int numMent = listMentee.size();
        int numOfPage = (numMent % numDis == 0 ? numMent / numDis : (numMent / numDis + 1));
        request.setAttribute("numOfPage", numOfPage);
        listMentee = actMentee.getListMenteeSearchPagination(search, page, numDis);
        request.setAttribute("indexPage", page);
        request.setAttribute("numDis", numDis);
        request.setAttribute("listMent", listMentee);
        request.setAttribute("search", search);
        int[] arrayNumMentor = getNumOfMentorOfMentee(listMentee);
        request.setAttribute("numMentor", arrayNumMentor);
        int[] arrayNumSkill = getNumOfSkillEachMentee(listMentee);
        request.setAttribute("numSkill", arrayNumSkill);
        float[] arrayHour = getTotalHourOfMentee(listMentee);
        request.setAttribute("numHour", arrayHour);
        request.getRequestDispatcher("/Admin/menteeListAdmin.jsp").forward(request, response);
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
