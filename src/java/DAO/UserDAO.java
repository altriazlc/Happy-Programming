package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import Model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DBContext {

    // Method to find a user by username and password
    public User findUserByUsername(String a) {
        String sql = "SELECT [RoleID], [Username], [Status], [CreateDate], "
                + "[Email], [Password] "
                + "FROM [dbo].[User] WHERE Username = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, a);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setRoleId(rs.getInt("RoleID"));
                user.setUsername(rs.getString("Username"));
                user.setStatus(rs.getString("Status"));
                user.setCreateDate(rs.getDate("CreateDate"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findUserPass(String username, String password) {
        String sql = "SELECT [RoleID], [Username], [Status], [CreateDate], "
                + "[Email], [Password] "
                + "FROM [dbo].[User] WHERE Username = ? AND Password = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setRoleId(rs.getInt("RoleID"));
                user.setUsername(rs.getString("Username"));
                user.setStatus(rs.getString("Status"));
                user.setCreateDate(rs.getDate("CreateDate"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to insert a new user into the database
    public void insertUser(User user) {
        String sql = "INSERT INTO [dbo].[User] ([RoleID], [Username], [Status], [CreateDate], "
                + "[Email], [Password]) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user.getRoleId());
            st.setString(2, user.getUsername());
            st.setString(3, user.getStatus());
            st.setDate(4, new java.sql.Date(user.getCreateDate().getTime())); // Convert java.util.Date to java.sql.Date
            st.setString(5, user.getEmail());
            st.setString(6, user.getPassword());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE [dbo].[User] SET [RoleID] = ?, [Status] = ?, [CreateDate] = ?, "
                + "[Email] = ?, [Password] = ? WHERE [Username] = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, user.getRoleId());
            st.setString(2, user.getStatus());
            st.setDate(3, new java.sql.Date(user.getCreateDate().getTime()));
            st.setString(4, user.getEmail());
            st.setString(5, user.getPassword());
            st.setString(6, user.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findUserByEmail(String mail) {
        String sql = "SELECT [RoleID], [Username], [Status], [CreateDate], "
                + "[Email], [Password] "
                + "FROM [dbo].[User] WHERE  Email = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, mail);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setRoleId(rs.getInt("RoleID"));
                user.setUsername(rs.getString("Username"));
                user.setStatus(rs.getString("Status"));
                user.setCreateDate(rs.getDate("CreateDate"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //change password lam lai
    public User getUserByUsername(String username) {
        String query = "SELECT * FROM [User] WHERE Username = ?";
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
                // Map other fields as needed
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //update user's password
    public boolean updatePassword(String username, String newPassword) {
        String query = "UPDATE [User] SET [Password] = ? WHERE Username = ?";
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, newPassword);
            ps.setString(2, username);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;  // return true if update is successful
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //Updatev1.1 - thay doi thanh mk tam thoi
    // Get user by email and account (username)
    public User getUserByEmailAndAccount(String email, String account) {
        String sql = "SELECT * FROM [User] WHERE Email = ? AND Username = ?";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, account);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("Username"));
                user.setRoleId(rs.getInt("RoleID"));
                user.setStatus(rs.getString("Status"));
                user.setCreateDate(rs.getDate("CreateDate"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // User not found
    }

    // Save temporary password in PasswordReset table
    public void createNewPass(String username, String tempPassword) {
        String sql = "BEGIN TRANSACTION;\n"
                + "\n"
                + "UPDATE PasswordReset\n"
                + "SET TemporaryPassword = ?\n"
                + "WHERE Username = ?;\n"
                + "\n"
                + "UPDATE [User]\n"
                + "SET Password = ?\n"
                + "WHERE Username = ?;\n"
                + "\n"
                + "COMMIT TRANSACTION;";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tempPassword);
            stmt.setString(2, username);
            stmt.setString(3, tempPassword);
            stmt.setString(4, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> gettAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = """
                     SELECT * FROM [User] 
                     WHERE RoleID NOT IN (3, 4)
                     ORDER BY CreateDate DESC;
                     """;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setUsername(rs.getString("Username"));
                user.setRoleId(rs.getInt("RoleID"));
                user.setStatus(rs.getString("Status"));
                user.setCreateDate(rs.getDate("CreateDate"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));

                // Add the user to the list
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Object[]> getUserCreationStats() {
        List<Object[]> stats = new ArrayList<>();
        String sql = """
                     SELECT MONTH(CreateDate) AS month, 
                            YEAR(CreateDate) AS year, 
                            COUNT(*) AS user_count
                     FROM [User]
                     WHERE RoleID NOT IN (3, 4)
                     GROUP BY YEAR(CreateDate), 
                              MONTH(CreateDate)
                     ORDER BY YEAR(CreateDate), 
                              MONTH(CreateDate);
                     """;

        try (
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int month = rs.getInt("month");
                int year = rs.getInt("year");
                int userCount = rs.getInt("user_count");
                stats.add(new Object[]{month, year, userCount}); // Adding an array to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }

    public static void main(String[] args) {
        UserDAO u = new UserDAO();
        String password = u.encrypt("yVMkvELb");
        System.out.println(password);
        System.out.println(u.findUserPass("User1", password));
    }

    public String encrypt(String password) {
        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            encrypted.append((char) (c + 5)); // Shift character by key
        }

        return encrypted.toString();
    }
    // Main method for testing
//   public static void main(String[] args) throws SQLException {
//        UserDAO u = new UserDAO();
    /*User newUser = new User();
        newUser.setRoleId(1);
        newUser.setUsername("hoanganhgp2");
        newUser.setStatus("active");
        LocalDate localDob = LocalDate.parse("2024-09-24");
        java.sql.Date dob = java.sql.Date.valueOf(localDob);
        newUser.setCreateDate(dob);  // current date
        newUser.setEmail("john.doe@example.com");
        newUser.setPassword("securepassword");
        System.out.println(newUser);
        u.updateUser(newUser);*/
//        System.out.println(u.findUserPass("user1", "123"));
//    }

}
