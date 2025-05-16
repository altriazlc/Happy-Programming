/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;


/**
 *
 * @author nhhag
 */
public class MentorRating {
    private Mentor mentor;
    private int rating;
    private int rate;
    private CV cv;
    private int numReq;
    private List<Skill> skills;

    public MentorRating(Mentor mentor, int rating, CV cv,int rate, int numReq, List<Skill> skills) {
        this.mentor = mentor;
        this.rating = rating;
        this.rate = rate;
        this.cv = cv;
        this.numReq = numReq;
        this.skills = skills;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

   

    public Mentor getMentor() {
        return mentor;
    }

    public int getRating() {
        return rating;
    }

    public CV getCv() {
        return cv;
    }

    public int getNumReq() {
        return numReq;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }

    public void setNumReq(int numReq) {
        this.numReq = numReq;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
    
    
}
