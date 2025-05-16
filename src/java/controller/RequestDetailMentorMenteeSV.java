/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AttendanceDAO;
import DAO.CVDAO;
import DAO.MenteeDAO;
import DAO.MentorDAO;
import DAO.RequestDAO;
import DAO.SkillDAO;
import DAO.SlotDAO;
import Model.Attendance;
import Model.CV;
import Model.Mentee;
import Model.Mentor;
import Model.Request;
import Model.Skill;
import Model.Slot;
import Model.SlotRequest;
import com.google.gson.Gson;
import static controller.Mentee.SlotViewServlet.convertDayInWeekToCurrentDate;
import static controller.Mentee.SlotViewServlet.convertDayInWeekToDatesBetweenRange;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
@WebServlet(name = "RequestDetailMentorMenteeSV", urlPatterns = {"/requestdetailmentormentee"})
public class RequestDetailMentorMenteeSV extends HttpServlet {

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
            out.println("<title>Servlet RequestDetailMentorMenteeSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequestDetailMentorMenteeSV at " + request.getContextPath() + "</h1>");
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
        AttendanceDAO actAttend = new AttendanceDAO();
        RequestDAO actRequest = new RequestDAO();
        MentorDAO actMentor = new MentorDAO();
        MenteeDAO actMentee = new MenteeDAO();
        SkillDAO actSkill = new SkillDAO();
        CVDAO actCV = new CVDAO();
        SlotDAO actSlot = new SlotDAO();
        PrintWriter out = response.getWriter();
        String requestId_raw = request.getParameter("requestID");
        int requestId;
        try {
            requestId = Integer.parseInt(requestId_raw);
        } catch (NumberFormatException e) {
            response.sendRedirect("home");
            return;
        }
        Request curRequest = actRequest.getRequestByID(requestId);
        request.setAttribute("request", curRequest);
        Mentor curMentor = actMentor.getMentorById(curRequest.getMentorId());
        request.setAttribute("mentor", curMentor);
        Mentee curMentee = actMentee.getMenteeByID(curRequest.getMenteeId());
        request.setAttribute("mentee", curMentee);
        Skill curSkill = actSkill.getSkillByID(curRequest.getSkillId());
        request.setAttribute("skill", curSkill);
        CV curCV = actCV.getCVbyMentorId(curMentor.getMentorId());
        request.setAttribute("cv", curCV);

        List<SlotRequest> menteeSRList = actCV.getSlotRequestbyRequestId(requestId);
        List<String> startDateconverted = new ArrayList<>();
        List<String> endDateconverted = new ArrayList<>();
        List<String> classTitle = new ArrayList<>();
        LocalDate startDate = menteeSRList.get(0).getStartDate();
        LocalDate endDate = menteeSRList.get(0).getEndDate();

        for (int k = 0; k < menteeSRList.size(); k++) {
            List<String> dateList = convertDayInWeekToDatesBetweenRange(menteeSRList.get(k).getDayInWeek(), startDate, endDate);
            for (int m = 0; m < dateList.size(); m++) {
                String startDateAdd = dateList.get(m) + "T" + menteeSRList.get(k).getStartTime();
                String endDateAdd = dateList.get(m) + "T" + menteeSRList.get(k).getEndTime();
                classTitle.add(menteeSRList.get(k).getFramework());
                startDateconverted.add(startDateAdd);
                endDateconverted.add(endDateAdd);
            }
            request.setAttribute("status", new Gson().toJson(startDateconverted));
            request.setAttribute("values", new Gson().toJson(endDateconverted));
            request.setAttribute("endValues", new Gson().toJson(classTitle));
        }

        request.getRequestDispatcher("requestDetailMentorMentee.jsp").forward(request, response);
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
