package clg.student;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.DatabaseConnection;


@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,  // 1MB - memory threshold
	    maxFileSize = 200 * 1024,         // 200KB per file
	    maxRequestSize = 1 * 1024 * 1024  // 1MB total request size
	)
public class AddStudentServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart=request.getPart("file");
		InputStream inputStream=filePart.getInputStream();
		
		PreparedStatement pstmt=null;
		PreparedStatement pstmtStatus=null;
		try (Workbook workbook = new XSSFWorkbook(inputStream)) {
			Sheet sheet=workbook.getSheetAt(0);
			Connection conn=DatabaseConnection.getConnection();
			
			String sqlInsertStudent="INSERT INTO student (stcode,stname,stroll,stdepartment,stphno,stgmail,stpassword,stadds,stdob) VALUES (?,?,?,?,?,?,?,?,?);";
			pstmt = conn.prepareStatement(sqlInsertStudent);
			
			for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                
                String code = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                String roll = row.getCell(2).getStringCellValue();
                String department = row.getCell(3).getStringCellValue();
                
                Cell phone_cell=row.getCell(4);
                long phone = (long) phone_cell.getNumericCellValue();
                		
                String email = row.getCell(5).getStringCellValue();
                String password = row.getCell(6).getStringCellValue();
                String address = row.getCell(7).getStringCellValue();
                
                
                // Convert dob from String to java.sql.Date
                Cell dobCell = row.getCell(8);
                java.sql.Date dob = null;
                if (dobCell != null) {
                    if (dobCell.getCellType() == CellType.NUMERIC) {
                        // If cell is in date format
                        Date excelDate = dobCell.getDateCellValue();
                        dob = new java.sql.Date(excelDate.getTime());
                    } else {
                        // If dob is stored as a String in Excel
                        String dobString = dobCell.getStringCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date parsedDate = sdf.parse(dobString);
                        dob = new java.sql.Date(parsedDate.getTime());
                    }
                }
                
                
                pstmt.setString(1, code);
                pstmt.setString(2, name);
                pstmt.setString(3, roll);
                pstmt.setString(4, department);
                pstmt.setLong(5, phone);
                pstmt.setString(6, email);
                pstmt.setString(7, password);
                pstmt.setString(8, address);
                pstmt.setDate(9, dob);
                
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            pstmt.close();
            
            String sqlInsertStudentStatus="INSERT INTO student_status (stcode,vote_status) VALUES (?,0);";
            pstmtStatus = conn.prepareStatement(sqlInsertStudentStatus);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; 
                String studentCode = row.getCell(0).getStringCellValue();
                pstmtStatus.setString(1, studentCode);
                pstmtStatus.addBatch();
            }
            pstmtStatus.executeBatch();
            
            
            response.sendRedirect("mng-student.jsp");
		}catch(Exception e) {
			 e.printStackTrace();
		}
	}

}
