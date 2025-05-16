/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Admin;

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
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tuong
 */
@WebServlet(name = "RequestDetailAdmin", urlPatterns = {"/requestDetailAdmin"})
public class RequestDetailAdmin extends HttpServlet {

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
            out.println("<title>Servlet RequestDetailAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequestDetailAdmin at " + request.getContextPath() + "</h1>");
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
        RequestDAO actRequest = new RequestDAO();
        MentorDAO actMentor = new MentorDAO();
        MenteeDAO actMentee = new MenteeDAO();
        AttendanceDAO actAttend = new AttendanceDAO();
        SkillDAO actSkill = new SkillDAO();
        CVDAO actCV = new CVDAO();
        SlotDAO actSlot = new SlotDAO();
        PrintWriter out = response.getWriter();
        String requestId_raw = request.getParameter("requestID");
        int requestId;
        try {
            requestId = Integer.parseInt(requestId_raw);
        } catch (NumberFormatException e) {
            response.sendRedirect("requestListAdmin");
            return;
        }
        Request curRequest = actRequest.getRequestByID(requestId);
        Mentor curMentor = actMentor.getMentorById(curRequest.getMentorId());
        Mentee curMentee = actMentee.getMenteeByID(curRequest.getMenteeId());
        Skill curSkill = actSkill.getSkillByID(curRequest.getSkillId());
        CV curCV = actCV.getCVbyMentorId(curMentor.getMentorId());

        //Get date mentor want to view schedule
        String date_raw = request.getParameter("start");
        LocalDate date = LocalDate.now();
        if (date_raw != null && !date_raw.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {

                date = LocalDate.parse(date_raw, formatter);
            } catch (DateTimeParseException ex) {
                ex.printStackTrace();
            }
        }
        LocalDate monday = getMondayDate(date);
        request.setAttribute("start", monday);
        List<String> daysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        Map<String, LocalDate> dateOfDay = new LinkedHashMap<>();

        int i = 0;
        for (String day : daysOfWeek) {
            dateOfDay.put(day, monday.plusDays(i));
            i++;
        }
        request.setAttribute("dateOfDay", dateOfDay);

        LocalDate sunday = monday.plusDays(6);
        //Get all slot between monday and saturday

        List<Attendance> slotInWeek = actAttend.getSchduleByRequestID(requestId, monday, sunday);
        //Get all slots in date for 7 day in week. 
        Map<String, List<Attendance>> listSlotInday = getAllSlotIndate(slotInWeek);

        //Set atribute
        request.setAttribute("request", curRequest);
        request.setAttribute("mentor", curMentor);
        request.setAttribute("mentee", curMentee);
        request.setAttribute("skill", curSkill);
        request.setAttribute("cv", curCV);
        request.setAttribute("start", date);
        request.setAttribute("daysOfWeek", daysOfWeek);
        request.setAttribute("slotInWeek", listSlotInday);
        
        //Get list of slot by request and and start date
        request.getRequestDispatcher("/Admin/requestDetailAdmin.jsp").forward(request, response);
    }

    public LocalDate getMondayDate(LocalDate input) {
        return input.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    public Map<String, List<Attendance>> getAllSlotIndate(List<Attendance> slotInWeek) {
        Map<String, List<Attendance>> slotsByDay = new LinkedHashMap<>();
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        // Initialize map with day keys
        for (String day : daysOfWeek) {
            slotsByDay.put(day, new ArrayList<>());
        }

        // Populate each day with relevant attendance records
        for (Attendance attend : slotInWeek) {
            String day = attend.getDayInWeek();
            if (day != null && slotsByDay.containsKey(day)) {
                slotsByDay.get(day).add(attend);
            }
        }

        return slotsByDay;
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
