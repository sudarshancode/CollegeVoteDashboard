package com.example;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.DatabaseConnection;

public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Connection conn=null;
		String queary ="SELECT * FROM admin";
		
		Boolean flag=false;
		
		conn=DatabaseConnection.getConnection();
		if(conn!=null) {
			System.out.print("Data base connected successfullay");
		}
		
		try {
			Statement stm=conn.createStatement();
			ResultSet rs=stm.executeQuery(queary);
			
			while(rs.next()) {
				if(username.equals(rs.getString("username")) && password.equals(rs.getString("password"))) {
					flag=true;
				}
			}
			if(flag) {
				HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("dashboard.jsp");
			}else {
				String msg="Invalid Username and Password";
				request.setAttribute("userId",msg);
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
				response.sendRedirect("index.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
