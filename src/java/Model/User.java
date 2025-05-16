
package Model;

import java.util.Date;

public class User {
    private int roleId;
    private String username;
    private String status;
    private Date createDate;
    private String email;
    private String password;

    // Getters and Setters
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Optionally, override toString() for easier debugging
    @Override
    public String toString() {
        return "User [roleId=" + roleId + ", username=" + username + ", status=" + status
                + ", createDate=" + createDate + ", email=" + email + ", password=" + password + "]";
    }
}
