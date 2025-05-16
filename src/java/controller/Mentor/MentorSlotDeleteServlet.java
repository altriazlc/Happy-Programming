/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Mentor;

import DAO.SlotDAO;
import Model.Slot;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "MentorSlotDeleteServlet", urlPatterns = {"/deleteslot"})
public class MentorSlotDeleteServlet extends HttpServlet {

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
            out.println("<title>Servlet MentorSlotDeleteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MentorSlotDeleteServlet at " + request.getContextPath() + "</h1>");
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
        String slotId_raw = request.getParameter("requestid");
        System.out.println("Slot id: "+slotId_raw);
//        String all = request.getParameter("all");
//        if (all!=null && all.equalsIgnoreCase("all")) {
//            String mentorid = request.getParameter("mentorid");
//            System.out.println(mentorid);
//            SlotDAO sld = new SlotDAO();
//            if (sld.deleteAllInactiveSlot()) {
//                response.sendRedirect("slotdraft?id=" + mentorid +"&mess=Your all slot has been deleted successfully!");
//            }else{
//                response.sendRedirect("slotdraft?id=" + mentorid +"&error=Your slot has been deleted all unsuccessfully!");
//            }
//            return;
//        }
        int slotId;
        try {
            SlotDAO sld = new SlotDAO();
            slotId = Integer.parseInt(slotId_raw);
            Slot slot = sld.getSlotsById2(slotId);
            if (slot.getStatus().equalsIgnoreCase("available")) {
                List<Slot> slotActive = sld.getListofActiveSlotsByMentorId(slot.getMentorID());
                for (Slot slot1 : slotActive) {
                    //re-add another slot except slot wantto delet
                    if (slot1.getSlotID() != slot.getSlotID()) {
                        slot1.setStatus("inactive");
                        sld.addSlot(slot1);
                    }
                }
            }
            if (slot.getStatus().equalsIgnoreCase("inactive")) {
                sld.deleteSlot(slotId);
            }
            //System.out.println(slotId);
            response.sendRedirect("slotdraft?id=" + slot.getMentorID() +"&mess=Your slot has been deleted all successfully!");
        } catch (NumberFormatException e) {
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
