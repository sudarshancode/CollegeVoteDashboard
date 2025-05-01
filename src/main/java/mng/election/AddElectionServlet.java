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

@WebServlet("/AddElectionServlet")
public class AddElectionServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String electionName = request.getParameter("electionName");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String status = request.getParameter("status");

		String insertQuery = "INSERT INTO election_mng (election_name, election_start, election_end, election_status) VALUES (?,?,?,?);";

		Connection conn = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement pst = conn.prepareStatement(insertQuery);

			pst.setString(1, electionName);
			pst.setString(2, startDate);
			pst.setString(3, endDate);

			if (status.equals("upcoming")) {
				pst.setInt(4, 0);
			} else if (status.equals("ongoing")) {
				pst.setInt(4, 1);
			} else {
				pst.setInt(4, 2);
			}

			int result = pst.executeUpdate();

			if (result > 0) {
				request.getSession().setAttribute("addElection", "Election Created Successfully");
			} else {
				request.getSession().setAttribute("addElection", "Failed to Create Election");
			}
			response.sendRedirect("mng-election.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
