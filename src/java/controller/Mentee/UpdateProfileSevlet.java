/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Mentee;

import DAO.MenteeDAO;
import DAO.MentorDAO;
import Model.Mentee;
import Model.Mentor;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
@WebServlet(name = "UpdateProfileSevlet", urlPatterns = {"/updateProfile"})
@MultipartConfig
public class UpdateProfileSevlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateMenteeProfileSevlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateMenteeProfileSevlet at " + request.getContextPath() + "</h1>");
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
        try {
            
        
        HttpSession session = request.getSession();
        User curUser = (User) session.getAttribute("acc");
        MenteeDAO actMentee = new MenteeDAO();
        MentorDAO actMentor = new MentorDAO();
        if (curUser == null) {
            response.sendRedirect("signin");
            return;
        }
        PrintWriter out = response.getWriter();
        int roleID = curUser.getRoleId();
        Mentee curMentee = new Mentee();
        if (roleID == 2) {
            curMentee = actMentee.findMenteeByUsername(curUser.getUsername());
            request.setAttribute("mentee", curMentee);
        } else if (roleID == 1) {
            Mentor curMentor = actMentor.findMentorByUsername(curUser.getUsername());
            request.getRequestDispatcher("updateCV?id=" + curMentor.getMentorId()).forward(request, response);
            return;
        }

        request.getRequestDispatcher("updateProfileMentee.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        User curUser = (User) session.getAttribute("acc");
        MenteeDAO actMentee = new MenteeDAO();
        MentorDAO actMentor = new MentorDAO();
        if (curUser == null) {
            response.sendRedirect("signin");
            return;
        }
        PrintWriter out = response.getWriter();
        int roleID = curUser.getRoleId();
        Mentee curMentee = new Mentee();
        if (roleID == 2) {
            curMentee = actMentee.findMenteeByUsername(curUser.getUsername());

        } else if (roleID == 1) {
            Mentor curMentor = actMentor.findMentorByUsername(curUser.getUsername());
            request.getRequestDispatcher("updateCV?id=" + curMentor.getMentorId()).forward(request, response);
            return;
        }
        Part filePart = request.getPart("avatar");
        if (filePart != null && filePart.getSize() > 0) {
            InputStream fileRead = filePart.getInputStream();
            byte[] image = fileRead.readAllBytes();
            curMentee.setAvatar(image);
        }
        curMentee.setFullName(request.getParameter("fullname"));
        curMentee.setUsername(request.getParameter("username"));
        String dob_raw = request.getParameter("dob");
        Date dob;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dob = dateFormat.parse(dob_raw);
            curMentee.setDateOfBirth(dob);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        //delete email
        curMentee.setPhone(request.getParameter("phone"));
        curMentee.setGender(request.getParameter("gender"));
        curMentee.setAddress(request.getParameter("address"));
        try {
            if (actMentee.updateMentee(curMentee)) {
                request.setAttribute("message", "Update Sucessfully!");
                curMentee = actMentee.findMenteeByUsername(curMentee.getUsername());
                request.setAttribute("mentee", curMentee);
                request.getRequestDispatcher("updateProfileMentee.jsp").forward(request, response);
            } else {
                request.setAttribute("Error", "Update Error!");
                curMentee = actMentee.findMenteeByUsername(curMentee.getUsername());
                request.setAttribute("mentee", curMentee);
                request.getRequestDispatcher("updateProfileMentee.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
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
