/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Mentor;

import DAO.CVDAO;
import DAO.MenteeDAO;
import DAO.MentorDAO;
import DAO.PaymentDAO;
import DAO.RequestDAO;
import DAO.SlotDAO;
import DAO.WalletDAO;
import Model.Mentee;
import Model.Payment;
import Model.Request;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
@WebServlet(name = "UpdateStatusByMentorSV", urlPatterns = {"/updatestatusbymentor"})
public class UpdateStatusByMentorSV extends HttpServlet {

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
            out.println("<title>Servlet UpdateStatusByMentorSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateStatusByMentorSV at " + request.getContextPath() + "</h1>");
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

        int roleID = curUser.getRoleId();
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        RequestDAO requestDAO = new RequestDAO();
        SlotDAO slotDAO = new SlotDAO();
        PaymentDAO paymentDAO = new PaymentDAO();
        Request requests = requestDAO.getRequestByID(requestId);
        WalletDAO walletDAO = new WalletDAO();
        MenteeDAO menteeDAO = new MenteeDAO();

        try {
            if (roleID == 1) { // Mentor actions
                String action = request.getParameter("action");
                int slotId = requestDAO.getSlotIdByRequestId(requestId);
                //Payment payment = new Payment(1, requestId, LocalDateTime.now(), requests.getPrice(), "1", curUser.getUsername(), "manager");
                Mentee menteeName = menteeDAO.getMenteeByID(requests.getMenteeId());

                switch (action) {
                    case "accept":
                        //Buoc 1: Cap nhat trang thai accept
                        requestDAO.updateStatusByMentor(requestId, "Processing");
                        //Buoc 2: Tu choi tat ca Mentee requests cung slot
                        requestDAO.rejectOtherMenteesForSameSlots(requestId);
                        //Buoc 3: Cap nhat trang thai co lien quan toi request sang Unavailable
                        requestDAO.setSlotStatusbyRequestSlotItemUnavailable(requestId);
                        //Buoc 4: update payment
                        paymentDAO.updatePaymentStatus(requestId, "2");
                        //Buoc 5: Update Hold
                        walletDAO.updateHoldByUsername(menteeName.getUsername(), walletDAO.getWalletByUsername(menteeName.getUsername()).getHold() - requests.getPrice());
                        walletDAO.updateWalletAddMoneyBalanceByUsername(menteeName.getUsername(), -requests.getPrice());
                        walletDAO.updateWalletAddMoneyBalanceByUsername("manager", requests.getPrice());
                        //Update wallet the rejected
                        Map<String, Float> listRejected = walletDAO.getHold(requestId);
                        listRejected.forEach((username, holdAmount) -> {
                            System.out.println("Username: " + username + ", Hold Amount: " + holdAmount);
                            boolean updated = walletDAO.updateHoldRejected(username, holdAmount);
                            
                        });
                        response.sendRedirect("listrequestofmentor");
                        break;
                    case "reject":
                        //Buoc 1: cap nhat trang thai reject
                        requestDAO.updateStatusByMentor(requestId, "Reject");
                        //Buoc 2: Update Hold
                        walletDAO.updateHoldByUsername(menteeName.getUsername(), walletDAO.getWalletByUsername(menteeName.getUsername()).getHold() - requests.getPrice());
                        response.sendRedirect("listrequestofmentor");
                        break;
                    case "complete":
                        requestDAO.updateStatusByMentor(requestId, "MentorAccept");
                        requestDAO.setSlotStatusbyRequestSlotItemAvailable(requestId);
                        response.sendRedirect("listrequestofmentor");
                        break;
                    default:
                        response.sendRedirect("listrequestofmentor");
                        break;
                }
            } else if (roleID == 2) {
                response.sendRedirect("home");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("listrequestofmentor");
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
