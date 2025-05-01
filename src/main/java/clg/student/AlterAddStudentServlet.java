package clg.student;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import com.example.DatabaseConnection;


@MultipartConfig
public class AlterAddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn=null;
		
		String studentId=request.getParameter("studentId");
		String studentName=request.getParameter("studentName");
		String studentRoll=request.getParameter("studentRoll");
		String studentDept=request.getParameter("studentDept");
		String studentAdds=request.getParameter("studentAdds");
		String studentDob=request.getParameter("studentDob");
		String studentPhone=request.getParameter("studentPhone");
		String studentEmail=request.getParameter("studentEmail");
		Part imagePart=request.getPart("studentPhoto");
		
		BigInteger phoneNumber = null;
		
		if (studentPhone != null && !studentPhone.trim().isEmpty()) {
			phoneNumber = new BigInteger(studentPhone.trim());
		}else {
			System.out.print("Hello gogoogog");
		}
		InputStream inputStream=imagePart.getInputStream();
		
		String sqlInsertStudent="INSERT INTO student (stcode,stname,stroll,stdepartment,stphno,stgmail,stpassword,stadds,stdob,image) VALUES (?,?,?,?,?,?,?,?,?,?);";
		String sqlInsertStudentStatus="INSERT INTO student_status (stcode,vote_status) VALUES (?,0);";
		try {
			conn=DatabaseConnection.getConnection();
			
			PreparedStatement pstmt=conn.prepareStatement(sqlInsertStudent);
			
			pstmt.setString(1, studentId);
			pstmt.setString(2, studentName);
			pstmt.setString(3, studentRoll);
			pstmt.setString(4, studentDept);
			pstmt.setString(8, studentAdds);
			pstmt.setString(9, studentDob);
			pstmt.setString(7, "Hello");
			pstmt.setLong(5, phoneNumber.longValueExact());
			pstmt.setString(6, studentEmail);
			pstmt.setBlob(10, inputStream);
			
			int row1 = pstmt.executeUpdate();
			int row2=-1;
			if(studentId != null) {
            	
            	PreparedStatement pst1=conn.prepareStatement(sqlInsertStudentStatus);
            	pst1.setString(1, studentId);
            	row2=pst1.executeUpdate();
            }
			
	        if (row1 > 0 && row2 >0) {
	        	response.sendRedirect("mng-student.jsp");
            	request.setAttribute("addStudent", "Student Insertion Successfully");
	        }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
