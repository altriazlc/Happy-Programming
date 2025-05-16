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
import DAO.SkillListDAO;
import Model.CV;
import Model.Mentee;
import Model.Mentor;
import Model.Request;
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
@WebServlet(name = "RateMentorSV", urlPatterns = {"/ratementor"})
public class RateMentorSV extends HttpServlet {

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
            out.println("<title>Servlet RateMentorSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RateMentorSV at " + request.getContextPath() + "</h1>");
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
        MentorDAO actMentor = new MentorDAO();
        CVDAO cvdao = new CVDAO();
        RequestDAO requestDAO = new RequestDAO();

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
        String mentorId_raw = request.getParameter("mentorId");
        int mentorId;
        try {
            mentorId = Integer.parseInt(mentorId_raw);
        } catch (NumberFormatException e) {
            System.out.println(e);
            return;
        }

        Mentor mentor = actMentor.getMentorById(mentorId);
        if (mentor == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Mentor not found");
            return;
        }
        int requestID = Integer.parseInt(request.getParameter("requestId"));
        requestDAO.getRequestByID(requestID);

        request.setAttribute("cvmentor", cvdao.getCVbyMentorId(mentorId));
        request.setAttribute("mentor", mentor);
        request.setAttribute("request", requestDAO.getRequestByID(requestID));
        request.getRequestDispatcher("rateMentor.jsp").forward(request, response);
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
        RateDAO rateDAO = new RateDAO();
        SkillListDAO actskilllist = new SkillListDAO();
        RequestDAO requestDAO = new RequestDAO();

        if (curUser == null) {
            response.sendRedirect("signin");
            return;
        }

        int roleID = curUser.getRoleId();
        if (roleID != 2) {  // Ensure only mentees can rate
            response.sendRedirect("home");
            return;
        }

        Mentee curMentee = actMentee.findMenteeByUsername(curUser.getUsername());
        int menteeId = curMentee.getMenteeId();

        int mentorId, rate, requestId;
        String comment;

        try {
            mentorId = Integer.parseInt(request.getParameter("mentorId"));
            rate = Integer.parseInt(request.getParameter("rating"));
            requestId = Integer.parseInt(request.getParameter("requestId"));
            comment = request.getParameter("comment");
            Request req = requestDAO.getRequestByID(requestId);

            // Check for existing rating for this specific request
            if (rateDAO.checkExistingRating(menteeId, mentorId, requestId)) {
                // Redirect to the profile with an error indicating a rating already exists
                response.sendRedirect("viewprofilecv?id=" + mentorId + "&error=alreadyRated");
                return;
            }
            
            Integer currentRating = actskilllist.getCurrentRating(req.getSkillId());

            if (currentRating == null || currentRating == 0) {
                actskilllist.updateRating(req.getSkillId(), rate, mentorId);
                response.sendRedirect("listmentor");
            } else {
                int newRating = (currentRating + rate) / 2;
                actskilllist.updateRating(req.getSkillId(), newRating, mentorId);
                response.sendRedirect("listmentor");
            }

            // Save the new rating if no existing rating is found
            rateDAO.saveRating(menteeId, mentorId, requestId, rate, comment);

            // Redirect with a success message
            response.sendRedirect("viewprofilecv?id=" + mentorId + "&success=ratingSaved");

        } catch (NumberFormatException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
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
