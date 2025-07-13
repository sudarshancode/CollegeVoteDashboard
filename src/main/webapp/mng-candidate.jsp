<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.example.DatabaseConnection" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/candidate-style.css">
    <title>Manage Candidate</title>
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
            <li><a href="admin-profile.jsp"><img src="icon/user.png"> Profile</a></li>
            <li><a href="admin-setting.jsp"><img src="icon/cogwheel.png"> Settings</a></li>
            <li><a href="LogoutServlet"><img src="icon/logout.png"> Logout</a></li>
        </ul>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <header>
            <h1>Manage Candidate</h1>
            <p>Manage your Candidate.</p>
        </header>

        <!-- Buttons -->
        <div class="button-group">
            <button id="add-btn" onclick="showForm('Add')"><img src="icon/add.png"> Add Candidate</button>
            <button id="update-btn" onclick="showForm('Update')"><img src="icon/pencil.png"> Update Candidate</button>
            <button id="delete-btn" onclick="showForm('Delete')"><img src="icon/delete.png"> Delete Candidate</button>
        </div>

        <!--Add Candidate-->
        <div id="addCandidateForm" class="form-container" style="display: none;">
            <h2>Add Candidate</h2>
            
            <form id="candidateForm" action="AddCandidateServlet" method="post">
                <label for="candidateName">Candidate Name:</label>
                <input type="text" id="candidateName" name="candidateName" placeholder="Candidate Name" required>

                <label for="candidateImage">Candidate Image:</label>
                <input type="text" id="candidateImage" name="candidateImage" placeholder="Candidate Image url" required>

                <label for="candidateParty">Party Name:</label>
                <input type="text" id="candidateParty" name="candidateParty" placeholder="Candidate Party Name" required>

                <label for="partySymbol">Party Symbol:</label>
                <input type="text" id="partySymbol" name="candidatePartySymbol" placeholder="Candidate Party Symbol url" required>

                <div id="gender-div">
                    <label> Select Gender:</label><br>
                    <input type="radio" id="male" name="candidateGender" value="Male" required>
                    <label for="male">Male</label>
                    <input type="radio" id="female" name="candidateGender" value="Female" required>
                    <label for="female">Female</label>
                </div>

                <label for="candidateDob">Date of birth:</label>
                <input type="date" id="candidateDob" name="candidateDob" placeholder="Candidate Date of Birth" required>

                <button type="submit">Add Candidate</button>
            </form>
        </div>

        <!--Update Candidate-->
        <div id="updateCandidateForm" class="form-container" style="display: none;">
            <h2>Update Candidate</h2>
            <% 
            	//Get Session from DashboardServlet
            	String username=(String) session.getAttribute("username"); 
            %>
            <form id="candidateForm" action="IdCandidateServlet" method="post">
                <label for="candidateId">Search Id:</label>
                <input type="text" id="candidateId" name="candidateId" placeholder="Candidate Id" required>
				<input type="hidden" name="username" value="<%= username %>"> 
				<button type="submit">Submit</button>
			</form>
        </div>

        <!--Delete Candidate-->
        <div id="deleteCandidateForm" class="form-container" style="display: none;">
            <h2>Delete Candidate</h2>
            <form id="candidateForm" action="DeleteCandidateServlet" method="post">
                <label for="candidateId">Candidate Id:</label>
                <input type="number" id="candidateId" name="candidateId" placeholder="Candidate Id" required>

                <button type="submit">Delete Candidate</button>
            </form>
        </div>

        <!--Candidate List-->
        <%
        	Connection conn=null;
        	Statement stmt=null;
        	String Queary="SELECT * FROM candidate";
        	try{
        		conn=DatabaseConnection.getConnection();
        		stmt=conn.createStatement();
        		ResultSet rs= stmt.executeQuery(Queary);
        %>
        <div class="candidate-list">
            <h2>Candidate List</h2>
            <% while(rs.next()){ %>
            <div class="cards">
                <div class="card">
                    <div id="profile-photo">
                        <img src="<%= rs.getString("c_image")%>">
                    </div>
                    <div class="candidate-details">
                        <h2><%= rs.getString("c_name") %></h2>
                        <div class="div1">
                            <p><strong>Candidate Id:</strong> <%= rs.getInt("c_id") %></p>
                            <p><strong>Party Name:</strong> <%= rs.getString("c_partyname") %></p>
                        </div>
                        <div class="div1">
                            <p><strong>DOB:</strong> <%= rs.getString("c_dob") %></p>
                            <p><strong>Age:</strong><%= rs.getInt("c_age") %></p>
                        </div>
                        <div class="div1">
                            <p><strong>Gender:</strong><%= rs.getString("c_gender") %></p>
                            <img src="<%= rs.getString("c_partylogo") %>">
                        </div>
                    </div>
                </div>
            </div>
            <%}
        	}catch(Exception e){
        		
        	} %>
        </div>
    </div>

    <script src="Js/candidate.js">
    </script>
</body>
</html>