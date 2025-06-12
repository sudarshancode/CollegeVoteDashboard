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
            <li><a href="#"><img src="icon/user.png"> Profile</a></li>
            <li><a href="#"><img src="icon/cogwheel.png"> Settings</a></li>
            <li><a href="#"><img src="icon/logout.png"> Logout</a></li>
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
    </div>
</body>
</html>