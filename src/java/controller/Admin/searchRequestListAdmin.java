/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Admin;

import DAO.MenteeDAO;
import DAO.RequestDAO;
import Model.Mentee;
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
@WebServlet(name = "searchRequestListAdmin", urlPatterns = {"/searchRequestListAdmin"})
public class searchRequestListAdmin extends HttpServlet {

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
            out.println("<title>Servlet searchRequestListAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet searchRequestListAdmin at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();

        MenteeDAO actMentee = new MenteeDAO();
        RequestDAO actRequest = new RequestDAO();

        // Retrieve filter parameters
        String status = request.getParameter("status") != null ? request.getParameter("status") : "";
        status = status.equalsIgnoreCase("all") ? "" : status;
        String search = request.getParameter("search") != null ? request.getParameter("search").trim().replaceAll("\\s+", " ") : "";
        String page_raw = request.getParameter("page");
        String numDis_raw = request.getParameter("numDis");
        String start_raw = request.getParameter("start");
        String end_raw = request.getParameter("end");

        // Set default values for pagination
        int page = (page_raw != null) ? Integer.parseInt(page_raw) : 1;
        int numDis = (numDis_raw != null) ? Integer.parseInt(numDis_raw) : 10;
        int stt = (page - 1) * numDis;
        LocalDate start = (start_raw != null && start_raw != "") ? LocalDate.parse(start_raw) : null;
        LocalDate end = (end_raw != null && end_raw != "") ? LocalDate.parse(end_raw) : null;
        out.print(start);
        out.print("null");

        // Pass filters to DAO for retrieval and filtering
        List<Request> listReq = actRequest.getRequestByStatusAndSearchPagination(search, page, numDis, status, start, end);

        // Calculate number of pages based on filtered list size
        int numMent = actRequest.getCountRequestByStatusAndSearch(search, status, start, end);
        out.print(numMent);
        int numOfPage = (numMent % numDis == 0 ? numMent / numDis : (numMent / numDis + 1));

        // Set attributes for use in JSP
        request.setAttribute("stt", stt);
        request.setAttribute("search", search);
        request.setAttribute("status", status);
        request.setAttribute("numOfPage", numOfPage);
        request.setAttribute("indexPage", page);
        request.setAttribute("numDis", numDis);
        request.setAttribute("listReq", listReq);
        request.setAttribute("start", start);
        request.setAttribute("end", end);
        // Retrieve mentee names and statuses to display alongside filtered data
        String[] listNameMentee = getRequestMenteeName(listReq);
        request.setAttribute("listName", listNameMentee);
        String[] listStatus = actRequest.getAllStatusInRequest();
        request.setAttribute("listStatus", listStatus);

        // Forward to JSP
        request.getRequestDispatcher("/Admin/searchRequestAdmin.jsp").forward(request, response);
    }

    public String[] getRequestMenteeName(List<Request> listReq) {
        MenteeDAO actMentee = new MenteeDAO();
        String[] listName = new String[listReq.size()];
        for (int i = 0; i < listReq.size(); i++) {
            int menteeId = listReq.get(i).getMenteeId();
            Mentee curMentee = actMentee.getMenteeByID(menteeId);
            listName[i] = curMentee.getUsername();
        }
        return listName;
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
        PrintWriter out = response.getWriter();
        MenteeDAO actMentee = new MenteeDAO();
        RequestDAO actRequest = new RequestDAO();

        // Retrieve filter parameters
        String status = request.getParameter("status") != null ? request.getParameter("status") : "";
        status = status.equalsIgnoreCase("all") ? "" : status;
        String search = request.getParameter("search") != null ? request.getParameter("search").trim().replaceAll("\\s+", " ") : "";
        String page_raw = request.getParameter("page");
        String numDis_raw = request.getParameter("numDis");
        String start_raw = request.getParameter("start");
        String end_raw = request.getParameter("end");
        // Set default values for pagination
        int page = (page_raw != null) ? Integer.parseInt(page_raw) : 1;
        int numDis = (numDis_raw != null) ? Integer.parseInt(numDis_raw) : 10;
        int stt = (page - 1) * numDis;
        LocalDate start = (start_raw != null && start_raw != "") ? LocalDate.parse(start_raw) : null;
        LocalDate end = (end_raw != null && end_raw != "") ? LocalDate.parse(end_raw) : null;

        // Pass filters to DAO for retrieval and filtering
        List<Request> listReq = actRequest.getRequestByStatusAndSearchPagination(search, page, numDis, status, start, end);

        // Calculate number of pages based on filtered list size
        int numMent = actRequest.getCountRequestByStatusAndSearch(search, status, start, end);
        int numOfPage = (numMent % numDis == 0 ? numMent / numDis : (numMent / numDis + 1));

        // Set attributes for use in JSP
        request.setAttribute("stt", stt);
        request.setAttribute("search", search);
        request.setAttribute("status", status);
        request.setAttribute("numOfPage", numOfPage);
        request.setAttribute("indexPage", page);
        request.setAttribute("numDis", numDis);
        request.setAttribute("listReq", listReq);
        request.setAttribute("start", start);
        request.setAttribute("end", end);

        // Retrieve mentee names and statuses to display alongside filtered data
        String[] listNameMentee = getRequestMenteeName(listReq);
        request.setAttribute("listName", listNameMentee);
        String[] listStatus = actRequest.getAllStatusInRequest();
        request.setAttribute("listStatus", listStatus);

        // Forward to JSP
        request.getRequestDispatcher("/Admin/searchRequestAdmin.jsp").forward(request, response);
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
