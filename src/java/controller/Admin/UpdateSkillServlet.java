package controller.Admin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tuong
 */
@WebServlet(urlPatterns = {"/updateSkillAdmin"})
@MultipartConfig
public class UpdateSkillServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateSkillServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateSkillServlet at " + request.getContextPath() + "</h1>");
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
        SkillDAO act = new SkillDAO();
        String id_raw = request.getParameter("updateId");
        int id = Integer.parseInt(id_raw);
        Skill curSkill = act.getSkillById(id);
        String error = request.getParameter("error");
        if (error != null && !error.isEmpty()) {
            request.setAttribute("error", error);
        }
        request.setAttribute("id", id);
        request.setAttribute("name", curSkill.getSkillName());
        request.setAttribute("img", curSkill.getBase64ImageFile());
        request.setAttribute("createDate", curSkill.getCreateDate());
        request.setAttribute("status", curSkill.getStatus());
        request.setAttribute("description", curSkill.getDescription());
        request.getRequestDispatcher("/Admin/updateSkillAdmin.jsp").forward(request, response);
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
        SkillDAO act = new SkillDAO();
        String id_raw = request.getParameter("id");
        int id = Integer.parseInt(id_raw);
        Skill curSkill = act.getSkillById(id);
        String name = request.getParameter("name");
        if (name.trim().isEmpty() || name.isEmpty()) {
            String error = "You must not leave this field empty!";
            response.sendRedirect("updateSkillAdmin?updateId=" + id + "&error=" + error);
            return;
        } else if (name.length() > 20) {
            String error = "Please enter name no longger than 20 character!";
            response.sendRedirect("updateSkillAdmin?updateId=" + id + "&error=" + error);
            return;
        }

        boolean checkDup = checkDupSkill(name, id);
        if (checkDup == true) {
            String error = "Skill name already exist!";
            request.setAttribute("error", "Skill name already exist!");
            response.sendRedirect("updateSkillAdmin?updateId=" + id + "&error=" + error);
            return;
        }
        //Get image file
        Part filePart = request.getPart("img");
        if (filePart != null && filePart.getSize() > 0) {
            InputStream fileRead = filePart.getInputStream();
            byte[] image = fileRead.readAllBytes();
            curSkill.setImg(image);
        }

        String status = request.getParameter("status");
        curSkill.setStatus(status);
        String description = request.getParameter("description");
        curSkill.setDescription(description);
        curSkill.setSkillName(name);

        Date date = new Date();
        curSkill.setCreateDate(date);
        
        boolean checkUpdate = act.updateSkillInfo(curSkill);
        if (checkUpdate == false) {
            response.sendRedirect("500.jsp");
            return;
        }
        response.sendRedirect("updateSkillAdmin?updateId=" + id);
    }

    public boolean checkDupSkill(String skillName, int id) {
        SkillDAO act = new SkillDAO();
        List<Skill> list = act.getListOfSkillByNameDifID(skillName, id);
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
