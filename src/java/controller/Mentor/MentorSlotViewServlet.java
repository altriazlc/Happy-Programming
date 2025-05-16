/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Mentor;

import DAO.CVDAO;
import Model.Slot;
import Model.SlotRequest;
import com.google.gson.Gson;
import static controller.Mentee.SlotViewServlet.convertDayInWeekToDatesBetweenRange;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "MentorSlotViewServlet", urlPatterns = {"/slotmentor"})
public class MentorSlotViewServlet extends HttpServlet {

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
            out.println("<title>Servlet MentorSlotViewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MentorSlotViewServlet at " + request.getContextPath() + "</h1>");
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
        String mentorId_raw = request.getParameter("id");
        String error = request.getParameter("error");
        String mess = request.getParameter("mess");

        int mentorId;
        try {
            mentorId = Integer.parseInt(mentorId_raw);
            CVDAO cvd = new CVDAO();
            List<SlotRequest> menteeSRList = cvd.getSlotRequestbyMentorId(mentorId);
            if (menteeSRList.isEmpty()) {
                //System.out.println("emptyroi");
                request.getRequestDispatcher("slotMentor.jsp").forward(request, response);
            }
            //debug converter START-------------------------------------
//            System.out.println(menteeSRList.get(0).getDayInWeek());
//            System.out.println(menteeSRList.get(0).getStartDate());
//            System.out.println(menteeSRList.get(0).getEndDate());
            List<String> startDateconverted = new ArrayList<>();
            List<String> endDateconverted = new ArrayList<>();
            List<String> classTitle = new ArrayList<>();

            LocalDate startDate = menteeSRList.get(0).getStartDate();
            LocalDate endDate = menteeSRList.get(0).getEndDate();

            for (int j = 0; j < menteeSRList.size(); j++) {
                List<String> dateList = convertDayInWeekToDatesBetweenRange(menteeSRList.get(j).getDayInWeek(), startDate, endDate);
                for (int i = 0; i < dateList.size(); i++) {
                    String startDateAdd = dateList.get(i) + "T" + menteeSRList.get(j).getStartTime();
                    String endDateAdd = dateList.get(i) + "T" + menteeSRList.get(j).getEndTime();
                    classTitle.add(menteeSRList.get(j).getFramework());
                    startDateconverted.add(startDateAdd);
                    endDateconverted.add(endDateAdd);
                }
                //debug converter result
                System.out.println("OK:");
                dateList.forEach(System.out::println);
                System.out.println("Combine:");
                startDateconverted.forEach(System.out::println);
                //debug converter END----------------------------

                request.setAttribute("values", new Gson().toJson(startDateconverted));
                request.setAttribute("endDateconverted", new Gson().toJson(endDateconverted));
                request.setAttribute("status", new Gson().toJson(classTitle));
                request.setAttribute("error", error);
                request.setAttribute("mess", mess);
            }

            request.getRequestDispatcher("slotMentor.jsp").forward(request, response);
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
