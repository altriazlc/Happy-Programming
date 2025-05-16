/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Mentor;

import DAO.CVDAO;
import DAO.HomeDAO;
import DAO.SlotDAO;
import Model.CV;
import Model.Mentee;
import Model.Mentor;
import Model.Rate;
import Model.Slot;
import Model.StatisticSkills;
import com.google.gson.Gson;
import static controller.Mentee.SlotViewServlet.convertDayInWeekToCurrentDate;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "MentorSlotCreateServlet", urlPatterns = {"/createslot"})
public class MentorSlotCreateServlet extends HttpServlet {

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
            out.println("<title>Servlet MentorSlotCreateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MentorSlotCreateServlet at " + request.getContextPath() + "</h1>");
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
        String mentorId_raw = request.getParameter("id");
        String error = request.getParameter("error");
        String mess = request.getParameter("mess");
        int mentorId;
        CVDAO cvd = new CVDAO();
        SlotDAO sld = new SlotDAO();
        
        try {
            mentorId = Integer.parseInt(mentorId_raw);
            
            Mentor mentor = cvd.getMentorByID(mentorId);
            List<Slot> activeSlotList = sld.getListofActiveSlotsByMentorId(mentorId);

            //request.setAttribute("skillMentor", mentorSkillList);
            request.setAttribute("error", error);
            request.setAttribute("mess", mess);
            request.setAttribute("uFound", mentor);
            request.setAttribute("slotList", activeSlotList);

            //slot view
            List<String> dateConverted = new ArrayList<>();
            List<String> enddateConverted = new ArrayList<>();
            List<String> statusSlot = new ArrayList<>();
            List<Slot> mentorActivedSlot = sld.getListofActiveSlotsByMentorId(mentorId);
            //System.out.println(mentorSlot.get(0).getDayInWeek());
            for (int i = 0; i < mentorActivedSlot.size(); i++) {
                String startDate = convertDayInWeekToCurrentDate(mentorActivedSlot.get(i).getDayInWeek()) + "T" + mentorActivedSlot.get(i).getStartTime();
                String endDate = convertDayInWeekToCurrentDate(mentorActivedSlot.get(i).getDayInWeek()) + "T" + mentorActivedSlot.get(i).getEndTime();
                statusSlot.add(mentorActivedSlot.get(i).getStatus());
                //System.out.println(startDate+", "+mentorSlot.get(i).getEndTime());
                dateConverted.add(startDate);
                enddateConverted.add(endDate);
            }

            request.setAttribute("status", new Gson().toJson(statusSlot));
            request.setAttribute("values", new Gson().toJson(dateConverted));
            request.setAttribute("endValues", new Gson().toJson(enddateConverted));

            request.getRequestDispatcher("slotCreate.jsp").forward(request, response);
        } catch (Exception e) {
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
        String dayinweek = request.getParameter("dayinweek");
        String slottime = request.getParameter("slotTime");
        String mentorId_raw = request.getParameter("mentorId");
        
        
        System.out.println(slottime);
        System.out.println(dayinweek);

        int mentorId;
        String start = "";
        String end = "";
        switch (slottime) {
            case "1" -> {
                start = "07:00:00";
                end = "08:50:00";
            }
            case "2" -> {
                start = "09:00:00";
                end = "10:50:00";
            }
            case "3" -> {
                start = "11:00:00";
                end = "12:50:00";
            }
            case "4" -> {
                start = "13:00:00";
                end = "14:50:00";
            }
            case "5" -> {
                start = "15:00:00";
                end = "16:50:00";
            }
            case "6" -> {
                start = "17:00:00";
                end = "18:50:00";
            }
            default -> {
                start = "00:00:00";
                end = "00:00:00";
                throw new IllegalArgumentException("Out of slot range!");
            }
        }
        try {
            mentorId = Integer.parseInt(mentorId_raw);
            CVDAO cvd = new CVDAO();
            SlotDAO sld = new SlotDAO();
            CV cv = cvd.getNewestCVbyMentorId(mentorId);
            //check if mentor have no actived cv
            CV activeCv = cvd.getCVActivebyMentorId(mentorId);
            if (activeCv==null) {
                response.sendRedirect("slotdraft?id=" + mentorId_raw + "&error=Unable to create the slot because your CV has not been activated. Please wait for the manager to approve your CV!");
                return;
            }
            List<Slot> listDraftSlot = sld.getListofInactiveSlotsByMentorId(mentorId);
            Slot slot = new Slot();
            slot.setMentorID(mentorId);
            slot.setStartTime(LocalTime.parse(start));
            slot.setEndTime(LocalTime.parse(end));
            slot.setDayInWeek(dayinweek);
            slot.setStatus("inactive");
            slot.setCVID(cv.getCvId());
            for (Slot slot1 : listDraftSlot) {
                if (slot1.getStartTime().equals(slot.getStartTime()) && slot1.getDayInWeek().equalsIgnoreCase(slot.getDayInWeek()))  {
                    //System.out.println("giong nhau roii");
                    response.sendRedirect("slotdraft?id=" + mentorId_raw + "&error=Unable to create the slot because a duplicate start time was found. Please choose a different time!");
                    return;
                }
                
            }
            if (cvd.addSlot(slot)) {
                response.sendRedirect("slotdraft?id=" + mentorId_raw + "&mess=Your slot has been created successfully!");
            } else {
                response.sendRedirect("slotdraft?id=" + mentorId_raw + "&error=Unable to create your slot in the Slot draft. Please try again.");
            }

        } catch (NumberFormatException e) {
            System.out.println(e);
        }
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
