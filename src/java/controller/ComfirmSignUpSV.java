/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.MenteeDAO;
import DAO.MentorDAO;
import DAO.UserDAO;
import Model.Mentor;
import Model.Mentee;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author nhhag
 */
@WebServlet(name = "ComfirmSignUpSV", urlPatterns = {"/confirm"})
public class ComfirmSignUpSV extends HttpServlet {

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
            out.println("<title>Servlet ComfirmSignUpSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComfirmSignUpSV at " + request.getContextPath() + "</h1>");
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
        String email = request.getParameter("email");
        try {
            if (email != null && !email.isEmpty()) {
                UserDAO userDAO = new UserDAO();
                MentorDAO mentorDAO = new MentorDAO();
                MenteeDAO menteeDAO = new MenteeDAO();
                User user = userDAO.findUserByEmail(email);
                if (user != null && "inactive".equals(user.getStatus())) {
                    user.setStatus("active");
                    userDAO.updateUser(user); 
                    request.setAttribute("message", "Your email has been confirmed. You can now log in.");
                } else {
                    request.setAttribute("message", "Invalid confirmation link.");
                }
            } else {
                request.setAttribute("message", "Invalid request.");
            }
            request.getRequestDispatcher("SignIn.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", e);
            request.getRequestDispatcher("SignIn.jsp").forward(request, response);
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
