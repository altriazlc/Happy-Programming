/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Manager;

import DAO.CVDAO;
import DAO.SlotDAO;
import Model.CV;
import Model.Mentor;
import Model.Skill;
import Model.SkillList;
import Model.Slot;
import Model.User;
import com.google.gson.Gson;
import static controller.Mentee.SlotViewServlet.convertDayInWeekToCurrentDate;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ManagerSlotCatebyMentorServlet", urlPatterns = {"/slotmanagercate"})
public class ManagerSlotCatebyMentorServlet extends HttpServlet {

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
            out.println("<title>Servlet ManagerSlotCatebyMentorServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerSlotCatebyMentorServlet at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        String error = request.getParameter("error");
        int id;
        try {

            id = Integer.parseInt(id_raw);
            CVDAO cvd = new CVDAO();
            SlotDAO sld = new SlotDAO();
            List<Mentor> listMentor = cvd.getListofMentor();

            List<Slot> listActiveSlot = sld.getListofActiveSlotsByMentorId(id);
            List<Slot> listInactiveSlot = sld.getListofInactiveSlotsByMentorId(id);
            

            //hien thi avata gan nhat
            List<CV> listActiveCV = cvd.getListofActiveCV();

            List<User> listUser = cvd.getListofUser();
//        System.out.println(listMentor.get(0).getUsername());
            request.setAttribute("mentorList", listMentor);
            request.setAttribute("listActiveSlot", listActiveSlot);
            request.setAttribute("listInactiveSlot", listInactiveSlot);

            request.setAttribute("listUser", listUser);
            request.setAttribute("listActiveCV", listActiveCV);

            request.setAttribute("id", id);
            
            request.setAttribute("error", error);

            //slot view active
            List<String> dateConverted = new ArrayList<>();
            List<String> enddateConverted = new ArrayList<>();
            List<String> statusSlot = new ArrayList<>();
            List<Slot> mentorActivedSlot = sld.getListofActiveSlotsByMentorId(id);
            //System.out.println(mentorSlot.get(0).getDayInWeek());
            for (int i = 0; i < mentorActivedSlot.size(); i++) {
                String startDate = convertDayInWeekToCurrentDate(mentorActivedSlot.get(i).getDayInWeek()) + "T" + mentorActivedSlot.get(i).getStartTime();
                String endDate = convertDayInWeekToCurrentDate(mentorActivedSlot.get(i).getDayInWeek()) + "T" + mentorActivedSlot.get(i).getEndTime();
                statusSlot.add(mentorActivedSlot.get(i).getStatus());
//                System.out.println(startDate+", "+endDate);
                dateConverted.add(startDate);
                enddateConverted.add(endDate);
            }

            request.setAttribute("status", new Gson().toJson(statusSlot));
            request.setAttribute("startTime", new Gson().toJson(dateConverted));
           // System.out.println(dateConverted);
            request.setAttribute("endTime", new Gson().toJson(enddateConverted));

            //slot view Inactive
            dateConverted = new ArrayList<>();
            enddateConverted = new ArrayList<>();
            statusSlot = new ArrayList<>();
            mentorActivedSlot = sld.getListofInactiveSlotsByMentorId(id);
            //System.out.println(mentorSlot.get(0).getDayInWeek());
            for (int i = 0; i < mentorActivedSlot.size(); i++) {
                String startDate = convertDayInWeekToCurrentDate(mentorActivedSlot.get(i).getDayInWeek()) + "T" + mentorActivedSlot.get(i).getStartTime();
                String endDate = convertDayInWeekToCurrentDate(mentorActivedSlot.get(i).getDayInWeek()) + "T" + mentorActivedSlot.get(i).getEndTime();
                statusSlot.add(mentorActivedSlot.get(i).getStatus());
                //System.out.println(startDate+", "+mentorSlot.get(i).getEndTime());
                dateConverted.add(startDate);
                enddateConverted.add(endDate);
            }

            request.setAttribute("status2", new Gson().toJson(statusSlot));
            request.setAttribute("startTime2", new Gson().toJson(dateConverted));
            //System.out.println(dateConverted);
            request.setAttribute("endTime2", new Gson().toJson(enddateConverted));
            request.getRequestDispatcher("managerCV.jsp").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println(e);
        }
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
        processRequest(request, response);
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
