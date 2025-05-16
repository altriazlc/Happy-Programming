/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nhhag
 */
import java.util.Date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payment {
    private int paymentId;
    private int requestId;
    private LocalDateTime paymentDate;
    private double totalAmount;
    private String status;
    private String sender;
    private String receiver;

    // No-argument constructor
    public Payment() {
    }

    // Parameterized constructor
    public Payment(int paymentId, int requestId, LocalDateTime paymentDate, double totalAmount, 
                   String status, String sender, String receiver) {
        this.paymentId = paymentId;
        this.requestId = requestId;
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.sender = sender;
        this.receiver = receiver;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    
    public String getFormattedPaymentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return paymentDate.format(formatter);
    }
    public String getFormattedPaymentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return paymentDate.format(formatter);
    }
    // Optional: toString method for debugging
    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", requestId=" + requestId +
                ", paymentDate=" + paymentDate +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
