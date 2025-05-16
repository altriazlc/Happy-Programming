
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Attendance;
import Model.Schedule;
import Model.Slot;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;

/**
 *
 * @author tuong
 */
public class AttendanceDAO extends DBContext {

    public List<Attendance> getSchduleByMentorID(int mentorID, LocalDate start, LocalDate end) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT a.[AttendID]\n"
                + "      ,r.[RequestID]\n"
                + "      ,a.[slotDate]\n"
                + "      ,a.[Status]\n"
                + "      ,r.[MenteeID]\n"
                + "      ,s.[StartTime]\n"
                + "      ,s.[EndTime]\n"
                + "      ,r.[Title]\n"
                + "      ,s.[DayInWeek]\n"
                + "  FROM [dbo].[Attendance] a\n"
                + "  LEFT JOIN [RequestSlotItem] rs ON a.[RequestSlotItem] = rs.[RequestSlotItem]\n"
                + "  LEFT JOIN [Slot] s ON s.[SlotID] = rs.[SlotID]\n"
                + "  LEFT JOIN [Request] r ON r.[RequestID] = rs.[RequestID]\n"
                + "  WHERE r.[MentorID] = ? \n"
                + "    AND a.[slotDate] BETWEEN ? AND ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, mentorID);
            preparedStatement.setDate(2, java.sql.Date.valueOf(start));
            preparedStatement.setDate(3, java.sql.Date.valueOf(end));
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Attendance curAttend = new Attendance();
                curAttend.setAttendID(rs.getInt("AttendID"));
                curAttend.setRequestID(rs.getInt("RequestID"));
                curAttend.setDate(rs.getDate("slotDate").toLocalDate());
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                curAttend.setStartTime(startTime);
                curAttend.setEndTime(endTime);
                curAttend.setStatus(rs.getString("Status"));
                curAttend.setTitle(rs.getString("Title"));
                curAttend.setDayInWeek(rs.getString("DayInWeek"));
                curAttend.setMenteeID(rs.getInt("MenteeID"));
                list.add(curAttend);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Attendance> getSchduleByMenteeID(int menteeID, LocalDate start, LocalDate end) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT a.[AttendID]\n"
                + "      ,r.[RequestID]\n"
                + "      ,a.[slotDate]\n"
                + "      ,a.[Status]\n"
                + "      ,r.[MenteeID]\n"
                + "      ,s.[StartTime]\n"
                + "      ,s.[EndTime]\n"
                + "      ,r.[Title]\n"
                + "      ,s.[DayInWeek]\n"
                + "  FROM [dbo].[Attendance] a\n"
                + "  LEFT JOIN [RequestSlotItem] rs ON a.[RequestSlotItem] = rs.[RequestSlotItem]\n"
                + "  LEFT JOIN [Slot] s ON s.[SlotID] = rs.[SlotID]\n"
                + "  LEFT JOIN [Request] r ON r.[RequestID] = rs.[RequestID]\n"
                + "  WHERE r.[MenteeID] = ? \n"
                + "    AND a.[slotDate] BETWEEN ? AND ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, menteeID);
            preparedStatement.setDate(2, java.sql.Date.valueOf(start));
            preparedStatement.setDate(3, java.sql.Date.valueOf(end));
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Attendance curAttend = new Attendance();
                curAttend.setAttendID(rs.getInt("AttendID"));
                curAttend.setRequestID(rs.getInt("RequestID"));
                curAttend.setDate(rs.getDate("slotDate").toLocalDate());
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                curAttend.setStartTime(startTime);
                curAttend.setEndTime(endTime);
                curAttend.setStatus(rs.getString("Status"));
                curAttend.setTitle(rs.getString("Title"));
                curAttend.setDayInWeek(rs.getString("DayInWeek"));
                curAttend.setMenteeID(rs.getInt("MenteeID"));
                list.add(curAttend);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Attendance> getSchduleByRequestID(int requestID, LocalDate start, LocalDate end) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT a.[AttendID]\n"
                + "      ,r.[RequestID]\n"
                + "      ,a.[slotDate]\n"
                + "      ,a.[Status]\n"
                + "      ,r.[MenteeID]\n"
                + "      ,s.[StartTime]\n"
                + "      ,s.[EndTime]\n"
                + "      ,r.[Title]\n"
                + "      ,s.[DayInWeek]\n"
                + "  FROM [dbo].[Attendance] a\n"
                + "  LEFT JOIN [RequestSlotItem] rs ON a.[RequestSlotItem] = rs.[RequestSlotItem]\n"
                + "  LEFT JOIN [Slot] s ON s.[SlotID] = rs.[SlotID]\n"
                + "  LEFT JOIN [Request] r ON r.[RequestID] = rs.[RequestID]\n"
                + "  WHERE r.[RequestID] = ? \n"
                + "    AND a.[slotDate] BETWEEN ? AND ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, requestID);
            preparedStatement.setDate(2, java.sql.Date.valueOf(start));
            preparedStatement.setDate(3, java.sql.Date.valueOf(end));
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Attendance curAttend = new Attendance();
                curAttend.setAttendID(rs.getInt("AttendID"));
                curAttend.setRequestID(rs.getInt("RequestID"));
                curAttend.setDate(rs.getDate("slotDate").toLocalDate());
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                curAttend.setStartTime(startTime);
                curAttend.setEndTime(endTime);
                curAttend.setStatus(rs.getString("Status"));
                curAttend.setTitle(rs.getString("Title"));
                curAttend.setDayInWeek(rs.getString("DayInWeek"));
                curAttend.setMenteeID(rs.getInt("MenteeID"));
                list.add(curAttend);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Attendance> getAttendedByMenteeID(int menteeID) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    a.[AttendID],\n"
                + "    r.[RequestID],\n"
                + "    a.[slotDate],\n"
                + "    a.[Status],\n"
                + "    r.[MenteeID],\n"
                + "    s.[StartTime],\n"
                + "    s.[EndTime],\n"
                + "    r.[Title],\n"
                + "    s.[DayInWeek]\n"
                + "FROM \n"
                + "    [dbo].[Attendance] a\n"
                + "    LEFT JOIN [RequestSlotItem] rs ON a.[RequestSlotItem] = rs.[RequestSlotItem]\n"
                + "    LEFT JOIN [Slot] s ON s.[SlotID] = rs.[SlotID]\n"
                + "    LEFT JOIN [Request] r ON r.[RequestID] = rs.[RequestID]\n"
                + "WHERE \n"
                + "    r.[MenteeID] = ?\n"
                + "    AND a.[Status] LIKE 'Attended';";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, menteeID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Attendance curAttend = new Attendance();
                curAttend.setAttendID(rs.getInt("AttendID"));
                curAttend.setRequestID(rs.getInt("RequestID"));
                curAttend.setDate(rs.getDate("slotDate").toLocalDate());
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                curAttend.setStartTime(startTime);
                curAttend.setEndTime(endTime);
                curAttend.setStatus(rs.getString("Status"));
                curAttend.setTitle(rs.getString("Title"));
                curAttend.setDayInWeek(rs.getString("DayInWeek"));
                curAttend.setMenteeID(rs.getInt("MenteeID"));
                list.add(curAttend);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean updateStatusAttendance(int attendID, String status) {
        boolean updateSuccess = true;
        String sql = "UPDATE [dbo].[Attendance]\n"
                + "   SET [Status] = ?\n"
                + " WHERE AttendID =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(2, attendID);
            st.setString(1, status);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean updateStatusAttendanceByDay() {
        String sql = """
                     update Attendance set Status='Absent' where (slotDate + 1)<getdate() and Status='Not yet';
                     update Attendance set Status='Not yet' where slotDate>getdate();
                     """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

    }

    public List<Attendance> getAllSlotOfRequest(int requestID) {
        List<Attendance> list = new ArrayList<>();
        String sql = "select rs.RequestSlotItem,s.DayInWeek \n"
                + "from Slot s \n"
                + "left join RequestSlotItem rs on s.SlotID = rs.SlotID\n"
                + "left join Request r on rs.RequestID = r.RequestID\n"
                + "WHERE r.RequestID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, requestID);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Attendance curAttend = new Attendance();
                curAttend.setRequestSlotItem(rs.getInt("RequestSlotItem"));
                curAttend.setDayInWeek(rs.getString("DayInWeek"));
                list.add(curAttend);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Schedule> getListOfSchedulebyMenteeId(int menteeId) {
        List<Schedule> list = new ArrayList<>();
        String sql = """
                     select r.RequestID,r.MenteeID,r.MentorID,r.Status,r.Price,r.Framework,s.DayInWeek,r.StartDate,r.EndDate,s.StartTime,s.EndTime,a.slotDate,a.Status[aStatus],r.SkillID
                     from Request r join RequestSlotItem rs on r.RequestID=rs.RequestID join Slot s on rs.SlotID=s.SlotID join Attendance a on a.RequestSlotItem=rs.RequestSlotItem
                     where r.MenteeID = ?
                     """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, menteeId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Schedule s = new Schedule();
                s.setRequestId(rs.getInt("RequestID"));
                s.setMenteeId(rs.getInt("MenteeID"));
                s.setMentorId(rs.getInt("MentorID"));
                s.setStatus(rs.getString("Status"));
                s.setPrice(rs.getFloat("Price"));
                s.setFramework(rs.getString("Framework"));
                s.setDayInWeek(rs.getString("DayInWeek"));
                s.setStartDate(rs.getDate("StartDate").toLocalDate());
                s.setEndDate(rs.getDate("EndDate").toLocalDate());
                s.setStartTime(rs.getTime("StartTime").toLocalTime());
                s.setEndTime(rs.getTime("EndTime").toLocalTime());
                s.setSlotDate(rs.getDate("slotDate").toLocalDate());
                s.setAttendanceStatus(rs.getString("aStatus"));
                s.setSkillId(rs.getInt("SkillID"));

                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean addAttendance(Attendance curAttendance) {
        String sql = "INSERT INTO [dbo].[Attendance]\n"
                + "           ([RequestSlotItem]\n"
                + "           ,[slotDate]\n"
                + "           ,[Status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, curAttendance.getRequestSlotItem());
            st.setDate(2, Date.valueOf(curAttendance.getDate()));
            st.setString(3, curAttendance.getStatus());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        AttendanceDAO act = new AttendanceDAO();
        LocalDate start = LocalDate.parse("2024-10-21");
        LocalDate end = LocalDate.parse("2024-10-27");
        List<Attendance> list = act.getSchduleByMentorID(2, start, end);
        System.out.println(list.get(0).getMenteeID());
    }

}
