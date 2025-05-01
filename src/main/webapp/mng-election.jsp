<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.example.DatabaseConnection" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/election-style.css">
    <title>Manage Election</title>
</head>
<body>
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
            <h1>Manage Election</h1>
            <p>Manage your college election.</p>
        </header>
        <!--Button Group-->
        <div class="button-group">
            <button id="add-btn" onclick="showForm('Add')"><img src="icon/add.png"> Add Election</button>
            <button id="update-btn" onclick="showForm('Update')"><img src="icon/pencil.png"> Update Election</button>
            <button id="delete-btn" onclick="showForm('Delete')"><img src="icon/delete.png"> Delete Election</button>
            <button id="notice-btn" onclick="showForm('Notice')"><img src="icon/notice.png"> Add Notice</button>
        </div>

        <!--Add Election-->
        <div id="addElectionForm" class="form-container" style="display: none;">
            <h2>Add Election</h2>
            <form id="electionForm" action="AddElectionServlet" method="post">
                <label for="electionName">Election Name:</label>
                <input type="text" id="electionName" name="electionName" placeholder="Election Name" required>

                <label for="startDate">Start Date:</label>
                <input type="date" id="startDate" name="startDate" placeholder="Start Date" required>

                <label for="endDate">End Date:</label>
                <input type="date" id="endDate" name="endDate" placeholder="End Date" required>

                <div id="status-div">
                    <label for="electionStatus">Election Status:</label>
                    <select id="electionStatus" name="status">
                        <option value="upcoming">UpComing</option>
                        <option value="ongoing">OnGoing</option>
                        <option value="completed">Completed</option>
                    </select>
                </div><br>
            
                <button type="submit">Add Election</button>
            </form>
        </div>

        <!--Update Candidate-->
        <div id="updateElectionForm" class="form-container" style="display: none;">
            <h2>Update Election</h2>
            <form id="electionForm" action="IdElectionServlet" method="post" >
                
                <label for="electionId">Election Id:</label>
                <input type="text" id="electionId" name="electionId" placeholder="Election Id" required>

                <button type="submit">Update Election</button>
            </form>
        </div>

        <!--Delete Candidate-->
        <div id="deleteElectionForm" class="form-container" style="display: none;">
            <h2>Delete Election</h2>
            <form id="electionForm" action="DeleteElectionServlet" method="post" >
                <label for="electionId">Election Id:</label>
                <input type="number" id="electionId" name="electionId" placeholder="Election Id" required>

                <button type="submit">Delete Election</button>
            </form>
        </div>
        <!--Add Notice-->
        <div id="addNoticeForm" class="form-container" style="display: none;">
            <h2>Add Notice</h2>
            <form id="noticeForm" action="AddNoticeServlet" method="post">
                <label for="notice">Notice:</label>
                
                <textarea type="text" id="notice" name="notice" placeholder="Enter new notice" required></textarea><br><br>

                <button type="submit">Add Notice</button>
            </form>
        </div>
		
		
		<%
        	Connection conn=null;
        	Statement stmt=null;
        	String Queary="SELECT * FROM election_mng;";
        	try{
        		conn=DatabaseConnection.getConnection();
        		stmt=conn.createStatement();
        		ResultSet rs= stmt.executeQuery(Queary);
        %>
        <!--Election List-->
        <div class="student-list">
            <h2>Election</h2>
            <table id="electionTable">
                <thead>
                    <tr>
                        <th>Election Id</th>
                        <th>Election Name</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Election Status</th>
                    </tr>
                </thead>
                <%while(rs.next()){ %>
                <tbody>
                    <td><%= rs.getInt("election_id") %></td>
                    <td><%= rs.getString("election_name") %></td>
                    <td><%= rs.getString("election_start") %></td>
                    <td><%= rs.getString("election_end") %></td>
                    <% 
                    	String electionStatus;
                    	int status=rs.getInt("election_status");
                    	if(status==0){
                    		electionStatus="Upcoming";
                    	}else if(status==1){
                    		electionStatus="Ongoing";
                    	}else{
                    		electionStatus="Completed";
                    	}
                    %>
                    <td><%= electionStatus %></td>
                </tbody>
                <%} %>
            </table>
            <%
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        %>
        </div>
        
		
		
		<%
        	Connection connection=null;
        	Statement statement=null;
        	String noticeQueary="SELECT * FROM vote_notice;";
        	try{
        		connection=DatabaseConnection.getConnection();
        		statement=connection.createStatement();
        		ResultSet res= statement.executeQuery(noticeQueary);
        %>
        <!--Notice Section-->
        <div class="notice-list">
            <h2>Notice List</h2>
            <table id="noticeTable">
                <thead>
                    <tr>
                        <th>Notice Id</th>
                        <th>Notice Body</th>
                        <th>Delete Notice</th>
                    </tr>
                </thead>
                <% while(res.next()){ %>
                <tbody>
                    <td><%= res.getInt("notice_id") %></td>
                    <td><%= res.getString("notice_text") %></td>
                    <td><a href="DeleteNoticeServlet?id=<%= res.getInt("notice_id") %>" 
                   		onclick="return confirm('Are you sure you want to delete this notice?');">
                   		<button>Delete</button>
                		</a>
                	</td>
                </tbody>
                <%} %>
            </table>
            <%
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        %>
        </div>
    </div>
    <script src="Js/election.js">
    </script>
</body>
</html>