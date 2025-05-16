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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import util.Email;

/**
 *
 * @author asus
 */
public class PasswordResetSV extends HttpServlet {

    // Generate a random temporary password
    private String generateTemporaryPassword() {
        SecureRandom random = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder temporaryPassword = new StringBuilder(8); // Length of 8 characters
        for (int i = 0; i < 8; i++) {
            temporaryPassword.append(characters.charAt(random.nextInt(characters.length())));
        }
        return temporaryPassword.toString();
    }

    private String encrypt(String password) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("requestreset.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String account = request.getParameter("account");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmailAndAccount(email, account);

        if (user != null) {
            // Step 1: Generate temporary password
            String temporaryPassword = generateTemporaryPassword();

            // Step 2: Encrypt the temporary password
            String encryptedPassword = encrypt(temporaryPassword);

            // Step 3: Save encrypted password in PasswordReset table
            userDAO.createNewPass(user.getUsername(), encryptedPassword);

            // Step 4: Send reset password email
            String emailContent = "Hi " + user.getUsername() + ",<br>"
                    + "You have requested a password reset. Use the following temporary password to log in: <b>" + temporaryPassword + "</b>.<br>";

            boolean emailSent = Email.sendEmail(user.getEmail(), "Password Reset Request", emailContent);

            // Step 4: Notify user whether email was sent successfully
            if (emailSent) {
                request.setAttribute("message", "Password reset link has been sent to your email.");
            } else {
                request.setAttribute("error", "There was an error sending the reset email. Please try again.");
            }

        } else {
            // No user found with the given email and account
            request.setAttribute("error", "No account found with the provided email and username.");
        }

        // Forward back to the password reset request page
        request.getRequestDispatcher("requestreset.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
