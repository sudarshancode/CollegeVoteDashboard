<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="style/dashboard-style.css">
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
        	<% String username=(String) session.getAttribute("username"); %>
            <h1>Welcome, <%=username %> </h1>
            <p>Manage your dashboard efficiently.</p>
        </header>

        <!-- Dashboard Stats -->
        
        <div class="cards">
            <div class="card" onclick="submitCandidate()">
                <h3><img src="icon/job-search.png"> Manage Candidates</h3>
                <p>Add, Update & Delete Candidates</p>
            </div>
            <div class="card" onclick="submitStudent()">
                <h3><img src="icon/students.png"> Manage Students</h3>
                <p>Add, Update & Delete Students</p>
            </div>
        </div>
        <div class="cards">
            <div class="card" onclick="submitElection()">
                <h3><img src="icon/polling.png"> Manage Election</h3>
                <p class="div-under">Start End Election</p>
            </div>
            <div class="card" onclick="submitResult()">
                <h3><img src="icon/analysis.png"> View Results</h3>
                <p class="div-under">View Voting Results</p>
            </div>
        </div>
    </div>

    <!--Hidden form-->
    <form id="candidateId" action="mng-candidate.jsp" method="post">
    	<input type="hidden" name="userSessionKey" value="<%= username%>">
    </form>
    <form id="studentId" action="mng-student.jsp" method="post">
    	<input type="hidden" name="userSessionKey" value="<%= username%>">
    </form>
    <form id="resultId" action="view-result.jsp" method="post">
    	<input type="hidden" name="userSessionKey" value="<%= username%>">
    </form>
    <form id="electionId" action="mng-election.jsp" method="post">
    	<input type="hidden" name="userSessionKey" value="<%= username%>">
    </form>
    <!-- Use for javascript-->
    <script src="Js/dashboard.js">

    </script>
</body>
</html>
