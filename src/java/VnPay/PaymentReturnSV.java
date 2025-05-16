package VnPay;

import DAO.PaymentDAO;
import DAO.RequestDAO;
import Model.Wallet;
import DAO.WalletDAO;
import Model.Payment;
import Model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/vnpay-return")
public class PaymentReturnSV extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set the response type
        response.setContentType("text/html;charset=UTF-8");

        // Create a map to store the fields from the request
        Map<String, String> fields = new HashMap<>();

        // Extract parameters from the request
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String fieldValue = URLEncoder.encode(request.getParameter(paramName), StandardCharsets.US_ASCII.toString());
            fields.put(paramName, fieldValue);
        }

        // Get the secure hash and remove it from the fields
        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        fields.remove("vnp_SecureHashType");
        fields.remove("vnp_SecureHash");

        // Create the sign value using the configuration method
        String signValue = Config.hashAllFields(fields);

        // Compare the secure hash and sign value to verify the transaction integrity
        if (signValue.equals(vnp_SecureHash)) {
            String transactionStatus = request.getParameter("vnp_TransactionStatus");

            // Retrieve the username from session
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("acc");

            if (user != null && user.getUsername() != null) {
                // Get the transaction amount (convert from cents to VND)
                double amount = Double.parseDouble(request.getParameter("vnp_Amount")) / 100;

                // Call the WalletDAO to get the wallet information
                WalletDAO walletDAO = new WalletDAO();
                Wallet wallet = walletDAO.getWalletByUsername(user.getUsername());

                if (wallet != null && "00".equals(transactionStatus)) {
                    // Update the wallet balance
                    double newBalance = wallet.getBalance() + amount;
                    walletDAO.updateWalletBalanceByUsername(user.getUsername(), newBalance);
                    // Create payment to save history
                    PaymentDAO paymentDAO = new PaymentDAO();
                    Payment samplePayment = new Payment();
                    samplePayment.setPaymentDate(LocalDateTime.now());
                    samplePayment.setSender(user.getUsername());
                    samplePayment.setReceiver("admin");
                    samplePayment.setStatus("0");
                    samplePayment.setTotalAmount(amount);
                    paymentDAO.addPayments(samplePayment);
                    // Set attributes to forward to the JSP
                    request.setAttribute("newBalance", newBalance);
                    request.setAttribute("vnp_TxnRef", request.getParameter("vnp_TxnRef"));
                    request.setAttribute("amount", amount);
                    request.setAttribute("vnp_OrderInfo", request.getParameter("vnp_OrderInfo"));
                    request.setAttribute("vnp_ResponseCode", request.getParameter("vnp_ResponseCode"));
                    request.setAttribute("vnp_TransactionNo", request.getParameter("vnp_TransactionNo"));
                    request.setAttribute("vnp_BankCode", request.getParameter("vnp_BankCode"));
                    request.setAttribute("vnp_PayDate", request.getParameter("vnp_PayDate"));
                    request.setAttribute("status", "success");
                } else {
                    // If the wallet is not found or transaction failed
                    request.setAttribute("status", "failed");
                    request.setAttribute("message", "Transaction failed or wallet not found.");
                }
            } else {
                // Handle session or user not found
                request.setAttribute("status", "error");
                request.setAttribute("message", "Session expired or user not logged in.");
            }
        } else {
            // Handle signature mismatch
            request.setAttribute("status", "error");
            request.setAttribute("message", "Signature mismatch!");
        }

        // Forward the request to the JSP page
        request.getRequestDispatcher("vnpay_return.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Optionally handle GET requests if needed
        doPost(request, response);
    }
}
