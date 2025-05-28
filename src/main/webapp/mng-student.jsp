<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.util.*,java.io.*, java.util.Base64" %>
<%@ page import="com.example.DatabaseConnection" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/student-style.css">
    <title>Manage Student</title>
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
            <li><a href="LogoutServlet"><img src="icon/logout.png"> Logout</a></li>
        </ul>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <header>
            <h1>Manage Student</h1>
            <p>Manage your college student.</p>
        </header>

        <!-- Buttons -->
        <div class="button-group">
            <button id="add-btn" onclick="showForm('Add')"><img src="icon/add.png"> Add Student</button>
            <button id="update-btn" onclick="showForm('Update')"><img src="icon/pencil.png"> Update Student</button>
            <button id="delete-btn" onclick="showForm('Delete')"><img src="icon/delete.png"> Delete Student</button>
        </div>

        <!--Add Student-->
        <div id="addStudentForm" class="form-container" style="display: none;">
			<h2>Add Student</h2>
			<!--  
			<p>*Note: Insert excel data </p>
			<form id="studentForm" action="AddStudentServlet" method="post" enctype="multipart/form-data">
				<input type="file" name="file" accept=".xlsx" required />
        		<input type="submit" value="Upload">
			</form>
			-->
			
			<form id="studentForm" action="AlterAddStudentServlet" method="post" enctype="multipart/form-data">
				<label for="studentId">Student Id:</label>
                <input type="text" id="studentId" name="studentId" placeholder="Student Id" required>

                <label for="studentName">Student Name:</label>
                <input type="text" id="studentName" name="studentName" placeholder="Student Name" required>

                <label for="studentRoll">Student Roll:</label>
                <input type="text" id="studentRoll" name="studentRoll" placeholder="Student Roll Number" required>

                <label for="studentDept">Student Department:</label>
                <input type="text" id="studentDept" name="studentDept" placeholder="Student Department" required>

				<label for="studentAdds">Student Address:</label>
                <input type="text" id="studentAdds" name="studentAdds" placeholder="Student Address" required>
				
                <label for="studentDob">Date of birth:</label>
                <input type="date" id="studentDob" name="studentDob" placeholder="Student Date of Birth" required>
        		
        		<label for="studentPhone">Student Phone Number:</label>
                <input type="tel" id="studentPhone" name="studentPhone" placeholder="+919876543210" required>
        		
        		<label for="studentEmail">Student Email:</label>
                <input type="email" id="studentEmail" name="studentEmail" placeholder="Student Email Address" required>
        		
        		<label for="studentPhoto">Student Photo:</label>
                <input type="file" id="studentPhoto" name="studentPhoto" required>
        		
        		<input type="submit" value="Upload">
			</form>
			
        </div>

        <!--Update Student-->
        <div id="updateStudentForm" class="form-container" style="display: none;">
			<h2>Update Student</h2>
			
			<form id="studentForm" action="CodeStudentServlet" method="post">
                <label for="studentCode">Search Code:</label>
                <input type="text" id="studentCode" name="studentCode" placeholder="Student Code" required>
				
				<button type="submit">Submit</button>
			</form>
        </div>

        <!--Delete Student-->
        <div id="deleteStudentForm" class="form-container" style="display: none;">
            <h2>Delete Student</h2>
            <form id="studentForm" action="DeleteStudentServlet" method="post">
                <label for="studentCode">Student Code:</label>
                <input type="text" id="studentCode" name="studentCode" placeholder="Student Code" required>

                <button type="submit">Delete Student</button>
            </form>
        </div>
		
		<!--Candidate List-->
        <%
        	Connection conn=null;
        	Statement stmt=null;
        	String Queary="SELECT * FROM student";
        	try{
        		conn=DatabaseConnection.getConnection();
        		stmt=conn.createStatement();
        		ResultSet rs= stmt.executeQuery(Queary);
            	
        %>
        <!--Student List Tabular form-->
        <div class="student-list">
            <h2>Student List</h2>
            <div id="search-div">
                <input type="text" name="search" placeholder="Search Student by Id">
                <button>Search</button>
            </div>
            <table id="studentTable">
                <thead>
                    <tr>
                        <th>Id Code</th>
                        <th>Name</th>
                        <th>Roll</th>
                        <th>Department</th>
                        <th>Address</th>
                        <th>DOB</th>
                        <th>Phone Number</th>
                        <th>Email</th>
                        <th>Password</th>
                        <th>Image</th>
                    </tr>
                </thead>
                <%while(rs.next()){ 
                	byte[] imgData = rs.getBytes("image");
                    String base64Image = "";
                    if (imgData != null) {
                        base64Image = Base64.getEncoder().encodeToString(imgData);
                    }
                %>
                <tbody>
                    <td><%= rs.getString("stcode") %></td>
                    <td><%= rs.getString("stname") %></td>
                    <td><%= rs.getString("stroll") %></td>
                    <td><%= rs.getString("stdepartment") %></td>
                    <td><%= rs.getString("stadds") %></td>
                    <td><%= rs.getString("stdob") %></td>
                    <td>+91<%= rs.getString("stphno") %></td>
                    <td><%= rs.getString("stgmail") %></td>
                    <td><%= rs.getString("stpassword") %></td>
                     <td>
            		<% if (!base64Image.isEmpty()) { %>
                			<img style="width:50px;height:35px;" src="data:image/jpeg;base64,<%= base64Image %>" alt="Image"/>
            		<% } else { %>
                		No Image
            			<% } %>
        			</td>
                </tbody>
                <%} %>
            </table>
        </div>
        <%
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        %>
    </div>

    <script src="Js/student.js">
    </script>
</body>
</html>