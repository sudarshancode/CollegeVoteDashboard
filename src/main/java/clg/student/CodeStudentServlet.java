package clg.student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.DatabaseConnection;


public class CodeStudentServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentCode=request.getParameter("studentCode");
		
		String Queary="SELECT * FROM student WHERE stcode=?;";
		
		Connection conn=DatabaseConnection.getConnection();
		
		try {
			PreparedStatement pst=conn.prepareStatement(Queary);
			pst.setString(1, studentCode);
			
			ResultSet rs=pst.executeQuery();
			
			if (rs.next()) {
				// Store data in request attributes
				request.setAttribute("studentCode", rs.getString("stcode"));
				request.setAttribute("studentName", rs.getString("stname"));
				request.setAttribute("studentRoll", rs.getString("stroll"));
				request.setAttribute("studentDept", rs.getString("stdepartment"));
				request.setAttribute("studentPhno", rs.getString("stphno"));
				request.setAttribute("studentEmail", rs.getString("stgmail"));
				request.setAttribute("studentPassword", rs.getString("stpassword"));
				request.setAttribute("studentAddress", rs.getString("stadds"));
				request.setAttribute("studentDob", rs.getString("stdob"));

				// Forward to JSP page
				request.getRequestDispatcher("update-student.jsp").forward(request, response);
			}else {
				response.sendRedirect("mng-student.jsp?error=Student Code not found");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
			HttpSession session = request.getSession();
			session.setAttribute("errorMessage", e.getMessage());

			response.sendRedirect("errorPage.jsp");
		}
	}

}
