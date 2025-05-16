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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhhag
 */
@WebServlet(name = "SkillHomeSV", urlPatterns = {"/skillhome"})
public class SkillHomeSV extends HttpServlet {

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
            out.println("<title>Servlet SkillHomeSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SkillHomeSV at " + request.getContextPath() + "</h1>");
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
        String index_raw = request.getParameter("index");
        String filter = request.getParameter("filter");
        SkillListDAO skilllistDAO = new SkillListDAO();
        SkillDAO skillDAO = new SkillDAO();

        if (filter == null) {
            filter = "";
        }
        int index = 1;
        try {
            index = Integer.parseInt(index_raw);
        } catch (NumberFormatException e) {
            System.out.println("Invalid index parameter, defaulting to 1.");
        }

        int totalSkill = 0;
        List<Skill> list = new ArrayList<>();
        switch (filter) {
            case "mostRequests":
                list = skillDAO.getListOfSkillByMostRequested(index, 9);
                break;
            case "leastRequests":
                list = skillDAO.getListOfSkillByLeastRequested(index, 9);
                break;
            case "mostMentors":
                list = skillDAO.getListOfSkillByMostMentors(index, 9);
                break;
            case "leastMentors":
                list = skillDAO.getListOfSkillByLeastMentors(index, 9);
                break;
            case "newestSkill":
                list = skillDAO.getListOfNewestSkills(index, 9);
                break;
            case "oldestSkill":
                list = skillDAO.getListOfOldestSkills(index, 9);
                break;
            case "":
                list = skillDAO.getListOfSkillPagings(index, 9);
            default:
                list = skillDAO.getListOfSkillPagings(index, 9);
                break;
        }

        int page = (totalSkill + 8) / 9;

        List<Skill> list2 = skillDAO.getListOfSkillByDate();
        int[] number = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            number[i] = skilllistDAO.getMentorBySkill(list.get(i).getSkillId()).size();
        }

        request.setAttribute("number", number);
        request.setAttribute("list2", list2);
        request.setAttribute("pageIndex", index);
        request.setAttribute("endP", page);
        request.setAttribute("list", list);
        request.getRequestDispatcher("SkillList.jsp").forward(request, response);

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
