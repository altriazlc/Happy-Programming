/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Mentor;

import DAO.AttendanceDAO;
import DAO.MentorDAO;
import Model.Attendance;
import Model.Mentor;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
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
@WebServlet(name = "MentorScheduleServlet", urlPatterns = {"/mentorSchedule"})
public class MentorScheduleServlet extends HttpServlet {

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
            out.println("<title>Servlet MentorScheduleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MentorScheduleServlet at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("signin");
        }
        User curUser = (User) session.getAttribute("acc");

        MentorDAO actMentor = new MentorDAO();
        Mentor curMentor = actMentor.findMentorByUsername(curUser.getUsername());
        String status = request.getParameter("status");
        String attendID_raw = request.getParameter("attendID");
        if (status != null && attendID_raw != null) {
            int attendID = Integer.parseInt(attendID_raw);
            boolean checkUpdate = actAttend.updateStatusAttendance(attendID, status);
            if (checkUpdate == false) {
                response.sendRedirect("500.jsp");
                return;
            }
        }
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
      
        List<Attendance> slotInWeek = actAttend.getSchduleByMentorID(curMentor.getMentorId(), monday, sunday);
        //Get all slots in date for 7 day in week. 
        Map<String, List<Attendance>> listSlotInday = getAllSlotIndate(slotInWeek);
        request.setAttribute("slotInWeek", listSlotInday);
        request.setAttribute("daysOfWeek", daysOfWeek);
        request.getRequestDispatcher("mentorSchedule.jsp").forward(request, response);

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
