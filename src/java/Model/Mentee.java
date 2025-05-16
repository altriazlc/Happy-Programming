
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Base64;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Mentee {
    private int menteeId;
    private int roleId;
    private byte [] avatar;
    private String username;
    private Date createDate;
    private String email;
    private String phone;
    private String address;
    private Date dateOfBirth;
    private String fullName;
    private String gender;
    private String status;
    private transient String base64FileImage;

    public Mentee() {
    }

    @Override
    public String toString() {
        return "Mentee{" + "menteeId=" + menteeId + ", roleId=" + roleId + ", avatar=" + avatar + ", username=" + username + ", createDate=" + createDate + ", email=" + email + ", phone=" + phone + ", address=" + address + ", dateOfBirth=" + dateOfBirth + ", fullName=" + fullName + ", gender=" + gender + ", status=" + status + ", base64FileImage=" + base64FileImage + '}';
    }

    public Mentee(int menteeId, int roleId, byte[] avatar, String username, Date createDate, String phone, String address, Date dateOfBirth, String fullName, String gender, String status) {
        this.menteeId = menteeId;
        this.roleId = roleId;
        this.avatar = avatar;
        this.username = username;
        this.createDate = createDate;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.fullName = fullName;
        this.gender = gender;
        this.status = status;
        if (avatar != null) {
            this.base64FileImage = Base64.getEncoder().encodeToString(avatar);
        } else {
            this.base64FileImage = null;
        }
        
    }

    public Mentee(int menteeId, int roleId, byte[] avatar, String username, Date createDate, String email, String phone, String address, Date dateOfBirth, String fullName, String gender, String status) {
        this.menteeId = menteeId;
        this.roleId = roleId;
        this.avatar = avatar;
        this.username = username;
        this.createDate = createDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.fullName = fullName;
        this.gender = gender;
        this.status = status;
        this.base64FileImage = Base64.getEncoder().encodeToString(avatar);
    }

    public int getMenteeId() {
        return menteeId;
    }

    public void setMenteeId(int menteeId) {
        this.menteeId = menteeId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
        if (avatar != null) {
        this.base64FileImage = Base64.getEncoder().encodeToString(avatar);
    }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBase64FileImage() {
        return base64FileImage;
    }

    public void setBase64FileImage(String base64FileImage) {
        this.base64FileImage = base64FileImage;
    }

   
    
}
