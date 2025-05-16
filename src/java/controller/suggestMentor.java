/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.CVDAO;
import DAO.MenteeDAO;
import DAO.MentorDAO;
import DAO.RequestDAO;
import DAO.SlotDAO;
import Model.CV;
import Model.Mentee;
import Model.Mentor;
import Model.Request;
import Model.Skill;
import Model.Slot;
import Model.MentorRating;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author nhhag
 */
@WebServlet(name="suggestMentor", urlPatterns={"/suggestMentor"})
public class suggestMentor extends HttpServlet {
   
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
            out.println("<title>Servlet suggestMentor</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet suggestMentor at " + request.getContextPath () + "</h1>");
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
        PrintWriter out = response.getWriter();
        RequestDAO requestDAO = new RequestDAO();
        HttpSession sesion = request.getSession();
        User a = new User();
        CVDAO cvd = new CVDAO();
        SlotDAO slotDAO = new SlotDAO();
        MentorDAO mentorDAO = new MentorDAO();
        MenteeDAO menteeDAO= new MenteeDAO();
        
        a = (User) sesion.getAttribute("acc");
        if (a == null) {
            response.sendRedirect("signin");
        }

        String experience = request.getParameter("experience");
        String priceRange = request.getParameter("priceRange");
        String sortBy = request.getParameter("sortBy");

        // Fetch mentors based on the mentee's preference
        try{
        Mentee mentee = menteeDAO.findMenteeByUsername(a.getUsername());
        List<CV> mentorCvList = requestDAO.getSuggestMentorCVByMentee();
        List<MentorRating> mentorRatingList = new ArrayList<>();
        // Populate the list with MentorRating objects
        for (CV cv : mentorCvList) { 
            int rating = cvd.getAveRatebyId(cv.getMentorId());
            Mentor mentor = mentorDAO.getMentorById(cv.getMentorId());
            int reviewsCount = cvd.getMentorRateList(cv.getMentorId()).size();
            int numRequest = requestDAO.getRequestByMentorID(cv.getMentorId()).size();
            List<Skill> skillList = cvd.getMentorSkillListByMentorID(cv.getMentorId());
            MentorRating mentorRating = new MentorRating(mentor, rating, cv, reviewsCount, numRequest, skillList);
            mentorRatingList.add(mentorRating);
        }

        // Apply filters
        mentorRatingList = filterByExperience(mentorRatingList, experience);
        mentorRatingList = filterByPriceRange(mentorRatingList, priceRange);

        if ("priceAsc".equals(sortBy)) {
            mentorRatingList.sort(Comparator.comparingDouble(mr -> {
                CV cv = ((MentorRating) mr).getCv(); 
                return (cv != null) ? cv.getPrice() : Double.MAX_VALUE; 
            }));
        } else if ("priceDesc".equals(sortBy)) {
            mentorRatingList.sort(Comparator.comparingDouble(mr -> {
                CV cv = ((MentorRating) mr).getCv(); 
                return (cv != null) ? cv.getPrice() : Double.MAX_VALUE; 
            }).reversed());
        } else if ("ratingAsc".equals(sortBy)) {
            mentorRatingList.sort(Comparator.comparingInt(MentorRating::getRating));
        } else if ("ratingDesc".equals(sortBy)) {
            mentorRatingList.sort(Comparator.comparingInt(MentorRating::getRating).reversed());
        }

        // Set the filtered and sorted list as an attribute
        request.setAttribute("mentorList", mentorRatingList);
        request.getRequestDispatcher("suggestMentor.jsp").forward(request, response);
        }
        catch(Exception e)
        {
             request.setAttribute("error", "List load error");
        request.getRequestDispatcher("suggestMentor.jsp").forward(request, response);
        }
    }

    private List<MentorRating> filterByExperience(List<MentorRating> mentors, String experience) {
        List<MentorRating> filteredMentors = new ArrayList<>();
        if (experience == null || experience.isEmpty()) {
            return mentors; // Return all if no filter is applied
        }

        for (MentorRating mentor : mentors) {
            int years = mentor.getCv().getYearOfExperience();
            switch (experience) {
                case "1":
                    if (years >= 1 && years <= 2) {
                        filteredMentors.add(mentor);
                    }
                    break;
                case "2":
                    if (years >= 3 && years <= 5) {
                        filteredMentors.add(mentor);
                    }
                    break;
                case "3":
                    if (years > 5) {
                        filteredMentors.add(mentor);
                    }
                    break;
            }
        }
        return filteredMentors;
    }

    private List<MentorRating> filterByPriceRange(List<MentorRating> mentors, String priceRange) {
        List<MentorRating> filteredMentors = new ArrayList<>();
        if (priceRange == null || priceRange.isEmpty()) {
            return mentors; // Return all if no filter is applied
        }

        for (MentorRating mentor : mentors) {
            float price = mentor.getCv().getPrice();
            switch (priceRange) {
                case "1":
                    if (price <= 500000) {
                        filteredMentors.add(mentor);
                    }
                    break;
                case "2":
                    if (price > 500000 && price <= 1000000) {
                        filteredMentors.add(mentor);
                    }
                    break;
                case "3":
                    if (price > 1000000) {
                        filteredMentors.add(mentor);
                    }
                    break;
            }
        }
        return filteredMentors;
    }

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
