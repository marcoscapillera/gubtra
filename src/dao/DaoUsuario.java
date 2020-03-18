package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();

	}

	public void salvar(BeanCursoJsp usuario) {

		try {

			String sql = "insert into usuario (login, pass, nombre) values (?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getPass());
			insert.setString(3, usuario.getName());
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

	public List<BeanCursoJsp> listar() throws Exception {

		List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();

		String sql = "select * from usuario";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();

			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setPass(resultSet.getString("pass"));
			beanCursoJsp.setName(resultSet.getString("nombre"));

			listar.add(beanCursoJsp);
		}

		return listar;
	}

	public void delete(String id) {

		try {

			String sql = "delete from usuario where id = '" + id + "' ";
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

	public BeanCursoJsp consultar(String id) throws Exception {

		String sql = "select * from usuario where id = '" + id + "' ";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();

			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setPass(resultSet.getString("pass"));
			beanCursoJsp.setName(resultSet.getString("nombre"));

			return beanCursoJsp;
		}

		return null;
	}

	public boolean validarLogin(String login) throws Exception {

		String sql = "select count(1) as qtd from usuario where login = '" + login + "' ";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0; /* Return true */
		}

		return false;
	}
	
	public boolean validarLoginUpdate(String login, String id) throws Exception {

		String sql = "select count(1) as qtd from usuario where login = '" + login + "'  and id <> " + id;

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0; /* Return true */
		}

		return false;
	}

	public void actualizar(BeanCursoJsp usuario) {

		try {

			String sql = "update usuario set login=?, pass = ?, nombre= ? where id= " + usuario.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getPass());
			preparedStatement.setString(3, usuario.getName());
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
	
	public boolean validarPass(String pass) throws Exception {

		String sql = "select count(1) as qtd from usuario where pass = '" + pass + "' ";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0; /* Return true */
		}

		return false;
	}

}
