package Conect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {

	private void connection() {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SIGEI", "root", "123456");
		
				
		} catch(SQLException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
	}
}
