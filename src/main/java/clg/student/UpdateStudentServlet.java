package clg.student;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.example.DatabaseConnection;

@WebServlet("/UpdateStudentServlet")
@MultipartConfig
public class UpdateStudentServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn=null;
		conn=DatabaseConnection.getConnection();
		
		String studentCode=request.getParameter("studentCode");
		String studentName=request.getParameter("studentName");
		String studentRoll=request.getParameter("studentRoll");
		String studentDept=request.getParameter("studentDepartment");
		String studentPhno=request.getParameter("studentPhno");
		String studentEmail=request.getParameter("studentEmail");
		String studentPassword=request.getParameter("studentPassword");
		String studentAddress=request.getParameter("studentAddress");
		String studentDob=request.getParameter("studentDob");
		Part imagePart=request.getPart("studentPhoto");
		
		InputStream inputStream=imagePart.getInputStream();
		String updateQueary="UPDATE student SET stcode=?, stname=? , stroll=?,stdepartment=?,stphno=?,stgmail=?,stpassword=?,stadds=?,stdob=?,image=? WHERE stcode=?;";
		
		try {
			PreparedStatement pst=conn.prepareStatement(updateQueary);
			
			pst.setString(1, studentCode);
			pst.setString(2, studentName);
			pst.setString(3, studentRoll);
			pst.setString(4, studentDept);
			pst.setString(5, studentPhno);
			pst.setString(6, studentEmail);
			pst.setString(7, studentPassword);
			pst.setString(8, studentAddress);
			pst.setString(9, studentDob);
			pst.setBlob(10, inputStream);
			pst.setString(11, studentCode);
			
			int result = pst.executeUpdate(); // Execute update query

            if (result > 0) {
                request.setAttribute("updateStudent", "Student updated successfully!");
            } else {
                request.setAttribute("updateStudent", "Student update failed!");
            }

            response.sendRedirect("mng-student.jsp");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
