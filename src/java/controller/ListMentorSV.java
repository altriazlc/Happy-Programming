/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CVDAO;
import DAO.MenteeDAO;
import DAO.MentorDAO;
import DAO.RateDAO;
import DAO.RequestDAO;
import DAO.SkillDAO;
import Model.CV;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
@WebServlet(name = "ListMentorSV", urlPatterns = {"/listmentor"})
public class ListMentorSV extends HttpServlet {

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
            out.println("<title>Servlet ListMentorSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListMentorSV at " + request.getContextPath() + "</h1>");
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
            Mentee curMentee = (Mentee) session.getAttribute("mentee");
            RequestDAO requestdao = new RequestDAO();
            CVDAO cvdao = new CVDAO();
            RateDAO rateDAO = new RateDAO();
            if (curUser == null) {
                response.sendRedirect("signin");
                return;
            }
            int roleID = curUser.getRoleId();

            if (roleID == 2) {
                List<CV> cvlist = cvdao.getListofActiveCV();
                List<Mentor> mentorlist = cvdao.getListofMentorByMenteeWithStatusSort(curUser.getUsername());
                List<Request> requestlist = requestdao.getListofRequestByMenteeID(curMentee.getMenteeId());

                // tạo map để lưu trạng thái đã đánh giá cho từng mentor-request
                Map<Integer, Boolean> ratedMap = new HashMap<>();
                for (Request req : requestlist) {
                    boolean hasRated = rateDAO.hasRated(curMentee.getMenteeId(), req.getMentorId(), req.getRequestId());
                    ratedMap.put(req.getRequestId(), hasRated);
                }

                request.setAttribute("cvlist", cvlist);
                request.setAttribute("mentorlist", mentorlist);
                request.setAttribute("requestlist", requestlist);
                request.setAttribute("ratedMap", ratedMap);
            } else if (roleID == 1) {
                response.sendRedirect("home");
                return;
            }

            request.getRequestDispatcher("listMentor.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println("Error retrieving mentors: " + e.getMessage());
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
        doGet(request, response);  // Handle both POST and GET the same way
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
