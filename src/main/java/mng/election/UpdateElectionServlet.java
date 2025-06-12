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



public class UpdateElectionServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int electionId=Integer.parseInt(request.getParameter("electionId"));
		String electionName=request.getParameter("electionName");
		String electionStart=request.getParameter("startDate");
		String electionEnd=request.getParameter("endDate");
		String electionStatus=request.getParameter("status");
		
		String updateQueary="UPDATE election_mng SET election_name=?, election_start=?, election_end=?,election_status=? WHERE election_id=?;";
		
		Connection conn=DatabaseConnection.getConnection();
		
		try {
			
			PreparedStatement pst=conn.prepareStatement(updateQueary);
			
			pst.setString(1, electionName);
			pst.setString(2, electionStart);
			pst.setString(3, electionEnd);
			
			if(electionStatus.equals("upcoming")) {
				pst.setInt(4, 0);
			}else if(electionStatus.equals("ongoing")) {
				pst.setInt(4, 1);
			}else {
				pst.setInt(4, 2);
			}
			pst.setInt(5, electionId);
			
			int result=pst.executeUpdate();
			
			if(result>0) {
				request.setAttribute("updateElection", "Election updated successfully!");
            } else {
                request.setAttribute("updateElection", "Election update failed!");
            }

            response.sendRedirect("mng-election.jsp");
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			HttpSession session=request.getSession();
			session.setAttribute("errorMessage", e.getMessage());
			response.sendRedirect("errorPage.jsp");
		}
	}
}
