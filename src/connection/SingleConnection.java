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
	
	private static String banco ="jdbc:postgresql://ec2-18-209-187-54.compute-1.amazonaws.com/dbh22hbj33snm7?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
	private static String user="ozacegeafmmqic";
	private static String password="f657e377e4e25e898d209eadfe0dd1f550d28727f5074244dafd88b65b560594";
	
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
