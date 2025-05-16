/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.CVDAO;
import DAO.HomeDAO;
import Model.Mentee;
import Model.Mentor;
import Model.Rate;
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
 * @author ADMIN
 */
@WebServlet(name="HomeServlet", urlPatterns={"/home"})
public class HomeServlet extends HttpServlet {
   
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
            out.println("<title>Servlet HomeServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath () + "</h1>");
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
        HomeDAO hdao = new HomeDAO();
        CVDAO cvd= new CVDAO();
        List<Skill> skillList = hdao.getListofSkill();
        List<Skill> skillListNewest = hdao.getListofSkillNewest();
        List<Skill> skillListMostLearn = hdao.getListofSkillMostLearn();
        
        
        List<Rate> rateList = cvd.getRateList();
        List<Mentor> mentorList = hdao.getListofMentor();
        List<Mentee> menteeList = hdao.getListofMentee();
        
        
        //List<Mentor> mentors = cvd.getMentorList();
        //System.out.println(rateList.get(0).getRate());
        //System.out.println(rateList.get(0).getMenteeId());
        int userNumb = hdao.countUsers();
        int mentorNumb = hdao.countMentor();
        int menteeCount = hdao.countMentee();
        int requestCount = hdao.countRequest();
        int completeRequestCount = cvd.countAllCompletedRequest();
       int rateCount = cvd.countAllRating();
        int skillCount = hdao.skillCount();
        float rateAve = hdao.getRateAve();
        //System.out.println(rateAve);
        request.setAttribute("rate", rateList);
        request.setAttribute("rateAve", rateAve);
        request.setAttribute("skillCount", skillCount);
        request.setAttribute("menteeCount", menteeCount);
         request.setAttribute("requestCount", requestCount);
         request.setAttribute("completeRequestCount", completeRequestCount);
         request.setAttribute("rateCount", rateCount);
         
         
         
        request.setAttribute("mentorList", mentorList);
        request.setAttribute("menteeList", menteeList);
         
        
        request.setAttribute("skill", skillList);
        request.setAttribute("skillListNewest", skillListNewest);
        request.setAttribute("skillListMostLearn", skillListMostLearn);
        

        
        request.setAttribute("userNum", userNumb);
        request.setAttribute("mentorNum", mentorNumb);
        
        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
