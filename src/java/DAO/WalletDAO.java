/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Wallet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import Model.Mentee;
import Model.User;
import java.util.HashMap;
import java.util.Map;

public class WalletDAO extends DBContext {

    public Wallet getWalletByUsername(String name) {
        String sql = "SELECT * FROM Wallet WHERE Username = ?";
        Wallet wallet = null;

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, name);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    wallet = new Wallet();
                    wallet.setWalletID(rs.getInt("WalletID"));
                    wallet.setUsername(rs.getString("Username"));
                    wallet.setBalance(rs.getDouble("Balance"));
                    wallet.setHold(rs.getDouble("Hold"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return wallet;
    }
    public boolean addNewWallet(String username) {
        String sql = "INSERT INTO [dbo].[Wallet] ([Balance],[Username],[Hold])   VALUES (0,?,0)";
        try (PreparedStatement rs = connection.prepareStatement(sql)) {
            rs.setString(1, username);
            int rowsInserted = rs.executeUpdate();
            return rowsInserted > 0; // Returns true if the payment was added successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }

    public boolean updateWalletBalanceByUsername(String username, double newBalance) {
        String sql = "UPDATE Wallet SET Balance = ? WHERE Username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, newBalance);
            st.setString(2, username);
            int rowsUpdated = st.executeUpdate(); // Execute the update statement
            return rowsUpdated > 0; // Return true if the update was successful
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean updateWalletAddMoneyBalanceByUsername(String username, double newBalance) {
        String sql = "UPDATE Wallet SET Balance = Balance+? WHERE Username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, newBalance);
            st.setString(2, username);
            int rowsUpdated = st.executeUpdate(); // Execute the update statement
            return rowsUpdated > 0; // Return true if the update was successful
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateHoldByUsername(String username, double Hold) {
        String sql = "UPDATE Wallet SET Hold = ? WHERE Username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, Hold);
            st.setString(2, username);
            int rowsUpdated = st.executeUpdate(); // Execute the update statement
            return rowsUpdated > 0; // Return true if the update was successful
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public Map<String, Float> getHold(int requestId) {
        Map<String, Float> map = new HashMap<>();
        String sql = """
                SELECT m.Username, r.Price 
                FROM Request r
                LEFT JOIN Mentee m ON r.MenteeID = m.MenteeID
                WHERE r.RequestID IN (
                    SELECT rsi.RequestID 
                    FROM RequestSlotItem rsi 
                    JOIN Slot s ON rsi.SlotID = s.SlotID 
                    WHERE rsi.SlotID IN (
                        SELECT SlotID 
                        FROM RequestSlotItem  
                        WHERE RequestID = ?) 
                    AND rsi.RequestID <> ?)
                AND r.Status = 'Open'
                """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, requestId);
            st.setInt(2, requestId);
            ResultSet rs = st.executeQuery();

            // Iterate through the result set and populate the map
            while (rs.next()) {
                String username = rs.getString("Username");
                Float price = rs.getFloat("Price");

                // Sum up the prices if the username already exists in the map
                map.put(username, map.getOrDefault(username, 0f) + price);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }
    public boolean updateHoldRejected(String username, float hold){
        String sql = """
                      Update Wallet 
                      set Hold = (
                      select Hold from Wallet where Username = ?
                      )- ?
                      where Username = ?
                     """;
         try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setFloat(2, hold);
            st.setString(3, username);
            st.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
         return true;
    }

    public static void main(String[] args) {
        WalletDAO act = new WalletDAO();
        System.out.println(act.getWalletByUsername(""));
        act.updateWalletBalanceByUsername("hoanganhgp23", 3000000);
        System.out.println(act.getWalletByUsername("hoanganhgp23"));
    }
}
