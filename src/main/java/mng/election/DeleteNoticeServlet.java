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

@WebServlet("/DeleteNoticeServlet")
public class DeleteNoticeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String noticeId=request.getParameter("id");
		
		Connection conn=DatabaseConnection.getConnection();
		
		String Queary="DELETE FROM vote_notice WHERE notice_id=?;";
		
		try {
			PreparedStatement pst=conn.prepareStatement(Queary);
			
			pst.setInt(1,Integer.parseInt(noticeId));
			
			int result=pst.executeUpdate();
			
			if(result>0) {
				response.sendRedirect("mng-election.jsp?message=Notice deleted successfully");
            }else {
                response.sendRedirect("mng-election.jsp?error=Notice Deleted Failed");
            }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
