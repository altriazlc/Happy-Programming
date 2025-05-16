/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Mentor;

import DAO.CVDAO;
import Model.Mentee;
import Model.Mentor;
import Model.Request;
import Model.User;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "MentorDashboardServlet", urlPatterns = {"/mentorStatisticRequest"})
public class MentorStatisticRequestServlet extends HttpServlet {

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
            out.println("<title>Servlet MentorDashboardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MentorDashboardServlet at " + request.getContextPath() + "</h1>");
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
        String mentorid_raw = request.getParameter("id");
        int mentorid;
        try {
            mentorid = Integer.parseInt(mentorid_raw);
            CVDAO cvd = new CVDAO();
            int rateAve = cvd.getAveRatebyId(mentorid);
            int invitedRequest = cvd.countInvitedRequestbyMentorId(mentorid);
            int compeletedRequest = cvd.countCompletedRequestbyMentorId(mentorid);
            int acceptedRequest = cvd.countAllCompletedRequestbyMentorId(mentorid);
            
            int canceledRequest = cvd.countCanceledRequestbyMentorId(mentorid);
            int countRequest = cvd.countRequestbyMentorId(mentorid);
            List<Request> requestList = cvd.getListofRequestbyMentorId(mentorid);
            List<Mentee> menteeList = cvd.getListofMentee();
            List<User> listUser = cvd.getListofUser();
            request.setAttribute("rateAve", rateAve);
            request.setAttribute("invitedRequest", invitedRequest);
            request.setAttribute("completedRequest", compeletedRequest);
            request.setAttribute("canceledRequest", canceledRequest);
            request.setAttribute("countRequest", countRequest);
            request.setAttribute("requestList", requestList);
            request.setAttribute("menteeList", menteeList);
            request.setAttribute("listUser", listUser);
            
            request.setAttribute("acceptedRequest", acceptedRequest);
            
            Map<String, Integer> monthlyRatings = cvd.getRatingMapbyMentorId(mentorid);
            //System.out.println(monthlyRatings.keySet() + ", " + monthlyRatings.values());
            List<String> keys = new ArrayList<>(monthlyRatings.keySet());
            List<Integer> values = new ArrayList<>(monthlyRatings.values());
            
            request.setAttribute("key", new Gson().toJson(keys));
            request.setAttribute("values", new Gson().toJson(values));

            request.getRequestDispatcher("mentorStatisticRequest.jsp").forward(request, response);
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
