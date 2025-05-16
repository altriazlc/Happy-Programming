/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Mentee;

import DAO.AttendanceDAO;
import DAO.CVDAO;
import DAO.SkillDAO;
import Model.Mentee;
import Model.Request;
import Model.Schedule;
import Model.Skill;
import Model.Slot;
import Model.SlotRequest;
import Model.User;
import com.google.gson.Gson;
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
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SlotViewServlet", urlPatterns = {"/slotview"})
public class SlotViewServlet extends HttpServlet {

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
            out.println("<title>Servlet SlotViewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SlotViewServlet at " + request.getContextPath() + "</h1>");
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
        String error = request.getParameter("error");
        String mess = request.getParameter("mess");

        HttpSession session = request.getSession();
        User curUser = (User) session.getAttribute("acc");
        Mentee mentee = (Mentee) session.getAttribute("mentee");

        if (curUser == null) {
            response.sendRedirect("signin");
            return;
        }

        int mentorId;
        try {
            mentorId = mentee.getMenteeId();
            AttendanceDAO atd = new AttendanceDAO();
            SkillDAO skd = new SkillDAO();
            //debug converter START-------------------------------------
//            System.out.println(menteeSRList.get(0).getDayInWeek());
//            System.out.println(menteeSRList.get(0).getStartDate());
//            System.out.println(menteeSRList.get(0).getEndDate());
            List<String> start = new ArrayList<>();
            List<String> end = new ArrayList<>();
            List<String> status = new ArrayList<>();
            List<String> framework = new ArrayList<>();
            List<Skill> skillList = skd.getListOfAllSkill();
            //atd.updateStatusAttendanceByDay();
            List<Schedule> scheduleList = atd.getListOfSchedulebyMenteeId(mentorId);
            if (scheduleList == null || scheduleList.isEmpty()) {
                mess = "It looks like you have no upcoming schedule. Please rent a mentor to get started!";
                request.setAttribute("error", error);
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("scheduleView.jsp").forward(request, response);
                return;
            }
//            System.out.println(scheduleList.get(0).getStatus());
//            System.out.println(scheduleList.get(0).getAttendanceStatus());

            for (int j = 0; j < scheduleList.size(); j++) {

                String startDateAdd = scheduleList.get(j).getSlotDate() + "T" + scheduleList.get(j).getStartTime();
//                System.out.println(scheduleList.get(j).getSlotDate());
                String endDateAdd = scheduleList.get(j).getSlotDate() + "T" + scheduleList.get(j).getEndTime();
                status.add(scheduleList.get(j).getAttendanceStatus());
                start.add(startDateAdd);
                end.add(endDateAdd);
                for (Skill skill : skillList) {
                    if (scheduleList.get(j).getSkillId() == skill.getSkillId()) {
                        framework.add(skill.getSkillName());
                    }
                }

                //debug converter result
//                System.out.println("OK:");
//                dateList.forEach(System.out::println);
//                System.out.println("Combine:");
//                startDateconverted.forEach(System.out::println);
                //debug converter END----------------------------
            }
            request.setAttribute("start", new Gson().toJson(start));
            request.setAttribute("end", new Gson().toJson(end));
            request.setAttribute("status", new Gson().toJson(status));
            request.setAttribute("framework", new Gson().toJson(framework));

            request.getRequestDispatcher("scheduleView.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
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
        String jsonData = request.getParameter("eventsArray");
        System.out.println(jsonData);
        
        
        response.sendRedirect("slotview");
    }

    public static String convertDayInWeekToCurrentDate(String dayInWeek) {
        // Lấy ngày hiện tại
        LocalDate today = LocalDate.now();

        // Chuyển đổi chuỗi thành DayOfWeek (ví dụ: "Monday" -> DayOfWeek.MONDAY)
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayInWeek.toUpperCase());

        // Tìm thứ trong tuần hiện tại
        LocalDate resultDate = today.with(DayOfWeek.MONDAY);

        while (resultDate.getDayOfWeek() != dayOfWeek) {
            resultDate = resultDate.plusDays(1);
        }

        // Chuyển đổi LocalDate thành chuỗi
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return resultDate.format(formatter);
    }

    public static List<String> convertDayInWeekToDatesBetweenRange(String dayInWeek, LocalDate startDate, LocalDate endDate) {
        // Chuyển đổi chuỗi thành DayOfWeek (ví dụ: "Monday" -> DayOfWeek.MONDAY)
        DayOfWeek targetDayOfWeek = DayOfWeek.valueOf(dayInWeek.toUpperCase());

        // Tạo danh sách để chứa các ngày kết quả
        List<String> resultDates = new ArrayList<>();

        // Lấy ngày đầu tiên trong khoảng thời gian có cùng thứ với dayInWeek
        LocalDate current = startDate.with(TemporalAdjusters.nextOrSame(targetDayOfWeek));

        // Lặp qua từng tuần cho đến khi vượt quá endDate
        while (!current.isAfter(endDate)) {
            // Thêm ngày vào danh sách kết quả
            resultDates.add(current.toString()); // Format là "yyyy-MM-dd" mặc định

            // Tăng thêm 1 tuần (7 ngày)
            current = current.plusWeeks(1);
        }

        return resultDates;
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
