package readRW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dataBase {
	
	public dataBase(){
		
	}
	
	public static Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
	    // db parameters
	    String url       = "jdbc:mysql://localhost:3306/mylibrary";
	    String user      = "root";
	    String password  = "Simple 0.6";
	 
	    // create a connection to the database
	    conn = DriverManager.getConnection(url, user, password);
	    // more processing here
	    // ... 
	} catch(SQLException e) {
	   System.out.println(e.getMessage());
	} finally {
	 try{
	           if(conn != null)
	             conn.close();
	 }catch(SQLException ex){
	           System.out.println(ex.getMessage());
	 }
	}
		return conn;
	}
	
}
