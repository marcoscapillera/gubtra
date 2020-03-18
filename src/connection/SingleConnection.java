package connection;

import java.sql.DriverManager;

import  java.sql.Connection ;

/*
 * 
 * @Marcos 
 * 
 * Responsable por la conexion
 * 
 * */

public class SingleConnection {
	
	private static String banco ="jdbc:postgresql://ec2-3-229-210-93.compute-1.amazonaws.com/dchrveno561m7a?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
	private static String user="mjrgqwitjntgmh";
	private static String password="16a95989fee677bb1dae363f4c660d55634d01820475d1ec2eccf89cadc244b7";
	
	private static Connection connection = null;
	
	
	static {
	
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	private static void conectar() {
		try {
			
			System.out.println("Conectando al banco");
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				System.out.println("Driver do banco PostgreSQL");
				connection = DriverManager.getConnection(banco, user, password);
				System.out.println("Passando os parametros de conexion al banco");
				connection.setAutoCommit(false);
				System.out.println("Conectado ao banco de dados gubtra con exito!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
			throw new RuntimeException("Error al conectar la base de edatos");
			
			
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	

}
