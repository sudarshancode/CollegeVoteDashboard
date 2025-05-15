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
	%>
    <!-- Sidebar -->
    <div class="sidebar">
        <h2>Admin Panel</h2>
        <ul>
            <li><a href="#"><img src="icon/house.png"> Dashboard</a></li>
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
        </header>

    </div>
</body>
</html>