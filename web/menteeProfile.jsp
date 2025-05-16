<%-- 
    Document   : menteeProfile
    Created on : Oct 3, 2024, 9:39:56 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Mentee" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
    <head>
        <title>View Mentee Profile</title>
        <style>
            .profile-label {
                font-weight: bold;
                margin-right: 10px;
            }
        </style>
    </head>
    <body>
        <h2>Profile</h2>

        <form action="updatementeeprofile" method="get">
            <div>
                <label class="profile-label">Avatar:</label>
                <img src="${mentee.avatar}" alt="Avatar" width="100" height="100">
            </div>
            <div>
                <label class="profile-label">Username:</label>
                <span>${mentee.username}</span>
            </div>
            <div>
                <label class="profile-label">Full Name:</label>
                <span>${mentee.fullName}</span>
            </div>
            <div>
                <label class="profile-label">Email:</label>
                <span>${mentee.email}</span>
            </div>
            <div>
                <label class="profile-label">Date of Birth:</label>
                <span>${mentee.dateOfBirth}</span>
            </div>
            <div>
                <label class="profile-label">Gender:</label>
                <span>${mentee.gender}</span>
            </div>
            <div>
                <label class="profile-label">Address:</label>
                <span>${mentee.address}</span>
            </div>
            <br>
            <button type="submit">Edit Profile</button>
        </form>
    </body>
</html>
