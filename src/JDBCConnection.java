import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection 
{
	Connection conn= null;
	public static Connection getConnection()
	{
	try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game","root","10062000ha");
			return conn;
			
		} catch (Exception e) {}
		return null; 
	}
	
}

