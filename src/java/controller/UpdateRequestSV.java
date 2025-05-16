/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CVDAO;
import DAO.MenteeDAO;
import DAO.MentorDAO;
import DAO.PaymentDAO;
import DAO.RequestDAO;
import DAO.SlotDAO;
import Model.Wallet;
import DAO.WalletDAO;
import Model.CV;
import Model.Mentee;
import Model.Request;
import Model.RequestSlotItem;
import Model.Skill;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author nhhag
 */
@WebServlet(name = "UpdateRequestSV", urlPatterns = {"/updateRequest"})
public class UpdateRequestSV extends HttpServlet {

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
            out.println("<title>Servlet UpdateRequestSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateRequestSV at " + request.getContextPath() + "</h1>");
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
        RequestDAO requestDAO = new RequestDAO();
        WalletDAO walletDAO = new WalletDAO();
        MenteeDAO menteeDAO = new MenteeDAO();
        a = (User) sesion.getAttribute("acc");
        if (a == null) {
            response.sendRedirect("signin");
        }
        PrintWriter out = response.getWriter();
        String id_raw = request.getParameter("id");
        String error = request.getParameter("error");
        String notify = request.getParameter("notify");
        String pay = request.getParameter("pay");

        int id;

        try {
            id = Integer.parseInt(id_raw);
            Mentee mentee = menteeDAO.findMenteeByUsername(a.getUsername());
            Request requests = requestDAO.getRequestByID(id);          
            List<Slot> slotList = slotDAO.getSlotsByMentorId(requests.getMentorId());
            List<RequestSlotItem> checkedSlot = requestDAO.getSlotByRequestID(id);
            CV cv = cvd.getCVbyMentorId(requests.getMentorId());
            List<Skill> list1 = cvd.getMentorSkillListByCVID(cv.getCvId());
            List<RequestSlotItem> listSlot = requestDAO.getDuplicateSlotByRequestID(mentee.getMenteeId(), requests.getMentorId(), requests.getRequestId());     
            request.setAttribute("selectedSlot", listSlot);
            request.setAttribute("cv", cv);
            request.setAttribute("mid", id);
            request.setAttribute("request", requests);
            request.setAttribute("skillList", list1);
            request.setAttribute("error", error);
            request.setAttribute("pay", pay);
            request.setAttribute("notify", notify);
            request.setAttribute("wallet", walletDAO.getWalletByUsername(a.getUsername()));
            request.setAttribute("slotList", slotList);
            request.setAttribute("checkedSlot", checkedSlot);
            request.setAttribute("mentor", mentorDAO.findMentorByID(requests.getMentorId()));
            request.getRequestDispatcher("updateRequest.jsp").forward(request, response);
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
        PrintWriter out = response.getWriter();
        RequestDAO requestDAO = new RequestDAO();
        MenteeDAO menteeDAO = new MenteeDAO();
        CVDAO cvDAO = new CVDAO();
        SlotDAO slotDAO = new SlotDAO();
        PaymentDAO paymentDAO = new PaymentDAO();
        WalletDAO walletDAO = new WalletDAO();

        HttpSession sesion = request.getSession();
        User a = new User();
        a = (User) sesion.getAttribute("acc");
        int menteeid = menteeDAO.findMenteeByUsername(a.getUsername()).getMenteeId();

        String id_raw = request.getParameter("id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String end = request.getParameter("end");
        String start = request.getParameter("start");
        String total = request.getParameter("totalPrice");
        String framework = request.getParameter("framework");
        String selectedSkills = request.getParameter("addSkills");
        String[] selectedSlot;
        if (request.getParameterValues("addSlot") == null || request.getParameterValues("addSlot").length == 0) {
            response.sendRedirect("updateRequest?id=" + id_raw + "&error=You can't upadate request without slot");
            return;
        }
        selectedSlot = request.getParameterValues("addSlot");

        try {
            int id = Integer.parseInt(id_raw);
            int skill = Integer.parseInt(selectedSkills);
            float totalP = Float.parseFloat(total);
            Request requests = requestDAO.getRequestByID(id);

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            LocalDate selectedStartDate = LocalDate.parse(start, dateFormatter);
            LocalDate selectedEndDate = LocalDate.parse(end, dateFormatter);

            LocalDateTime now = LocalDateTime.now();
            LocalDate creaDate = LocalDate.now();
             if (selectedStartDate.isBefore(creaDate)) {
                response.sendRedirect("updateRequest?id=" + id + "&error=Start date cannot be earlier than current time");
                return;
            }


            if (selectedEndDate.isBefore(selectedStartDate)) {
                response.sendRedirect("updateRequest?id=" + id + "&error=End date cannot be earlier than start date");
                return;
            }

           
             if (totalP == 0) {
                response.sendRedirect("updateRequest?id=" + id + "&error=You request must have at least 1 slot");
                return;
            } 
             
            Wallet wallet = walletDAO.getWalletByUsername(a.getUsername());
            if (wallet == null || wallet.getBalance() < totalP) {
                response.sendRedirect("updateRequest?id=" + id + "&error=Your account doesn't have enough money&pay=oke");
                return;
            }

            if (wallet.getBalance() < (totalP + wallet.getHold())) {
                response.sendRedirect("updateRequest?id=" + id + "&pay=Your account doesn't have enough money to created more request&pay=oke");
                return;
            }
            
            Request newRequest = new Request(requests.getRequestId(), requests.getMentorId(), requests.getMenteeId(), totalP,
                    content, creaDate, "Open", title, framework, selectedStartDate, selectedEndDate, skill);
            requestDAO.updateRequest(newRequest);
            walletDAO.updateHoldByUsername(a.getUsername(), wallet.getHold() - requests.getPrice() + totalP);
            paymentDAO.updatePaymentAmount(id, totalP);
            requestDAO.updateItemsByRequestID(id, selectedSlot);
            response.sendRedirect("updateRequest?id=" + id + "&notify=Update request succesfully");

        } catch (Exception e) {
            response.sendRedirect("updateRequest?id=" + id_raw + "&error=An error occured during update request");
        }

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
