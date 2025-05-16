/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Rate;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author tuong
 */
public class RateDAO extends DBContext {

    public List<Rate> getAllRate() {
        List<Rate> listRate = new ArrayList<>();
        String sql = "SELECT [MentorID]\n"
                + "      ,[MenteeID]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Status]\n"
                + "      ,[Comment]\n"
                + "      ,[Rate]\n"
                + "  FROM [dbo].[Rate]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Rate curRate = new Rate();
                curRate.setMentorId(rs.getInt("MentorID"));
                curRate.setMenteeId(rs.getInt("MenteeID"));
                curRate.setCreateDate(rs.getDate("CreateDate"));
                curRate.setStatus(rs.getString("Status"));
                curRate.setComment(rs.getString("Comment"));
                curRate.setRate(rs.getInt("Rate"));
                listRate.add(curRate);
            }
        } catch (Exception e) {
        }
        return listRate;
    }

    public List<Rate> getAllRates() {
        List<Rate> listRate = new ArrayList<>();
        String sql = "SELECT [MentorID]\n"
                + "      ,[MenteeID]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Status]\n"
                + "      ,[Comment]\n"
                + "      ,[Rate]\n"
                + "      ,[RequestID]\n"
                + "      ,[RateID]\n"
                + "  FROM [Rate]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Rate curRate = new Rate();
                curRate.setMentorId(rs.getInt("MentorID"));
                curRate.setMenteeId(rs.getInt("MenteeID"));
                curRate.setCreateDate(rs.getDate("CreateDate"));
                curRate.setStatus(rs.getString("Status"));
                curRate.setComment(rs.getString("Comment"));
                curRate.setRate(rs.getInt("Rate"));
                listRate.add(curRate);
            }
        } catch (Exception e) {
        }
        return listRate;
    }

    public void saveRating(int menteeId, int mentorId, int requestId, int rate, String comment) {
        String query = "INSERT INTO Rate (MentorID, MenteeID, RequestID, Rate, Comment, CreateDate, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, mentorId);
            ps.setInt(2, menteeId);
            ps.setInt(3, requestId);
            ps.setInt(4, rate);
            ps.setString(5, comment);
            ps.setDate(6, new java.sql.Date(new Date().getTime())); //current date
            ps.setString(7, "Active");

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveSkillRating(int skillId, int rate) {
        String query = """
                       UPDATE SkillList
                       SET Rating = ?
                       WHERE SkillID = ?;
                       """;

        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, rate);
            ps.setInt(2, skillId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Rate getRatingForRequest(int menteeId, int mentorId, int requestId) {
        String sql = "SELECT * FROM Rate WHERE MenteeID = ? AND MentorID = ? AND RequestID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, menteeId);
            stmt.setInt(2, mentorId);
            stmt.setInt(3, requestId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Assuming Rate has a constructor that initializes it from ResultSet
                return new Rate(
                        rs.getInt("MentorID"),
                        rs.getInt("MenteeID"),
                        rs.getDate("CreateDate"),
                        rs.getString("Status"),
                        rs.getString("Comment"),
                        rs.getInt("Rate"),
                        rs.getInt("RequestID"),
                        rs.getInt("RateID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addRating(int menteeId, int mentorId, int requestId, String comment, int rate) {
        String sql = "INSERT INTO Rate (MentorID, MenteeID, RequestID, CreateDate, Status, Comment, Rate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mentorId);
            stmt.setInt(2, menteeId);
            stmt.setInt(3, requestId);
            stmt.setDate(4, new java.sql.Date(System.currentTimeMillis())); // Set current date as create date
            stmt.setString(5, "active"); // Assuming "Active" status for new ratings
            stmt.setString(6, comment);
            stmt.setInt(7, rate);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Return true if the rating was successfully added
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkExistingRating(int menteeId, int mentorId, int requestId) {
        String query = "SELECT COUNT(*) FROM Rate WHERE MenteeID = ? AND MentorID = ? AND RequestID = ?";

        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, menteeId);
            ps.setInt(2, mentorId);
            ps.setInt(3, requestId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // If count > 0, it means a rating already exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // No existing rating found or query failed
    }

    public int sumRateMentor(int mentorID) {
        String sql = "select Sum(Rate) as sum from Rate \n"
                + "where MentorID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int sum = rs.getInt("sum");
                if (rs.wasNull()) {
                    return 0; 
                }
                return sum;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
     public int CountRateMentor(int mentorID) {
        String sql = "select Count(RateID) as count from Rate \n"
                + "where MentorID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                if (rs.wasNull()) {
                    return 0; 
                }
                return count;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean hasRated(int menteeId, int mentorId, int requestId) {
        String query = """
                       SELECT RateID FROM Rate 
                       WHERE MenteeID = ? AND MentorID = ? AND RequestID = ? AND Status IN ('Active', 'active');
                       """;
        try (
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, menteeId);
            ps.setInt(2, mentorId);
            ps.setInt(3, requestId);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // Trả về true nếu đã có đánh giá
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println(new RateDAO().getRatingForRequest(6, 7, 1));
    }
}
