/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Slot;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author nhhag
 */
public class SlotDAO extends DBContext {

    public List<Slot> getSlotsByMentorId(int mentorId) {
        List<Slot> slots = new ArrayList<>();
        String sql = "SELECT SlotID, MentorID, StartTime, EndTime, DayInWeek, Status\n"
                + "                 FROM Slot \n"
                + "                 WHERE MentorID = ? and (Status like 'unavailable' or  Status like 'available')\n"
                + "                \n"
                + "                              ORDER BY CASE DayInWeek \n"
                + "                                WHEN 'Monday' THEN 1 \n"
                + "                               WHEN 'Tuesday' THEN 2 \n"
                + "                                WHEN 'Wednesday' THEN 3 \n"
                + "                                WHEN 'Thursday' THEN 4 \n"
                + "                               WHEN 'Friday' THEN 5 \n"
                + "                               WHEN 'Saturday' THEN 6 \n"
                + "                               WHEN 'Sunday' THEN 7 \n"
                + "                                ELSE 8 END;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, mentorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int slotId = resultSet.getInt("SlotID");
                LocalTime startTime = resultSet.getTime("StartTime").toLocalTime();
                LocalTime endTime = resultSet.getTime("EndTime").toLocalTime();
                String dayInWeek = resultSet.getString("DayInWeek");
                String status = resultSet.getString("Status");

                Slot slot = new Slot(slotId, mentorId, startTime, endTime, dayInWeek, status);
                slots.add(slot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return slots;
    }

    public Slot getSlotsById(int Id) {
        String sql = "SELECT SlotID, MentorID, StartTime, EndTime, DayInWeek, Status FROM Slot WHERE SlotID = ? ";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int slotId = rs.getInt("SlotID");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                String dayInWeek = rs.getString("DayInWeek");
                String status = rs.getString("Status");
                int mentorId = rs.getInt("mentorID");

                Slot slot = new Slot(slotId, mentorId, startTime, endTime, dayInWeek, status);
                return slot;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Slot getSlotsById2(int Id) {
        String sql = "SELECT * FROM Slot WHERE SlotID = ? ";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int slotId = rs.getInt("SlotID");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                String dayInWeek = rs.getString("DayInWeek");
                String status = rs.getString("Status");
                int mentorId = rs.getInt("mentorID");
                int cvid = rs.getInt("CVID");

                Slot slot = new Slot(slotId, mentorId, startTime, endTime, dayInWeek, status, cvid);
                return slot;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public void deleteSlot(int Id) {
        String sql = "Update Slot set Status = 'deleted' where SlotID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Slot> getValidSlotByRequestId(int requestId) {
        List<Slot> listSlot = new ArrayList<>();
        String sql = "SELECT s.[SlotID]\n"
                + "      ,[MentorID]\n"
                + "      ,[StartTime]\n"
                + "      ,[EndTime]\n"
                + "      ,[DayInWeek]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[Slot]  s \n"
                + "right join RequestSlotItem rs \n"
                + "on s.SlotID = rs.SlotID\n"
                + "where rs.RequestID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, requestId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int slotId = resultSet.getInt("SlotID");
                LocalTime startTime = resultSet.getTime("StartTime").toLocalTime();
                LocalTime endTime = resultSet.getTime("EndTime").toLocalTime();
                String dayInWeek = resultSet.getString("DayInWeek");
                String status = resultSet.getString("Status");

                Slot slot = new Slot(slotId, requestId, startTime, endTime, dayInWeek, status);
                listSlot.add(slot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSlot;
    }

    public List<Slot> getSlotInDate(LocalDate start, LocalDate end, int requestID) {
        List<Slot> listSlot = new ArrayList<>();
        String sql = "SELECT \n"
                + "    s.[SlotID],\n"
                + "    s.[MentorID],\n"
                + "    [StartTime],\n"
                + "    [EndTime],\n"
                + "    [DayInWeek],\n"
                + "    s.[Status],\n"
                + "    r.StartDate,\n"
                + "    r.EndDate\n"
                + "FROM \n"
                + "    [dbo].[Slot] s \n"
                + "RIGHT JOIN \n"
                + "    RequestSlotItem rs ON s.SlotID = rs.SlotID \n"
                + "RIGHT JOIN \n"
                + "    Request r ON r.RequestID = rs.RequestID\n"
                + "WHERE \n"
                + "    rs.RequestID = ?\n"
                + "    AND (r.StartDate <= ? \n"
                + "    AND r.EndDate >= ?)\n"
                + "ORDER BY \n"
                + "    s.StartTime ASC;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, requestID);
            preparedStatement.setDate(2, java.sql.Date.valueOf(start));
            preparedStatement.setDate(3, java.sql.Date.valueOf(end));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int slotId = resultSet.getInt("SlotID");
                LocalTime startTime = resultSet.getTime("StartTime").toLocalTime();
                LocalTime endTime = resultSet.getTime("EndTime").toLocalTime();
                String dayInWeek = resultSet.getString("DayInWeek");
                String status = resultSet.getString("Status");

                Slot slot = new Slot(slotId, requestID, startTime, endTime, dayInWeek, status);
                listSlot.add(slot);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework instead
        }
        return listSlot;
    }

    public void updateSlotStatusToUnavailable(int slotId) throws SQLException {
        String query = "UPDATE Slot SET status = 'unavailable' WHERE slotID = ?";
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, slotId);
            ps.executeUpdate();
        }
    }

    public void updateSlotStatusToAvailable(int slotId) throws SQLException {
        String query = "UPDATE Slot SET status = 'available' WHERE slotID = ?";
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, slotId);
            ps.executeUpdate();
        }
    }

    public static void main(String[] args) {
        SlotDAO slotDAO = new SlotDAO();
        slotDAO.deleteAllActiveSlot(7);
        // Correcting the date initialization using LocalDate.of()
        LocalDate start = LocalDate.of(2022, 1, 7); // Start date: January 7, 2022
        LocalDate end = LocalDate.of(2022, 1, 8);   // End date: January 8, 2022

//        // Fetching slots in the given date range for request ID 1
//        List<Slot> slots = slotDAO.getSlotInDate(start, end, 1);
//
//        // Printing the fetched slots
//        // Assuming getSlotsById is a method that retrieves slots by request ID
//        System.out.println(slots.get(3).getDayInWeek());
    }

    public List<Slot> getListofSlotsByCVID(int cvId) {
        List<Slot> slots = new ArrayList<>();
        String sql = "select * from Slot where CVID=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cvId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int slotId = rs.getInt("SlotID");
                int mentorId = rs.getInt("MentorID");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                String dayInWeek = rs.getString("DayInWeek");
                String status = rs.getString("Status");
                int cvid = rs.getInt("CVID");

                Slot slot = new Slot(slotId, mentorId, startTime, endTime, dayInWeek, status, cvid);
                slots.add(slot);
            }
            return slots;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Slot> getListofActiveSlotsByMentorId(int id) {
        List<Slot> slots = new ArrayList<>();
        String sql = "select * from Slot where MentorID=? and Status = 'available'";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int slotId = rs.getInt("SlotID");
                int mentorId = rs.getInt("MentorID");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                String dayInWeek = rs.getString("DayInWeek");
                String status = rs.getString("Status");
                int cvid = rs.getInt("CVID");

                Slot slot = new Slot(slotId, mentorId, startTime, endTime, dayInWeek, status, cvid);
                slots.add(slot);
            }
            return slots;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public List<Slot> getListofActiveSlotsJoinRequestSlotByMentorId(int id) {
        List<Slot> slots = new ArrayList<>();
        String sql = "select s.* from Slot s join RequestSlotItem r on s.SlotID=r.SlotID where s.MentorID=? and (s.Status = 'available' or s.Status = 'unavailable')";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int slotId = rs.getInt("SlotID");
                int mentorId = rs.getInt("MentorID");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                String dayInWeek = rs.getString("DayInWeek");
                String status = rs.getString("Status");
                int cvid = rs.getInt("CVID");

                Slot slot = new Slot(slotId, mentorId, startTime, endTime, dayInWeek, status, cvid);
                slots.add(slot);
            }
            return slots;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public List<Slot> getListofUnActiveSlotsJoinRequestSlotByMentorId(int id) {
        List<Slot> slots = new ArrayList<>();
        String sql = "select s.* from Slot s join RequestSlotItem r on s.SlotID=r.SlotID where s.MentorID=? and ( s.Status = 'unavailable')";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int slotId = rs.getInt("SlotID");
                int mentorId = rs.getInt("MentorID");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                String dayInWeek = rs.getString("DayInWeek");
                String status = rs.getString("Status");
                int cvid = rs.getInt("CVID");

                Slot slot = new Slot(slotId, mentorId, startTime, endTime, dayInWeek, status, cvid);
                slots.add(slot);
            }
            return slots;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<Slot> getListofActiveSlotsByRequestId(int id) {
        List<Slot> slots = new ArrayList<>();
        String sql = """
                     select s.* from Request r
                     join RequestSlotItem rs on r.RequestID = rs.RequestID
                     join Slot s on s.SlotID = rs.SlotID
                     where r.RequestID = ? 
                     """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int slotId = rs.getInt("SlotID");
                int mentorId = rs.getInt("MentorID");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                String dayInWeek = rs.getString("DayInWeek");
                String status = rs.getString("Status");
                int cvid = rs.getInt("CVID");

                Slot slot = new Slot(slotId, mentorId, startTime, endTime, dayInWeek, status, cvid);
                slots.add(slot);
            }
            return slots;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<Slot> getListofSlotsByMentorId(int id) {
        List<Slot> slots = new ArrayList<>();
        String sql = "select * from Slot where MentorID=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int slotId = rs.getInt("SlotID");
                int mentorId = rs.getInt("MentorID");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                String dayInWeek = rs.getString("DayInWeek");
                String status = rs.getString("Status");
                int cvid = rs.getInt("CVID");

                Slot slot = new Slot(slotId, mentorId, startTime, endTime, dayInWeek, status, cvid);
                slots.add(slot);
            }
            return slots;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Slot> getListofInactiveSlotsByMentorId(int id) {
        List<Slot> slots = new ArrayList<>();
        String sql = "select * from Slot where MentorID=? and Status = 'inactive'";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int slotId = rs.getInt("SlotID");
                int mentorId = rs.getInt("MentorID");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                String dayInWeek = rs.getString("DayInWeek");
                String status = rs.getString("Status");
                int cvid = rs.getInt("CVID");

                Slot slot = new Slot(slotId, mentorId, startTime, endTime, dayInWeek, status, cvid);
                slots.add(slot);
            }
            return slots;
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

    public boolean updateSlot(Slot slot) {
        String sql = "UPDATE [dbo].[Slot]\n"
                + "   SET [MentorID] = ?\n"
                + "      ,[StartTime] = ?\n"
                + "      ,[EndTime] = ?\n"
                + "      ,[DayInWeek] = ?\n"
                + "      ,[Status] = ?\n"
                + "      ,[CVID] = ?\n"
                + " WHERE SlotID =?";
        try (PreparedStatement rs = connection.prepareStatement(sql)) {
            rs.setInt(1, slot.getMentorID());
            rs.setTime(2, java.sql.Time.valueOf(slot.getStartTime()));
            rs.setTime(3, java.sql.Time.valueOf(slot.getEndTime()));
            rs.setString(4, slot.getDayInWeek());
            rs.setString(5, slot.getStatus());
            rs.setInt(6, slot.getCVID());
            rs.setInt(7, slot.getSlotID());
            int rowsInserted = rs.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllInactiveSlot(int mentorId) {
        String sql = "Update [dbo].[Slot] set Status='deleted' WHERE MentorID=? and Status='inactive'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorId);
            int query = st.executeUpdate();
            return query > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteAllActiveSlot(int mentorId) {
        String sql = "Update [dbo].[Slot] set Status='deleted' WHERE MentorID=? and Status='available'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorId);
            int query = st.executeUpdate();
            return query > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean setSlotInactiveToActivebyMentorId(int mentorId) {
        String sql = "update Slot set Status ='available' where MentorID=? and Status='inactive'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorId);
            int query = st.executeUpdate();
            return query > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean setSlotActiveToInctivebyMentorId(int mentorId) {
        String sql = "update Slot set Status ='inactive' where MentorID=? and Status='available'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorId);
            int query = st.executeUpdate();
            return query > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
}
