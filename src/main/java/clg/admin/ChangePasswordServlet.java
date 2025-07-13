package clg.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.DatabaseConnection;


public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");

		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = DatabaseConnection.getConnection();

			//Check current password
			pst = conn.prepareStatement("SELECT password FROM admin WHERE username = ?");
			pst.setString(1, username);
			rs = pst.executeQuery();

			if (rs.next()) {
				String dbPassword = rs.getString("password");

				if (currentPassword.equals(dbPassword)) {
					//Check if new passwords match
					if (newPassword.equals(confirmPassword)) {
						pst.close();
						pst = conn.prepareStatement("UPDATE admin SET password = ? WHERE username = ?");
						pst.setString(1, newPassword);
						pst.setString(2, username);

						int result = pst.executeUpdate();
						if (result > 0) {
							request.setAttribute("message", "Password updated successfully.");
						} else {
							request.setAttribute("message", "Failed to update password.");
						}
					} else {
						request.setAttribute("message", "New passwords do not match.");
					}
				} else {
					request.setAttribute("message", "Current password is incorrect.");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Error: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (pst != null)
					pst.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		request.getRequestDispatcher("admin-setting.jsp").forward(request, response);
	}
}
