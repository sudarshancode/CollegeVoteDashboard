package clg.candidate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.DatabaseConnection;


public class IdCandidateServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//Create session for update-candidate.jsp page
		String username=request.getParameter("username");
		HttpSession session=request.getSession();
		session.setAttribute("username", username);
		
		int candidateId = Integer.parseInt(request.getParameter("candidateId"));
		String query = "SELECT * FROM candidate WHERE c_id = ?";
		Connection conn = DatabaseConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(query);
			pre.setInt(1, candidateId);
			ResultSet rs = pre.executeQuery();

			if (rs.next()) {
				// Store data in request attributes
				request.setAttribute("candidateId", rs.getInt("c_id"));
				request.setAttribute("candidateName", rs.getString("c_name"));
				request.setAttribute("candidateImage", rs.getString("c_image"));
				request.setAttribute("candidateParty", rs.getString("c_partyname"));
				request.setAttribute("candidatePartySymbol", rs.getString("c_partylogo"));
				request.setAttribute("candidateGender", rs.getString("c_gender"));
				request.setAttribute("candidateDob", rs.getString("c_dob"));
				request.setAttribute("candidateAge", rs.getInt("c_age"));

				// Forward to JSP page
				request.getRequestDispatcher("update-candidate.jsp").forward(request, response);
			}else {
				response.sendRedirect("mng-candidate.jsp?error=Candidate ID not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
