/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.CV;
import Model.Mentor;
import Model.SkillList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

/**
 *
 * @author nhhag
 */
public class SkillListDAO extends DBContext {

    public List<Mentor> getMentorBySkill(int id) {
        List<Mentor> list = new ArrayList<>();
        String sql = "SELECT DISTINCT m.* \n"
                + "FROM mentor m\n"
                + "JOIN SkillList s ON m.MentorID = s.MentorID\n"
                + "LEFT JOIN CV cv ON s.CVID = cv.CVID\n"
                + "WHERE s.SkillID = ? \n"
                + "  AND m.Status = 'active'\n"
                + "  AND (cv.Status = 'active');";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Mentor mentor = new Mentor();
                mentor.setMentorId(rs.getInt("MentorID"));
                mentor.setRoleId(rs.getInt("RoleID"));
                mentor.setUsername(rs.getString("Username"));
                mentor.setCreateDate(rs.getDate("CreateDate"));
                mentor.setPhone(rs.getString("Phone"));
                mentor.setAddress(rs.getString("Address"));
                mentor.setDateOfBirth(rs.getDate("DateOfBirth"));
                mentor.setFullName(rs.getString("FullName"));
                mentor.setGender(rs.getString("Gender"));
                mentor.setStatus(rs.getString("Status"));
                list.add(mentor);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<CV> getCVbySkill(int id) {
        List<CV> list = new ArrayList<>();
        String sql = "select DISTINCT CV.* \n"
                + "from CV \n"
                + "join SkillList s on CV.MentorID = s.MentorID \n"
                + "join Mentor m on cv.MentorID = m.MentorID\n"
                + "where s.SkillID =? and cv.Status like 'active'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CV cv = new CV(rs.getInt("cvId"), rs.getInt("mentorId"),
                        rs.getString("education"), rs.getString("experience"),
                        rs.getString("activity"), rs.getString("professionIntroduction"),
                        rs.getString("certificate"), rs.getDate("createDate"),
                        rs.getString("jobProfession"), rs.getInt("yearOfExperience"),
                        rs.getString("serviceDescription"), rs.getString("status"),
                        rs.getString("framework"), rs.getBytes("avatar"), rs.getFloat("Price"));
                list.add(cv);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Integer getCurrentRating(int skillId) {
        String query = """
                       SELECT *
                       FROM SkillList 
                       WHERE SkillID = ?
                       """;
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, skillId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Rating");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateRating(int skillId, int rating, int mentorId) {
        String updateQuery = "UPDATE SkillList SET Rating = ? WHERE SkillID = ? AND MentorID = ?";
        try (
                PreparedStatement ps = connection.prepareStatement(updateQuery)) {
            ps.setInt(1, rating);
            ps.setInt(2, skillId);
            ps.setInt(3, mentorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SkillListDAO s = new SkillListDAO();
        List<CV> list = s.getCVbySkill(1);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}
