/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CV;

import DAO.CVDAO;
import Model.CV;
import Model.Mentor;
import Model.Skill;
import Model.StatisticSkills;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CVUpdateServlet", urlPatterns = {"/cvupdate"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 300, // 5 MB
        maxRequestSize = 1024 * 1024 * 1000 // 10 MB
)
public class CVUpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CVUpdateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CVUpdateServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cvId_raw = request.getParameter("id");
        String error = request.getParameter("error");

        int cvId;
        CVDAO cvd = new CVDAO();
        try {
            cvId = Integer.parseInt(cvId_raw);

            CV cv = cvd.getCVbyCVId(cvId);
            List<StatisticSkills> mentorSkillList = cvd.getCVSkillList(cvId);
            List<Skill> skillList = cvd.getSkillList(cv.getCvId());
            //System.out.println(mentorSkillList.get(0).getSkillName());
            request.setAttribute("skillMentor", mentorSkillList);
            request.setAttribute("skillList", skillList);

            Mentor mentor = cvd.getMentorByID(cv.getMentorId());
            String email = cvd.getUserEmail(cv.getMentorId());

            request.setAttribute("error", error);
            request.setAttribute("email", email);
            request.setAttribute("uFound", mentor);
            request.setAttribute("cvFound", cv);
            request.setAttribute("cvId", cvId);

            //System.out.println(mentor.getFullName());
            request.getRequestDispatcher("updateCV.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //getParameterValues khac getParameter
        String[] deleteSkills = request.getParameterValues("deleteSkills");
        String[] addSkills = request.getParameterValues("addSkills");
        String cvId_raw = request.getParameter("cvId");
        String userId_raw = request.getParameter("mentorId");
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String dob_raw = request.getParameter("dob");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        //System.out.println(fullname);
        String profession = request.getParameter("profession");
        String year_raw = request.getParameter("year");
        
        String framework = request.getParameter("framework");
        String education = request.getParameter("education");

        String activity = request.getParameter("activity");
        String professionIntroduction = request.getParameter("professionIntroduction");
        String serviceDescription = request.getParameter("serviceDescription");
        String experience = request.getParameter("experience");
        String price_raw = request.getParameter("price");

        Date dob;
        int userid,year;
        int cvId;

        float price;
        Part filePart = null;
        if (request.getPart("avatar").getSize()< (1024 * 1024 * 5)) {
            System.out.println("nahnha");
            filePart = request.getPart("avatar");
        }

        try {

            userid = Integer.parseInt(userId_raw);
            cvId = Integer.parseInt(cvId_raw);
            year = Integer.parseInt(year_raw);
            price = Float.parseFloat(price_raw);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dob = dateFormat.parse(dob_raw);
            

            CVDAO cvdao = new CVDAO();
            String nameId = cvdao.getMentorByID(userid).getUsername();
            CV cv = cvdao.getCVbyCVId(cvId);

            //img processing
            byte[] avatar = null;
            //check image too big
            //check user not update avatar
            if (filePart.getSize() > 0) {
                InputStream fileRead = filePart.getInputStream();
                avatar = fileRead.readAllBytes();
            } else {
                //System.out.println(cv.getCvId());
                avatar = cv.getAvatar();
            }

            //check email exist
            if (cvdao.checkDuplicateEmail(email, nameId)) {
                //System.out.println("true");
                email = "error";
                response.sendRedirect("cvupdate?id=" + userid + "&error=email_exists");
                return;
            }

            Mentor newMentor = new Mentor(userid, username, phone, address,
                    dob, fullname, gender);
            CV newCv = new CV(userid, education, experience, activity,
                    professionIntroduction, profession,year, serviceDescription, framework, avatar, price);
            if (cv.getStatus().equalsIgnoreCase("inactive")) {

                //System.out.println(nameId+" and "+email);
                cvdao.updateUser(nameId, username, email);
                //System.out.println(newCv.getMentorId());
                cvdao.updateMentor(newMentor);

                if (deleteSkills != null) {
                    cvdao.deleteMentorSkills(cvId, deleteSkills);
                    //System.out.println("Del !null");
                }

                if (addSkills != null) {
                    cvdao.insertMentorSkills(userid, addSkills, cvId);
                    //System.out.println("Add !null");
                }
                if (cvdao.updateCV(newCv, cvId)) {
                    response.sendRedirect("cvlist?id=" + newCv.getMentorId() + "&mess=Your CV has been updated successfully!");
                } else {
                    response.sendRedirect("cvlist?id=" + newCv.getMentorId() + "&error=Unable to update your CV. Please try again.");
                }
            }
            if (cv.getStatus().equalsIgnoreCase("active")) {
                System.out.println("nah");
                int newcvId = cvdao.createCV(newCv);
                //System.out.println(cvId);
                //add skills
                if (addSkills != null) {
                    cvdao.insertMentorSkills(userid, addSkills, newcvId);
                    //System.out.println("Add !null");
                }
                if (cvId == -1) {
                    response.sendRedirect("cvlist?id=" + userId_raw + "&error=Unable to update your CV. Please try again.");

                }
                response.sendRedirect("cvlist?id=" + userid + "&mess=Your active CV has been updated and successfully saved as a draft!");
            }
        }catch (NullPointerException e) {
            System.out.println(e);
            response.sendRedirect("cvlist?id=" + userId_raw + "&error=File size exceeds the 5 MB limit. Please upload a smaller file.");
        }catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("cvlist?id=" + userId_raw + "&error=Unable to update your CV. Please try again.");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
