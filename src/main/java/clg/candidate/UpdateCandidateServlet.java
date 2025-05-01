package clg.candidate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.DatabaseConnection;

@WebServlet("/UpdateCandidateServlet")
public class UpdateCandidateServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn=null;
		conn=DatabaseConnection.getConnection();
		
		int candidateId=Integer.parseInt(request.getParameter("candidateId"));
		String candidateName= request.getParameter("candidateName");
		String candidateImg=request.getParameter("candidateImage");
		String candidateParty= request.getParameter("candidateParty");
		String candidatePartySymbol= request.getParameter("candidatePartySymbol");
		String candidateGender=request.getParameter("candidateGender");
		String candidateDob=request.getParameter("candidateDob");
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(candidateDob, formatter);

        // Get current date
        LocalDate currentDate = LocalDate.now();

        // Calculate age
        int candidateAge = Period.between(dob, currentDate).getYears();
        
		String updateQuery = "UPDATE candidate SET c_name=?, c_image=?, c_partyname=?, c_partylogo=?, c_dob=?, c_age=?, c_gender=? WHERE c_id=?";
	
		try {
			PreparedStatement pst=conn.prepareStatement(updateQuery);
			
			// Set values in PreparedStatement
            pst.setString(1, candidateName);
            pst.setString(2, candidateImg);
            pst.setString(3, candidateParty);
            pst.setString(4, candidatePartySymbol);
            pst.setString(5, candidateDob);
            pst.setInt(6, candidateAge);
            pst.setString(7, candidateGender);
            pst.setInt(8, candidateId);

            int result = pst.executeUpdate(); // Execute update query

            if (result > 0) {
                request.setAttribute("updateCandidate", "Candidate updated successfully!");
            } else {
                request.setAttribute("updateCandidate", "Candidate update failed!");
            }

            response.sendRedirect("mng-candidate.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
