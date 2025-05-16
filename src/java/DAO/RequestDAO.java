/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.CV;
import Model.Mentor;
import Model.Request;
import Model.RequestSlotData;
import Model.RequestSlotItem;
import Model.SkillList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Time;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author nhhag
 */
public class RequestDAO extends DBContext {

    public List<RequestSlotItem> getDuplicateSlot(int id, int id2) {
        List<RequestSlotItem> requests = new ArrayList<>();

        String sql = "SELECT * FROM RequestSlotItem r\n"
                + "join Request rq on r.RequestID = rq.RequestID\n"
                + "where rq.Status = 'Open' and rq.MenteeID=? and MentorID=?";

        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, id2);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                RequestSlotItem s = new RequestSlotItem();
                s.setRequestId(rs.getInt("RequestID"));
                s.setRequestSlotItemId(rs.getInt("RequestSlotItem"));
                s.setSlotId(rs.getInt("SlotID"));
                requests.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests;
    }

    public List<RequestSlotItem> getDuplicateSlotByRequestID(int id, int id2, int id3) {
        List<RequestSlotItem> requests = new ArrayList<>();

        String sql = "SELECT * \n"
                + "FROM RequestSlotItem r\n"
                + "JOIN Request rq ON r.RequestID = rq.RequestID\n"
                + "WHERE rq.Status = 'Open' \n"
                + "  AND rq.MenteeID = ? \n"
                + "  AND MentorID = ?\n"
                + "  AND rq.RequestID != ?;";

        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, id2);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                RequestSlotItem s = new RequestSlotItem();
                s.setRequestId(rs.getInt("RequestID"));
                s.setRequestSlotItemId(rs.getInt("RequestSlotItem"));
                s.setSlotId(rs.getInt("SlotID"));
                requests.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests;
    }

    public List<RequestSlotItem> getSlotByRequestID(int id) {
        List<RequestSlotItem> requests = new ArrayList<>();

        String sql = "select *\n"
                + "from RequestSlotItem rq\n"
                + "where rq.Requestid = ?";

        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                int requestSlotItemID = rs.getInt("RequestSlotItem");
                int slotId = rs.getInt("SlotID");

                RequestSlotItem requestSlotItem = new RequestSlotItem(requestSlotItemID, id, slotId);

                requests.add(requestSlotItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests;
    }

    public void insertRequest(Request request) {
        String sql = "INSERT INTO [dbo].[Request]\n"
                + "           ([MentorID]\n"
                + "           ,[MenteeID]\n"
                + "           ,[Price]\n"
                + "           ,[Note]\n"
                + "           ,[CreateDate]\n"
                + "           ,[Status]\n"
                + "           ,[Title]\n"
                + "           ,[Framework]\n"
                + "           ,[StartDate]\n"
                + "           ,[EndDate]\n"
                + "           ,[SkillID])\n"
                + "     VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            // Set parameters for the query
            st.setInt(1, request.getMentorId());
            st.setInt(2, request.getMenteeId());
            st.setFloat(3, request.getPrice());
            st.setString(4, request.getNote());
            st.setDate(5, Date.valueOf(request.getCreateDate()));
            st.setString(6, request.getStatus());
            st.setString(7, request.getTitle());
            st.setString(8, request.getFramework());
            st.setDate(9, Date.valueOf(request.getStartDate()));
            st.setDate(10, Date.valueOf(request.getEndDate()));
            st.setInt(11, request.getSkillId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateRequest(Request request) {
        String sql = "UPDATE [dbo].[Request]\n"
                + "SET [MentorID] = ?,\n"
                + "    [MenteeID] = ?,\n"
                + "    [Price] = ?,\n"
                + "    [Note] = ?,\n"
                + "    [CreateDate] = ?,\n"
                + "    [Status] = ?,\n"
                + "    [Title] = ?,\n"
                + "    [Framework] = ?,\n"
                + "    [StartDate] = ?,\n"
                + "    [EndDate] = ?,\n"
                + "    [SkillID] = ?\n"
                + "WHERE [RequestID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Set parameters for the query
            st.setInt(1, request.getMentorId());
            st.setInt(2, request.getMenteeId());
            st.setFloat(3, request.getPrice());
            st.setString(4, request.getNote());
            st.setDate(5, Date.valueOf(request.getCreateDate()));
            st.setString(6, request.getStatus());
            st.setString(7, request.getTitle());
            st.setString(8, request.getFramework());
            st.setDate(9, Date.valueOf(request.getStartDate()));
            st.setDate(10, Date.valueOf(request.getEndDate()));
            st.setInt(11, request.getSkillId());
            st.setInt(12, request.getRequestId());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateItemsByRequestID(int requestId, String[] newSlots) {
        String deleteSql = "DELETE FROM [dbo].[RequestSlotItem] WHERE RequestID = ?";
        String insertSql = "INSERT INTO [dbo].[RequestSlotItem] (RequestID, SlotID) VALUES (?, ?)";

        try {
            PreparedStatement st = connection.prepareStatement(deleteSql);
            st.setInt(1, requestId);
            st.executeUpdate();

            st = connection.prepareStatement(insertSql);
            for (String slotIdStr : newSlots) {
                int slotId = Integer.parseInt(slotIdStr);
                st.setInt(1, requestId);
                st.setInt(2, slotId);
                st.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItemByRequestID(String[] slots) {
        String sql1 = "SELECT TOP 1 RequestID FROM [dbo].[Request] ORDER BY RequestID DESC";
        String sql2 = "INSERT INTO [dbo].[RequestSlotItem] (RequestID, SlotID) VALUES (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql1);
            ResultSet rs = st.executeQuery();
            int lastRequestId = 0;
            if (rs.next()) {
                lastRequestId = rs.getInt("RequestID");
            }

            st = connection.prepareStatement(sql2);
            for (String slotIdStr : slots) {
                int slotId = Integer.parseInt(slotIdStr);
                st.setInt(1, lastRequestId);
                st.setInt(2, slotId);
                st.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNewestRequest() {
        String sql1 = "SELECT TOP 1 RequestID FROM [dbo].[Request] ORDER BY RequestID DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql1);
            ResultSet rs = st.executeQuery();
            int lastRequestId = 0;
            if (rs.next()) {
                lastRequestId = rs.getInt("RequestID");
            }
            return lastRequestId;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public int countValidRequestMentorID(int mentorID) {
        String sql1 = "Select count(RequestID) as count \n"
                + "from Request \n"
                + "where MentorID = ? and \n"
                + "[Status] != 'Pending' and [Status] != 'Cancel'";
        try {
            PreparedStatement st = connection.prepareStatement(sql1);
            st.setInt(1, mentorID);
            ResultSet rs = st.executeQuery();
            int lastRequestId = 0;
            if (rs.next()) {
                lastRequestId = rs.getInt("count");
            }
            return lastRequestId;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Request> getAllRequestByStatus(String status) {
        List<Request> listRequest = new ArrayList<>();
        String sql = "SELECT*"
                + "  FROM [dbo].[Request]\n"
                + "  Where Request.Status like ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + status + "%");
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

        } catch (Exception e) {
        }
        return listRequest;

    }

    public List<Request> getAllCurrentValidate() {
        List<Request> listRequest = new ArrayList<>();
        String sql = """
                  SELECT*  FROM [dbo].[Request] R
                  Where r.status like 'Studying' or r.status like 'MentorAccept'
                  """;

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

        } catch (Exception e) {
        }
        return listRequest;

    }

    public List<Request> getAllCompleteRequest() {
        List<Request> listRequest = new ArrayList<>();
        String sql = "SELECT*"
                + "  FROM [dbo].[Request]\n"
                + "  Where Request.Status like 'Completed' or  Request.Status like 'Paid'";
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

        } catch (Exception e) {
        }
        return listRequest;

    }

    public List<Request> getAllRequest() {
        List<Request> listRequest = new ArrayList<>();
        String sql = "SELECT *"
                + "  FROM [dbo].[Request]\n";
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

        } catch (Exception e) {
        }
        return listRequest;

    }

    public List<Request> getRequestsByMenteeUsername(String username) {
        List<Request> requests = new ArrayList<>();
        String query = "SELECT * FROM [Request] WHERE MenteeID = (SELECT MenteeID FROM Mentee WHERE Username = ?) Order by RequestID DESC";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Request request = new Request(
                        rs.getInt("RequestID"),
                        rs.getInt("MentorID"),
                        rs.getInt("MenteeID"),
                        rs.getFloat("Price"),
                        rs.getString("Note"),
                        rs.getDate("CreateDate").toLocalDate(),
                        rs.getString("Status"),
                        rs.getString("Title"),
                        rs.getString("Framework"),
                        rs.getDate("StartDate").toLocalDate(),
                        rs.getDate("EndDate").toLocalDate(),
                        rs.getInt("SkillID"));
                requests.add(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }

    public void deleteRequest(int requestId) {
        String sql = "DELETE FROM Request WHERE RequestID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, requestId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNumOfValidMentorEachMentee(int menteeId) {
        int count = 0;
        String sql = """
                     select COUNT(DISTINCT MentorID) from Request 
                     where [Status] != 'Open' and [Status] != 'Processing' 
                     and [Status] != 'Canceled'
                     and MenteeID = ?
                        """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, menteeId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getNumOfValidSkillEachMentee(int menteeId) {
        int count = 0;
        String sql = """
                select COUNT(DISTINCT SkillID) from Request 
                where [Status] != 'Open' and [Status] != 'Processing' 
                and [Status] != 'Canceled'
                and MenteeID = ?
                        """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, menteeId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Request> getValidRequestByMenteeID(int menteeId) {
        List<Request> listReq = new ArrayList<>();
        String sql = "SELECT [RequestID]\n"
                + "      ,[MentorID]\n"
                + "      ,[MenteeID]\n"
                + "      ,[Price]\n"
                + "      ,[Note]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Status]\n"
                + "      ,[Title]\n"
                + "      ,[Framework]\n"
                + "      ,[StartDate]\n"
                + "      ,[EndDate]\n"
                + "      ,[SkillID]\n"
                + "  FROM [dbo].[Request]\n"
                + "where [Status] != 'Cancel' and [Status] != 'Pending'\n"
                + "and MenteeID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, menteeId);
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

                listReq.add(curRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listReq;
    }

    public List<Request> getRequestStatisticByMenteeID(int menteeId) {
        List<Request> listReq = new ArrayList<>();
        String sql = "SELECT * FROM [Request]\n"
                + "where [Status] != 'Canceled' and [Status] != 'Pending' and [Status] != 'Reject' and [Status] != 'Open' and MenteeID = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, menteeId);
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

                listReq.add(curRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listReq;
    }

    public int countTotalMentorsForMentee(int menteeId) {
        int mentorCount = 0;
        String sql = "SELECT COUNT(DISTINCT MentorID) FROM Request WHERE MenteeID = ? AND Status NOT IN ('Canceled', 'Reject', 'Pending')";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, menteeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                mentorCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentorCount;
    }

    public int countTotalRequestsForMentee(int menteeId) {
        int requestCount = 0;
        String sql = "SELECT COUNT(*) FROM Request WHERE MenteeID = ? ";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, menteeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                requestCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requestCount;
    }

    public List<Request> getAllRequestPagination(int page, int disNum) {
        List<Request> listReq = new ArrayList<>();
        String sql = "SELECT [RequestID]\n"
                + "      ,[MentorID]\n"
                + "      ,[MenteeID]\n"
                + "      ,[Price]\n"
                + "      ,[Note]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Status]\n"
                + "      ,[Title]\n"
                + "      ,[Framework]\n"
                + "      ,[StartDate]\n"
                + "      ,[EndDate]\n"
                + "      ,[SkillID]\n"
                + "  FROM [dbo].[Request]\n"
                + "  order by RequestID\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int offset = (page - 1) * disNum;
            st.setInt(1, offset);
            st.setInt(2, disNum);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Request curRequest = new Request();
                curRequest.setRequestId(rs.getInt("RequestID"));
                curRequest.setMentorId(rs.getInt("MentorID"));
                curRequest.setMenteeId(rs.getInt("MenteeID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setNote(rs.getString("Note"));

                Date createDateSql = rs.getDate("CreateDate");
                if (createDateSql != null) {
                    curRequest.setCreateDate(createDateSql.toLocalDate());
                }

                Date startDateSql = rs.getDate("StartDate");
                if (startDateSql != null) {
                    curRequest.setStartDate(startDateSql.toLocalDate());
                }

                Date endDateSql = rs.getDate("EndDate");
                if (endDateSql != null) {
                    curRequest.setEndDate(endDateSql.toLocalDate());
                }

                curRequest.setStatus(rs.getString("Status"));
                curRequest.setTitle(rs.getString("Title"));
                curRequest.setSkillId(rs.getInt("SkillID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setFramework(rs.getString("Framework"));

                listReq.add(curRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listReq;
    }

    public Request getRequestByID(int id) {
        String sql = "SELECT *"
                + "  FROM [dbo].[Request]"
                + "Where RequestID = ?\n";
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
                return curRequest;
            }

        } catch (Exception e) {
        }
        return null;

    }

    public String[] getAllStatusInRequest() {
        String sql = "SELECT DISTINCT [Status] FROM Request";
        List<String> statuses = new ArrayList<>();

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                // Retrieve the status and add it to the list
                String status = rs.getString("Status");
                statuses.add(status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert the list to a String array
        return statuses.toArray(new String[0]);
    }

    public List<Request> getAllRequestPaginationByStatus(int page, int disNum, String status) {
        List<Request> listReq = new ArrayList<>();
        String sql = "SELECT [RequestID]\n"
                + "      ,[MentorID]\n"
                + "      ,[MenteeID]\n"
                + "      ,[Price]\n"
                + "      ,[Note]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Status]\n"
                + "      ,[Title]\n"
                + "      ,[Framework]\n"
                + "      ,[StartDate]\n"
                + "      ,[EndDate]\n"
                + "      ,[SkillID]\n"
                + "  FROM [dbo].[Request]\n"
                + "  where [Status] = ?\n"
                + "  order by RequestID\n"
                + "offset ? rows\n"
                + "fetch next ? rows only";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int offset = (page - 1) * disNum;
            st.setString(1, status);
            st.setInt(2, offset);
            st.setInt(3, disNum);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Request curRequest = new Request();
                curRequest.setRequestId(rs.getInt("RequestID"));
                curRequest.setMentorId(rs.getInt("MentorID"));
                curRequest.setMenteeId(rs.getInt("MenteeID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setNote(rs.getString("Note"));

                Date createDateSql = rs.getDate("CreateDate");
                if (createDateSql != null) {
                    curRequest.setCreateDate(createDateSql.toLocalDate());
                }

                Date startDateSql = rs.getDate("StartDate");
                if (startDateSql != null) {
                    curRequest.setStartDate(startDateSql.toLocalDate());
                }

                Date endDateSql = rs.getDate("EndDate");
                if (endDateSql != null) {
                    curRequest.setEndDate(endDateSql.toLocalDate());
                }

                curRequest.setStatus(rs.getString("Status"));
                curRequest.setTitle(rs.getString("Title"));
                curRequest.setSkillId(rs.getInt("SkillID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setFramework(rs.getString("Framework"));

                listReq.add(curRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listReq;
    }

    public List<Request> getAllRequestBySearch(String search) {
        List<Request> listReq = new ArrayList<>();
        String sql = " SELECT DISTINCT [RequestID]\n"
                + "      ,[MentorID]\n"
                + "      ,r.[MenteeID]\n"
                + "      ,[Price]\n"
                + "      ,[Note]\n"
                + "      ,r.[CreateDate]\n"
                + "      ,r.[Status]\n"
                + "      ,[Title]\n"
                + "      ,[Framework]\n"
                + "      ,[StartDate]\n"
                + "      ,[EndDate]\n"
                + "      ,[SkillID]\n"
                + "  FROM [dbo].[Request] r LEFT JOIN Mentee m \n"
                + "  on r.MenteeID = m.MenteeID\n"
                + "  Where RequestID like ? \n"
                + "  OR Title like ?\n"
                + "  OR m.Username like ?\n"
                + "  ORDER BY RequestID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, 0);
            if (converSearch(search)) {
                st.setInt(1, Integer.parseInt(search));
            }
            String searchPattern = "%" + search + "%";
            st.setString(2, searchPattern);
            st.setString(3, searchPattern);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Request curRequest = new Request();
                curRequest.setRequestId(rs.getInt("RequestID"));
                curRequest.setMentorId(rs.getInt("MentorID"));
                curRequest.setMenteeId(rs.getInt("MenteeID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setNote(rs.getString("Note"));

                Date createDateSql = rs.getDate("CreateDate");
                if (createDateSql != null) {
                    curRequest.setCreateDate(createDateSql.toLocalDate());
                }

                Date startDateSql = rs.getDate("StartDate");
                if (startDateSql != null) {
                    curRequest.setStartDate(startDateSql.toLocalDate());
                }

                Date endDateSql = rs.getDate("EndDate");
                if (endDateSql != null) {
                    curRequest.setEndDate(endDateSql.toLocalDate());
                }

                curRequest.setStatus(rs.getString("Status"));
                curRequest.setTitle(rs.getString("Title"));
                curRequest.setSkillId(rs.getInt("SkillID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setFramework(rs.getString("Framework"));

                listReq.add(curRequest);
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return listReq;
    }

    public List<Request> getAllRequestBySearchPagination(String search, int page, int disNum) {
        List<Request> listReq = new ArrayList<>();
        String sql = "  SELECT DISTINCT [RequestID]\n"
                + "      ,[MentorID]\n"
                + "      ,r.[MenteeID]\n"
                + "      ,[Price]\n"
                + "      ,[Note]\n"
                + "      ,r.[CreateDate]\n"
                + "      ,r.[Status]\n"
                + "      ,[Title]\n"
                + "      ,[Framework]\n"
                + "      ,[StartDate]\n"
                + "      ,[EndDate]\n"
                + "      ,[SkillID]\n"
                + "  FROM [dbo].[Request] r LEFT JOIN Mentee m \n"
                + "  on r.MenteeID = m.MenteeID\n"
                + "  Where RequestID like ? \n"
                + "  OR Title like ?\n"
                + "  OR m.Username like ?\n"
                + "  ORDER BY RequestID\n"
                + "  OFFSET ? ROWS\n"
                + "  FETCH NEXT ? ROW ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, 0);
            if (converSearch(search)) {
                st.setInt(1, Integer.parseInt(search));
            }
            String searchPattern = "%" + search + "%";
            st.setString(2, searchPattern);
            st.setString(3, searchPattern);
            int numOffSet = (page - 1) * disNum;
            st.setInt(4, numOffSet);
            st.setInt(5, disNum);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Request curRequest = new Request();
                curRequest.setRequestId(rs.getInt("RequestID"));
                curRequest.setMentorId(rs.getInt("MentorID"));
                curRequest.setMenteeId(rs.getInt("MenteeID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setNote(rs.getString("Note"));

                Date createDateSql = rs.getDate("CreateDate");
                if (createDateSql != null) {
                    curRequest.setCreateDate(createDateSql.toLocalDate());
                }

                Date startDateSql = rs.getDate("StartDate");
                if (startDateSql != null) {
                    curRequest.setStartDate(startDateSql.toLocalDate());
                }

                Date endDateSql = rs.getDate("EndDate");
                if (endDateSql != null) {
                    curRequest.setEndDate(endDateSql.toLocalDate());
                }

                curRequest.setStatus(rs.getString("Status"));
                curRequest.setTitle(rs.getString("Title"));
                curRequest.setSkillId(rs.getInt("SkillID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setFramework(rs.getString("Framework"));

                listReq.add(curRequest);
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return listReq;
    }

    public boolean converSearch(String search) {
        try {
            int id = Integer.parseInt(search);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getCountRequestByStatusAndSearch(String search, String status, LocalDate startDate, LocalDate endDate) {
        int count = 0;
        String sql = "SELECT COUNT(DISTINCT r.RequestID) "
                + "FROM [dbo].[Request] r LEFT JOIN Mentee m "
                + "ON r.MenteeID = m.MenteeID "
                + "WHERE (r.RequestID LIKE ? "
                + "OR r.Title LIKE ? "
                + "OR m.Username LIKE ?) "
                + "AND r.Status LIKE ?";

        sql += (startDate != null) ? " AND r.StartDate >= ?" : "";
        sql += (endDate != null) ? " AND r.EndDate <= ?" : "";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int paramIndex = 1;
            st.setInt(paramIndex++, 0);
            if (converSearch(search)) {
                st.setInt(paramIndex, Integer.parseInt(search));
            }

            String searchPattern = "%" + search + "%";
            st.setString(paramIndex++, searchPattern);
            st.setString(paramIndex++, searchPattern);
            String statusPattern = "%" + status + "%";
            st.setString(paramIndex++, statusPattern);
            if (startDate != null) {
                st.setDate(paramIndex++, java.sql.Date.valueOf(startDate));
            }
            if (endDate != null) {
                st.setDate(paramIndex++, java.sql.Date.valueOf(endDate));
            }

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public List<Request> getRequestByStatusAndSearchPagination(String search, int page, int disNum, String status, LocalDate startDate, LocalDate endDate) {
        List<Request> listReq = new ArrayList<>();
        String sql = "  SELECT DISTINCT [RequestID]\n"
                + "      ,[MentorID]\n"
                + "      ,r.[MenteeID]\n"
                + "      ,[Price]\n"
                + "      ,[Note]\n"
                + "      ,r.[CreateDate]\n"
                + "      ,r.[Status]\n"
                + "      ,[Title]\n"
                + "      ,[Framework]\n"
                + "      ,[StartDate]\n"
                + "      ,[EndDate]\n"
                + "      ,[SkillID]\n"
                + "  FROM [dbo].[Request] r LEFT JOIN Mentee m \n"
                + "  on r.MenteeID = m.MenteeID\n"
                + "  Where (RequestID like ? \n"
                + "  OR Title like ?\n"
                + "  OR m.Username like ?)\n"
                + "  and r.Status like ? \n";

        sql += (startDate != null) ? " and r.StartDate >= ?" : "";
        sql += (endDate != null) ? " and r.EndDate <= ?" : "";
        sql += "  ORDER BY RequestID\n"
                + "  OFFSET ? ROWS\n"
                + "  FETCH NEXT ? ROW ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int paramIndex = 1;
            st.setInt(paramIndex++, 0);
            if (converSearch(search)) {
                st.setInt(paramIndex, Integer.parseInt(search));
            }

            String searchPattern = "%" + search + "%";
            st.setString(paramIndex++, searchPattern);
            st.setString(paramIndex++, searchPattern);
            String statusPattern = "%" + status + "%";
            st.setString(paramIndex++, statusPattern);
            if (startDate != null) {
                st.setDate(paramIndex++, java.sql.Date.valueOf(startDate));
            }
            if (endDate != null) {
                st.setDate(paramIndex++, java.sql.Date.valueOf(endDate));
            }
            int numOffSet = (page - 1) * disNum;
            st.setInt(paramIndex++, numOffSet);
            st.setInt(paramIndex++, disNum);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Request curRequest = new Request();
                curRequest.setRequestId(rs.getInt("RequestID"));
                curRequest.setMentorId(rs.getInt("MentorID"));
                curRequest.setMenteeId(rs.getInt("MenteeID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setNote(rs.getString("Note"));

                Date createDateSql = rs.getDate("CreateDate");
                if (createDateSql != null) {
                    curRequest.setCreateDate(createDateSql.toLocalDate());
                }

                Date startDateSql = rs.getDate("StartDate");
                if (startDateSql != null) {
                    curRequest.setStartDate(startDateSql.toLocalDate());
                }

                Date endDateSql = rs.getDate("EndDate");
                if (endDateSql != null) {
                    curRequest.setEndDate(endDateSql.toLocalDate());
                }

                curRequest.setStatus(rs.getString("Status"));
                curRequest.setTitle(rs.getString("Title"));
                curRequest.setSkillId(rs.getInt("SkillID"));
                curRequest.setPrice(rs.getFloat("Price"));
                curRequest.setFramework(rs.getString("Framework"));

                listReq.add(curRequest);
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return listReq;

    }

    public List<CV> getSuggestMentorCVByMentee() {
        List<Integer> mentorIds = new ArrayList<>();
        List<CV> mentor = new ArrayList<>();
        CVDAO m = new CVDAO();
        String sql = """
            SELECT TOP 10 m.MentorID, COUNT(r.RequestID) AS RequestCount
                        FROM Mentor m
                        LEFT JOIN Request r ON m.MentorID = r.MentorID where m.Status='active'
                        GROUP BY m.MentorID
                        ORDER BY RequestCount DESC;
        """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int mentorId = rs.getInt("MentorID");
                mentorIds.add(mentorId);
            }
            for (int i = 0; i < mentorIds.size(); i++) {
                mentor.add(m.getCVbyMentorId(mentorIds.get(i)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mentor;
    }

    public List<Request> getRequestOfMentor(String mentorName) {
        List<Request> requestlist = new ArrayList<>();
        String sql = """
                     SELECT * FROM [Request] WHERE MentorID = (SELECT MentorID FROM Mentor WHERE Username = ?) Order by RequestID DESC
                     """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, mentorName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Request request = new Request(
                        rs.getInt("RequestID"),
                        rs.getInt("MentorID"),
                        rs.getInt("MenteeID"),
                        rs.getFloat("Price"),
                        rs.getString("Note"),
                        rs.getDate("CreateDate").toLocalDate(),
                        rs.getString("Status"),
                        rs.getString("Title"),
                        rs.getString("Framework"),
                        rs.getDate("StartDate").toLocalDate(),
                        rs.getDate("EndDate").toLocalDate(),
                        rs.getInt("SkillID"));

                requestlist.add(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestlist;
    }

    public void updateStatusByMentor(int requestId, String newStatus) throws SQLException {
        String query = "UPDATE Request SET Status = ? WHERE RequestID = ?";
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, newStatus);
            ps.setInt(2, requestId);
            ps.executeUpdate();
        }
    }

    public void updateRequestStatus(int requestId, String newStatus) throws SQLException {
        String query = "UPDATE Request SET Status = ? WHERE RequestID = ?";
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, newStatus);
            ps.setInt(2, requestId);
            ps.executeUpdate();
        }
    }

    public int getSlotIdByRequestId(int requestId) throws SQLException {
        String query = "SELECT slotId FROM RequestSlotItem WHERE requestId = ?";
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, requestId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("slotId");
            }
        }
        return -1; // If no slot is found for the request
    }

    public boolean setSlotStatusbyRequestSlotItemUnavailable(int requestId) throws SQLException {
        String query = """
                   UPDATE Slot
                   SET status = 'unavailable'
                   WHERE slotID IN (
                       SELECT s.slotID
                       FROM RequestSlotItem rqs
                       JOIN Slot s ON rqs.SlotID = s.SlotID
                       WHERE rqs.RequestID = ?);
                   """;
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, requestId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0; // Return true if any rows were updated
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean setSlotStatusbyRequestSlotItemAvailable(int requestId) throws SQLException {
        String query = """
                   UPDATE Slot
                   SET status = 'available'
                   WHERE slotID IN (
                       SELECT s.slotID
                       FROM RequestSlotItem rqs
                       JOIN Slot s ON rqs.SlotID = s.SlotID
                       WHERE rqs.RequestID = ?);
                   """;
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, requestId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0; // Return true if any rows were updated
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public List<Mentor> getSuggestMentorByMentee(int menteeId) {
        List<Integer> mentorIds = new ArrayList<>();
        List<Mentor> mentorCV = new ArrayList<>();
        MentorDAO m = new MentorDAO();

        String sql = """
            SELECT DISTINCT sl.MentorID
            FROM SkillList sl
            JOIN Request r ON sl.SkillID = r.SkillID
            WHERE r.MenteeID = ?
            AND NOT EXISTS (
                SELECT 1
                FROM Request r2
                WHERE r2.MentorID = sl.MentorID
            )
        """;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, menteeId);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                int mentorId = resultSet.getInt("MentorID");
                mentorIds.add(mentorId);
            }
            for (int i = 0; i < mentorIds.size(); i++) {
                mentorCV.add(m.getMentorById(mentorIds.get(i)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mentorCV;
    }

    public List<Request> getRequestByMentorID(int mentorId) {
        List<Request> listReq = new ArrayList<>();
        String sql = "SELECT [RequestID]\n"
                + "      ,[MentorID]\n"
                + "      ,[MenteeID]\n"
                + "      ,[Price]\n"
                + "      ,[Note]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Status]\n"
                + "      ,[Title]\n"
                + "      ,[Framework]\n"
                + "      ,[StartDate]\n"
                + "      ,[EndDate]\n"
                + "      ,[SkillID]\n"
                + "  FROM [dbo].[Request]\n"
                + "where MentorID = ?";
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

                listReq.add(curRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listReq;
    }

    public void rejectOtherMenteesForSameSlots(int requestId) throws SQLException {
        String sql = "UPDATE Request SET Status = 'Reject' "
                + "WHERE RequestID IN (SELECT rsi.RequestID FROM RequestSlotItem rsi "
                + "JOIN Slot s ON rsi.SlotID = s.SlotID "
                + "WHERE rsi.SlotID IN (SELECT SlotID FROM RequestSlotItem WHERE RequestID = ?) "
                + "AND rsi.RequestID <> ? ) AND Status = 'Open'";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, requestId);
        ps.setInt(2, requestId);
        ps.executeUpdate();
    }

    public List<Request> getRequestsByMenteeId(int menteeId) {
        // Query to fetch all requests of a particular mentee from the database
        String sql = "SELECT * FROM Request WHERE MenteeID = ? AND Status NOT IN ('Canceled', 'Rejected')";

        List<Request> requests = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, menteeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Request request = new Request();
                request.setRequestId(rs.getInt("RequestID"));
                request.setMentorId(rs.getInt("MentorID"));
                request.setMenteeId(rs.getInt("MenteeID"));
                request.setPrice(rs.getFloat("Price"));
                request.setNote(rs.getString("Note"));
                request.setCreateDate(rs.getDate("CreateDate").toLocalDate());
                request.setStatus(rs.getString("Status"));
                request.setTitle(rs.getString("Title"));
                request.setFramework(rs.getString("Framework"));
                request.setStartDate(rs.getDate("StartDate").toLocalDate());
                request.setEndDate(rs.getDate("EndDate").toLocalDate());
                request.setSkillId(rs.getInt("SkillID"));
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public List<Request> getListofRequestByMenteeID(int menteeId) {
        List<Request> requestList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = """
                     SELECT * FROM [dbo].[Request] where [Status] = 'Completed' or [Status] = 'Paid' and MenteeID = ? 
                     """;
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, menteeId);
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
                requestList.add(curRequest);
            }

            return requestList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Request> getListofRequestDistinctByMenteeID(int menteeId) {
        List<Request> requestList = new ArrayList<>();
        //lenh sql select * from categories cach 1:
        String sql = """
                     SELECT DISTINCT Framework, Request.*  
                     FROM [dbo].[Request] 
                     WHERE ([Status] = 'Completed' OR [Status] = 'Paid') 
                     AND MenteeID = ?
                     """;
        //cach 2: vao sql phai chuot vao bang chon scriptable as
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, menteeId);
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
                requestList.add(curRequest);
            }

            return requestList;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<SkillList> getSkillsForCompletedOrPaidRequests(int menteeId) {
        List<SkillList> skillsList = new ArrayList<>();
        String query = """
                       SELECT MIN(sl.SkillListID) AS SkillListID, sl.SkillID, sl.MentorID, MIN(sl.Rating) AS Rating, MIN(sl.CVID) AS CVID
                       FROM SkillList sl
                       JOIN Request r ON sl.SkillID = r.SkillID AND sl.MentorID = r.MentorID
                       WHERE r.MenteeID = ? AND (r.Status = 'Completed' OR r.Status = 'Paid')
                       GROUP BY sl.SkillID, sl.MentorID, r.Framework;
                       """;

        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, menteeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SkillList skill = new SkillList(
                        rs.getInt("SkillListID"),
                        rs.getInt("SkillID"),
                        rs.getInt("MentorID"),
                        rs.getInt("Rating"),
                        rs.getInt("CVID")
                );
                skillsList.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skillsList;
    }

    public double getAttendancePercentage(int requestId) {
        double attendancePercentage = 0.0;
        String sql = """
            SELECT COALESCE(ROUND((CAST(SUM(CASE WHEN A.Status = 'Attended' THEN 1 ELSE 0 END) AS FLOAT) 
                / NULLIF(COUNT(A.AttendID), 0)) * 100, 2), 0) AS AttendancePercentage
            FROM Request R
            LEFT JOIN RequestSlotItem RSI ON R.RequestID = RSI.RequestID
            LEFT JOIN Attendance A ON RSI.RequestSlotItem = A.RequestSlotItem
            WHERE R.RequestID = ?
            GROUP BY R.RequestID;
            """;
        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, requestId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    attendancePercentage = rs.getDouble("AttendancePercentage");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendancePercentage;
    }

    public List<Request> getRequestsBySkillId(int skillId) {
        // Query to fetch all requests of a particular mentee from the database
        String sql = "SELECT distinct [RequestID]\n"
                + "      ,[MentorID]\n"
                + "      ,[MenteeID]\n"
                + "      ,[Price]\n"
                + "      ,[Note]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Status]\n"
                + "      ,[Title]\n"
                + "      ,[Framework]\n"
                + "      ,[StartDate]\n"
                + "      ,[EndDate]\n"
                + "      ,[SkillID]\n"
                + "  FROM [dbo].[Request]\n"
                + "  Where SkillID = ?";

        List<Request> requests = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, skillId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Request request = new Request();
                request.setRequestId(rs.getInt("RequestID"));
                request.setMentorId(rs.getInt("MentorID"));
                request.setMenteeId(rs.getInt("MenteeID"));
                request.setPrice(rs.getFloat("Price"));
                request.setNote(rs.getString("Note"));
                request.setCreateDate(rs.getDate("CreateDate").toLocalDate());
                request.setStatus(rs.getString("Status"));
                request.setTitle(rs.getString("Title"));
                request.setFramework(rs.getString("Framework"));
                request.setStartDate(rs.getDate("StartDate").toLocalDate());
                request.setEndDate(rs.getDate("EndDate").toLocalDate());
                request.setSkillId(rs.getInt("SkillID"));
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public static void main(String[] args) {
        RequestDAO act = new RequestDAO();
        LocalDate start = LocalDate.parse("2024-10-19");
        LocalDate end = LocalDate.parse("2024-11-09");
        List<Request> list = act.getRequestByStatusAndSearchPagination("b", 1, 10, "", start, end);
        int count = act.getCountRequestByStatusAndSearch("a", "", start, end);
        Request curRequest = act.getRequestByID(6);
        System.out.println(curRequest);
    }
}
