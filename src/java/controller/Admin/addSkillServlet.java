/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Admin;

import DAO.SkillDAO;
import Model.Skill;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.InputStream;

/**
 *
 * @author tuong
 */
@WebServlet(name = "addSkillServlet", urlPatterns = {"/addSkillAdmin"})
@MultipartConfig
public class addSkillServlet extends HttpServlet {

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
            out.println("<title>Servlet addSkillServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addSkillServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/Admin/addSkill.jsp").forward(request, response);
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
        String name = request.getParameter("name");
        if (name.trim().isEmpty() || name.isEmpty()) {
            request.setAttribute("error", "You must not leave this field empty!");
            request.getRequestDispatcher("/Admin/addSkill.jsp").forward(request, response);
            return;
        } else if (name.length() > 20) {
            request.setAttribute("error", "Please enter name no longger than 20 character!");
            request.getRequestDispatcher("/Admin/addSkill.jsp").forward(request, response);
            return;
        }

        boolean checkDup = checkDupSkill(name);
        if (checkDup == true) {
            request.setAttribute("error", "Skill name already exist!");
            request.getRequestDispatcher("/Admin/addSkill.jsp").forward(request, response);
            return;
        }
        //Skill image doing
        Part filePart = request.getPart("img");
        InputStream fileRead = filePart.getInputStream();
        byte[] image = fileRead.readAllBytes();
        String status = request.getParameter("status");
        String description = request.getParameter("description");
        Date date = new Date();
        Skill newSkill = new Skill(0, name, date, description, status, image);
        SkillDAO dao = new SkillDAO();
        boolean checkDao = dao.insertNewSkill(newSkill);
        if (checkDao == false) {
            response.sendRedirect("500.jsp");
            return;
        }
        request.setAttribute("successAdd", "Add new skill successfully");
        request.getRequestDispatcher("/Admin/addSkill.jsp").forward(request, response);
    }

    public boolean checkDupSkill(String skillName) {
        SkillDAO act = new SkillDAO();
        List<Skill> list = act.getListOfSkillByName(skillName);
        if (!list.isEmpty()) {
            return true;
        }
        return false;

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
