/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.MenteeDAO;
import DAO.MentorDAO;
import DAO.UserDAO;
import DAO.WalletDAO;
import Model.Mentee;
import Model.Mentor;
import util.Email;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author nhhag
 */
@WebServlet(name = "SignUpSV", urlPatterns = {"/signup"})
public class SignUpSV extends HttpServlet {

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
            out.println("<title>Servlet SignUpSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpSV at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("Signup.jsp").forward(request, response);

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
        UserDAO userDAO = new UserDAO();
        MentorDAO mentorDAO = new MentorDAO();
        MenteeDAO menteeDAO = new MenteeDAO();
        WalletDAO walletDAO = new WalletDAO();

        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        String mail = request.getParameter("email");
        String fname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String dob_raw = request.getParameter("dob");
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        String role_raw = request.getParameter("role");

        request.setAttribute("username", username);
        request.setAttribute("email", mail);
        request.setAttribute("fullname", fname);
        request.setAttribute("phone", phone);
        request.setAttribute("dob", dob_raw);
        request.setAttribute("address", address);

        try {

            LocalDate localDob = LocalDate.parse(dob_raw);
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(localDob, currentDate).getYears();

            java.sql.Date dob = java.sql.Date.valueOf(localDob);

            int role = Integer.parseInt(role_raw);

            boolean errorUser = true;

            if (userDAO.findUserByUsername(username) != null) {
                request.setAttribute("uerror", "User already exists.");
                errorUser = false;
            }

            if (userDAO.findUserByEmail(mail) != null) {
                request.setAttribute("eerror", "Email already exists.");
                errorUser = false;
            }
            if (errorUser == false) {
                request.getRequestDispatcher("Signup.jsp").forward(request, response);
                return;
            }

            boolean error = true;
            if (!pass.equals(repass)) {
                request.setAttribute("perror1", "Passwords do not match.");
                error = false;
            }

            if (!checkValidPass(pass)) {
                request.setAttribute("perror1", "Password does not meet requirements.");
                error = false;
            }

            if (phone.length() < 10 || !phone.matches("\\d+")) {
                request.setAttribute("pherror", "Phone number must contain only digits and have at least 10 digits.");
                error = false;
            }

            if ((role == 1 && (age < 20 || age > 65)) || (role == 2 && (age < 10 || age > 65))) {
                request.setAttribute("aerror", role == 1
                        ? "Your age must be between 20 and 65 years old for mentors."
                        : "Your age must be between 10 and 65 years old for mentees.");
                error = false;

            }

            User newUser = new User();
            newUser.setUsername(username);
            String enpass = encrypt(pass);
            newUser.setPassword(enpass);
            newUser.setEmail(mail);
            newUser.setRoleId(role);
            newUser.setCreateDate(new Date());
            newUser.setStatus("inactive");
            userDAO.insertUser(newUser);

            if (role == 1) {
                mentorDAO.insertMentor(role, username, dob, phone, address, dob, fname, sex, "inactive");
                walletDAO.addNewWallet(username);
            } else {
                menteeDAO.insertMentee(role, null, username, dob, mail, phone, address, dob, fname, sex, "inactive");
                walletDAO.addNewWallet(username);
            }

            if (error == false) {
                request.getRequestDispatcher("Signup.jsp").forward(request, response);
                return;
            }

            if (error == true && errorUser == true) {
                String subject = "Confirm Your Signup";
                String content = "<html>"
                        + "<body style='font-family: Arial, sans-serif;'>"
                        + "<div style='max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px;'>"
                        + "<h2 style='color: #007bff; text-align: center;'>Welcome to Happy Programming!</h2>"
                        + "<p>Dear " + fname + ",</p>"
                        + "<p>Thank you for signing up with us! We're thrilled to have you on board. Please review your information below and click the button to confirm your email address.</p>"
                        + "<div style='background-color: #f9f9f9; padding: 15px; border-radius: 8px;'>"
                        + "<p><strong>Full Name:</strong> " + fname + "</p>"
                        + "<p><strong>Email:</strong> " + mail + "</p>"
                        + "<p><strong>Phone:</strong> " + phone + "</p>"
                        + "<p><strong>Date of Birth:</strong> " + dob_raw + "</p>"
                        + "<p><strong>Gender:</strong> " + sex + "</p>"
                        + "<p><strong>Address:</strong> " + address + "</p>"
                        + "</div>"
                        + "<p style='margin-top: 20px;'>Please click the button below to confirm your email address:</p>"
                        + "<a href='http://khanhnnhe181337.id.vn/happy_programming/confirm?email=" + mail + "'"
                        + " style='display: inline-block; background-color: #28a745; color: #fff; text-decoration: none; padding: 12px 24px; border-radius: 5px;'>Confirm Email</a>"
                        + "<p style='margin-top: 20px;'>If you did not sign up for this account, please disregard this email.</p>"
                        + "<hr style='border-top: 1px solid #ddd;' />"
                        + "<p style='text-align: center; font-size: 12px; color: #777;'>© 2024 Happy Programming. All rights reserved.</p>"
                        + "<p style='text-align: center; font-size: 12px;'>"
                        + "<a href='#' style='text-decoration: none; color: #007bff;'>Unsubscribe</a> | "
                        + "<a href='#' style='text-decoration: none; color: #007bff;'>Contact Us</a>"
                        + "</p>"
                        + "</div>"
                        + "</body>"
                        + "</html>";

                Email.sendEmail(mail, subject, content);

                request.getRequestDispatcher("success.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("notify", e);
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
        }

    }

    private boolean checkValidPass(String pass) {
        boolean hasUpperCase = false;
        boolean hasNumber = false;
        if (pass.length() < 6 || pass.length() > 18) {
            return false;
        }
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isDigit(pass.charAt(i))) {
                hasNumber = true;
            }
            if (Character.isUpperCase(pass.charAt(i))) {
                hasUpperCase = true;
            }
        }
        if (hasNumber == true && hasUpperCase == true) {
            return true;
        }

        return false;
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
