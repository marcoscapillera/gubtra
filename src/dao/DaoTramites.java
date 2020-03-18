package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import beans.BeanTramites;
import connection.SingleConnection;

public class DaoTramites {

	private Connection connection;

	public DaoTramites() {
		connection = SingleConnection.getConnection();

	}

	public void salvar(BeanTramites tramites) {
		System.out.println("entro salvar");

		try {

			String sql = "INSERT INTO tramites (tramite,fecha, costo) VALUES (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, tramites.getTramite());
			insert.setString(2, tramites.getFecha());
			insert.setString(3, tramites.getCosto());
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

	public List<BeanTramites> listar() throws Exception {

		List<BeanTramites> listar = new ArrayList<BeanTramites>();

		String sql = "select * from tramites";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanTramites beanTramites = new BeanTramites();

			beanTramites.setId(resultSet.getLong("id"));
			beanTramites.setTramite(resultSet.getString("tramite"));
			beanTramites.setFecha(resultSet.getString("fecha"));
			beanTramites.setCosto(resultSet.getString("costo"));
			

			listar.add(beanTramites);
		}

		return listar;
	}

	public void delete(String id) {

		try {

			String sql = "delete from tramites where id = '" + id + "' ";
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

	public BeanTramites consultar(String id) throws Exception {

		String sql = "select * from tramites where id = '" + id + "' ";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			BeanTramites beanTramites = new BeanTramites();
			
			beanTramites.setId(resultSet.getLong("id"));
			beanTramites.setTramite(resultSet.getString("tramite"));
			beanTramites.setFecha(resultSet.getString("fecha"));
			beanTramites.setCosto(resultSet.getString("costo"));


			return beanTramites;
		}

		return null;
	}


	public void actualizar(BeanTramites tramites) {

		try {

			String sql = "update tramites set tramite = ?, fecha= ?, costo=? where id= " + tramites.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tramites.getTramite());
			preparedStatement.setString(2, tramites.getFecha());
			preparedStatement.setString(3, tramites.getCosto());
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
