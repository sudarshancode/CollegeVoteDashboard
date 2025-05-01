package com.example;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sessionKey=request.getParameter("userSessionKey");
		
		System.out.print(sessionKey);
		//Create Session
		HttpSession session=request.getSession();
		session.setAttribute("username", sessionKey);
		response.sendRedirect("index.jsp");
	}
}
