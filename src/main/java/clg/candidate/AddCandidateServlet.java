package clg.candidate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.DatabaseConnection;

@WebServlet("/AddCandidateServlet")
public class AddCandidateServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn=null;
		
		conn=DatabaseConnection.getConnection();
		
		//Add Candidate form handle
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
		
		Boolean flag=false;
		
		String insertQueary="INSERT INTO candidate (c_name,c_image,c_partyname,c_partylogo,c_dob,c_age,c_gender) VALUES (?,?,?,?,?,?,?);";
		String insertVote="INSERT INTO vote_count (c_id,total_votes,c_name) VALUES (?,0,?)";
		try {
			PreparedStatement pst=conn.prepareStatement(insertQueary,Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, candidateName);
			pst.setString(2, candidateImg);
			pst.setString(3, candidateParty);
			pst.setString(4, candidatePartySymbol);
			pst.setString(5, candidateDob);
			pst.setInt(6, candidateAge);
			pst.setString(7, candidateGender);
			
			int result=pst.executeUpdate();
			if(result>0) {
				flag=true;
			}else {
				flag=false;
			}
			
			ResultSet rst=pst.getGeneratedKeys();
			
			int candidateId = -1;
            if (rst.next()) {
                candidateId = rst.getInt(1);
            }
            
            if(candidateId != -1) {
            	
            	PreparedStatement pst1=conn.prepareStatement(insertVote);
            	pst1.setInt(1, candidateId);
            	pst1.setString(2,candidateName);
            	pst1.executeUpdate();
            }
            
            if(flag) {
            	response.sendRedirect("mng-candidate.jsp");
            	request.setAttribute("addCandidate", "Candidate Insertion Successfully");
            }
            
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
