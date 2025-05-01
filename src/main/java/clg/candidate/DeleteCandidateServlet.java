package clg.candidate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.DatabaseConnection;


public class DeleteCandidateServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn=null;
		
		int candidateId = Integer.parseInt(request.getParameter("candidateId")); 
        
        conn = DatabaseConnection.getConnection();
        
        String deleteVoteQuery = "DELETE FROM vote_count WHERE c_id = ?";  
        String deleteCandidateQuery = "DELETE FROM candidate WHERE c_id = ?";
        
        try {
			PreparedStatement voteStm=conn.prepareStatement(deleteVoteQuery);
			voteStm.setInt(1, candidateId);
			
			voteStm.executeUpdate();
			
			PreparedStatement candidateStm = conn.prepareStatement(deleteCandidateQuery);
            candidateStm.setInt(1, candidateId);
            
            int res=candidateStm.executeUpdate();
            
            if (res > 0) {
                response.sendRedirect("mng-candidate.jsp?message=Candidate deleted successfully");
            }else {
                response.sendRedirect("mng-candidate.jsp?error=Candidate ID not found");
            }
            
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
