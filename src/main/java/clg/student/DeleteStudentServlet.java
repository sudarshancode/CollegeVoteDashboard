package clg.student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.DatabaseConnection;

@WebServlet("/DeleteStudentServlet")
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
		}
	}

}
