/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Skill;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

/**
 *
 * @author tuong
 */
public class SkillDAO extends DBContext {

    public boolean insertNewSkill(Skill newSkill) {
        String sql = "INSERT INTO [dbo].[Skill]\n"
                + "           ([SkillName]\n"
                + "           ,[CreateDate]\n"
                + "           ,[Description]\n"
                + "           ,[Status]\n"
                + "           ,[img])\n"
                + "     VALUES(?,?,?,?,?)";
        try {
            PreparedStatement rs = connection.prepareStatement(sql);
            rs.setString(1, newSkill.getSkillName());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String curTime = dtf.format(now);
            rs.setString(2, curTime);
            rs.setString(3, newSkill.getDescription());
            rs.setString(4, newSkill.getStatus());
            rs.setBytes(5, newSkill.getImg());
            rs.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public List<Skill> getListOfAllSkill() {
        List<Skill> listSkill = new ArrayList<>();
        String sql = "SELECT *    FROM [dbo].[Skill]\n"
                + "where Status = 'active'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id_raw = rs.getString("SkillID");
                int id = Integer.parseInt(id_raw);
                String name = rs.getString("SkillName");
                String date_raw = rs.getString("CreateDate");
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formater.parse(date_raw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("img");
                Skill curSkill = new Skill();
                curSkill.setSkillId(id);
                curSkill.setSkillName(name);
                curSkill.setCreateDate(date);
                curSkill.setDescription(description);
                curSkill.setStatus(status);
                if (img != null) {
                    curSkill.setImg(img);
                }
                listSkill.add(curSkill);
            }
        } catch (Exception e) {
        }

        return listSkill;
    }

    public List<Skill> getListOfAllSkillExceptId(int excludedId) {
        List<Skill> listSkill = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Skill] WHERE Status = 'active' AND SkillID <> ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, excludedId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id_raw = rs.getString("SkillID");
                int id = Integer.parseInt(id_raw);
                String name = rs.getString("SkillName");
                String date_raw = rs.getString("CreateDate");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(date_raw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("img");
                Skill curSkill = new Skill();
                curSkill.setSkillId(id);
                curSkill.setSkillName(name);
                curSkill.setCreateDate(date);
                curSkill.setDescription(description);
                curSkill.setStatus(status);
                if (img != null) {
                    curSkill.setImg(img);
                }
                listSkill.add(curSkill);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Consider proper logging here
        }

        return listSkill;
    }

    public List<Skill> getListOfSkillPagings(int page, int numShow) {
        List<Skill> listSkill = new ArrayList<>();
        String sql = "SELECT [SkillID]\n"
                + "             ,[SkillName]\n"
                + "             ,[CreateDate]\n"
                + "             ,[Description]\n"
                + "             ,[Status]\n"
                + "             ,[Img]\n"
                + "             FROM [dbo].[Skill]\n"
                + "		where Status like 'active'\n"
                + "             order by Skill.SkillName asc\n"
                + "             offset ? rows\n"
                + "             fetch next ? row only";
        int start = (page - 1) * numShow;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, start);
            st.setInt(2, numShow);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id_raw = rs.getString("SkillID");
                int id = Integer.parseInt(id_raw);
                String name = rs.getString("SkillName");
                String date_raw = rs.getString("CreateDate");
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formater.parse(date_raw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("img");
                Skill curSkill = new Skill();
                curSkill.setSkillId(id);
                curSkill.setSkillName(name);
                curSkill.setCreateDate(date);
                curSkill.setDescription(description);
                curSkill.setStatus(status);
                if (img != null) {
                    curSkill.setImg(img);
                }
                listSkill.add(curSkill);
            }
        } catch (Exception e) {
        }
        return listSkill;
    }
    
    public List<Skill> getListOfSkillPaging(int page, int numShow) {
        List<Skill> listSkill = new ArrayList<>();
        String sql = "SELECT [SkillID]\n"
                + "             ,[SkillName]\n"
                + "             ,[CreateDate]\n"
                + "             ,[Description]\n"
                + "             ,[Status]\n"
                + "             ,[Img]\n"
                + "             FROM [dbo].[Skill]\n"
                + "             order by Skill.SkillID asc\n"
                + "             offset ? rows\n"
                + "             fetch next ? row only";
        int start = (page - 1) * numShow;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, start);
            st.setInt(2, numShow);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id_raw = rs.getString("SkillID");
                int id = Integer.parseInt(id_raw);
                String name = rs.getString("SkillName");
                String date_raw = rs.getString("CreateDate");
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formater.parse(date_raw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("img");
                Skill curSkill = new Skill();
                curSkill.setSkillId(id);
                curSkill.setSkillName(name);
                curSkill.setCreateDate(date);
                curSkill.setDescription(description);
                curSkill.setStatus(status);
                if (img != null) {
                    curSkill.setImg(img);
                }
                listSkill.add(curSkill);
            }
        } catch (Exception e) {
        }
        return listSkill;
    }

    public List<Skill> getListOfNewestSkills(int page, int numShow) {
        List<Skill> listSkill = new ArrayList<>();
        String sql = "SELECT s.SkillID, s.SkillName, s.CreateDate, s.Description, s.Status, s.Img "
                + "FROM Skill s "
                + "WHERE s.Status LIKE 'active' "
                + "ORDER BY s.CreateDate DESC, s.SkillName ASC " // Order by newest create date
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        int start = (page - 1) * numShow;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, start);
            st.setInt(2, numShow);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("SkillID");
                String name = rs.getString("SkillName");
                String dateRaw = rs.getString("CreateDate");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date createDate = formatter.parse(dateRaw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("Img");

                Skill curSkill = new Skill();
                curSkill.setSkillId(id);
                curSkill.setSkillName(name);
                curSkill.setCreateDate(createDate);
                curSkill.setDescription(description);
                curSkill.setStatus(status);
                if (img != null) {
                    curSkill.setImg(img);
                }

                listSkill.add(curSkill);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log any errors
        }
        return listSkill;
    }

    public List<Skill> getListOfOldestSkills(int page, int numShow) {
        List<Skill> listSkill = new ArrayList<>();
        String sql = "SELECT s.SkillID, s.SkillName, s.CreateDate, s.Description, s.Status, s.Img "
                + "FROM Skill s "
                + "WHERE s.Status LIKE 'active' "
                + "ORDER BY s.CreateDate ASC, s.SkillName ASC " // Order by oldest create date
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        int start = (page - 1) * numShow;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, start);
            st.setInt(2, numShow);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("SkillID");
                String name = rs.getString("SkillName");
                String dateRaw = rs.getString("CreateDate");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date createDate = formatter.parse(dateRaw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("Img");

                Skill curSkill = new Skill();
                curSkill.setSkillId(id);
                curSkill.setSkillName(name);
                curSkill.setCreateDate(createDate);
                curSkill.setDescription(description);
                curSkill.setStatus(status);
                if (img != null) {
                    curSkill.setImg(img);
                }

                listSkill.add(curSkill);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log any errors
        }
        return listSkill;
    }

    public List<Skill> getListOfSkillByLeastMentors(int page, int numShow) {
        List<Skill> listSkill = new ArrayList<>();
        String sql = "SELECT s.SkillID, s.SkillName, s.CreateDate, s.Description, s.Status, s.Img, "
                + "COUNT(DISTINCT sl.MentorID) AS mentor_count "
                + "FROM Skill s "
                + "LEFT JOIN SkillList sl ON s.SkillID = sl.SkillID "
                + "LEFT JOIN Mentor m ON sl.MentorID = m.MentorID "
                + "WHERE s.Status LIKE 'active' AND m.Status LIKE 'active' "
                + "GROUP BY s.SkillID, s.SkillName, s.CreateDate, s.Description, s.Status, s.Img "
                + "ORDER BY mentor_count ASC, s.SkillName ASC " // Order by least mentors
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        int start = (page - 1) * numShow;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, start);
            st.setInt(2, numShow);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("SkillID");
                String name = rs.getString("SkillName");
                String dateRaw = rs.getString("CreateDate");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date createDate = formatter.parse(dateRaw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("Img");

                Skill curSkill = new Skill();
                curSkill.setSkillId(id);
                curSkill.setSkillName(name);
                curSkill.setCreateDate(createDate);
                curSkill.setDescription(description);
                curSkill.setStatus(status);
                if (img != null) {
                    curSkill.setImg(img);
                }

                listSkill.add(curSkill);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Logging the error is good practice
        }
        return listSkill;
    }

    public List<Skill> getListOfSkillByMostMentors(int page, int numShow) {
        List<Skill> listSkill = new ArrayList<>();
        String sql = "SELECT s.SkillID, s.SkillName, s.CreateDate, s.Description, s.Status, s.Img, "
                + "COUNT(DISTINCT sl.MentorID) AS mentor_count "
                + "FROM Skill s "
                + "LEFT JOIN SkillList sl ON s.SkillID = sl.SkillID "
                + "LEFT JOIN Mentor m ON sl.MentorID = m.MentorID "
                + "WHERE s.Status LIKE 'active' AND m.Status LIKE 'active' "
                + "GROUP BY s.SkillID, s.SkillName, s.CreateDate, s.Description, s.Status, s.Img "
                + "ORDER BY mentor_count DESC, s.SkillName ASC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        int start = (page - 1) * numShow;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, start);
            st.setInt(2, numShow);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("SkillID");
                String name = rs.getString("SkillName");
                String dateRaw = rs.getString("CreateDate");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date createDate = formatter.parse(dateRaw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("Img");

                Skill curSkill = new Skill();
                curSkill.setSkillId(id);
                curSkill.setSkillName(name);
                curSkill.setCreateDate(createDate);
                curSkill.setDescription(description);
                curSkill.setStatus(status);
                if (img != null) {
                    curSkill.setImg(img);
                }

                listSkill.add(curSkill);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Logging the error is good practice
        }
        return listSkill;
    }

    public List<Skill> getListOfSkillByLeastRequested(int page, int numShow) {
        List<Skill> listSkill = new ArrayList<>();
        String sql = "SELECT s.[SkillID], s.[SkillName], s.[CreateDate], s.[Description], s.[Status], s.[Img], COUNT(rs.SkillID) AS request_count "
                + "FROM [dbo].[Skill] s "
                + "LEFT JOIN [dbo].[Request] rs ON s.SkillID = rs.SkillID "
                + "WHERE s.Status = 'active' "
                + "GROUP BY s.SkillID, s.SkillName, s.CreateDate, s.Description, s.Status, s.Img "
                + "ORDER BY request_count ASC, s.SkillName ASC " // Order by least requested
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        int start = (page - 1) * numShow;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, start);
            st.setInt(2, numShow);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("SkillID");
                String name = rs.getString("SkillName");
                String date_raw = rs.getString("CreateDate");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(date_raw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("Img");

                Skill curSkill = new Skill();
                curSkill.setSkillId(id);
                curSkill.setSkillName(name);
                curSkill.setCreateDate(date);
                curSkill.setDescription(description);
                curSkill.setStatus(status);
                if (img != null) {
                    curSkill.setImg(img);
                }
                listSkill.add(curSkill);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log any errors
        }
        return listSkill;
    }

    public List<Skill> getListOfSkillByMostRequested(int page, int numShow) {
        List<Skill> listSkill = new ArrayList<>();
        String sql = "SELECT \n"
                + "    s.[SkillID], \n"
                + "    s.[SkillName], \n"
                + "    s.[CreateDate], \n"
                + "    s.[Description], \n"
                + "    s.[Status], \n"
                + "    s.[Img], \n"
                + "    COUNT(rs.SkillID) AS request_count\n"
                + "FROM \n"
                + "    [dbo].[Skill] s \n"
                + "LEFT JOIN \n"
                + "    [dbo].[Request] rs ON s.SkillID = rs.SkillID \n"
                + "WHERE \n"
                + "    s.Status = 'active' \n"
                + "GROUP BY \n"
                + "    s.SkillID, \n"
                + "    s.SkillName, \n"
                + "    s.CreateDate, \n"
                + "    s.Description, \n"
                + "    s.Status, \n"
                + "    s.Img \n"
                + "ORDER BY \n"
                + "    request_count DESC, \n"
                + "    s.SkillName ASC \n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT ? ROWS ONLY;";

        int start = (page - 1) * numShow;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, start);
            st.setInt(2, numShow);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("SkillID");
                String name = rs.getString("SkillName");
                String date_raw = rs.getString("CreateDate");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(date_raw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("Img");

                Skill curSkill = new Skill();
                curSkill.setSkillId(id);
                curSkill.setSkillName(name);
                curSkill.setCreateDate(date);
                curSkill.setDescription(description);
                curSkill.setStatus(status);
                if (img != null) {
                    curSkill.setImg(img);
                }
                listSkill.add(curSkill);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log any errors
        }
        return listSkill;
    }

    public void deleteSkillById(int skillId) {
        String sql = "DELETE FROM [dbo].[Skill]\n"
                + "      WHERE SkillID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, skillId);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Skill getSkillById(int skillId) {
        String sql = "SELECT [SkillID]\n"
                + "      ,[SkillName]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Description]\n"
                + "      ,[Status]\n"
                + "      ,[Img]\n"
                + "  FROM [dbo].[Skill]\n"
                + "  where SkillID=?";
        Skill curSkill = new Skill();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, skillId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id_raw = rs.getString("SkillID");
                curSkill.setSkillId(Integer.parseInt(id_raw));
                curSkill.setSkillName(rs.getString("SkillName"));
                String date_raw = rs.getString("CreateDate");
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formater.parse(date_raw);
                curSkill.setCreateDate(date);
                curSkill.setDescription(rs.getString("Description"));
                curSkill.setStatus(rs.getString("Status"));
                curSkill.setImg(rs.getBytes("Img"));
            }
        } catch (Exception e) {
        }
        return curSkill;
    }

    public boolean updateSkillInfo(Skill updateSkill) {
        String sql = "UPDATE [dbo].[Skill]\n"
                + "   SET [SkillName] =?\n"
                + "      ,[CreateDate] = ?\n"
                + "      ,[Description] = ?\n"
                + "      ,[Status] =?\n"
                + "      ,[Img] = ?\n"
                + " WHERE SkillID=?";
        try {
            PreparedStatement rs = connection.prepareStatement(sql);
            rs.setString(1, updateSkill.getSkillName());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String curTime = dtf.format(now);
            rs.setString(2, curTime);
            rs.setString(3, updateSkill.getDescription());
            rs.setString(4, updateSkill.getStatus());
            rs.setBytes(5, updateSkill.getImg());
            rs.setInt(6, updateSkill.getSkillId());
            rs.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Skill> getListOfSkillByName(String skillName) {
        List<Skill> listSkill = new ArrayList<>();
        String sql = "SELECT [SkillID], \n"
                + "       [SkillName], \n"
                + "       [CreateDate], \n"
                + "       [Description], \n"
                + "       [Status], \n"
                + "       [Img]\n"
                + "FROM [dbo].[Skill]\n"
                + "WHERE [SkillName] Like ?\n"
                + "ORDER BY [SkillID] ASC;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + skillName + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id_raw = rs.getString("SkillID");
                int id = Integer.parseInt(id_raw);
                String name = rs.getString("SkillName");
                String date_raw = rs.getString("CreateDate");
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formater.parse(date_raw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("img");
                Skill curSkill = new Skill();
                curSkill.setSkillId(id);
                curSkill.setSkillName(name);
                curSkill.setCreateDate(date);
                curSkill.setDescription(description);
                curSkill.setStatus(status);
                if (img != null) {
                    curSkill.setImg(img);
                }
                listSkill.add(curSkill);
            }
        } catch (Exception e) {
        }
        return listSkill;
    }

    public List<Skill> getListOfSkillByDate() {
        List<Skill> listSkill = new ArrayList<>();
        String sql = "SELECT Top 3 [SkillID], \n"
                + "       [SkillName], \n"
                + "       [CreateDate], \n"
                + "       [Description], \n"
                + "       [Status], \n"
                + "       [Img]\n"
                + "FROM [dbo].[Skill]\n"
                + "ORDER BY [CreateDate] desc;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id_raw = rs.getString("SkillID");
                int id = Integer.parseInt(id_raw);
                String name = rs.getString("SkillName");
                String date_raw = rs.getString("CreateDate");
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formater.parse(date_raw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("img");
                Skill curSkill = new Skill();
                curSkill.setSkillId(id);
                curSkill.setSkillName(name);
                curSkill.setCreateDate(date);
                curSkill.setDescription(description);
                curSkill.setStatus(status);
                if (img != null) {
                    curSkill.setImg(img);
                }
                listSkill.add(curSkill);
            }
        } catch (NumberFormatException | SQLException | ParseException e) {
        }
        return listSkill;
    }

    public List<Skill> getListOfSkillByNameDifID(String skillName, int skillID) {
        List<Skill> listSkill = new ArrayList<>();
        String sql = "SELECT [SkillID]\n"
                + "      ,[SkillName]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Description]\n"
                + "      ,[Status]\n"
                + "      ,[Img]\n"
                + "  FROM [dbo].[Skill] where SkillName = ?  and SkillID !=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, skillName);
            st.setInt(2, skillID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id_raw = rs.getString("SkillID");
                int id = Integer.parseInt(id_raw);
                String name = rs.getString("SkillName");
                String date_raw = rs.getString("CreateDate");
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formater.parse(date_raw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("img");
                Skill curSkill = new Skill();
                curSkill.setSkillId(id);
                curSkill.setSkillName(name);
                curSkill.setCreateDate(date);
                curSkill.setDescription(description);
                curSkill.setStatus(status);
                if (img != null) {
                    curSkill.setImg(img);
                }
                listSkill.add(curSkill);
            }
        } catch (Exception e) {
        }
        return listSkill;
    }

    public Skill getSkillByID(int skillID) {
        String sql = "SELECT [SkillID]\n"
                + "      ,[SkillName]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Description]\n"
                + "      ,[Status]\n"
                + "      ,[Img]\n"
                + "  FROM [dbo].[Skill] where SkillID =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, skillID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id_raw = rs.getString("SkillID");
                int id = Integer.parseInt(id_raw);
                String name = rs.getString("SkillName");
                String date_raw = rs.getString("CreateDate");
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formater.parse(date_raw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("img");
                Skill curSkill = new Skill(id, name, date, description, status, img);
                return curSkill;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Skill getSkillByRequestID(int requestID) {
        String sql = """
                     select s.* from Skill s 
                     join Request r on r.SkillID = s.SkillID
                     where r.RequestID = ?
                     """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, requestID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id_raw = rs.getString("SkillID");
                int id = Integer.parseInt(id_raw);
                String name = rs.getString("SkillName");
                String date_raw = rs.getString("CreateDate");
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formater.parse(date_raw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("img");
                Skill curSkill = new Skill(id, name, date, description, status, img);
                return curSkill;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Skill> getListOfSkillByNamePagination(int page, int numShow, String skillName) {
        List<Skill> listSkill = new ArrayList<>();
        String sql = "SELECT [SkillID]\n"
                + "      ,[SkillName]\n"
                + "      ,[CreateDate]\n"
                + "      ,[Description]\n"
                + "      ,[Status]\n"
                + "      ,[Img]\n"
                + "  FROM [dbo].[Skill]\n"
                + "  WHERE [SkillName] LIKE '%' + ? + '%' \n"
                + "  order by SkillID asc\n"
                + "  offset ? row \n"
                + "  fetch next ? row only";
        int start = (page - 1) * numShow;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, skillName);
            st.setInt(2, start);
            st.setInt(3, numShow);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id_raw = rs.getString("SkillID");
                int id = Integer.parseInt(id_raw);
                String name = rs.getString("SkillName");
                String date_raw = rs.getString("CreateDate");
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formater.parse(date_raw);
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                byte[] img = rs.getBytes("img");
                Skill curSkill = new Skill();
                curSkill.setSkillId(id);
                curSkill.setSkillName(name);
                curSkill.setCreateDate(date);
                curSkill.setDescription(description);
                curSkill.setStatus(status);
                if (img != null) {
                    curSkill.setImg(img);
                }
                listSkill.add(curSkill);
            }
        } catch (Exception e) {
        }
        return listSkill;
    }

    public static void main(String[] args) {
        SkillDAO act = new SkillDAO();
        List<Skill> list = act.getListOfSkillByName("py");
        System.out.println(list.size());

    }
}
