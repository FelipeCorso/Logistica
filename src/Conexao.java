import java.sql.*;

public class Conexao {
	static String status = "";
	
	public static Connection getConnection(){
		Connection conn = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
						
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dblog","loguser", "logistica@2014");
		}
		catch(Exception e){
			status = e.getMessage();
		}
		
		return conn;
	}
}
