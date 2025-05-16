package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import Model.Mentee;
import Model.User;
import java.util.ArrayList;
import java.util.List;

public class MenteeDAO extends DBContext {

    public void insertMentee(int roleId, byte[] avatar, String username, Date createDate, String email,
            String phone, String address, Date dateOfBirth, String fullName,
            String gender, String status) throws SQLException {
        String sql = "INSERT INTO Mentee (RoleID, Avatar, Username, CreateDate, Email, Phone, Address, "
                + "DateOfBirth, FullName, Gender, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, roleId);
            st.setBytes(2, avatar);
            st.setString(3, username);
            st.setDate(4, new java.sql.Date(createDate.getTime()));
            st.setString(5, email);
            st.setString(6, phone);
            st.setString(7, address);
            st.setDate(8, new java.sql.Date(dateOfBirth.getTime()));
            st.setString(9, fullName);
            st.setString(10, gender);
            st.setString(11, status);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean updateMentee(Mentee mentee) throws SQLException {
        String sql = "UPDATE Mentee SET RoleID = ?, Avatar = ?, Username = ?, CreateDate = ?, "
                + "Phone = ?, Address = ?, DateOfBirth = ?, FullName = ?, Gender = ?, "
                + "Status = ? WHERE username = ?"; // Assuming MenteeID is the unique identifier

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, mentee.getRoleId());
            st.setBytes(2, mentee.getAvatar());
            st.setString(3, mentee.getUsername());
            st.setDate(4, new Date(mentee.getCreateDate().getTime()));
            st.setString(5, mentee.getPhone());
            //xoa email
            st.setString(6, mentee.getAddress());
            st.setDate(7, new Date(mentee.getDateOfBirth().getTime()));
            st.setString(8, mentee.getFullName());
            st.setString(9, mentee.getGender());
            st.setString(10, mentee.getStatus());
            st.setString(11, mentee.getUsername());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Mentee findMenteeByUsername(String username) {
        Mentee mentee = null;
        String sql = "SELECT [MenteeID]\n"
                + "      ,[RoleID]\n"
                + "      ,[Avatar]\n"
                + "      ,[Username]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "      ,[DateOfBirth]\n"
                + "      ,[FullName]\n"
                + "      ,[Gender]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[Mentee] "
                + "where Username = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                mentee = new Mentee();
                mentee.setMenteeId(rs.getInt("MenteeID"));
                mentee.setRoleId(rs.getInt("RoleID"));
                mentee.setUsername(rs.getString("Username"));
                byte[] avatar = rs.getBytes("Avatar");
                if (avatar != null) {
                    mentee.setAvatar(rs.getBytes("Avatar"));
                }
                mentee.setCreateDate(rs.getDate("CreateDate"));
                //xoa email
                mentee.setPhone(rs.getString("Phone"));
                mentee.setAddress(rs.getString("Address"));
                mentee.setDateOfBirth(rs.getDate("DateOfBirth"));
                mentee.setFullName(rs.getString("FullName"));
                mentee.setGender(rs.getString("Gender"));
                mentee.setStatus(rs.getString("Status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return mentee; // Return the retrieved Mentor object or null if not found
    }

    public List<Mentee> getAllMentee() {
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
            e.printStackTrace();
        }

        return listMen;
    }

    public List<Mentee> getListMenteePagination(int page, int display) {
        List<Mentee> listMen = new ArrayList<>();
        String sql = "SELECT [MenteeID]\n"
                + "      ,[RoleID]\n"
                + "      ,[Avatar]\n"
                + "      ,[Username]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Email]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "      ,[DateOfBirth]\n"
                + "      ,[FullName]\n"
                + "      ,[Gender]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[Mentee]\n"
                + "  order by FullName asc\n"
                + "  OFFSET ? ROWS\n"
                + "  FETCH NEXT ? ROW  ONLY";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int offset = (page - 1) * display;
            st.setInt(1, offset);
            st.setInt(2, display);
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
            e.printStackTrace();
        }

        return listMen;
    }

    public List<Mentee> getListMenteeSearchPagination(String search, int page, int display) {
        List<Mentee> listMen = new ArrayList<>();
        String sql = "SELECT distinct [MenteeID]\n"
                + "      ,[RoleID]\n"
                + "      ,[Avatar]\n"
                + "      ,[Username]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Email]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "      ,[DateOfBirth]\n"
                + "      ,[FullName]\n"
                + "      ,[Gender]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[Mentee]\n"
                + "  Where MenteeID = ?\n"
                + " OR  Username like ?\n"
                + " OR FullName like ?\n"
                + " order by FullName asc\n"
                + " OFFSET ? ROWS\n"
                + " FETCH NEXT ? ROW  ONLY";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, 0);
            if (converSearch(search)) {
                st.setInt(1, Integer.parseInt(search));
            }
            String searchPattern = "%" + search + "%";
            st.setString(2, searchPattern);
            st.setString(3, searchPattern);
            int offset = (page - 1) * display;
            st.setInt(4, offset);
            st.setInt(5, display);
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
            e.printStackTrace();
        }

        return listMen;
    }

    public boolean converSearch(String search) {
        try {
            int id = Integer.parseInt(search);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Mentee getMenteeByID(int menteeId) {
        Mentee mentee = null;
        String sql = "SELECT [MenteeID]\n"
                + "      ,[RoleID]\n"
                + "      ,[Avatar]\n"
                + "      ,[Username]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "      ,[DateOfBirth]\n"
                + "      ,[FullName]\n"
                + "      ,[Gender]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[Mentee] "
                + "where MenteeID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, menteeId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                mentee = new Mentee();
                mentee.setMenteeId(rs.getInt("MenteeID"));
                mentee.setRoleId(rs.getInt("RoleID"));
                mentee.setUsername(rs.getString("Username"));
                byte[] avatar = rs.getBytes("Avatar");
                if (avatar != null) {
                    mentee.setAvatar(rs.getBytes("Avatar"));
                }
                mentee.setCreateDate(rs.getDate("CreateDate"));
                //xoa email
                mentee.setPhone(rs.getString("Phone"));
                mentee.setAddress(rs.getString("Address"));
                mentee.setDateOfBirth(rs.getDate("DateOfBirth"));
                mentee.setFullName(rs.getString("FullName"));
                mentee.setGender(rs.getString("Gender"));
                mentee.setStatus(rs.getString("Status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return mentee; // Return the retrieved Mentor object or null if not found
    }

    public int getTotalMentees() {
        int count = 0;
        String query = "SELECT COUNT(*) AS total FROM Mentee WHERE status = 'active'";

        try (
                PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void updateStatus(int menteeId, String status) {
        String sql = "UPDATE Mentee SET Status = ? WHERE MenteeID = ?";
        try (
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, menteeId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MenteeDAO actMentee = new MenteeDAO();
        List<Mentee> list1 = actMentee.getListMenteeSearchPagination("boni", 1, 10);
        System.out.println(list1.get(4).getFullName());
    }

}
