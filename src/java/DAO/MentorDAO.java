package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import Model.Mentor;
import java.util.ArrayList;
import java.util.List;

public class MentorDAO extends DBContext {

    public void insertMentor(int roleId, String username, Date createDate, String phone, String address,
            Date dateOfBirth, String fullName, String gender, String status) {
        String sql = "INSERT INTO Mentor (RoleID, Username, CreateDate, Phone, Address, DateOfBirth, FullName, Gender, Status) "
                + "VALUES (?, ?, GETDATE(), ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, roleId);
            st.setString(2, username);
            st.setString(3, phone);
            st.setString(4, address);
            st.setDate(5, new java.sql.Date(dateOfBirth.getTime()));
            st.setString(6, fullName);
            st.setString(7, gender);
            st.setString(8, status);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateMentor(Mentor mentor) {
        String sql = "UPDATE Mentor SET RoleID = ?, Username = ?, CreateDate = ?, "
                + "Phone = ?, Address = ?, DateOfBirth = ?, FullName = ?, Gender = ?, "
                + "Status = ? WHERE Username = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, mentor.getRoleId());
            st.setString(2, mentor.getUsername());
            st.setDate(3, new java.sql.Date(mentor.getCreateDate().getTime()));
            st.setString(4, mentor.getPhone());
            st.setString(5, mentor.getPhone());
            st.setDate(6, new java.sql.Date(mentor.getDateOfBirth().getTime()));
            st.setString(7, mentor.getFullName());
            st.setString(8, mentor.getGender());
            st.setString(9, mentor.getStatus());
            st.setString(10, mentor.getUsername());

            st.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public Mentor findMentorByUsername(String username) {
        Mentor mentor = null;
        String sql = "SELECT [MentorID]\n"
                + "      ,[RoleID]\n"
                + "      ,[Username]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "      ,[DateOfBirth]\n"
                + "      ,[FullName]\n"
                + "      ,[Gender]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[Mentor] "
                + "where Username = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                mentor = new Mentor();
                mentor.setRoleId(rs.getInt("RoleID"));
                mentor.setUsername(rs.getString("Username"));
                mentor.setCreateDate(rs.getDate("CreateDate"));
                mentor.setPhone(rs.getString("Phone"));
                mentor.setAddress(rs.getString("Address"));
                mentor.setDateOfBirth(rs.getDate("DateOfBirth"));
                mentor.setFullName(rs.getString("FullName"));
                mentor.setGender(rs.getString("Gender"));
                mentor.setStatus(rs.getString("Status"));
                mentor.setMentorId(rs.getInt("MentorID"));
            }
        } catch (SQLException e) {

        }

        return mentor; // Return the retrieved Mentor object or null if not found
    }

    public Mentor findMentorByID(int id) {
        Mentor mentor = null;
        String sql = "SELECT [MentorID]\n"
                + "      ,[RoleID]\n"
                + "      ,[Username]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "      ,[DateOfBirth]\n"
                + "      ,[FullName]\n"
                + "      ,[Gender]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[Mentor] "
                + "where MentorID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                mentor = new Mentor();
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
            }
        } catch (SQLException e) {

        }

        return mentor; // Return the retrieved Mentor object or null if not found
    }

    public List<Mentor> getAllMentor() {
        List<Mentor> listMentor = new ArrayList<>();
        String sql = "SELECT [MentorID]\n"
                + "      ,[RoleID]\n"
                + "      ,[Username]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "      ,[DateOfBirth]\n"
                + "      ,[FullName]\n"
                + "      ,[Gender]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[Mentor]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
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
                listMentor.add(mentor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listMentor;
    }

    public List<Mentor> getListMentorPagiantion(int page, int numDis) {
        List<Mentor> listMentor = new ArrayList<>();
        String sql = "SELECT [MentorID]\n"
                + "      ,[RoleID]\n"
                + "      ,[Username]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "      ,[DateOfBirth]\n"
                + "      ,[FullName]\n"
                + "      ,[Gender]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[Mentor]\n"
                + "Order by Mentor.MentorID\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT ? ROW ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int numOffSet = (page - 1) * numDis;
            st.setInt(1, numOffSet);
            st.setInt(2, numDis);
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
                listMentor.add(mentor);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return listMentor;
    }

    public boolean changeStatusMentorById(int mentorId, String status) {
        String sql = "UPDATE [dbo].[Mentor]\n"
                + "   SET [Status] = ?\n"
                + " WHERE Mentor.MentorID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setInt(2, mentorId);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Mentor> searchListMentorPagiantion(String search, int page, int numDis) {
        List<Mentor> listMentor = new ArrayList<>();
        String sql = "SELECT DISTINCT Mentor.[MentorID],\n"
                + "       [RoleID],\n"
                + "       [Username],\n"
                + "       Mentor.[CreateDate],\n"
                + "       [Phone],\n"
                + "       [Address],\n"
                + "       [DateOfBirth],\n"
                + "       [FullName],\n"
                + "       [Gender],\n"
                + "       Mentor.[Status]\n"
                + "FROM [dbo].[Mentor]\n"
                + "LEFT JOIN CV ON Mentor.MentorID = CV.MentorID\n"
                + "WHERE Mentor.MentorID = ? OR\n"
                + "      Mentor.Username LIKE ? OR\n"
                + "      Mentor.FullName LIKE ? OR\n"
                + "      CV.JobProfession LIKE ?\n"
                + "ORDER BY Mentor.[MentorID]\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, 0);
            if (converSearch(search)) {
                st.setInt(1, Integer.parseInt(search));
            }
            String searchPattern = "%" + search + "%";
            st.setString(2, searchPattern);
            st.setString(3, searchPattern);
            st.setString(4, searchPattern);
            int numOffSet = (page - 1) * numDis;
            st.setInt(5, numOffSet);
            st.setInt(6, numDis);
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
                listMentor.add(mentor);
            }
        } catch (Exception e) {
        }

        return listMentor;
    }

    public boolean converSearch(String search) {
        try {
            int id = Integer.parseInt(search);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public List<Mentor> searchAllMentor(String search) {
        List<Mentor> listMentor = new ArrayList<>();
        String sql = "SELECT DISTINCT Mentor.[MentorID],\n"
                + "       [RoleID],\n"
                + "       [Username],\n"
                + "       Mentor.[CreateDate],\n"
                + "       [Phone],\n"
                + "       [Address],\n"
                + "       [DateOfBirth],\n"
                + "       [FullName],\n"
                + "       [Gender],\n"
                + "       Mentor.[Status]\n"
                + "FROM [dbo].[Mentor]\n"
                + "LEFT JOIN CV ON Mentor.MentorID = CV.MentorID\n"
                + "WHERE Mentor.MentorID = ? OR\n"
                + "      Mentor.Username LIKE ? OR\n"
                + "      Mentor.FullName LIKE ? OR\n"
                + "      CV.JobProfession LIKE ?\n"
                + "ORDER BY Mentor.[MentorID]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, 0);
            if (converSearch(search)) {
                st.setInt(1, Integer.parseInt(search));
            }
            String searchPattern = "%" + search + "%";
            st.setString(2, searchPattern);
            st.setString(3, searchPattern);
            st.setString(4, searchPattern);
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
                listMentor.add(mentor);
            }
        } catch (Exception e) {
        }

        return listMentor;
    }

    public Mentor getMentorById(int mentorId) {
        Mentor mentor = null;
        String sql = "SELECT * FROM Mentor WHERE MentorID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                mentor = new Mentor();
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mentor;
    }

    public int getTotalMentors() {
        int count = 0;
        String query = """
                       SELECT COUNT(*) AS TotalMentors
                       FROM Mentor;
                       """;

        try (
                PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("TotalMentors");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void updateStatus(int mentorId, String status) {
        String sql = "UPDATE Mentor SET Status = ? WHERE MentorID = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, mentorId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mentor> getAllMentorHaveSkillId(int skillId) {
        List<Mentor> listMentor = new ArrayList<>();
        String sql = "SELECT distinct m.[MentorID]\n"
                + "      ,[RoleID]\n"
                + "      ,[Username]\n"
                + "      ,m.[CreateDate]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "      ,[DateOfBirth]\n"
                + "      ,[FullName]\n"
                + "      ,[Gender]\n"
                + "      ,m.[Status]\n"
                + "  FROM [dbo].[Mentor] m\n"
                + "  right join SkillList sl on m.MentorID = sl.MentorID\n"
                + "  right join Skill s on s.SkillID = sl.SkillID\n"
                + "  where s.SkillID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, skillId);
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
                listMentor.add(mentor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listMentor;
    }

    public static void main(String[] args) {
        MentorDAO act = new MentorDAO();
        //act.insertMentor(2, "hoanganhgp234", new Date(2004, 12, 11), "0961316508", "VN", new Date(2004, 12, 11), "Hoang Anh", "Male", "active");
        System.out.println(act.getTotalMentors());
    }
}
