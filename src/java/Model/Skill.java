/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nhhag
 */
import java.util.Base64;
import java.util.Date;

public class Skill {

    private int skillId;
    private String skillName;
    private Date createDate;
    private String description;
    private String status;
    private byte[] img;
    private transient String base64ImageFile;
    private int rating;

    // Constructors
    public Skill() {
    }

    public Skill(int skillId, String skillName, Date createDate,String description,byte[] img,int rating) {
        this.skillId = skillId;
        this.skillName = skillName;
        this.createDate = createDate;
        this.description = description;
        this.img = img;
        this.base64ImageFile = Base64.getEncoder().encodeToString(img);
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

 

    
    
    public Skill(int skillId, String skillName, Date createDate, String description, String status, byte[] img) {
        this.skillId = skillId;
        this.skillName = skillName;
        this.createDate = createDate;
        this.description = description;
        this.status = status;
        this.img = img;
        this.base64ImageFile = Base64.getEncoder().encodeToString(img);
    }

    public Skill(int skillId, String skillName) {
        this.skillId = skillId;
        this.skillName = skillName;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
        this.base64ImageFile = Base64.getEncoder().encodeToString(img);
    }

    public String getBase64ImageFile() {
        return base64ImageFile;
    }

    public void setBase64ImageFile(String base64ImageFile) {
        this.base64ImageFile = base64ImageFile;
    }
    public void setBase64ImageFile(byte[] img) {
        this.base64ImageFile = Base64.getEncoder().encodeToString(img);
    }
    

}
