import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/loginServlet")
public class loginServlet extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement ps;
	
	@Override
	public void init(ServletConfig config) {
		ServletContext context = config.getServletContext();
		String driverClass = config.getInitParameter("org.mariadb.jdbc.Driver");
		//System.out.println(driverClass);
		
		String db = "jdbc:mariadb://mariadb.vamk.fi/e2101083_java_server";
		
		String dbUser = context.getInitParameter("db_user");
		String dbPassword = context.getInitParameter("db_password");
		
		try { 
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(db,dbUser,dbPassword);
			ps = conn.prepareStatement("SELECT * FROM login_details WHERE username =  ? AND password = ?");
			//System.out.println(conn);
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		System.out.println(email);
		System.out.println(password);
		
		 try {
	            // Bind the user's input to the prepared statement
	            ps.setString(1, email);
	            ps.setString(2, password);
	            

	            ResultSet rs = ps.executeQuery();
	            RequestDispatcher reqDis;
	            
	            if (rs.next()) {
	                // User exists in the database, login successful
	                reqDis = req.getRequestDispatcher("homeServlet");
	                req.setAttribute("message", "Login successfully");
	                reqDis.forward(req, res);
	            } else {
	                // User not found in the database, login unsuccessful
	                reqDis = req.getRequestDispatcher("login.html");
	                reqDis.include(req, res);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	@Override
	public void destroy() {
		try {
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	
	}
}