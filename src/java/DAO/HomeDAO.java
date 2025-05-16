/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.CV;
import Model.Mentee;
import Model.Mentor;
import Model.Skill;
import Model.User;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class HomeDAO extends DBContext {

    //get all users
    public void delete(int id) {
        String sql = "delete from Users where UserID =? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int countMentor() {
        String sql = "SELECT COUNT(*) FROM [dbo].[User] where [dbo].[User].RoleID=1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                //System.out.println(rs.getInt(""));
                return rs.getInt("");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int countUsers() {
        String sql = "SELECT COUNT(*) FROM [dbo].[User]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                //System.out.println(rs.getInt(""));
                return rs.getInt("");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public CV getCVbyMentor(int id) {
        //lenh sql select * from categories cach 1:
        String sql = "select*from [dbo].[CV] where MentorID =?;";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public void updateCV(CV c) {
        String sql = "UPDATE [dbo].[CV]\n"
                + "   SET [Education] = ?\n"
                + "      ,[Experience] = ?\n"
                + "      ,[Activity] = ?\n"
                + "      ,[ProfessionIntroduction] = ?\n"
                + "      ,[JobProfession] = ?\n"
                + "      ,[YearOfExperience] = ?\n"
                + "      ,[ServiceDescription] = ?\n"
                + "      ,[Framework] = ?\n"
                + " WHERE [MentorID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getEducation());
            st.setString(2, c.getExperience());
            st.setString(3, c.getActivity());
            st.setString(4, c.getProfessionIntroduction());
            st.setString(5, c.getJobProfession());
            st.setInt(6, c.getYearOfExperience());
            st.setString(7, c.getServiceDescription());
            st.setString(8, c.getFramework());
            st.setInt(9, c.getMentorId());
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public List<Skill> getListofSkill() {
        List<Skill> list = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = """
                     select top 4 s.SkillID,s.SkillName,s.CreateDate,s.Description,s.Img,Avg(Rating)[Rating]
                     from Skill s join SkillList sl on s.SkillID=sl.SkillID
                     				where s.Status='active'
                     				group by s.SkillID,s.SkillName,s.CreateDate,s.Img,s.Description
                     				order by Rating desc;
                     """;
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Skill nSkill = new Skill(rs.getInt("skillId"),
                        rs.getString("skillName"), rs.getDate("createDate"), rs.getString("description"),
                        rs.getBytes("img"), rs.getInt("rating"));
                list.add(nSkill);

            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Skill> getListofSkillNewest() {
        List<Skill> list = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "SELECT TOP 4 \n"
                + "    s.SkillID, \n"
                + "    s.SkillName, \n"
                + "    s.CreateDate, \n"
                + "    s.Description, \n"
                + "    s.Img, \n"
                + "    AVG(sl.Rating) AS [Rating]\n"
                + "FROM \n"
                + "    Skill s \n"
                + "JOIN \n"
                + "    SkillList sl ON s.SkillID = sl.SkillID\n"
                + "WHERE \n"
                + "    s.Status = 'active'\n"
                + "GROUP BY \n"
                + "    s.SkillID, \n"
                + "    s.SkillName, \n"
                + "    s.CreateDate, \n"
                + "    s.Description, \n"
                + "    s.Img\n"
                + "ORDER BY \n"
                + "   s.CreateDate DESC;";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Skill nSkill = new Skill(rs.getInt("skillId"),
                        rs.getString("skillName"), rs.getDate("createDate"), rs.getString("description"),
                        rs.getBytes("img"), rs.getInt("rating"));
                list.add(nSkill);

            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Skill> getListofSkillMostLearn() {
        List<Skill> list = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "SELECT TOP 4 \n"
                + "    s.SkillID, \n"
                + "    s.SkillName, \n"
                + "    s.CreateDate, \n"
                + "    s.Description, \n"
                + "    s.Img, \n"
                + "    AVG(sl.Rating) AS [Rating]\n"
                + "FROM \n"
                + "    Skill s \n"
                + "JOIN \n"
                + "    SkillList sl ON s.SkillID = sl.SkillID\n"
                + "Join\n"
                + "	Request r on r.SkillID=s.SkillID\n"
                + "WHERE \n"
                + "    s.Status = 'active'\n"
                + "GROUP BY \n"
                + "    s.SkillID, \n"
                + "    s.SkillName, \n"
                + "    s.CreateDate, \n"
                + "    s.Description, \n"
                + "    s.Img\n"
                + "ORDER BY \n"
                + "   avg(r.SkillID) DESC;";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Skill nSkill = new Skill(rs.getInt("skillId"),
                        rs.getString("skillName"), rs.getDate("createDate"), rs.getString("description"),
                        rs.getBytes("img"), rs.getInt("rating"));
                list.add(nSkill);

            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public float getRateAve() {
        String sql = "SELECT AVG(CAST(Rate AS FLOAT)) FROM Rate;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                //System.out.println(rs.getInt(""));
                return rs.getFloat("");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int skillCount() {
        String sql = "select count(SkillID) from Skill";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                //System.out.println(rs.getInt(""));
                return rs.getInt("");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int countMentee() {
        String sql = "SELECT COUNT(*) FROM [dbo].[User] where [dbo].[User].RoleID=2";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                //System.out.println(rs.getInt(""));
                return rs.getInt("");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int countRequest() {
        String sql = "select count(RequestID) from Request";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                //System.out.println(rs.getInt(""));
                return rs.getInt("");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Mentor> getListofMentor() {
        List<Mentor> list = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select*from Mentor";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Mentor mentor = new Mentor(rs.getInt("mentorId"), rs.getInt("roleId"),
                        rs.getString("username"),
                        rs.getDate("createDate"),
                        rs.getString("phone"), rs.getString("address"),
                        rs.getDate("dateOfBirth"), rs.getString("fullName"),
                        rs.getString("gender"), rs.getString("status"));
                list.add(mentor);

            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Mentee> getListofMentee() {
        List<Mentee> list = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select*from Mentee";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Mentee mentee = new Mentee(rs.getInt("menteeId"), rs.getInt("roleId"),
                        rs.getBytes("avatar"), rs.getString("username"),
                        rs.getDate("createDate"), rs.getString("phone"), rs.getString("address"),
                        rs.getDate("dateOfBirth"), rs.getString("fullName"),
                        rs.getString("gender"), rs.getString("status"));

                list.add(mentee);

            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }
}
