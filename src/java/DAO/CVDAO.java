/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.CV;
import Model.Mentee;
import Model.Mentor;
import Model.Payment;
import Model.Rate;
import Model.Request;
import Model.Skill;
import Model.SkillList;
import Model.Slot;
import Model.SlotRequest;
import Model.StatisticSkills;
import Model.User;
import Model.Wallet;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class CVDAO extends DBContext {

    public Mentor getMentorByID(int id) {
        //lenh sql select * from categories cach 1:
        String sql = "select*from [dbo].[Mentor] where MentorID =?;";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Mentor mentor = new Mentor(rs.getInt("mentorId"), rs.getInt("roleId"),
                        rs.getString("username"),
                        rs.getDate("createDate"),
                        rs.getString("phone"), rs.getString("address"),
                        rs.getDate("dateOfBirth"), rs.getString("fullName"),
                        rs.getString("gender"), rs.getString("status"));
                return mentor;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public Mentor getMentorByUsername(String username) {
        //lenh sql select * from categories cach 1:
        String sql = "select * from Mentor where Username = ?";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Mentor mentor = new Mentor(rs.getInt("mentorId"), rs.getInt("roleId"),
                        rs.getString("username"),
                        rs.getDate("createDate"),
                        rs.getString("phone"), rs.getString("address"),
                        rs.getDate("dateOfBirth"), rs.getString("fullName"),
                        rs.getString("gender"), rs.getString("status"));
                //System.out.println("founded");
                return mentor;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public CV getCVbyMentorId(int id) {
        //lenh sql select * from categories cach 1:
        String sql = "select*from [dbo].[CV] where MentorID =? and Status='active';";
        //cach 2: vao sql phai chuot vao bang chon script"select*from [dbo].[CV] where MentorIDable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                CV cv = new CV(rs.getInt("cvId"), rs.getInt("mentorId"),
                        rs.getString("education"), rs.getString("experience"),
                        rs.getString("activity"), rs.getString("professionIntroduction"),
                        rs.getString("certificate"), rs.getDate("createDate"),
                        rs.getString("jobProfession"), rs.getInt("yearOfExperience"),
                        rs.getString("serviceDescription"), rs.getString("status"),
                        rs.getString("framework"), rs.getBytes("avatar"), rs.getFloat("price"));
                return cv;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public CV getNewestCVbyMentorId(int id) {
        //lenh sql select * from categories cach 1:
        String sql = "select top (1) * from [dbo].[CV] where MentorID =? order by CreateDate desc;";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                CV cv = new CV(rs.getInt("cvId"), rs.getInt("mentorId"),
                        rs.getString("education"), rs.getString("experience"),
                        rs.getString("activity"), rs.getString("professionIntroduction"),
                        rs.getString("certificate"), rs.getDate("createDate"),
                        rs.getString("jobProfession"), rs.getInt("yearOfExperience"),
                        rs.getString("serviceDescription"), rs.getString("status"),
                        rs.getString("framework"), rs.getBytes("avatar"), rs.getFloat("price"));
                return cv;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public CV getCVbyCVId(int id) {
        //lenh sql select * from categories cach 1:
        String sql = "select*from [dbo].[CV] where CVID =?;";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                CV cv = new CV(rs.getInt("cvId"), rs.getInt("mentorId"),
                        rs.getString("education"), rs.getString("experience"),
                        rs.getString("activity"), rs.getString("professionIntroduction"),
                        rs.getString("certificate"), rs.getDate("createDate"),
                        rs.getString("jobProfession"), rs.getInt("yearOfExperience"),
                        rs.getString("serviceDescription"), rs.getString("status"),
                        rs.getString("framework"), rs.getBytes("avatar"), rs.getFloat("price"));
                return cv;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public CV getCVActivebyMentorId(int id) {
        //lenh sql select * from categories cach 1:
        String sql = "select*from CV where MentorID=? and Status like 'active';";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                CV cv = new CV(rs.getInt("cvId"), rs.getInt("mentorId"),
                        rs.getString("education"), rs.getString("experience"),
                        rs.getString("activity"), rs.getString("professionIntroduction"),
                        rs.getString("certificate"), rs.getDate("createDate"),
                        rs.getString("jobProfession"), rs.getInt("yearOfExperience"),
                        rs.getString("serviceDescription"), rs.getString("status"),
                        rs.getString("framework"), rs.getBytes("avatar"), rs.getFloat("price"));
                return cv;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<CV> getListofCVbyMentorId(int id) {
        List<CV> cvList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select*from CV where MentorID=? order by CVID desc;";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                java.sql.Timestamp timestamp = rs.getTimestamp("createDate");
                CV cv = new CV(rs.getInt("cvId"),
                        rs.getInt("mentorId"), rs.getString("education"),
                        rs.getString("experience"), rs.getString("activity"),
                        rs.getString("professionIntroduction"),
                        rs.getString("certificate"),
                        rs.getString("jobProfession"), rs.getInt("yearOfExperience"),
                        rs.getString("serviceDescription"), rs.getString("status"),
                        rs.getString("framework"), rs.getBytes("avatar"),
                        rs.getFloat("price"), timestamp);
                cvList.add(cv);

            }
            return cvList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<CV> getListofCV() {
        List<CV> cvList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select*from CV order by CVID desc";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                java.sql.Timestamp timestamp = rs.getTimestamp("createDate");
                CV cv = new CV(rs.getInt("cvId"),
                        rs.getInt("mentorId"), rs.getString("education"),
                        rs.getString("experience"), rs.getString("activity"),
                        rs.getString("professionIntroduction"),
                        rs.getString("certificate"),
                        rs.getString("jobProfession"), rs.getInt("yearOfExperience"),
                        rs.getString("serviceDescription"), rs.getString("status"),
                        rs.getString("framework"), rs.getBytes("avatar"),
                        rs.getFloat("price"), timestamp);
                cvList.add(cv);

            }
            return cvList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<CV> getListofActiveCV() {
        List<CV> cvList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select*from CV where Status='active'";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                java.sql.Timestamp timestamp = rs.getTimestamp("createDate");
                CV cv = new CV(rs.getInt("cvId"),
                        rs.getInt("mentorId"), rs.getString("education"),
                        rs.getString("experience"), rs.getString("activity"),
                        rs.getString("professionIntroduction"),
                        rs.getString("certificate"),
                        rs.getString("jobProfession"), rs.getInt("yearOfExperience"),
                        rs.getString("serviceDescription"), rs.getString("status"),
                        rs.getString("framework"), rs.getBytes("avatar"),
                        rs.getFloat("price"), timestamp);
                cvList.add(cv);

            }
            return cvList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public void updateMentor(Mentor c) {
        String sql = "UPDATE [dbo].[Mentor]\n"
                + "   SET [Phone] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[DateOfBirth] = ?\n"
                + "      ,[FullName] = ?\n"
                + "      ,[Gender] = ?\n"
                + " WHERE MentorID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getPhone());
            st.setString(2, c.getAddress());
            java.sql.Date sqlDob = new java.sql.Date(c.getDateOfBirth().getTime());
            st.setDate(3, sqlDob);
            st.setString(4, c.getFullName());
            st.setString(5, c.getGender());
            st.setInt(6, c.getMentorId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean updateCV(CV c, int cvid) {
        String sql = "UPDATE [dbo].[CV]\n"
                + "   SET [Education] = ?\n"
                + "      ,[Experience] = ?\n"
                + "      ,[Activity] = ?\n"
                + "      ,[ProfessionIntroduction] = ?,[CreateDate] = GETDATE()\n"
                + "      ,[JobProfession] = ?\n"
                + "      ,[YearOfExperience] = ?\n"
                + "      ,[ServiceDescription] = ?\n"
                + "      ,[Framework] = ?,[Avatar] =?, [Price] =?\n"
                + " WHERE [CVID] = ?;SELECT SCOPE_IDENTITY();";
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
            st.setBytes(9, c.getAvatar());
            st.setFloat(10, c.getPrice());
            st.setInt(11, cvid);
            int queryLine = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();

            return queryLine > 0;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void updateUser(String userid, String username, String email) {
        String sql = "update [User] set Username=?,Email=? where Username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, email);
            st.setString(3, userid);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<StatisticSkills> getCVSkillList(int id) {
        List<StatisticSkills> list = new ArrayList<>();
        String sql = "select s.SkillID,s.SkillName,sl.Rating from Skill s join SkillList sl on s.SkillID=sl.SkillID where CVID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                StatisticSkills skillStats = new StatisticSkills(rs.getInt("skillId"),
                        rs.getString("skillName"), rs.getInt("rating"));
                list.add(skillStats);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging
        }
        return list;
    }

    public List<StatisticSkills> getCVSkillListByMentor(int id) {
        List<StatisticSkills> list = new ArrayList<>();
        String sql = "select s.SkillID,s.SkillName,sl.Rating from Skill s join SkillList sl on s.SkillID=sl.SkillID where MentorID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                StatisticSkills skillStats = new StatisticSkills(rs.getInt("skillId"),
                        rs.getString("skillName"), rs.getInt("rating"));
                list.add(skillStats);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging
        }
        return list;
    }

    public List<Skill> getMentorSkillListByMentorID(int id) {
        List<Skill> list = new ArrayList<>();
        String sql = "select distinct s.SkillID,s.SkillName from Skill s join \n"
                + "SkillList sl on \n"
                + "s.SkillID=sl.SkillID where MentorID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill(rs.getInt("skillId"),
                        rs.getString("skillName"));
                list.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging
        }
        return list;
    }

    public List<Skill> getMentorSkillListByCVID(int id) {
        List<Skill> list = new ArrayList<>();
        String sql = "select s.SkillID,s.SkillName from Skill s join SkillList sl on s.SkillID=sl.SkillID and sl.CVID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill(rs.getInt("skillId"),
                        rs.getString("skillName"));
                list.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging
        }
        return list;
    }

    public List<Skill> getActiveCVSkillListByCVID(int id) {
        List<Skill> list = new ArrayList<>();
        String sql = "select s.* from Skill s join SkillList sl on s.SkillID=sl.SkillID where CVID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill(rs.getInt("skillId"),
                        rs.getString("skillName"));
                list.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging
        }
        return list;
    }

    public List<Skill> getSkillList(int id) {
        List<Skill> list = new ArrayList<>();
        String sql = "select s.SkillID,s.SkillName from Skill s left join SkillList sl on s.SkillID=sl.SkillID and sl.CVID = ?\n"
                + "where sl.SkillID is null;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Skill skillName = new Skill(rs.getInt("skillId"), rs.getString("skillName"));
                list.add(skillName);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging
        }
        return list;
    }

    public void deleteMentorSkills(int MentorId, String[] deleteSkills) {
        String sql = "delete from SkillList where CVID =? and SkillID =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            for (String i : deleteSkills) {
                st.setInt(1, MentorId);
                st.setInt(2, Integer.parseInt(i));
                st.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean insertMentorSkills(int MentorId, String[] addSkills, int cvId) {
        String sql = "INSERT INTO [dbo].[SkillList]\n"
                + "           ([SkillID]\n"
                + "           ,[MentorID]\n"
                + "           ,[Rating]\n"
                + "           ,[CVID])\n"
                + "     VALUES\n"
                + "           (?,?,null,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            for (String addSkill : addSkills) {

                st.setInt(1, Integer.parseInt(addSkill));
                st.setInt(2, MentorId);
                st.setInt(3, cvId);
                st.executeUpdate();
            }
            return true;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public String getUserEmail(int MentorId) {
        String sql = "select * from [User] u join Mentor m on u.Username=m.Username where MentorID = ?";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, MentorId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("Email");

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public boolean checkDuplicateEmail(String email, String username) {
        String sql = "select Status,Username,Email from [User] where Email = ?";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if ((!username.equalsIgnoreCase(rs.getString("Username"))
                        && !email.isEmpty() || rs.getString("Status").equalsIgnoreCase("inactive"))) {
                    return true; // email exsist
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    public int createCV(CV cv) {
        int CVid = -1;
        String sql = "INSERT INTO [dbo].[CV]\n"
                + "           ([MentorID]\n"
                + "           ,[Education]\n"
                + "           ,[Experience]\n"
                + "           ,[Activity]\n"
                + "           ,[ProfessionIntroduction]\n"
                + "           ,[YearOfExperience]\n"
                + "           ,[CreateDate]\n"
                + "           ,[JobProfession]\n"
                + "           ,[ServiceDescription]\n"
                + "           ,[Status]\n"
                + "           ,[Framework]\n"
                + "           ,[Avatar],[Price])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,GETDATE(),?,?,'inactive',?,?,?)SELECT SCOPE_IDENTITY();";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cv.getMentorId());
            st.setString(2, cv.getEducation());
            st.setString(3, cv.getExperience());
            st.setString(4, cv.getActivity());
            st.setString(5, cv.getProfessionIntroduction());
            st.setInt(6, cv.getYearOfExperience());
            st.setString(7, cv.getJobProfession());
            st.setString(8, cv.getServiceDescription());
            st.setString(9, cv.getFramework());
            st.setBytes(10, cv.getAvatar());
            st.setFloat(11, cv.getPrice());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                CVid = rs.getInt(1); // Lấy CVid tự động tạo
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return CVid;
    }

    public List<Rate> getMentorRateList(int id) {
        List<Rate> list = new ArrayList<>();
        String sql = "select * from Rate where MentorID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Rate rate = new Rate(rs.getInt("mentorId"), rs.getInt("menteeId"),
                        rs.getDate("createDate"), rs.getString("status"),
                        rs.getString("comment"), rs.getInt("rate"));
                list.add(rate);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging

        }
        return list;
    }

    public List<CV> getMostEficientCV() {
        List<CV> listCV = new ArrayList<>();
        String sql = "WITH RankedCVs AS (\n"
                + "    SELECT CV.[CVID],\n"
                + "           CV.[MentorID],\n"
                + "           CV.[Education],\n"
                + "           CV.[Experience],\n"
                + "           CV.[Activity],\n"
                + "           CV.[ProfessionIntroduction],\n"
                + "           CV.[Certificate],\n"
                + "           CV.[CreateDate],\n"
                + "           CV.[JobProfession],\n"
                + "           CV.[YearOfExperience],\n"
                + "           CV.[ServiceDescription],\n"
                + "           CV.[Status],\n"
                + "           CV.[Framework],\n"
                + "           CV.[Avatar],\n"
                + "           ROW_NUMBER() OVER (PARTITION BY CV.[MentorID] \n"
                + "                              ORDER BY \n"
                + "                                CASE WHEN CV.[Status] = 'Active' THEN 1 ELSE 2 END,  -- prioritize active CVs\n"
                + "                                CV.[CreateDate] DESC  -- then by newest date\n"
                + "                             ) AS rn\n"
                + "    FROM [dbo].[CV]\n"
                + ")\n"
                + "SELECT m.[MentorID],\n"
                + "       r.[CVID],\n"
                + "       r.[Education],\n"
                + "       r.[Experience],\n"
                + "       r.[Activity],\n"
                + "       r.[ProfessionIntroduction],\n"
                + "       r.[Certificate],\n"
                + "       r.[CreateDate],\n"
                + "       r.[JobProfession],\n"
                + "       r.[YearOfExperience],\n"
                + "       r.[ServiceDescription],\n"
                + "       r.[Status],\n"
                + "       r.[Framework],\n"
                + "       r.[Avatar]\n"
                + "FROM [dbo].[Mentor] m\n"
                + "LEFT JOIN RankedCVs r\n"
                + "  ON m.[MentorID] = r.[MentorID] AND r.rn = 1;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CV curCV = new CV();
                curCV.setMentorId(rs.getInt("MentorID"));
                curCV.setCvId(rs.getInt("CVID"));
                curCV.setEducation(rs.getString("Education"));
                curCV.setExperience(rs.getString("Experience"));
                curCV.setActivity(rs.getString("Activity"));
                curCV.setProfessionIntroduction(rs.getString("ProfessionIntroduction"));
                curCV.setCertificate(rs.getString("Certificate"));

                curCV.setCreateDate(rs.getString("CreateDate"));
                curCV.setJobProfession(rs.getString("JobProfession"));
                curCV.setYearOfExperience(rs.getInt("YearOfExperience"));
                curCV.setServiceDescription(rs.getString("ServiceDescription"));
                curCV.setStatus(rs.getString("Status"));
                curCV.setFramework(rs.getString("Framework"));
                byte[] avatar = rs.getBytes("Avatar");
                if (avatar != null) {
                    curCV.setAvatar(rs.getBytes("Avatar"));
                }
                listCV.add(curCV);

            }
        } catch (SQLException e) {
        }
        return listCV;
    }

    public List<Rate> getRateList() {
        List<Rate> list = new ArrayList<>();
        String sql = "select * from Rate";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Rate rate = new Rate(rs.getInt("mentorId"), rs.getInt("menteeId"),
                        rs.getDate("createDate"), rs.getString("status"),
                        rs.getString("comment"), rs.getInt("rate"));
                list.add(rate);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging

        }
        return null;
    }

    public int getAveRatebyId(int id) {
        String sql = "select Avg(Rate) from Rate where MentorID =?;";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("");

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    public static void main(String[] args) {
        CVDAO c = new CVDAO();
        System.out.println(c.getCVbyMentorId(2).getJobProfession());
    }

    public boolean deleteCV(int id) {

        String sql = "delete from CV where CVID =? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            int query = st.executeUpdate();
            return query > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;

    }

    public List<Mentor> getListofMentor() {
        List<Mentor> mentorList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select * from [Mentor] order by MentorID desc";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Mentor mentor = new Mentor(rs.getInt("mentorId"),
                        rs.getInt("roleId"), rs.getString("username"),
                        rs.getDate("createDate"), rs.getString("phone"),
                        rs.getString("address"), rs.getDate("dateOfBirth"),
                        rs.getString("fullName"), rs.getString("gender"),
                        rs.getString("status"));
                mentorList.add(mentor);

            }
            return mentorList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<User> getListofUser() {
        List<User> userList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select * from [User]";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                User us = new User();
                us.setUsername(rs.getString("username"));
                us.setEmail(rs.getString("email"));
                userList.add(us);

            }
            return userList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Skill> getListofSkill() {
        List<Skill> userList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select * from [Skill]";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Skill skill = new Skill();
                skill.setSkillId(rs.getInt("skillId"));
                skill.setSkillName(rs.getString("skillName"));
                userList.add(skill);

            }
            return userList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<SkillList> getListofSkillList() {
        List<SkillList> userList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select * from SkillList";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                SkillList skillList = new SkillList(rs.getInt("skillListId"),
                        rs.getInt("skillId"), rs.getInt("mentorId"),
                        rs.getInt("rating"), rs.getInt("cvId"));
                userList.add(skillList);

            }
            return userList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public void setStatusActiveCvId(int id) {
        String sql = "Update CV set Status ='active' where CVID =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void setStatusInactiveCvId(int id) {
        String sql = "Update CV set Status ='inactive' where CVID =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Map<String, Integer> getRatingMapbyMentorId(int mentorid) {
        Map<String, Integer> monthlyRatings = new HashMap<>();
        String sql = "select s.SkillName,sl.Rating from SkillList sl join Skill s on sl.SkillID = s.SkillID where sl.MentorID =?";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                String month = "'" + rs.getString("SkillName") + "'";
                int rating = rs.getInt("Rating");

                //System.out.println(monthName+", "+rating);
                monthlyRatings.put(month, rating);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return monthlyRatings;
    }

    public int countInvitedRequestbyMentorId(int mentorid) {
        String sql = "select count(MentorID) from Request where MentorID=? and Status ='Open'";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("");

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    public int countAcceptedRequestbyMentorId(int mentorid) {
        String sql = "select count(MentorID) from Request where MentorID=? and Status ='Pending'";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("");

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    public int countCompletedRequestbyMentorId(int mentorid) {
        String sql = "select count(MentorID) from Request where MentorID=? and Status ='Completed' ";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("");

            }
        } catch (SQLException e) {

            System.out.println(e);
        }

        return 0;
    }

    public int countAllCompletedRequestbyMentorId(int mentorid) {
        String sql = "select count(MentorID) from Request where MentorID=? and (Status ='Completed' or Status ='Paid') ";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("");

            }
        } catch (SQLException e) {

            System.out.println(e);
        }

        return 0;
    }

    public int countAllCompletedRequest() {
        String sql = "select count(MentorID) from Request where (Status ='Completed' or Status ='Paid') ";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("");

            }
        } catch (SQLException e) {

            System.out.println(e);
        }

        return 0;
    }

    public int countAllRating() {
        String sql = "select count(RateID) from Rate";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("");

            }
        } catch (SQLException e) {

            System.out.println(e);
        }

        return 0;
    }

    public int countCanceledRequestbyMentorId(int mentorid) {
        String sql = "select count(MentorID) from Request where MentorID=? and Status ='Reject'";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("");

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    public int countRequestbyMentorId(int mentorid) {
        String sql = "select count(MentorID) from Request where MentorID=?";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("");

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    public List<Mentor> getListofMentorbyName(String name) {
        List<Mentor> mentorList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select m.MentorID,m.RoleID,m.Username,m.CreateDate,m.Phone,m.Address,m.DateOfBirth,m.FullName,m.Gender,m.Status\n"
                + "from Mentor m join [User] u on m.Username=u.Username where m.FullName like ? or m.Username like ? or u.Email like ?";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            st.setString(2, "%" + name + "%");
            st.setString(3, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Mentor mentor = new Mentor(rs.getInt("mentorId"),
                        rs.getInt("roleId"), rs.getString("username"),
                        rs.getDate("createDate"), rs.getString("phone"),
                        rs.getString("address"), rs.getDate("dateOfBirth"),
                        rs.getString("fullName"), rs.getString("gender"),
                        rs.getString("status"));
                mentorList.add(mentor);

            }
            return mentorList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<User> getListofUserbyName(String name) {
        List<User> userList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select * from [User] where Email like ?";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                User us = new User();
                us.setUsername(rs.getString("username"));
                us.setEmail(rs.getString("email"));
                userList.add(us);

            }
            return userList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Slot> getSlotByMentorId(int mentorId) {
        List<Slot> slotList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select * from Slot where MentorID = ?";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Slot sl = new Slot();
                sl.setSlotID(rs.getInt("slotID"));
                sl.setMentorID(rs.getInt("mentorID"));
                sl.setStartTime(rs.getTime("StartTime").toLocalTime());
                sl.setEndTime(rs.getTime("EndTime").toLocalTime());
                sl.setDayInWeek(rs.getString("dayInWeek"));
                sl.setStatus(rs.getString("status"));

                slotList.add(sl);
            }
            return slotList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Request> getListofRequest() {
        List<Request> listRequest = new ArrayList<>();
        String sql = "select * from Request order by RequestID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Request curRequest = new Request();
                curRequest.setRequestId(rs.getInt("RequestID"));
                curRequest.setMentorId(rs.getInt("MentorID"));
                curRequest.setMenteeId(rs.getInt("MenteeID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setNote(rs.getString("Note"));
                LocalDate curCreaDate = rs.getDate("CreateDate").toLocalDate();
                curRequest.setCreateDate(curCreaDate);
                curRequest.setStatus(rs.getString("Status"));
                curRequest.setTitle(rs.getString("Title"));
                LocalDate start = rs.getDate("StartDate").toLocalDate();
                LocalDate end = rs.getDate("EndDate").toLocalDate();
                curRequest.setStartDate(start);
                curRequest.setEndDate(end);
                curRequest.setSkillId(rs.getInt("SkillID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setFramework(rs.getString("Framework"));
                listRequest.add(curRequest);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listRequest;

    }

    public List<Request> getListofRequestbyStaus() {
        List<Request> listRequest = new ArrayList<>();
        String sql = "select * from Request where Status not like 'open' and Status not like 'reject' and Status not like 'paid' order by RequestID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Request curRequest = new Request();
                curRequest.setRequestId(rs.getInt("RequestID"));
                curRequest.setMentorId(rs.getInt("MentorID"));
                curRequest.setMenteeId(rs.getInt("MenteeID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setNote(rs.getString("Note"));
                LocalDate curCreaDate = rs.getDate("CreateDate").toLocalDate();
                curRequest.setCreateDate(curCreaDate);
                curRequest.setStatus(rs.getString("Status"));
                curRequest.setTitle(rs.getString("Title"));
                LocalDate start = rs.getDate("StartDate").toLocalDate();
                LocalDate end = rs.getDate("EndDate").toLocalDate();
                curRequest.setStartDate(start);
                curRequest.setEndDate(end);
                curRequest.setSkillId(rs.getInt("SkillID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setFramework(rs.getString("Framework"));
                listRequest.add(curRequest);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listRequest;

    }

    public List<Request> getListofRequestbyMentorId(int mentorId) {
        List<Request> listRequest = new ArrayList<>();
        String sql = "select * from Request where MentorID=? order by RequestID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Request curRequest = new Request();
                curRequest.setRequestId(rs.getInt("RequestID"));
                curRequest.setMentorId(rs.getInt("MentorID"));
                curRequest.setMenteeId(rs.getInt("MenteeID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setNote(rs.getString("Note"));
                LocalDate curCreaDate = rs.getDate("CreateDate").toLocalDate();
                curRequest.setCreateDate(curCreaDate);
                curRequest.setStatus(rs.getString("Status"));
                curRequest.setTitle(rs.getString("Title"));
                LocalDate start = rs.getDate("StartDate").toLocalDate();
                LocalDate end = rs.getDate("EndDate").toLocalDate();
                curRequest.setStartDate(start);
                curRequest.setEndDate(end);
                curRequest.setSkillId(rs.getInt("SkillID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setFramework(rs.getString("Framework"));
                listRequest.add(curRequest);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listRequest;

    }

    public List<Request> getListofCompleteRequest() {
        List<Request> listRequest = new ArrayList<>();
        String sql = "select * from Request where Status='Paid' or Status='Completed' order by RequestID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Request curRequest = new Request();
                curRequest.setRequestId(rs.getInt("RequestID"));
                curRequest.setMentorId(rs.getInt("MentorID"));
                curRequest.setMenteeId(rs.getInt("MenteeID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setNote(rs.getString("Note"));
                LocalDate curCreaDate = rs.getDate("CreateDate").toLocalDate();
                curRequest.setCreateDate(curCreaDate);
                curRequest.setStatus(rs.getString("Status"));
                curRequest.setTitle(rs.getString("Title"));
                LocalDate start = rs.getDate("StartDate").toLocalDate();
                LocalDate end = rs.getDate("EndDate").toLocalDate();
                curRequest.setStartDate(start);
                curRequest.setEndDate(end);
                curRequest.setSkillId(rs.getInt("SkillID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setFramework(rs.getString("Framework"));
                listRequest.add(curRequest);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listRequest;

    }

    public List<Request> getListofCompleteRequestbyRequestId(int id) {
        List<Request> listRequest = new ArrayList<>();
        String sql = "select * from Request where RequestID =? and (Status='Paid' or Status='Completed') order by RequestID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Request curRequest = new Request();
                curRequest.setRequestId(rs.getInt("RequestID"));
                curRequest.setMentorId(rs.getInt("MentorID"));
                curRequest.setMenteeId(rs.getInt("MenteeID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setNote(rs.getString("Note"));
                LocalDate curCreaDate = rs.getDate("CreateDate").toLocalDate();
                curRequest.setCreateDate(curCreaDate);
                curRequest.setStatus(rs.getString("Status"));
                curRequest.setTitle(rs.getString("Title"));
                LocalDate start = rs.getDate("StartDate").toLocalDate();
                LocalDate end = rs.getDate("EndDate").toLocalDate();
                curRequest.setStartDate(start);
                curRequest.setEndDate(end);
                curRequest.setSkillId(rs.getInt("SkillID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setFramework(rs.getString("Framework"));
                listRequest.add(curRequest);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listRequest;

    }

    public List<Mentee> getListofMentee() {
        List<Mentee> listMen = new ArrayList<>();
        String sql = "SELECT [MenteeID],"
                + "      [RoleID],"
                + "      [Avatar],"
                + "      [Username],"
                + "      [CreateDate],"
                + "      [Email],"
                + "      [Phone],"
                + "      [Address],"
                + "      [DateOfBirth],"
                + "      [FullName],"
                + "      [Gender],"
                + "      [Status]"
                + "  FROM [dbo].[Mentee]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Mentee curMentee = new Mentee();
                curMentee.setMenteeId(rs.getInt("MenteeID"));
                curMentee.setRoleId(rs.getInt("RoleID"));
                byte[] avatar = rs.getBytes("Avatar");
                if (avatar != null) {
                    curMentee.setAvatar(avatar);
                } else {
                    curMentee.setAvatar(null);
                }
                curMentee.setUsername(rs.getString("Username"));
                curMentee.setCreateDate(rs.getDate("CreateDate"));
                curMentee.setEmail(rs.getString("Email"));
                curMentee.setPhone(rs.getString("Phone"));
                curMentee.setAddress(rs.getString("Address"));
                curMentee.setDateOfBirth(rs.getDate("DateOfBirth"));
                curMentee.setFullName(rs.getString("FullName"));
                curMentee.setGender(rs.getString("Gender"));
                curMentee.setStatus(rs.getString("Status"));
                listMen.add(curMentee);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return listMen;
    }

    public List<Payment> getListofPaymentbyRequestId(int id) {
        List<Payment> paymentList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select * from Payment where RequestID =?;";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Payment p = new Payment();
                p.setPaymentId(rs.getInt("paymentId"));
                p.setRequestId(rs.getInt("requestId"));
                p.setPaymentDate(rs.getTimestamp("paymentDate").toLocalDateTime());
                p.setTotalAmount(rs.getFloat("totalAmount"));
                p.setStatus(rs.getString("status"));
                p.setSender(rs.getString("sender"));
                p.setReceiver(rs.getString("receiver"));

                paymentList.add(p);

            }
            return paymentList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Payment> getListofPaymentbyRequestIdStep1(int id) {
        List<Payment> paymentList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select * from Payment where RequestID =? and Status='2'";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Payment p = new Payment();
                p.setPaymentId(rs.getInt("paymentId"));
                p.setRequestId(rs.getInt("requestId"));
                p.setPaymentDate(rs.getTimestamp("paymentDate").toLocalDateTime());
                p.setTotalAmount(rs.getFloat("totalAmount"));
                p.setStatus(rs.getString("status"));
                p.setSender(rs.getString("sender"));
                p.setReceiver(rs.getString("receiver"));

                paymentList.add(p);

            }
            return paymentList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Payment> getListofPaymentbyRequestIdStep2(int id) {
        List<Payment> paymentList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select * from Payment where RequestID =? and Status='3'";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Payment p = new Payment();
                p.setPaymentId(rs.getInt("paymentId"));
                p.setRequestId(rs.getInt("requestId"));
                p.setPaymentDate(rs.getTimestamp("paymentDate").toLocalDateTime());
                p.setTotalAmount(rs.getFloat("totalAmount"));
                p.setStatus(rs.getString("status"));
                p.setSender(rs.getString("sender"));
                p.setReceiver(rs.getString("receiver"));

                paymentList.add(p);

            }
            return paymentList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public Request getRequestbyRequestId(int requestId) {
        String sql = "select * from Request where RequestID =?;";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, requestId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                Request rq = new Request();
                rq.setRequestId(rs.getInt("RequestID"));
                rq.setMentorId(rs.getInt("MentorID"));
                rq.setMenteeId(rs.getInt("MenteeID"));
                rq.setPrice(rs.getFloat("Price"));
                rq.setNote(rs.getString("Note"));
                LocalDate curCreaDate = rs.getDate("CreateDate").toLocalDate();
                rq.setCreateDate(curCreaDate);
                rq.setStatus(rs.getString("Status"));
                rq.setTitle(rs.getString("Title"));
                LocalDate start = rs.getDate("StartDate").toLocalDate();
                LocalDate end = rs.getDate("EndDate").toLocalDate();
                rq.setStartDate(start);
                rq.setEndDate(end);
                rq.setSkillId(rs.getInt("SkillID"));
                rq.setPrice(rs.getFloat("Price"));
                rq.setFramework(rs.getString("Framework"));

                return rq;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public void setStatusStudyingRequestId(int requestId) {
        String sql = "Update Request set Status ='Studying' where RequestID =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, requestId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void setStatusProcessingRequestId(int requestId) {
        String sql = "Update Request set Status ='Processing' where RequestID =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, requestId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void setStatusPaidRequestId(int requestId) {
        String sql = "Update Request set Status ='Paid' where RequestID =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, requestId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void setStatusCompleteRequestId(int requestId) {
        String sql = "Update Request set Status ='Completed' where RequestID =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, requestId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<SlotRequest> getSlotRequestbyMenteeId(int menteeId) {
        List<SlotRequest> srList = new ArrayList<>();
        String sql = """
                     select s.SlotID,s.StartTime,s.EndTime,s.DayInWeek,
                     	r.RequestID,r.MentorID,r.MenteeID,r.Price,r.Status,r.Title,r.Framework,r.StartDate,r.EndDate
                     	from Slot s join RequestSlotItem rs on s.SlotID=rs.SlotID join Request r on rs.RequestID=r.RequestID where r.MenteeID=?
                     """;
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, menteeId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                SlotRequest sr = new SlotRequest();
                sr.setSlotID(rs.getInt("slotID"));
                sr.setStartTime(rs.getTime("startTime").toLocalTime());
                sr.setEndTime(rs.getTime("endTime").toLocalTime());
                sr.setDayInWeek(rs.getString("dayInWeek"));
                sr.setRequestId(rs.getInt("RequestID"));
                sr.setMentorId(rs.getInt("MentorID"));
                sr.setMenteeId(rs.getInt("MenteeID"));
                sr.setPrice(rs.getFloat("Price"));
                sr.setStatus(rs.getString("Status"));
                sr.setTitle(rs.getString("Title"));
                LocalDate start = rs.getDate("StartDate").toLocalDate();
                LocalDate end = rs.getDate("EndDate").toLocalDate();
                sr.setStartDate(start);
                sr.setEndDate(end);
                sr.setPrice(rs.getFloat("Price"));
                sr.setFramework(rs.getString("Framework"));

                //System.out.println(sr.getMenteeId());
                srList.add(sr);
            }
            return srList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<SlotRequest> getSlotRequestbyMentorId(int mentorId) {
        List<SlotRequest> srList = new ArrayList<>();
        String sql = "select s.SlotID,s.StartTime,s.EndTime,s.DayInWeek,\n"
                + "r.RequestID,r.MentorID,r.MenteeID,r.Price,r.Status,r.Title,r.Framework,r.StartDate,r.EndDate\n"
                + "from Slot s join RequestSlotItem rs on s.SlotID=rs.SlotID join Request r on rs.RequestID=r.RequestID\n"
                + "where r.MentorID=?";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                SlotRequest sr = new SlotRequest();
                sr.setSlotID(rs.getInt("slotID"));
                sr.setStartTime(rs.getTime("startTime").toLocalTime());
                sr.setEndTime(rs.getTime("endTime").toLocalTime());
                sr.setDayInWeek(rs.getString("dayInWeek"));
                sr.setRequestId(rs.getInt("RequestID"));
                sr.setMentorId(rs.getInt("MentorID"));
                sr.setMenteeId(rs.getInt("MenteeID"));
                sr.setPrice(rs.getFloat("Price"));
                sr.setStatus(rs.getString("Status"));
                sr.setTitle(rs.getString("Title"));
                LocalDate start = rs.getDate("StartDate").toLocalDate();
                LocalDate end = rs.getDate("EndDate").toLocalDate();
                sr.setStartDate(start);
                sr.setEndDate(end);
                sr.setPrice(rs.getFloat("Price"));
                sr.setFramework(rs.getString("Framework"));

                //System.out.println(sr.getMenteeId());
                srList.add(sr);
            }
            return srList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<SlotRequest> getSlotRequestbyRequestId(int requestId) {
        List<SlotRequest> srList = new ArrayList<>();
        String sql = """
                     select s.SlotID,s.StartTime,s.EndTime,s.DayInWeek,
                     r.RequestID,r.MentorID,r.MenteeID,r.Price,r.Status,r.Title,r.Framework,r.StartDate,r.EndDate
                     from Slot s join RequestSlotItem rs on s.SlotID=rs.SlotID join Request r on rs.RequestID=r.RequestID
                     where r.RequestID=? 
                     """;
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, requestId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                SlotRequest sr = new SlotRequest();
                sr.setSlotID(rs.getInt("slotID"));
                sr.setStartTime(rs.getTime("startTime").toLocalTime());
                sr.setEndTime(rs.getTime("endTime").toLocalTime());
                sr.setDayInWeek(rs.getString("dayInWeek"));
                sr.setRequestId(rs.getInt("RequestID"));
                sr.setMentorId(rs.getInt("MentorID"));
                sr.setMenteeId(rs.getInt("MenteeID"));
                sr.setPrice(rs.getFloat("Price"));
                sr.setStatus(rs.getString("Status"));
                sr.setTitle(rs.getString("Title"));
                LocalDate start = rs.getDate("StartDate").toLocalDate();
                LocalDate end = rs.getDate("EndDate").toLocalDate();
                sr.setStartDate(start);
                sr.setEndDate(end);
                sr.setPrice(rs.getFloat("Price"));
                sr.setFramework(rs.getString("Framework"));

                //System.out.println(sr.getMenteeId());
                srList.add(sr);
            }
            return srList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Mentor> getListofMentorWithStatus() {
        List<Mentor> mentorList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = "select m.* from [Mentor] m join Request r on m.MentorID = r.MentorID where r.[Status] = 'Completed'";
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Mentor mentor = new Mentor(rs.getInt("mentorId"),
                        rs.getInt("roleId"), rs.getString("username"),
                        rs.getDate("createDate"), rs.getString("phone"),
                        rs.getString("address"), rs.getDate("dateOfBirth"),
                        rs.getString("fullName"), rs.getString("gender"),
                        rs.getString("status"));
                mentorList.add(mentor);

            }
            return mentorList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public Wallet getManagerWallet() {
        String sql = "select * from Wallet\n"
                + "where Wallet.Username = 'manager'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Wallet s = new Wallet();
                s.setBalance(rs.getDouble("Balance"));
                s.setUsername(rs.getString("Username"));
                s.setWalletID(rs.getInt("WalletID"));
                return s;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean addPayment(Payment payment) {
        String sql = "INSERT INTO payment (requestId, paymentDate, totalAmount, status, sender, receiver) VALUES (?, GETDATE(), ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, payment.getRequestId());
            pstmt.setDouble(2, payment.getTotalAmount());
            pstmt.setString(3, payment.getStatus());
            pstmt.setString(4, payment.getSender());
            pstmt.setString(5, payment.getReceiver());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0; // Returns true if the payment was added successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }

    public void setWalletTransactionbyManager(float price, String mentorname) {
        String sql = "update Wallet set Balance =Balance+? where Username like ?\n"
                + "update Wallet set Balance =Balance-? where Username like 'manager'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setFloat(1, price);
            st.setString(2, mentorname);
            st.setFloat(3, price);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void setWalletTransactionbyMentee(float price, String menteeString) {
        String sql = "update Wallet set Balance =Balance+? where Username like 'manager'\n"
                + "update Wallet set Balance =Balance-? where Username like '?'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setFloat(1, price);
            st.setString(2, menteeString);
            st.setFloat(3, price);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Mentor> getListofMentorByMenteeWithStatus(String username) {
        List<Mentor> mentorList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = """
                     select mr.*, r.RequestID from [Mentor] mr join Request r on mr.MentorID = r.MentorID
                                          join Mentee mt on  mt.MenteeID = r.MenteeID
                                          where (r.[Status] = 'Completed' or r.[Status] = 'Paid') and mt.Username = ? 
                     """;
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Mentor mentor = new Mentor(rs.getInt("mentorId"),
                        rs.getInt("roleId"), rs.getString("username"),
                        rs.getDate("createDate"), rs.getString("phone"),
                        rs.getString("address"), rs.getDate("dateOfBirth"),
                        rs.getString("fullName"), rs.getString("gender"),
                        rs.getString("status"));
                mentor.setRequestId(rs.getInt("RequestID"));
                mentorList.add(mentor);
            }

            return mentorList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Mentor> getListofMentorByMenteeWithStatusSort(String username) {
        List<Mentor> mentorList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = """
                     SELECT mr.*, r.RequestID FROM [Mentor] mr 
                     JOIN [Request] r ON mr.MentorID = r.MentorID
                     JOIN [Mentee] mt ON mt.MenteeID = r.MenteeID
                     WHERE (r.[Status] = 'Completed' OR r.[Status] = 'Paid') AND mt.Username = ?
                     ORDER BY r.RequestID DESC;
                     """;
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Mentor mentor = new Mentor(rs.getInt("mentorId"),
                        rs.getInt("roleId"), rs.getString("username"),
                        rs.getDate("createDate"), rs.getString("phone"),
                        rs.getString("address"), rs.getDate("dateOfBirth"),
                        rs.getString("fullName"), rs.getString("gender"),
                        rs.getString("status"));
                mentor.setRequestId(rs.getInt("RequestID"));
                mentorList.add(mentor);
            }

            return mentorList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public boolean addSlot(Slot slot) {
        String sql = "INSERT INTO [dbo].[Slot] ([MentorID],[StartTime],[EndTime],[DayInWeek],[Status],[CVID]) \n"
                + "VALUES (?,?,?,?,?,?)";
        try (PreparedStatement rs = connection.prepareStatement(sql)) {
            rs.setInt(1, slot.getMentorID());
            rs.setTime(2, java.sql.Time.valueOf(slot.getStartTime()));
            rs.setTime(3, java.sql.Time.valueOf(slot.getEndTime()));
            rs.setString(4, slot.getDayInWeek());
            rs.setString(5, slot.getStatus());
            rs.setInt(6, slot.getCVID());
            int rowsInserted = rs.executeUpdate();
            return rowsInserted > 0; // Returns true if the payment was added successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }

    public void setStatusInactiveMentor(int mentorId) {
        String sql = "update Mentor set Status='inactive' where MentorID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void setStatusActiveMentor(int mentorId) {
        String sql = "update Mentor set Status='active' where MentorID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
