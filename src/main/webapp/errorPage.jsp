<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.io.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .error-container {
            background-color: #fff;
            border: 2px solid #ff4c4c;
            border-radius: 10px;
            padding: 30px 50px;
            box-shadow: 0 0 15px rgba(0,0,0,0.2);
            text-align: center;
        }
        .error-container h2 {
            color: #ff0000;
            margin-bottom: 20px;
        }
        .error-container p {
            font-size: 16px;
            color: #333;
            word-wrap: break-word;
        }
    </style>
</head>
<body>

    <div class="error-container">
        <h2>An error occurred</h2>

        <%
            String errorMessage = (String) session.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
                <p><b>Error Details:</b> <%= errorMessage %></p>
        <%
                session.removeAttribute("errorMessage"); // remove after showing
            } else {
        %>
                <p>No error details available.</p>
        <%
            }
        %>
    </div>

</body>
</html>
