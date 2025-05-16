/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Mentee;

import DAO.CVDAO;
import DAO.MenteeDAO;
import DAO.MentorDAO;
import DAO.RateDAO;
import DAO.RequestDAO;
import DAO.SkillDAO;
import DAO.SkillListDAO;
import Model.Mentee;
import Model.Mentor;
import Model.Request;
import Model.Skill;
import Model.SkillList;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author asus
 */
@WebServlet(name = "RateSkillSV", urlPatterns = {"/rateskill"})
public class RateSkillSV extends HttpServlet {

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
            out.println("<title>Servlet RateSkillSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RateSkillSV at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User curUser = (User) session.getAttribute("acc");
        MenteeDAO actMentee = new MenteeDAO();
        SkillDAO actSkill = new SkillDAO();
        RequestDAO requestdao = new RequestDAO();
        CVDAO cvdao = new CVDAO();

        if (curUser == null) {
            response.sendRedirect("signin");
            return;
        }
        int roleID = curUser.getRoleId();
        Mentee curMentee = new Mentee();
        if (roleID == 2) {
            curMentee = actMentee.findMenteeByUsername(curUser.getUsername());
            request.setAttribute("mentee", curMentee);
        } else if (roleID == 1) {
            response.sendRedirect("home");
            return;
        }
        String requestid_raw = request.getParameter("requestId");
        int requestId;
        try {
            requestId = Integer.parseInt(requestid_raw);
        } catch (NumberFormatException e) {
            System.out.println(e);
            return;
        }

        Skill skill = actSkill.getSkillByRequestID(requestId);
        List<Request> requestlist = requestdao.getListofRequestByMenteeID(curMentee.getMenteeId());
        List<Mentor> mentorlist = cvdao.getListofMentorByMenteeWithStatus(curUser.getUsername());
        if (skill == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Skill not found");
            return;
        }

        request.setAttribute("skill", skill);
        request.setAttribute("requestlist", requestlist);
        request.setAttribute("mentorlist", mentorlist);
        request.getRequestDispatcher("rateSkill.jsp").forward(request, response);
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
        SkillListDAO actskilllist = new SkillListDAO();

        if (curUser == null) {
            response.sendRedirect("signin");
            return;
        }

        int roleID = curUser.getRoleId();
        if (roleID != 2) {
            response.sendRedirect("home");
            return;
        }

        int skillId, rate;

        try {
            skillId = Integer.parseInt(request.getParameter("skillId"));
            rate = Integer.parseInt(request.getParameter("rating"));

            Integer currentRating = actskilllist.getCurrentRating(skillId);

            if (currentRating == null || currentRating == 0) {
                //actskilllist.updateRating(skillId, rate);
                response.sendRedirect("listmentor");
                return;
            } else {
                int newRating = (currentRating + rate) / 2;
                //actskilllist.updateRating(skillId, newRating);
                response.sendRedirect("listmentor");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
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
