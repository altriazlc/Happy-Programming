/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Rate {
    private int mentorId,menteeId;
    private Date createDate;
    private String status,comment;
    private int rate;
    private int requestId, rateId;

    public Rate() {
    }

    public Rate(int mentorId, int menteeId, Date createDate, String Status, String comment, int rate) {
        this.mentorId = mentorId;
        this.menteeId = menteeId;
        this.createDate = createDate;
        this.status = Status;
        this.comment = comment;
        this.rate = rate;
    }

    public Rate(int mentorId, int menteeId, Date createDate, String status, String comment, int rate, int requestId, int rateId) {
        this.mentorId = mentorId;
        this.menteeId = menteeId;
        this.createDate = createDate;
        this.status = status;
        this.comment = comment;
        this.rate = rate;
        this.requestId = requestId;
        this.rateId = rateId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public int getMenteeId() {
        return menteeId;
    }

    public void setMenteeId(int menteeId) {
        this.menteeId = menteeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rate{" + "mentorId=" + mentorId + ", menteeId=" + menteeId + ", createDate=" + createDate + ", status=" + status + ", comment=" + comment + ", rate=" + rate + '}';
    }
    
}
