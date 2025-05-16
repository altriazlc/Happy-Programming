/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author ADMIN
 */
public class SlotRequest {
    private int slotID;
    private java.time.LocalTime startTime;
    private java.time.LocalTime endTime;
    private String dayInWeek;
    private int requestId;
    private int mentorId;
    private int menteeId;
    private float price;
    private String status;
    private String title;
    private String framework;
    private LocalDate startDate;
    private LocalDate endDate;

    public SlotRequest() {
    }

    public SlotRequest(int slotID, LocalTime startTime, LocalTime endTime, String dayInWeek, int requestId, int mentorId, int menteeId, float price, String status, String title, String framework, LocalDate startDate, LocalDate endDate) {
        this.slotID = slotID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayInWeek = dayInWeek;
        this.requestId = requestId;
        this.mentorId = mentorId;
        this.menteeId = menteeId;
        this.price = price;
        this.status = status;
        this.title = title;
        this.framework = framework;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getDayInWeek() {
        return dayInWeek;
    }

    public void setDayInWeek(String dayInWeek) {
        this.dayInWeek = dayInWeek;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    
    
}

