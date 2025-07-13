<%@ page import="java.sql.*, com.example.DatabaseConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/result-style.css">
    <title>Result</title>
</head>
<body>
	<%-- Check if user is logged in --%>
	<%
    	if (session.getAttribute("username") == null) {
        	response.sendRedirect("index.jsp"); // Redirect to login page if not logged in
    	}
	
		int totalVotes = 0;
	    try {
	        java.sql.Connection conn = com.example.DatabaseConnection.getConnection();
	        java.sql.PreparedStatement pst = conn.prepareStatement("SELECT SUM(total_votes) FROM vote_count");
	        java.sql.ResultSet rs = pst.executeQuery();
	        if(rs.next()) {
	            totalVotes = rs.getInt(1);
	        }
	        rs.close();
	        pst.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        
			session.setAttribute("errorMessage", e.getMessage());
			response.sendRedirect("errorPage.jsp");
	    }
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
        <header>
            <h1>Election Result</h1>
            <p>What is the Result of this election?</p>
         	<div class="total-votes-box">
                Total Votes: <b><%= totalVotes %></b>
            </div>
        </header>

        <div class="chart-container">
            <div class="chart">
                <h2>Bar Chart</h2>
                <img src="VoteChart?type=bar" alt="Bar Chart">
            </div>

            <div class="chart">
                <h2>Pie Chart</h2>
                <img src="VoteChart?type=pie" alt="Pie Chart">
            </div>
        </div>
        
        <%
		    Connection conn = null;
		    Statement stmt = null;
		    ResultSet rs = null;
		
		    try {
				
		    	Connection conn1 = com.example.DatabaseConnection.getConnection();
		
		        String query = "SELECT v.c_id, c.c_name, c.c_image,c.c_partyname, c.c_partylogo, v.total_votes " +
		                       "FROM vote_count v JOIN candidate c ON v.c_id = c.c_id";
		        stmt = conn1.createStatement();
		        rs = stmt.executeQuery(query);
		%>
        
        <div class="table-container">
		     <table>
		      <thead>
		        <tr>
		          <th>ID</th>
		          <th>Image</th>
		          <th>Name</th>
		          <th>Party Name</th>
		          <th>Party Flag</th>
		          <th>Total Vote</th>
		        </tr>
		      </thead>
		      <tbody>
		       <%
                while (rs.next()) {
                    int id = rs.getInt("c_id");
                    String name = rs.getString("c_name");
                    String logo = rs.getString("c_image");
                    String partyName = rs.getString("c_partyname");
                    String partyLogo = rs.getString("c_partylogo");
                    int votes = rs.getInt("total_votes");
            	%>
		        <tr>
		          <td><%= id %></td>
		          <td><img class="img-class" src="<%= logo %>" alt="No Img"></td>
		          <td><%= name %></td>
		          <td><%=partyName %></td>
		          <td><img class="logo-class" src="<%=partyLogo %>" alt="No Flag"></td>
		          <td><%= votes %></td>
		        </tr>
		        <%
                }
            	%>
		      </tbody>
		    </table>
        </div>
        <%
		    } catch (Exception e) {
		        out.println("Error: " + e.getMessage());
		    } finally {
		        if (rs != null) try { rs.close(); } catch (Exception e) {}
		        if (stmt != null) try { stmt.close(); } catch (Exception e) {}
		        if (conn != null) try { conn.close(); } catch (Exception e) {}
		    }
		%>
    </div>
</body>
</html>