/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nhhag
 */
public class SkillList {

    private int id;
    private int skillId;
    private int mentorId;
    private int rating;
    private int cvId;
    

    public SkillList() {
    }

    public SkillList(int id, int skillId, int mentorId, int rating) {
        this.id = id;
        this.skillId = skillId;
        this.mentorId = mentorId;
        this.rating = rating;
    }

    public SkillList(int id, int skillId, int mentorId, int rating, int cvId) {
        this.id = id;
        this.skillId = skillId;
        this.mentorId = mentorId;
        this.rating = rating;
        this.cvId = cvId;
    }

    public int getCvId() {
        return cvId;
    }

    public void setCvId(int cvId) {
        this.cvId = cvId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    
    
}
