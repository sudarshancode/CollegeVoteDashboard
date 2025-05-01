package mng.election;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.DatabaseConnection;


public class AddNoticeServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String notice=request.getParameter("notice");
		
		Connection conn=DatabaseConnection.getConnection();
		String insertQuery="INSERT INTO vote_notice (notice_text) VALUES (?);";
		try {
			PreparedStatement pst=conn.prepareStatement(insertQuery);
			
			pst.setString(1, notice);
			
			int result=pst.executeUpdate();
			
			if(result>0) {
				response.sendRedirect("mng-election.jsp?message=Notice Added successfully");
            }else {
                response.sendRedirect("mng-election.jsp?error=Notice Added Failed");
            }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
