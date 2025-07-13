<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.util.*,java.io.*, java.util.Base64" %>
<%@ page import="com.example.DatabaseConnection" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin Setting</title>
	<link rel="stylesheet" href="style/admin-setting-style.css">
</head>
<body>
	<!-- Sidebar -->
    <div class="sidebar">
        <h2>Admin Panel</h2>
        <ul>
            <li><a href="dashboard.jsp"><img src="icon/house.png"> Dashboard</a></li>
            <li><a href="admin-profile.jsp"><img src="icon/user.png"> Profile</a></li>
            <li><a href="admin-setting.jsp"><img src="icon/cogwheel.png"> Settings</a></li>
            <li><a href="LogoutServlet"><img src="icon/logout.png"> Logout</a></li>
        </ul>
    </div>
    <!-- Main Content -->
    <div class="main-content"> 
    	<div class="container">
		    <h2 id="heading-h2">Change Password</h2>
		    <form action="ChangePasswordServlet" method="post">
		        <label for="currentPassword">Current Password:</label>
		        <input type="password" name="currentPassword" required>
		
		        <label for="newPassword">New Password:</label>
		        <input type="password" name="newPassword" required>
		
		        <label for="confirmPassword">Confirm New Password:</label>
		        <input type="password" name="confirmPassword" required>
		
		        <button type="submit" class="btn">Update Password</button>
		
		        <%
		            String msg = (String) request.getAttribute("message");
		            if (msg != null) {
		        %>
		            <div class="message"><%= msg %></div>
		        <%
		            }
		        %>
		    </form>
		</div>
    </div>

</body>
</html>