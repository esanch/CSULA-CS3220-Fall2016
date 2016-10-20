
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/ListEmployees", loadOnStartup = 1)
public class ListEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

        try{
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e ){
            throw new ServletException( e );
        }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection c = null;
		List<Employee> employees = new ArrayList<>();
		
		try {
			
			c = ConnectionUtils.getMySQLConnection("root", "1234", "localhost", 3306, "test");
			Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from employees" );
            
            while( rs.next() )
            {
            	
                int id  = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String address = rs.getString("address");
                employees.add(new Employee(id, firstName, lastName, address));
            }
			
		} catch (SQLException e) {
			// Escalate to Server error
			throw new ServletException(e);
		} 
		//Always close connections, no matter what happened
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}

		
		request.setAttribute("employees", employees);
		request.getRequestDispatcher("/WEB-INF/ListEmployees.jsp").forward(request, response);
	}

}
