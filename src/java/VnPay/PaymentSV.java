/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package VnPay;

import DAO.CVDAO;
import DAO.MenteeDAO;
import DAO.MentorDAO;
import DAO.PaymentDAO;
import DAO.SlotDAO;
import DAO.WalletDAO;
import Model.User;
import Model.Mentee;
import Model.Payment;
import Model.Wallet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhhag
 */
@WebServlet(name = "PaymentSV", urlPatterns = {"/payment"})
public class PaymentSV extends HttpServlet {

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
            out.println("<title>Servlet PaymentSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentSV at " + request.getContextPath() + "</h1>");
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
        HttpSession sesion = request.getSession();
        User a = new User();
        CVDAO cvd = new CVDAO();
        SlotDAO slotDAO = new SlotDAO();
        MentorDAO mentorDAO = new MentorDAO();
        MenteeDAO menteeDAO = new MenteeDAO();
        WalletDAO walletDAO = new WalletDAO();
        PaymentDAO paymentDAO = new PaymentDAO();
        String page = request.getParameter("page");

        a = (User) sesion.getAttribute("acc");
        if (a == null) {
            response.sendRedirect("signin");
        }
        int currentPage = 1;
        if (page != null) {
            try {
                currentPage = Integer.parseInt(page);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }

        int totalItems, totalPages = 0;
        List<Payment> list = new ArrayList<>();
        if (a.getRoleId() == 2) {
            totalItems = paymentDAO.getAllPaymentsByMenteeUserName(a.getUsername()).size();
            totalPages = totalItems / 9;
            if (totalItems % 9 != 0) {
                totalPages++;
            }
            list = paymentDAO.getAllPaymentsByMenteeUserNamePagnition(a.getUsername(), currentPage, 9);
        } else {
            totalItems = paymentDAO.getAllPaymentsByMentorUserName(a.getUsername()).size();
            totalPages = totalItems / 9;
            if (totalItems % 9 != 0) {
                totalPages++;
            }
            list = paymentDAO.getAllPaymentsByMentorUserNamePagnition(a.getUsername(), currentPage, 9);
        }
        Mentee mentee = menteeDAO.findMenteeByUsername(a.getUsername());
        Wallet wallet = walletDAO.getWalletByUsername(a.getUsername());

        request.setAttribute("list", list);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("mentee", mentee);
        request.setAttribute("wallet", wallet);
        request.getRequestDispatcher("vnpay_pay.jsp").forward(request, response);
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
