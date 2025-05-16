
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author tuong
 */
public class Attendance {
    private int attendID, requestID, MentorID, MenteeID,requestSlotItem;
    private String status, title,dayInWeek;
    private java.time.LocalTime startTime;
    private java.time.LocalTime endTime;
    private LocalDate date;

    public Attendance() {
    }

    public Attendance(int attendID, int requestSlotItem, String status, LocalDate date) {
        this.attendID = attendID;
        this.requestSlotItem = requestSlotItem;
        this.status = status;
        this.date = date;
    }

    public int getAttendID() {
        return attendID;
    }

    public void setAttendID(int attendID) {
        this.attendID = attendID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getMentorID() {
        return MentorID;
    }

    public void setMentorID(int MentorID) {
        this.MentorID = MentorID;
    }

    public int getMenteeID() {
        return MenteeID;
    }

    public void setMenteeID(int MenteeID) {
        this.MenteeID = MenteeID;
    }

    public int getRequestSlotItem() {
        return requestSlotItem;
    }

    public void setRequestSlotItem(int requestSlotItem) {
        this.requestSlotItem = requestSlotItem;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDayInWeek() {
        return dayInWeek;
    }

    public void setDayInWeek(String dayInWeek) {
        this.dayInWeek = dayInWeek;
    }

}
