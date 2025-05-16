/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author asus
 */
import java.time.LocalDate;
import java.time.LocalTime;

public class RequestSlotData {
    private int slotId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String dayInWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public RequestSlotData(int slotId, LocalDate startDate, LocalDate endDate, 
                           String dayInWeek, LocalTime startTime, LocalTime endTime) {
        this.slotId = slotId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dayInWeek = dayInWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public int getSlotId() {
        return slotId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getDayInWeek() {
        return dayInWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
