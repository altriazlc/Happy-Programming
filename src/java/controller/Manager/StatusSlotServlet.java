/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Manager;

import DAO.CVDAO;
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
@WebServlet(name = "StatusSlotServlet", urlPatterns = {"/slotstatus"})
public class StatusSlotServlet extends HttpServlet {

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
            out.println("<title>Servlet StatusSlotServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatusSlotServlet at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        String id_raw = request.getParameter("id");

        int id;
        try {
            SlotDAO sld = new SlotDAO();
            CVDAO cvd = new CVDAO();
            id = Integer.parseInt(id_raw);
            if (action.equalsIgnoreCase("active")) {
                System.out.println("alo"+sld.getListofUnActiveSlotsJoinRequestSlotByMentorId(id));
                if (sld.getListofUnActiveSlotsJoinRequestSlotByMentorId(id).isEmpty()){
                sld.deleteAllActiveSlot(id);
                sld.setSlotInactiveToActivebyMentorId(id);
                    
                }else{
                   response.sendRedirect("slotmanagercate?id=" + id +"&error=Mentor already have actived Request, cannot active new Slot"); 
                }
                //check slot of mentor aviable or not to set mentor status to active or inactive
                if (cvd.getCVbyMentorId(id)!=null) {
                    //System.out.println("active ne");
                    cvd.setStatusActiveMentor(id);
                }
                if (cvd.getCVbyMentorId(id)==null) {
                    //System.out.println("deactive rui");
                    cvd.setStatusInactiveMentor(id);
                }
            }
            if (action.equalsIgnoreCase("deactive")) {
                cvd.setStatusInactiveMentor(id);
                
                //check if any request that used that slot
                if (sld.getListofUnActiveSlotsJoinRequestSlotByMentorId(id).isEmpty()) {
                sld.deleteAllInactiveSlot(id);
                sld.setSlotActiveToInctivebyMentorId(id);
                }else{
                response.sendRedirect("slotmanagercate?id=" + id +"&error=Mentor already have actived Request, cannot deactive Slot");
                    
                }
            }

            response.sendRedirect("slotmanagercate?id=" + id);
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
