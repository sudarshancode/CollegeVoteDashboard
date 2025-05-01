package mng.election;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.DatabaseConnection;

@WebServlet("/AddNoticeServlet")
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
