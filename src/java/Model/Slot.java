/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalTime;

/**
 *
 * @author nhhag
 */
public class Slot {
    private int slotID;
    private int mentorID;
    private java.time.LocalTime startTime;
    private java.time.LocalTime endTime;
    private String dayInWeek;
    private String status;
    private int CVID;

    public Slot() {
    }

    // Constructor
    public Slot(int slotID, int mentorID, java.time.LocalTime startTime, java.time.LocalTime endTime, String dayInWeek, String status) {
        this.slotID = slotID;
        this.mentorID = mentorID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayInWeek = dayInWeek;
        this.status = status;
    }

    public Slot(int slotID, int mentorID, LocalTime startTime, LocalTime endTime, String dayInWeek, String status, int CVID) {
        this.slotID = slotID;
        this.mentorID = mentorID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayInWeek = dayInWeek;
        this.status = status;
        this.CVID = CVID;
    }

    public int getCVID() {
        return CVID;
    }

    public void setCVID(int CVID) {
        this.CVID = CVID;
    }

    // Getters and Setters
    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public int getMentorID() {
        return mentorID;
    }

    public void setMentorID(int mentorID) {
        this.mentorID = mentorID;
    }

    public java.time.LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(java.time.LocalTime startTime) {
        this.startTime = startTime;
    }

    public java.time.LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(java.time.LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getDayInWeek() {
        return dayInWeek;
    }

    public void setDayInWeek(String dayInWeek) {
        this.dayInWeek = dayInWeek;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Optional: Override toString() for better readability
    @Override
    public String toString() {
        return "Slot{" +
                "slotID=" + slotID +
                ", mentorID=" + mentorID +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", dayInWeek='" + dayInWeek + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
