/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Manager;

import DAO.CVDAO;

import Model.Mentee;
import Model.Mentor;
import Model.Payment;
import Model.Request;
import Model.User;
import Model.Wallet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ManagerRequestCatebyRequest", urlPatterns = {"/paymentmanagercate"})
public class ManagerRequestCatebyRequest extends HttpServlet {

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
            out.println("<title>Servlet ManagerRequestCatebyRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerRequestCatebyRequest at " + request.getContextPath() + "</h1>");
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
        String requestId_raw = request.getParameter("id");
        String error = request.getParameter("error");
        String mess = request.getParameter("mess");
        
        
        int requestId;
        try {
            requestId = Integer.parseInt(requestId_raw);
            CVDAO cvd = new CVDAO();
            List<Request> requestList = cvd.getListofRequestbyStaus();
            List<Mentee> menteeList = cvd.getListofMentee();
            List<Payment> paymentList1 = cvd.getListofPaymentbyRequestIdStep1(requestId);
            List<Payment> paymentListStatus4 = cvd.getListofPaymentbyRequestIdStep2(requestId);
            List<Payment> paymentList = cvd.getListofPaymentbyRequestId(requestId);
            List<Mentor> mentorList = cvd.getListofMentor();
            List<User> listUser = cvd.getListofUser();
            List<Request> completeRequestList = cvd.getListofCompleteRequest();
            Wallet wallet =cvd.getManagerWallet();
            request.setAttribute("requestList", requestList);
            request.setAttribute("menteeList", menteeList);
            request.setAttribute("mentorList", mentorList);
            request.setAttribute("listUser", listUser);
            request.setAttribute("completeRequestList", completeRequestList);

            request.setAttribute("paymentList1", paymentList1);
            request.setAttribute("lastStatusPayment", paymentListStatus4);
            
            
            request.setAttribute("wallet", wallet);
            request.setAttribute("error", error);
            request.setAttribute("mess", mess);
            
            
            System.out.println(paymentListStatus4.isEmpty());

            request.getRequestDispatcher("managerPayment.jsp").forward(request, response);
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
