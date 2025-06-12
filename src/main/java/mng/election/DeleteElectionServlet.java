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


public class DeleteElectionServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int electionId=Integer.parseInt(request.getParameter("electionId"));
		
		String Query="DELETE FROM election_mng where election_id=?;";
		
		Connection conn=DatabaseConnection.getConnection();
		
		try {
			PreparedStatement pst=conn.prepareStatement(Query);
			
			pst.setInt(1, electionId);
			
			int result=pst.executeUpdate();
			
			if(result>0) {
				response.sendRedirect("mng-election.jsp?message=Election deleted successfully");
            }else {
                response.sendRedirect("mng-election.jsp?error=Election ID not found");
            }
			
		}catch(Exception e) {
			e.printStackTrace();
			
			HttpSession session=request.getSession();
			session.setAttribute("errorMessage", e.getMessage());
			response.sendRedirect("errorPage.jsp");
		}
	}

}
