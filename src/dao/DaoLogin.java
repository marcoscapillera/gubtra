package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import connection.SingleConnection;

public class DaoLogin {
	
	private Connection connection;
	
	public DaoLogin() {
		connection = SingleConnection.getConnection();
		
	}
	
	public boolean validarLogin(String login, String pass) throws Exception{
		String sql="select*from usuario where login= '"+login+"' and pass='"+pass+"' ";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		System.out.println("Nombre:"+login);
		System.out.println("Nombre:"+pass);
		
		if (resultSet.next()) {
			
			System.out.println("El usuario fue encontrado en el sistema");
			return true; // Tiene Usuario
			
		}else {
			System.out.println("El usuario no fue encontrado en el sistema");
			return false; // No valido el usuario
		}
		
		
		
	}
	
	


}
