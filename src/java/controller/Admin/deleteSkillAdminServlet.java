/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Admin;

import DAO.SkillDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Model.Skill;

/**
 *
 * @author tuong
 */
@WebServlet(name="deleteSkillAdmin", urlPatterns={"/deleteSkillAdmin"})
public class deleteSkillAdminServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet deleteSkillAdminServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteSkillAdminServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        SkillDAO act = new SkillDAO();
        List<Skill> listAllSkill = act.getListOfAllSkill();
        String page_raw = request.getParameter("page");
        String numDis_raw = request.getParameter("numDis");
        String deleteID_raw = request.getParameter("id");
        int deleteId = Integer.parseInt(deleteID_raw);
        act.deleteSkillById(deleteId);
        int page, numDis;
        if (page_raw != null) {
            page = Integer.parseInt(page_raw);
        } else {
            page = 1;
        }
        if (numDis_raw != null) {
            numDis = Integer.parseInt(numDis_raw);
        } else {
            numDis = 10;
        }
        int numSkill = listAllSkill.size();
        int numOfPage = (numSkill % numDis == 0 ? numSkill / numDis : (numSkill / numDis + 1));
        request.setAttribute("numOfPage", numOfPage);
        listAllSkill = act.getListOfSkillPaging(page, numDis);
        request.setAttribute("indexPage", page);
        request.setAttribute("numDis", numDis);

        request.setAttribute("list", listAllSkill);
        request.getRequestDispatcher("/Admin/viewSkillAdmin.jsp").forward(request, response);

    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
