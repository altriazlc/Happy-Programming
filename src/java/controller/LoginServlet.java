/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/loginAdmin"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("loginAd.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String rememberMe = request.getParameter("rememberMe");
        String enpass = encrypt(pass);
        UserDAO u = new UserDAO();
        User a = u.findUserPass(username, enpass);
        try {

            if (a == null) {
                request.setAttribute("notify", "Wrong username or password");
                request.getRequestDispatcher("loginAd.jsp").forward(request, response);
            } else {
                if (a != null) {
                    if (a.getRoleId() != 3 && a.getRoleId() != 4) {
                        request.setAttribute("notify", "Only admin and manager can log in here "+a.getRoleId());
                        request.getRequestDispatcher("loginAd.jsp").forward(request, response);
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("acc", a);
                    if (rememberMe != null) {  // Checkbox was checked
                        Cookie usernameCookie = new Cookie("usernameAdmin", username);
                        Cookie passwordCookie = new Cookie("passAdmin", pass);

                        // Set cookie age to one week (7 days)
                        usernameCookie.setMaxAge(7 * 24 * 60 * 60);
                        passwordCookie.setMaxAge(7 * 24 * 60 * 60);

                        // Add cookies to the response
                        response.addCookie(usernameCookie);
                        response.addCookie(passwordCookie);
                    } else {
                        // Clear cookies if "Remember Me" is not checked
                        Cookie usernameCookie = new Cookie("usernameAdmin", null);
                        Cookie passwordCookie = new Cookie("passAdmin", null);

                        // Invalidate the cookies by setting the max age to 0
                        usernameCookie.setMaxAge(0);
                        passwordCookie.setMaxAge(0);

                        response.addCookie(usernameCookie);
                        response.addCookie(passwordCookie);
                    }
                    if (a.getRoleId() == 3) {
                        response.sendRedirect("admindashboard");
                    }
                    if (a.getRoleId() == 4) {
                        response.sendRedirect("cvmanager");
                    }
                }

            }
        } catch (Exception e) {
            request.setAttribute("notify", "Error occured ");
            request.getRequestDispatcher("loginAd.jsp").forward(request, response);
        }
    }

    private String encrypt(String pass) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(pass.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException ex) {
            digest = "";
        } catch (NoSuchAlgorithmException ex) {
            digest = "";
        }
        return digest;
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
