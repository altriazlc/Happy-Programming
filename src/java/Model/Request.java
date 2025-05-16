/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nhhag
 */
import java.time.LocalDate;
import java.time.LocalTime;

public class Request {

    private int requestId;
    private int mentorId;
    private int menteeId;
    private float price;
    private String note;
    private LocalDate createDate;
    private String status;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String framework;
    private double attendancePercentage;
    private int skillId;


    public Request() {
    }

    public Request(int requestId, int mentorId, int menteeId, float price, String note, LocalDate createDate,
            String status, String title, String framework, LocalDate startDate, LocalDate endDate, int skillId) {
        this.requestId = requestId;
        this.mentorId = mentorId;
        this.menteeId = menteeId;
        this.price = price;
        this.note = note;
        this.createDate = createDate;
        this.status = status;
        this.title = title;
        this.framework = framework;
        this.startDate = startDate;
        this.endDate = endDate;
        this.skillId = skillId;
    }

    public Request(int requestId, int mentorId, int menteeId, float price, String note, LocalDate createDate, String status, String title, LocalDate startDate, LocalDate endDate, String framework, double attendancePercentage, int skillId) {
        this.requestId = requestId;
        this.mentorId = mentorId;
        this.menteeId = menteeId;
        this.price = price;
        this.note = note;
        this.createDate = createDate;
        this.status = status;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.framework = framework;
        this.attendancePercentage = attendancePercentage;
        this.skillId = skillId;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    @Override
    public String toString() {
        return "Request{" + "requestId=" + requestId + ", mentorId=" + mentorId + ", menteeId=" + menteeId + ", price=" + price + ", note=" + note + ", createDate=" + createDate + ", status=" + status + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate + ", framework=" + framework + ", skillId=" + skillId + '}';
    }

    public int getSkillId() {
        return skillId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
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
}
