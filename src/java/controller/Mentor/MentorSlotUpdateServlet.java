/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Mentor;

import DAO.CVDAO;
import DAO.SlotDAO;
import Model.CV;
import Model.Slot;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="MentorSlotUpdateServlet", urlPatterns={"/updateslot"})
public class MentorSlotUpdateServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet MentorSlotUpdateServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MentorSlotUpdateServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String dayinweek = request.getParameter("dayinweek");
        String slottime = request.getParameter("slotTime");
        String mentorId_raw = request.getParameter("mentorId");
        String slotId_raw = request.getParameter("slotId");
        

        System.out.println(slottime);
        System.out.println(dayinweek);
        System.out.println(slotId_raw);
        
        int mentorId,slotId;
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
            slotId = Integer.parseInt(slotId_raw);
            CVDAO cvd = new CVDAO();
            SlotDAO sld = new SlotDAO();
            Slot getSlot = sld.getSlotsById2(slotId);
            CV cv = cvd.getNewestCVbyMentorId(mentorId);
            Slot slot = new Slot();
            slot.setSlotID(slotId);
            slot.setMentorID(mentorId);
            slot.setStartTime(LocalTime.parse(start));
            slot.setEndTime(LocalTime.parse(end));
            slot.setDayInWeek(dayinweek);
            slot.setStatus("inactive");
            slot.setCVID(cv.getCvId());
            
            if (getSlot.getStatus().equalsIgnoreCase("available")) {
                System.out.println("active");
                List<Slot> slotActive = sld.getListofActiveSlotsByMentorId(getSlot.getMentorID());
                for (Slot slot1 : slotActive) {
                    //re-add another slot except slot wantto delet
                    if (slot1.getSlotID() != getSlot.getSlotID()) {
                        slot1.setStatus("inactive");
                        sld.addSlot(slot1);
                    }
                    //if found that slot active, do add
                    if (slot1.getSlotID() == getSlot.getSlotID()) {
                        sld.addSlot(slot);
                    }
                }
                response.sendRedirect("slotdraft?id=" + mentorId_raw + "&mess=Your slot has been updated successfully in the Slot draft!");
            }
            if (getSlot.getStatus().equalsIgnoreCase("inactive")) {
                System.out.println("inactive");
                if (sld.updateSlot(slot)) {
                    response.sendRedirect("slotdraft?id=" + mentorId_raw + "&mess=Your slot has been updated successfully in the Slot draft!");
                }else{
                    response.sendRedirect("slotdraft?id=" + mentorId_raw + "&mess=Unable to create your slot in the Slot draft. Please try again.");
                }
                
                
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
            response.sendRedirect("slotdraft?id=" + mentorId_raw + "&mess=Unable to create your slot in the Slot draft. Please try again.");
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
