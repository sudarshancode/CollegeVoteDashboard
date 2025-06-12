package mng.election;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
public class AddNoticeServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String notice_title=request.getParameter("notice_title");
		String notice=request.getParameter("notice");
		Part noticeBg=request.getPart("notice-bg");
		
		Connection conn=DatabaseConnection.getConnection();
		
		InputStream inputStream=noticeBg.getInputStream();
		
		String insertQuery="INSERT INTO vote_notice (notice_title,notice_text,notice_bg) VALUES (?,?,?);";
		try {
			PreparedStatement pst=conn.prepareStatement(insertQuery);
			
			pst.setString(1, notice_title);
			pst.setString(2, notice);
			pst.setBlob(3, inputStream);
			
			int result=pst.executeUpdate();
			
			if(result>0) {
				response.sendRedirect("mng-election.jsp?message=Notice Added successfully");
            }else {
                response.sendRedirect("mng-election.jsp?error=Notice Added Failed");
            }
			
		}catch(Exception e) {
			e.printStackTrace();
			
			HttpSession session=request.getSession();
			session.setAttribute("errorMessage", e.getMessage());
			response.sendRedirect("errorPage.jsp");
		}
		
	}
}
