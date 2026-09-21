import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		PrintWriter print = response.getWriter(); 
		
		String name = request.getParameter("fullName");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String branch = request.getParameter("branch");
		
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		
		String url = "jdbc:mysql://localhost:3306/akhil";
		String user = "root";
		String password = "@Wzkejy69ts";
		String query= """
				INSERT INTO users (name,userName,email,password,branch ) VALUES (?,?,?,?,?)
		""";
		
		try ( Connection connection = DriverManager.getConnection(url,user,password);
				PreparedStatement statement = connection.prepareStatement(query);
				) {
			
			
				
				statement.setString(1, name);
				statement.setString(2, userName);
				statement.setString(3, email);
				statement.setString(4, pass);
				statement.setString(5, branch);
				
				int rowsAffected = statement.executeUpdate();
				
				
				if(rowsAffected!=0) {
					print.println("<h1>Registration Success ...!</h1>");
				} else {
					print.println("<h1>Registration Failed ...!</h1>");
				}
			
			} catch(SQLException e) {
			System.out.println(e);
		}
		
		
		catch(Exception e) {
			System.out.println(e);
		}
	}

	
}
