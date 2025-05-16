/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Mentee;

import DAO.MenteeDAO;
import DAO.MentorDAO;
import DAO.RequestDAO;
import DAO.SlotDAO;
import Model.Mentee;
import Model.Mentor;
import Model.Request;
//import Model.RequestSlotData;
import Model.Slot;
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
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
@WebServlet(name = "StatisticRequestByMenteeSV", urlPatterns = {"/statisticrequestbymentee"})
public class StatisticRequestByMenteeSV extends HttpServlet {

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
            out.println("<title>Servlet StatisticRequestByMenteeSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticRequestByMenteeSV at " + request.getContextPath() + "</h1>");
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

        if (curUser == null) {
            response.sendRedirect("signin");
            return;
        }

        MenteeDAO menteeDAO = new MenteeDAO();
        MentorDAO mentorDAO = new MentorDAO();
        RequestDAO requestDAO = new RequestDAO();

        if (curUser.getRoleId() == 2) {
            Mentee curMentee = menteeDAO.findMenteeByUsername(curUser.getUsername());
            int menteeId = curMentee.getMenteeId();

            List<Request> listRequests = requestDAO.getRequestStatisticByMenteeID(menteeId);
            List<String> formattedHoursPerRequestList = new ArrayList<>(); //danh sách định dạng giờ
            List<Float> hoursPerRequestList = new ArrayList<>();
            List<Mentor> mentorlist = mentorDAO.getAllMentor();

            //tinh tong tat ca so gio tu moi request
            float totalHours = 0;
            int totalRequests = requestDAO.countTotalRequestsForMentee(menteeId);
            int totalMentors = requestDAO.countTotalMentorsForMentee(menteeId);

            for (Request req : listRequests) {
                float hoursForRequest = getTotalHourOfRequest(req); // tinh tong so gio cua moi request
                hoursPerRequestList.add(hoursForRequest);  // them vao list
                totalHours += hoursForRequest;  //tong so gio

                //dịnh dạng giờ cho từng request
                String formattedHours = formatTotalHours(hoursForRequest);
                formattedHoursPerRequestList.add(formattedHours); //thêm vào danh sách
            }

            System.out.println("Hours Per Request List: " + hoursPerRequestList);

            // Round the total hours to 2 decimal places
            totalHours = (float) Math.round(totalHours * 100) / 100;
            String formattedTotalHours = formatTotalHours(totalHours);

            request.setAttribute("formattedTotalHours", formattedTotalHours);
            request.setAttribute("listRequests", listRequests);
            request.setAttribute("formattedHoursPerRequestList", formattedHoursPerRequestList);
            request.setAttribute("totalRequests", totalRequests);
            request.setAttribute("totalMentors", totalMentors);
            request.setAttribute("mentorlist", mentorlist);

            request.getRequestDispatcher("statisticRequestByMentee.jsp").forward(request, response);
        } else {
            response.sendRedirect("home");
        }
    }

    //chuyển đổi từ số thập phân giờ thành giờ và phút
    private String formatTotalHours(float totalHours) {
        int hours = (int) totalHours; //lấy phần nguyên của giờ
        int minutes = Math.round((totalHours - hours) * 60); //chuyển phần thập phân thành phút
        return hours + " hour " + minutes + " minute";
    }

    //tinh tong so gio cua 1 request
    public float getTotalHourOfRequest(Request request) {
        SlotDAO slotDAO = new SlotDAO();
        List<Slot> listSlots = slotDAO.getValidSlotByRequestId(request.getRequestId());
        float totalHours = 0;
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();

        //lap lai giua startdate va enddate
        while (!startDate.isAfter(endDate)) {
            String currentDayOfWeek = startDate.getDayOfWeek().toString();

            for (Slot slot : listSlots) {
                if (slot.getDayInWeek().equalsIgnoreCase(currentDayOfWeek)) {
                    LocalTime startTime = slot.getStartTime();
                    LocalTime endTime = slot.getEndTime();
                    float durationInHours = Duration.between(startTime, endTime).toMinutes() / 60.0f;
                    totalHours += durationInHours;
                }
            }

            startDate = startDate.plusDays(1);  //chuyen sang ngay tiep theo
        }

        return totalHours;
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
