package clg.student;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
public class UpdateStudentServlet extends HttpServlet {
	
	private String getValue(Part part) throws IOException {
	    InputStream input = part.getInputStream();
	    byte[] bytes = input.readAllBytes();
	    return new String(bytes, "UTF-8").trim();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn=null;
		conn=DatabaseConnection.getConnection();
		
		String studentCode = getValue(request.getPart("studentCode"));
		String studentName = getValue(request.getPart("studentName"));
		String studentRoll = getValue(request.getPart("studentRoll"));
		String studentDept = getValue(request.getPart("studentDepartment"));
		String studentPhno = getValue(request.getPart("studentPhno"));
		String studentEmail = getValue(request.getPart("studentEmail"));
		String studentPassword = getValue(request.getPart("studentPassword"));
		String studentAddress = getValue(request.getPart("studentAddress"));
		String studentDob = getValue(request.getPart("studentDob"));
		Part imagePart = request.getPart("studentPhoto");
		
		
		InputStream inputStream = null;
        if (imagePart != null && imagePart.getSize() > 0) {
            inputStream = imagePart.getInputStream();
        }
		
        
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
			
			System.out.print(result);
			
            if (result > 0) {
                request.setAttribute("updateStudent", "Student updated successfully!");
            } else {
            	
                request.setAttribute("updateStudent", "Student update failed!");
            }

            response.sendRedirect("mng-student.jsp");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			HttpSession session=request.getSession();
			session.setAttribute("errorMessage", e.getMessage());
			
			response.sendRedirect("errorPage.jsp");
		}
	}

}
