/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CV;

import DAO.CVDAO;
import DAO.SlotDAO;
import Model.CV;
import Model.Mentor;
import Model.Skill;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CVCreateServlet", urlPatterns = {"/cvcreate"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 300, // 5 MB
        maxRequestSize = 1024 * 1024 * 1000 // 10 MB
)

public class CVCreateServlet extends HttpServlet {

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
            out.println("<title>Servlet CVCreateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CVCreateServlet at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");

        String error = request.getParameter("error");
        int id;
        CVDAO cvd = new CVDAO();
        try {
            id = Integer.parseInt(id_raw);
            List<Skill> skillList = cvd.getListofSkill();
            request.setAttribute("skillList", skillList);
            Mentor mentor = cvd.getMentorByID(id);
            //check cv exist

            String email = cvd.getUserEmail(id);
            request.setAttribute("email", email);
            request.setAttribute("uFound", mentor);

            request.getRequestDispatcher("createCV.jsp").forward(request, response);
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
        String[] addSkills = request.getParameterValues("addSkills");

        String userId_raw = request.getParameter("mentorId");

        String profession = request.getParameter("profession");
        String year_raw = request.getParameter("year");
        
        String framework = request.getParameter("framework");
        String education = request.getParameter("education");

        String activity = request.getParameter("activity");
        String professionIntroduction = request.getParameter("professionIntroduction");
        String serviceDescription = request.getParameter("serviceDescription");
        String experience = request.getParameter("experience");
        Part filePart = null;
        if (request.getPart("avatar").getSize()< (1024 * 1024 * 5)) {
            System.out.println("nahnha");
            filePart = request.getPart("avatar");
        }
        String price_raw = request.getParameter("price");

        int userid,year;
        float price;

        try {
            userid = Integer.parseInt(userId_raw);
            price = Float.parseFloat(price_raw);
            year = Integer.parseInt(year_raw);
            System.out.println(year);
            //img processing
            InputStream fileRead = filePart.getInputStream();
            byte[] avatar = fileRead.readAllBytes();
            //System.out.println(avatar);
            //System.out.println(fileRead);

            CVDAO cvdao = new CVDAO();
            CV newCv = new CV(userid, education, experience, activity,
                    professionIntroduction, profession,year, serviceDescription, framework, avatar, price);
            int cvId = cvdao.createCV(newCv);
            //System.out.println(cvId);
            //add skills
            if (addSkills != null) {
                cvdao.insertMentorSkills(userid, addSkills, cvId);
                //System.out.println("Add !null");
            }
            if (cvId == -1) {
                response.sendRedirect("cvlist?id=" + userId_raw + "&error=Unable to create your CV. Please try again.");

            }
            SlotDAO sld = new SlotDAO();
            System.out.println(sld.getListofSlotsByMentorId(newCv.getMentorId()));
            response.sendRedirect("cvlist?id=" + userid + "&mess=Your CV has been created successfully!");
        } catch (NullPointerException e) {
            response.sendRedirect("cvcreate?id=" + userId_raw + "&error=File size exceeds the 5 MB limit. Please upload a smaller file.");
        } catch (Exception e) {
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
