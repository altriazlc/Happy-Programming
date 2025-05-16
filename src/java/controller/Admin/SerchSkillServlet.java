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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author tuong
 */
@WebServlet(name = "SerchSkillServlet", urlPatterns = {"/adminSearchSkill"})
public class SerchSkillServlet extends HttpServlet {

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
            out.println("<title>Servlet SerchSkillServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SerchSkillServlet at " + request.getContextPath() + "</h1>");
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
        String page_raw = request.getParameter("page");
        String numDis_raw = request.getParameter("numDis");
        int page, numDis;
        if (page_raw != null) {
            page = Integer.parseInt(page_raw);
        } else {
            page = 1;
        }
        if (numDis_raw != null) {
            numDis = Integer.parseInt(numDis_raw);
        } else {
            numDis = 5;
        }
        int stt = (page - 1) * numDis;
        request.setAttribute("stt", stt);

        String searchName = request.getParameter("search");
        if(searchName == null || searchName==""){
            response.sendRedirect("SkillListAdmin");
            return;
        }
        List<Skill> listAllSkill = act.getListOfSkillByName(searchName);
        int numSkill = listAllSkill.size();
        int numOfPage = (numSkill % numDis == 0 ? numSkill / numDis : (numSkill / numDis + 1));
        request.setAttribute("numOfPage", numOfPage);
        request.setAttribute("indexPage", page);
        request.setAttribute("numDis", numDis);
        listAllSkill = act.getListOfSkillByNamePagination(page, numDis, searchName);
        request.setAttribute("search", searchName);
        request.setAttribute("list", listAllSkill);
        request.setAttribute("search", searchName);
        request.getRequestDispatcher("/Admin/adminSearchSkill.jsp").forward(request, response);
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
        String curStatus = curSkill.getStatus();
        if (curStatus.equalsIgnoreCase("active")) {
            curSkill.setStatus("Inactive");
        } else {
            curSkill.setStatus("Active");
        }
        boolean checkUpdateSkill = act.updateSkillInfo(curSkill);
        if (checkUpdateSkill == false) {
            response.sendRedirect("500.jsp");
            return;
        }
        String page_raw = request.getParameter("page");
        String numDis_raw = request.getParameter("numDis");
        int page, numDis;
        if (page_raw != null) {
            page = Integer.parseInt(page_raw);
        } else {
            page = 1;
        }
        if (numDis_raw != null) {
            numDis = Integer.parseInt(numDis_raw);
        } else {
            numDis = 5;
        }
        int stt = (page - 1) * numDis;
        request.setAttribute("stt", stt);
        String searchName = request.getParameter("search");
        List<Skill> listAllSkill = act.getListOfSkillByName(searchName);
        int numSkill = listAllSkill.size();
        int numOfPage = (numSkill % numDis == 0 ? numSkill / numDis : (numSkill / numDis + 1));
        request.setAttribute("numOfPage", numOfPage);
        request.setAttribute("indexPage", page);
        request.setAttribute("numDis", numDis);
        listAllSkill = act.getListOfSkillByNamePagination(page, numDis, searchName);
        request.setAttribute("search", searchName);
        request.setAttribute("list", listAllSkill);
        request.getRequestDispatcher("/Admin/adminSearchSkill.jsp").forward(request, response);
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
