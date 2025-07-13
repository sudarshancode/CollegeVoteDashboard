<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/update-candidate-style.css">
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
            <li><a href="admin-profile.jsp"><img src="icon/user.png"> Profile</a></li>
            <li><a href="admin-setting.jsp"><img src="icon/cogwheel.png"> Settings</a></li>
            <li><a href="LogoutServlet"><img src="icon/logout.png"> Logout</a></li>
        </ul>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <header>
            <h1>Update Candidate</h1>
            <p>Update the candidate sensitively </p>
        </header>
		
		<div id="updateCandidateForm" class="form-container">
			
			<form id="candidateFormUpdate" action="UpdateCandidateServlet" method="post" >
				<label for="candidateId">Candidate Id:</label>
                <input type="text" id="candidateId" name="candidateId" value="<%= request.getAttribute("candidateId") !=null ? request.getAttribute("candidateId") : " "  %>" placeholder="Candidate Id" required>	
                
                <label for="candidateName">Candidate Name:</label>
                <input type="text" id="candidateName" value="<%= request.getAttribute("candidateName") !=null ? request.getAttribute("candidateName") : " " %>" name="candidateName" placeholder="Candidate Name" required>

                <label for="candidateImage">Candidate Image:</label>
                <input type="text" id="candidateImage" name="candidateImage" value="<%= request.getAttribute("candidateImage") !=null ? request.getAttribute("candidateImage") : " " %>" placeholder="Candidate Image url" required>

                <label for="candidateParty">Party Name:</label>
                <input type="text" id="candidateParty" name="candidateParty" value="<%= request.getAttribute("candidateParty") !=null ? request.getAttribute("candidateParty") : " " %>" placeholder="Candidate Party Name" required>

                <label for="partySymbol">Party Symbol:</label>
                <input type="text" id="partySymbol" name="candidatePartySymbol" value="<%= request.getAttribute("candidatePartySymbol") !=null ? request.getAttribute("candidatePartySymbol") : " " %>" placeholder="Candidate Party Symbol url" required>

                <div id="gender-div">
                    <label> Select Gender:</label><br>
                    <input type="radio" id="male" name="candidateGender" value="Male" required>
                    <label for="male">Male</label>
                    <input type="radio" id="female" name="candidateGender" value="Female" required>
                    <label for="female">Female</label>
                </div>

                <label for="candidateDob">Date of birth:</label>
                <input type="date" id="candidateDob" name="candidateDob" value="<%= request.getAttribute("candidateDob") %>" placeholder="Candidate Date of Birth" required>
   
                <button type="submit">Update Candidate</button>
            </form>
		</div>
    </div>
</body>
</html>