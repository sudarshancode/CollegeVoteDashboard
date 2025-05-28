<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/update-student-style.css">
    <title>Update Candidate</title>
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
            <h1>Update Student</h1>
            <p>Update the student sensitively </p>
        </header>
		
		<div id="updateStudentForm" class="form-container">
			
			<form id="studentFormUpdate" action="UpdateStudentServlet" method="post" enctype="multipart/form-data" >
				
				<label for="studentCode">Student Code: <span style="color:green "><%= request.getAttribute("studentCode") !=null ? request.getAttribute("studentCode") : " "  %></span></label>
                <input type="hidden" id="studentCode" name="studentCode" value="<%= request.getAttribute("studentCode") !=null ? request.getAttribute("studentCode") : " "  %>" placeholder="Student Code" required>	
                
                <label for="studentName">Student Name:</label>
                <input type="text" id="studentName" name="studentName" value="<%= request.getAttribute("studentName") !=null ? request.getAttribute("studentName") : " " %>"  placeholder="Student Name" required>

                <label for="studentRoll">Student Roll:</label>
                <input type="text" id="studentRoll" name="studentRoll" value="<%= request.getAttribute("studentRoll") !=null ? request.getAttribute("studentRoll") : " " %>" placeholder="Student Roll" required>

                <label for="studentDepartment">Department:</label>
                <input type="text" id="studentDepartment" name="studentDepartment" value="<%= request.getAttribute("studentDept") !=null ? request.getAttribute("studentDept") : " " %>" placeholder="Student Department" required>

                <label for="studentPhno">Phone No.:</label>
                <input type="text" id="studentPhno" name="studentPhno" value="<%= request.getAttribute("studentPhno") !=null ? request.getAttribute("studentPhno") : " " %>" placeholder="Student Ph No." required>

				<label for="studentEmail">Email Address:</label>
                <input type="text" id="studentEmail" name="studentEmail" value="<%= request.getAttribute("studentEmail") !=null ? request.getAttribute("studentEmail") : " " %>" placeholder="Student Email Address" required>

				<label for="studentPassword">Password:</label>
                <input type="text" id="studentPassword" name="studentPassword" value="<%= request.getAttribute("studentPassword") !=null ? request.getAttribute("studentPassword") : " " %>" placeholder="Student Password" required>

				<label for="studentAddress">Address:</label>
                <input type="text" id="studentAddress" name="studentAddress" value="<%= request.getAttribute("studentAddress") !=null ? request.getAttribute("studentAddress") : " " %>" placeholder="Student Address" required>

                <label for="studentDob">Date of birth:</label>
                <input type="date" id="studentDob" name="studentDob" value="<%= request.getAttribute("studentDob") %>" placeholder="Student Date of Birth" required>
   				
   				<label for="studentPhoto">Student Photo:</label>
                <input type="file" id="studentPhoto" name="studentPhoto"  onchange="checkFileSizeInput(this)" required>
   				
                <button type="submit">Update Student</button>
            </form>
		</div>
    </div>
</body>
<script>
    function checkFileSizeInput(input) {
        const file = input.files[0];
        if (file) {
            const sizeInKB = file.size / 1024;
            if (sizeInKB > 150) {
                alert("Image size must be 150KB or less. Your file is " + Math.round(sizeInKB) + "KB");
                input.value = ""; // Clear the input so user has to select a new file
            }
        }
    }
</script>
</html>