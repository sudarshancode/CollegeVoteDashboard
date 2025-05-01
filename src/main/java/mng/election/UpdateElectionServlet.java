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


@WebServlet("/UpdateElectionServlet")
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
		}
	}
}
