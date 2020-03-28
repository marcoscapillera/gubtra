package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanDocumento;
import beans.BeanTramites;
import connection.SingleConnection;

public class DaoDocumento {

	private Connection connection;

	public DaoDocumento() {
		connection = SingleConnection.getConnection();

	}

	public void salvar(BeanDocumento documento) {
		System.out.println("entro salvar");

		try {

			String sql = "INSERT INTO documento (numero_documento,nombre_documento, validez,fotobase64, contenttype) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(	 1, documento.getNumdocumento());
			insert.setString(2, documento.getNombredocumento());
			insert.setString(3, documento.getValidez());
			insert.setString(4, documento.getFotoBase64());
			insert.setString(5, documento.getContentType());

			
			
			insert.execute();
			connection.commit();
			System.out.println("Todo correcto, salvando datos");

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}

	}

	public List<BeanDocumento> listar() throws Exception {

		List<BeanDocumento> listar = new ArrayList<BeanDocumento>();

		String sql = "select * from documento";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanDocumento beanDocumento = new BeanDocumento();

			beanDocumento.setNumdocumento(resultSet.getLong("numero_documento"));
			beanDocumento.setNombredocumento(resultSet.getString("nombre_documento"));
			beanDocumento.setValidez(resultSet.getString("validez"));
			beanDocumento.setFotoBase64(resultSet.getString("fotobase64"));
			beanDocumento.setContentType(resultSet.getString("contenttype"));
			
			

			listar.add(beanDocumento);
		}

		return listar;
	}

	public void delete(String numero_documento) {

		try {

			String sql = "delete from documento where numero_documento = '" + numero_documento + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

			connection.commit();

			System.out.println("Borro id");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("no borro1");

			try {

				connection.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
				System.out.println("no borro2");

			}

		}

	}

	public BeanDocumento consultar(String numero_documento) throws Exception {

		String sql = "select * from documento where numero_documento = '" + numero_documento + "' ";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			BeanDocumento beandocumento = new BeanDocumento();
			
			beandocumento.setNumdocumento(resultSet.getLong("numero_documento"));
			beandocumento.setNombredocumento(resultSet.getString("nombre_documento"));
			beandocumento.setValidez(resultSet.getString("validez"));
			beandocumento.setFotoBase64(resultSet.getString("fotobase64"));
			beandocumento.setContentType(resultSet.getString("contenttype"));
			


			return beandocumento;
		}

		return null;
	}


	public void actualizar(BeanDocumento documento) {

		try {

			String sql = "update documento set  nombre_documento= ?,validez=?  where numero_documento= " + documento.getNumdocumento();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, documento.getNombredocumento());
			preparedStatement.setString(2, documento.getValidez());

			
			
			preparedStatement.execute();
			connection.commit();

			System.out.println("actualizo");

		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("no actualizo");
			try {

				connection.rollback();

			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("no actualizo1");
			}

		}

	}
	


}
