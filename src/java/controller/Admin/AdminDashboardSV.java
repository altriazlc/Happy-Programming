/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Admin;

import DAO.MenteeDAO;
import DAO.MentorDAO;
import DAO.UserDAO;
import Model.Mentee;
import Model.Mentor;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import com.google.gson.Gson;

/**
 *
 * @author asus
 */
@WebServlet(name = "AdminDashboardSV", urlPatterns = {"/admindashboard"})
public class AdminDashboardSV extends HttpServlet {

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
            out.println("<title>Servlet AdminDashboardSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminDashboardSV at " + request.getContextPath() + "</h1>");
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
        MenteeDAO menteeDAO = new MenteeDAO();
        MentorDAO mentorDAO = new MentorDAO();
        UserDAO userDAO = new UserDAO();

        List<User> listUsers = userDAO.gettAllUsers();
        List<Mentor> listMentors = mentorDAO.getAllMentor();
        List<Mentee> listMentees = menteeDAO.getAllMentee();
        List<Object[]> userCreationStats = userDAO.getUserCreationStats();
        int totalMentees = menteeDAO.getTotalMentees();
        int totalMentors = mentorDAO.getTotalMentors();

        // Check if 'mentorid' and 'mentorstatus' parameters are provided
        String mentorIdParam = request.getParameter("mentorid");
        String newMentorStatus = request.getParameter("mentorstatus");
        if (mentorIdParam != null && newMentorStatus != null) {
            try {
                int mentorId = Integer.parseInt(mentorIdParam);
                mentorDAO.updateStatus(mentorId, newMentorStatus);
            } catch (NumberFormatException e) {
                System.err.println("Invalid mentor ID: " + mentorIdParam);
            }
        }

        // Check if 'menteeid' and 'menteestatus' parameters are provided
        String menteeIdParam = request.getParameter("menteeid");
        String newMenteeStatus = request.getParameter("menteestatus");
        if (menteeIdParam != null && newMenteeStatus != null) {
            try {
                int menteeId = Integer.parseInt(menteeIdParam);
                menteeDAO.updateStatus(menteeId, newMenteeStatus);
            } catch (NumberFormatException e) {
                System.err.println("Invalid mentee ID: " + menteeIdParam);
            }
        }

        Gson gson = new Gson();
        String userCreationStatsJson = gson.toJson(userCreationStats);
        request.setAttribute("userCreationStatsJson", userCreationStatsJson);

        request.setAttribute("totalMentors", totalMentors);
        request.setAttribute("totalMentees", totalMentees);
        request.setAttribute("mentorlist", listMentors);
        request.setAttribute("menteelist", listMentees);
        request.setAttribute("listuser", listUsers);

        request.getRequestDispatcher("/Admin/adminDashboard.jsp").forward(request, response);
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
