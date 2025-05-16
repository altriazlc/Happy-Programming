/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CV;

import DAO.CVDAO;
import DAO.SlotDAO;
import Model.CV;
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
@WebServlet(name = "CVDeleteServlet", urlPatterns = {"/cvdelete"})
public class CVDeleteServlet extends HttpServlet {

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
            out.println("<title>Servlet CVDeleteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CVDeleteServlet at " + request.getContextPath() + "</h1>");
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
        int CVid;
        try {
            CVDAO cvd = new CVDAO();
            SlotDAO sld = new SlotDAO();
            CVid = Integer.parseInt(id_raw);
            CV cv = cvd.getCVbyCVId(CVid);
            
            //check if mentor have current request or not, if not deactive all slots
            if (cv.getStatus().equalsIgnoreCase("active")) {
                System.out.println("active");
                System.out.println(cv.getMentorId());
                if (sld.getListofActiveSlotsJoinRequestSlotByMentorId(cv.getMentorId()).isEmpty()) {
                    //setSlot to Inactive after delete cv
                    if (cvd.deleteCV(CVid)) {
                        sld.deleteAllInactiveSlot(cv.getMentorId());
                        sld.setSlotActiveToInctivebyMentorId(cv.getMentorId());
                        System.out.println("empty");
                        response.sendRedirect("cvlist?id=" + cv.getMentorId() + "&error=Your active CV has been deleted successfully! your slot has been deactive!");
                    } else {
                        response.sendRedirect("cvlist?id=" + cv.getMentorId() + "&error=Unable to delete your CV. Please try again.");
                    }
                }
                response.sendRedirect("cvlist?id=" + cv.getMentorId() + "&error=Unable to delete your CV, have current request!");
                return;
            }
            if (cvd.deleteCV(CVid)) {
                cvd.setStatusInactiveMentor(cv.getMentorId());
                response.sendRedirect("cvlist?id=" + cv.getMentorId() + "&mess=Your CV has been deleted successfully!");
            } else {
                response.sendRedirect("cvlist?id=" + cv.getMentorId() + "&error=Unable to delete your CV. Please try again.");
            }
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
