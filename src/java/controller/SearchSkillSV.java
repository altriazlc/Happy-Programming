/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.SkillDAO;
import DAO.SkillListDAO;
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
 * @author nhhag
 */
@WebServlet(name = "SearchSkillSV", urlPatterns = {"/searchskill"})
public class SearchSkillSV extends HttpServlet {

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
            out.println("<title>Servlet SearchSkillSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchSkillSV at " + request.getContextPath() + "</h1>");
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
        String skillname = request.getParameter("skill");
        String index_raw = request.getParameter("index");
        SkillDAO skillDAO = new SkillDAO();
        skillname = skillname.toLowerCase();
        SkillListDAO skilllistDAO = new SkillListDAO();
        if (skillname == null || skillname.trim().isEmpty()) {
            response.sendRedirect("skillhome?index=1");
        } else {
            List<Skill> listSkill = skillDAO.getListOfSkillByName(skillname);
            List<Skill> list2 = skillDAO.getListOfSkillByDate();
            int index = 1;
            try {
                index = Integer.parseInt(index_raw);
            } catch (NumberFormatException e) {
                System.out.println("Invalid index parameter, defaulting to 1.");
            }
            int totalSkill = listSkill.size();
            int page;
            if (totalSkill / 9 == 0) {
                page = totalSkill / 9;
            } else {
                page = totalSkill / 9 + 1;
            }
            List<Skill> list = skillDAO.getListOfSkillByNamePagination(index, 9, skillname);
            int[] number = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                number[i] = skilllistDAO.getMentorBySkill(list.get(i).getSkillId()).size();
            }
            if (list == null || list.isEmpty()) {
                request.setAttribute("notify", "There is no skill name: " + skillname);
                request.getRequestDispatcher("SkillList.jsp").forward(request, response);
            } else {
                request.setAttribute("url", "searchskill?skill=" + skillname);
                request.setAttribute("number", number);
                request.setAttribute("pageIndex", index);
                request.setAttribute("endP", page);
                request.setAttribute("list", list);
                request.setAttribute("list2", list2);
                request.setAttribute("notify", "There is " + totalSkill + " skills found ");
                request.getRequestDispatcher("SkillList.jsp").forward(request, response);
            }
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
