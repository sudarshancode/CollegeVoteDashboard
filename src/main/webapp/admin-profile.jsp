<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.util.*,java.io.*, java.util.Base64" %>
<%@ page import="com.example.DatabaseConnection" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin Profile</title>
	<link rel="stylesheet" href="style/admin-profile-style.css">
</head>
<body>
<%-- Check if user is logged in --%>
	<%
		String username=(String) session.getAttribute("username");
    	if (session.getAttribute("username") == null) {
        	response.sendRedirect("index.jsp"); // Redirect to login page if not logged in
    	}
		Connection conn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	
	    try {
	        conn = DatabaseConnection.getConnection();
	        pst = conn.prepareStatement("SELECT * FROM admin WHERE username = ?");
	        pst.setString(1, username);
	        rs = pst.executeQuery();
	
	        if (rs.next()) {
	            String name = rs.getString("admin_name");
	            String email = rs.getString("email");
	            String phone = rs.getString("phone");
	            String address = rs.getString("address");
	            String department = rs.getString("department");
	            String role = rs.getString("role");
	            String profileImage = rs.getString("profile_image");
	%>
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
	    <div class="profile-container">
		    <img class="profile-img" src="<%= profileImage %>" alt="Profile Image">
		    <div class="username"><%= username %></div>
		
		    <ul class="details-list">
		    	<li><span>Name:</span> <%= name %></li>
		        <li><span>Email:</span> <%= email %></li>
		        <li><span>Phone:</span> <%= phone %></li>
		        <li><span>Address:</span> <%= address %></li>
		        <li><span>Department:</span> <%= department %></li>
		        <li><span>Role:</span> <%= role %></li>
		    </ul>
		</div>
    </div>
    <%
        } else {
            out.println("<p>Admin not found!</p>");
        }

    } catch (Exception e) {
        e.printStackTrace();
        out.println("Error: " + e.getMessage());
    } finally {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (pst != null) pst.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
%>
</body>
</html>