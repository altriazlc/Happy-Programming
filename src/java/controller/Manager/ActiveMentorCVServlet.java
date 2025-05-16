/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Manager;

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
@WebServlet(name = "ActiveMentorCVServlet", urlPatterns = {"/activecv"})
public class ActiveMentorCVServlet extends HttpServlet {

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
            out.println("<title>Servlet ActiveMentorCVServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActiveMentorCVServlet at " + request.getContextPath() + "</h1>");
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
        int cvid;
        try {
            cvid = Integer.parseInt(id_raw);
            CVDAO cvd = new CVDAO();
            CV cv = cvd.getCVbyCVId(cvid);
            int mentorId = cv.getMentorId();
            List<CV> listCV = cvd.getListofCVbyMentorId(mentorId);
            boolean haveCVActive=false;
            for (CV cv1 : listCV) {
                if (cv1.getStatus().equalsIgnoreCase("active")) {
                    haveCVActive = true;
                }
            }
//            System.out.println(haveCVActive);
            if (cv.getStatus().equalsIgnoreCase("inactive") && haveCVActive) {
                //error
                response.sendRedirect("cvmanagercate?id=" + mentorId + "&error=Mentor already have actived CV");
            } else {
                if (cv.getStatus().equalsIgnoreCase("inactive")) {
                    cvd.setStatusActiveCvId(cvid);
                    SlotDAO sld = new SlotDAO();
                    //check slot of mentor aviable or not to set mentor status to active or inactive
                    if (!sld.getListofActiveSlotsByMentorId(cv.getMentorId()).isEmpty()) {
                        //System.out.println("active ne");
                        cvd.setStatusActiveMentor(cv.getMentorId());
                    }
                    if (sld.getListofActiveSlotsByMentorId(cv.getMentorId()).isEmpty()) {
                        //System.out.println("deactive rui");
                        cvd.setStatusInactiveMentor(cv.getMentorId());
                    }
                    response.sendRedirect("cvmanagercate?id=" + mentorId);
                }
                //when manager wanna deactive CV
                if (cv.getStatus().equalsIgnoreCase("active")) {
                    SlotDAO sld = new SlotDAO();
                //check mentor have any current request if not, deactive slot
                    if (sld.getListofUnActiveSlotsJoinRequestSlotByMentorId(cv.getMentorId()).isEmpty()) {
                        cvd.setStatusInactiveCvId(cvid);
                        cvd.setStatusInactiveMentor(cv.getMentorId());
                        System.out.println("emty ne");
                        response.sendRedirect("cvmanagercate?id=" + mentorId);
                    } else {
                        response.sendRedirect("cvmanagercate?id=" + mentorId + "&error=Mentor already have actived Request, cannot deactive CV");
                    }

                }
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
