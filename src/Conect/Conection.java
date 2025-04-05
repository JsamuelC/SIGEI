package Conect;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conection {
	
	private static Connection connection;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/SIGEI";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "123456";		
	
	private Conection() {
		
	}
	
	public static Connection getConnection() throws SQLException{
		
		
		if (connection == null || connection.isClosed() ) {
			
			try {
				connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				System.out.print("conexion establecida");
				
			} catch (SQLException e ) {
				e.printStackTrace();
				throw new SQLException ("Error al conectar la base de datos", e);
			}
		}
		return connection;
	}
	
	public static void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()  ) {
				
				connection.close();
			}
		} catch (SQLException e ) {
			e.printStackTrace();
		}
	}

}