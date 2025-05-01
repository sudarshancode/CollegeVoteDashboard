<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/index-style.css"> 
    <title>Admin Login</title>
</head>
<body>
    <div id="main-div">
        <div id="header">
        <%String error=(String) request.getAttribute("userId"); %>
            <h1>College Vote</h1>
            <% if(error!= null){ %>
            <h3><%=error %></h3>
            <%} %>
        </div>
        <div id="form-holder">
        	<h2>Login</h2>
            <form class="login-form" action="LoginServlet" method="post">
                <input id="user-name"  type="text" name="username" placeholder="Username" required><br>
                <input id="password" type="password" name="password" placeholder="Password" required><br>
                <input id="submitBtn" type="submit" value="Login">
            </form>
        </div>
    </div>
    <script src="Js/index.js">
    </script>
</body>
</html>