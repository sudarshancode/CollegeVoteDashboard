<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/update-election-style.css">
    <title>Update election</title>
</head>
<body>
	<%-- Check if user is logged in --%>
	<%
    	if (session.getAttribute("username") == null) {
        	response.sendRedirect("index.jsp"); // Redirect to login page if not logged in
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
            <h1>Update Election</h1>
            <p>Update the election sensitively </p>
        </header>
		
		<div id="updateElectionForm" class="form-container">
			
			<form id="electionFormUpdate" action="UpdateElectionServlet" method="post" >
				
				 <label for="electionId">Election Id: <%= request.getAttribute("electionId") %></label>
                <input type="hidden" id="electionId" name="electionId" value="<%= request.getAttribute("electionId") %>"  required>
                
				<label for="electionName">Election Name:</label>
                <input type="text" id="electionName" name="electionName" value="<%= request.getAttribute("electionName") %>"  required>

                <label for="startDate">Start Date:</label>
                <input type="date" id="startDate" name="startDate" value="<%= request.getAttribute("electionStart") %>" required>

                <label for="endDate">End Date:</label>
                <input type="date" id="endDate" name="endDate" value="<%= request.getAttribute("electionEnd") %>" required>

                <div id="status-div">
                    <label for="electionStatus">Election Status:</label>
                    <select id="electionStatus" name="status">
                        <option value="upcoming">UpComing</option>
                        <option value="ongoing">OnGoing</option>
                        <option value="completed">Completed</option>
                    </select>
                </div><br>
   
                <button type="submit">Update Election</button>
            </form>
		</div>
    </div>
</body>
</html>