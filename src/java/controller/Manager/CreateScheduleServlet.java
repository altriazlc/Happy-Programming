/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Manager;

import DAO.AttendanceDAO;
import DAO.RequestDAO;
import Model.Attendance;
import Model.Request;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author tuong
 */
@WebServlet(name="CreateScheduleServlet", urlPatterns={"/createSchedule"})
public class CreateScheduleServlet extends HttpServlet {
   
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
            out.println("<title>Servlet CreateScheduleServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateScheduleServlet at " + request.getContextPath () + "</h1>");
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
        AttendanceDAO actAttend = new AttendanceDAO();
        RequestDAO actRequest = new RequestDAO();
        Request curRequest = actRequest.getRequestByID(8);
        
        
        boolean checkInsetSchedule = setScheduelForRequest(curRequest);
        processRequest(request, response);
    }
    public static boolean setScheduelForRequest(Request curRequest){
         AttendanceDAO actAttend = new AttendanceDAO();
        RequestDAO actRequest = new RequestDAO();
        List<Attendance> listAttend = actAttend.getAllSlotOfRequest(curRequest.getRequestId());
        LocalDate startDate = curRequest.getStartDate();
         LocalDate endDate = curRequest.getEndDate();
        while (startDate.isBefore(endDate) || startDate.equals(endDate)) { // Loop until the end date is reached
            for (Attendance curAttend : listAttend) { // Iterate through slots
                String dayInWeek = startDate.getDayOfWeek().toString(); // Get the day of the week
                if (curAttend.getDayInWeek().equalsIgnoreCase(dayInWeek)) {
                    curAttend.setDate(startDate);
                    curAttend.setStatus("Not yet");
                    boolean check = actAttend.addAttendance(curAttend);
                    if(check == false)
                        return false;
                }
            }
            startDate = startDate.plusDays(1); // Move to the next day
        }
        return true;
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
