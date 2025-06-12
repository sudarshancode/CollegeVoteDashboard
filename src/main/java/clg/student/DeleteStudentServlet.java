package clg.student;

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


public class DeleteStudentServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn=null;
		
		String studentCode = request.getParameter("studentCode"); 
        
        conn = DatabaseConnection.getConnection();
        
        String deleteVoteQuery = "DELETE FROM student_status WHERE stcode = ?";  
        String deleteCandidateQuery = "DELETE FROM student WHERE stcode = ?";
        
        try {
			PreparedStatement statusStm=conn.prepareStatement(deleteVoteQuery);
			statusStm.setString(1, studentCode);
			
			statusStm.executeUpdate();
			
			PreparedStatement studentStm = conn.prepareStatement(deleteCandidateQuery);
            studentStm.setString(1, studentCode);
            
            int res=studentStm.executeUpdate();
            
            if (res > 0) {
                response.sendRedirect("mng-student.jsp?message=Student deleted successfully");
            }else {
                response.sendRedirect("mng-student.jsp?error=Student code not found");
            }
            
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			HttpSession session=request.getSession();
			session.setAttribute("errorMessage", e.getMessage());
			
			response.sendRedirect("errorPage.jsp");
		}catch (Exception e) {
			//Catch any other exception
			e.printStackTrace();
			
			HttpSession session = request.getSession();
			session.setAttribute("errorMessage", e.getMessage());
			response.sendRedirect("errorPage.jsp");
		}
	}

}
