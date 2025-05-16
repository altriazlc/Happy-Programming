/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CVDAO;
import DAO.MenteeDAO;
import DAO.UserDAO;
import DAO.WalletDAO;
import Model.CV;
import Model.Mentee;
import Model.Mentor;
import Model.User;
import Model.Wallet;
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
 * @author nhhag
 */
@WebServlet(name = "SignInSV", urlPatterns = {"/signin"})
public class SignInSV extends HttpServlet {

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
            out.println("<title>Servlet SignInSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignInSV at " + request.getContextPath() + "</h1>");
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
        String username = null;
        String password = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                } else if ("pass".equals(cookie.getName())) {
                    password = cookie.getValue();
                }
            }
        }

        // Set username and password as request attributes
        request.setAttribute("username", username);
        request.setAttribute("password", password);

        // Forward to the JSP page
        request.getRequestDispatcher("SignIn.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        UserDAO u = new UserDAO();
        User a = u.findUserPass(username, enpass);
        try {

            if (a == null) {
                request.setAttribute("notify", "Wrong username or password");
                request.getRequestDispatcher("SignIn.jsp").forward(request, response);
            } else {
                if (a != null && a.getStatus().equalsIgnoreCase("active")) {

                    if (a.getRoleId() == 3 || a.getRoleId() == 4) {
                        request.setAttribute("notify", "Admin and manager can't login here");
                        request.getRequestDispatcher("SignIn.jsp").forward(request, response);
                    }
                    if (a.getRoleId() == 1) {
                        CVDAO cvd = new CVDAO();
                        WalletDAO wld = new WalletDAO();
                        session.setAttribute("acc", a);
                        Mentor mentor = cvd.getMentorByUsername(a.getUsername());
                        if (mentor.getStatus().equalsIgnoreCase("active"));
                        {
                            CV cvactive = cvd.getCVbyMentorId(mentor.getMentorId());
                            session.setAttribute("cvactive", cvactive);
                        }
                        Wallet wallet = wld.getWalletByUsername(a.getUsername());
                        session.setAttribute("wallet", wallet);
                        session.setAttribute("mentor", mentor);

                    }//mentee avatar process

                    if (a.getRoleId() == 2) {
                        MenteeDAO mtd = new MenteeDAO();
                        WalletDAO wld = new WalletDAO();
                        session.setAttribute("acc", a);
                        Mentee mentee = mtd.findMenteeByUsername(a.getUsername());
                        Wallet wallet = wld.getWalletByUsername(a.getUsername());

                        session.setAttribute("wallet", wallet);
                        session.setAttribute("mentee", mentee);
                    }
                    if (rememberMe != null) {  // Checkbox was checked
                        Cookie usernameCookie = new Cookie("username", username);
                        Cookie passwordCookie = new Cookie("pass", pass);

                        // Set cookie age to one week (7 days)
                        usernameCookie.setMaxAge(7 * 24 * 60 * 60);
                        passwordCookie.setMaxAge(7 * 24 * 60 * 60);

                        // Add cookies to the response
                        response.addCookie(usernameCookie);
                        response.addCookie(passwordCookie);
                    } else {
                        // Clear cookies if "Remember Me" is not checked
                        Cookie usernameCookie = new Cookie("username", null);
                        Cookie passwordCookie = new Cookie("pass", null);

                        // Invalidate the cookies by setting the max age to 0
                        usernameCookie.setMaxAge(0);
                        passwordCookie.setMaxAge(0);

                        response.addCookie(usernameCookie);
                        response.addCookie(passwordCookie);
                    }
                    response.sendRedirect("home");
                } else if (a != null && a.getStatus().equalsIgnoreCase("inactive")) {
                    request.setAttribute("notify", "Your account is not active, please active by link in your email");
                    request.getRequestDispatcher("SignIn.jsp").forward(request, response);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("notify", "Error occured ");
            request.getRequestDispatcher("SignIn.jsp").forward(request, response);
        }
    }

    public static String encrypt(String pass) {
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
