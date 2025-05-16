/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Admin;

import DAO.MentorDAO;
import DAO.RequestDAO;
import DAO.SkillDAO;
import Model.Mentor;
import Model.Request;
import Model.Skill;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author tuong
 */
@WebServlet(name = "SkillDetailAdminServlet", urlPatterns = {"/skillDetailAdmin"})
public class SkillDetailAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet SkillDetailAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SkillDetailAdminServlet at " + request.getContextPath() + "</h1>");
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
        SkillDAO actSkill = new SkillDAO();
        RequestDAO actReq = new RequestDAO();
        MentorDAO actMentor = new MentorDAO();

        //Get parameteer
        String skillId_raw = request.getParameter("skillID");
        int skillId = 0;
        if (skillId_raw != null && skillId_raw != "") {
            try {
                skillId = Integer.parseInt(skillId_raw);
            } catch (NumberFormatException e) {
                response.sendRedirect("SkillListAdmin");
            }
        } else {
            response.sendRedirect("SkillListAdmin");
            return;
        }

        //Set variable
        Skill curSkill = actSkill.getSkillByID(skillId);
        List<Request> listRequest = actReq.getRequestsBySkillId(skillId);
        List<Mentor> listMentor = actMentor.getAllMentorHaveSkillId(skillId);
        //Set variable value to page
        request.setAttribute("curSkill", curSkill);
        request.setAttribute("liReq", listRequest);
        request.setAttribute("liMentor", listMentor);

        request.getRequestDispatcher("/Admin/skillDetailAdmin.jsp").forward(request, response);
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
        SkillDAO actSkill = new SkillDAO();
        RequestDAO actReq = new RequestDAO();
        MentorDAO actMentor = new MentorDAO();

        //Get parameteer
        String skillId_raw = request.getParameter("skillID");
        int skillId = 0;
        if (skillId_raw != null && skillId_raw != "") {
            try {
                skillId = Integer.parseInt(skillId_raw);
            } catch (NumberFormatException e) {
                response.sendRedirect("SkillListAdmin");
            }
        } else {
            response.sendRedirect("SkillListAdmin");
            return;
        }

        //Set variable
        Skill curSkill = actSkill.getSkillByID(skillId);
        List<Request> listRequest = actReq.getRequestsBySkillId(skillId);
        List<Mentor> listMentor = actMentor.getAllMentorHaveSkillId(skillId);
        //Update status
        String curStatus = curSkill.getStatus();
        if (curStatus.equalsIgnoreCase("active")) {
            curSkill.setStatus("Inactive");
        } else {
            curSkill.setStatus("Active");
        }
        boolean checkUpdateSkill = actSkill.updateSkillInfo(curSkill);
        if (checkUpdateSkill == false) {
            response.sendRedirect("500.jsp");
            return;
        }
        //Set variable value to page
        request.setAttribute("curSkill", curSkill);
        request.setAttribute("liReq", listRequest);
        request.setAttribute("liMentor", listMentor);

        request.getRequestDispatcher("/Admin/skillDetailAdmin.jsp").forward(request, response);
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
