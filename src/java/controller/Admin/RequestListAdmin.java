package controller.Admin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuong
 */
@WebServlet(urlPatterns = {"/requestListAdmin"})
public class RequestListAdmin extends HttpServlet {

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
            out.println("<title>Servlet RequestListAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequestListAdmin at " + request.getContextPath() + "</h1>");
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
        MenteeDAO actMentee = new MenteeDAO();
        RequestDAO actRequest = new RequestDAO();
        List<Request> listReq = actRequest.getAllRequest();
        String page_raw = request.getParameter("page");
        String numDis_raw = request.getParameter("numDis");
        int page, numDis;
        if (page_raw != null) {
            page = Integer.parseInt(page_raw);
        } else {
            page = 1;
        }
        if (numDis_raw != null) {
            numDis = Integer.parseInt(numDis_raw);
        } else {
            numDis = 10;
        }
        int stt = (page - 1) * numDis;
        request.setAttribute("stt", stt);
        int numMent = listReq.size();
        int numOfPage = (numMent % numDis == 0 ? numMent / numDis : (numMent / numDis + 1));
        request.setAttribute("numOfPage", numOfPage);
        listReq = actRequest.getAllRequestPagination(page, numDis);
        request.setAttribute("indexPage", page);
        request.setAttribute("numDis", numDis);
        request.setAttribute("listReq", listReq);
        String[] listNameMentee = getRequestMenteeName(listReq);
        request.setAttribute("listName", listNameMentee);
        String[] listStatus = actRequest.getAllStatusInRequest();
        request.setAttribute("listStatus", listStatus);
        request.getRequestDispatcher("/Admin/RequestListAdmin.jsp").forward(request, response);
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
        List<Request> listReq = actRequest.getAllRequest();
        String status = request.getParameter("status");
       
        if (status != null) {
            if (!status.equalsIgnoreCase("all")) {
                listReq = actRequest.getAllRequestByStatus(status);
                request.setAttribute("status", status);
            } else {
                response.sendRedirect("requestListAdmin");
                return;
            }
            
        }
        String page_raw = request.getParameter("page");
        String numDis_raw = request.getParameter("numDis");
        int page, numDis;
        if (page_raw != null) {
            page = Integer.parseInt(page_raw);
        } else {
            page = 1;
        }
        if (numDis_raw != null) {
            numDis = Integer.parseInt(numDis_raw);
        } else {
            numDis = 10;
        }
        int stt = (page - 1) * numDis;
        request.setAttribute("stt", stt);
        int numMent = listReq.size();
        int numOfPage = (numMent % numDis == 0 ? numMent / numDis : (numMent / numDis + 1));
        request.setAttribute("numOfPage", numOfPage);

        listReq = actRequest.getAllRequestPaginationByStatus(page, numDis, status);
        request.setAttribute("indexPage", page);
        request.setAttribute("numDis", numDis);
        request.setAttribute("listReq", listReq);
        String[] listNameMentee = getRequestMenteeName(listReq);
        request.setAttribute("listName", listNameMentee);
        String[] listStatus = actRequest.getAllStatusInRequest();
        request.setAttribute("listStatus", listStatus);
        request.getRequestDispatcher("/Admin/RequestListAdmin.jsp").forward(request, response);
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
