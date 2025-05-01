package mng.election;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.DatabaseConnection;


@WebServlet("/IdElectionServlet")
public class IdElectionServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int electionId=Integer.parseInt(request.getParameter("electionId"));
		
		String query="select * FROM election_mng WHERE election_id=?;";
		
		Connection conn=DatabaseConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(query);
			pre.setInt(1, electionId);
			ResultSet rs = pre.executeQuery();
			
			if(rs.next()) {
				request.setAttribute("electionId",rs.getInt("election_id"));
				request.setAttribute("electionName", rs.getString("election_name"));
				request.setAttribute("electionStart", rs.getString("election_start"));
				request.setAttribute("electionEnd", rs.getString("election_end"));
				request.setAttribute("electionStatus", rs.getInt("election_status"));
				
				request.getRequestDispatcher("update-election.jsp").forward(request, response);
			}else {
				response.sendRedirect("mng-election.jsp?error=election ID not found");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
