package dao;

import model.Usuario;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class UsuarioDAO {

	private final DAO dao;

	public UsuarioDAO(DAO dao) {
		this.dao = dao;
	}

	
	
	public boolean addUser(Usuario usuario) throws SQLException {
		try (PreparedStatement statement = dao.getConexao().prepareStatement(
				"INSERT INTO usuario (" +
						"email, senha, usuarionome, numerosres, cep, telefone, numerocartao, cvc, datavalidade)" +
						"VALUES (?,?,?,?,?,?,?,?,?)"
		)) {

			statement.setString(1, usuario.getEmail());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getNome());
			statement.setInt(4, usuario.getNumRes());
			statement.setInt(5, usuario.getCEP());
			statement.setString(6, usuario.getTelefone());
			statement.setString(7, usuario.getCartao());
			statement.setString(8, usuario.getCVC());
			statement.setDate(9, usuario.getValidade());

			return statement.executeUpdate() > 0;
		}
	}

	
	public Usuario getByID(int id) throws SQLException {
		try (PreparedStatement statement = dao.getConexao().prepareStatement(
				"SELECT * FROM usuario WHERE id = ?"
		)) {
			return Usuario.parseUsuario(statement.executeQuery()).get(0);
		}


	}
	
	
	
	public boolean update(Usuario usuario) throws SQLException {

		try (PreparedStatement statement = dao.getConexao().prepareStatement(
				"UPDATE usuario " +
						"SET" +
						"email = ?," +
						"senha = ?," +
						"usuarionome = ?," +
						"numerosres = ?," +
						"cep = ?," +
						"telefone = ?," +
						"numerocartao = ?," +
						"cvc = ?," +
						"datavalidade = ?" +
						"WHERE id = ?"
		)) {

			statement.setString(1, usuario.getEmail());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getNome());
			statement.setInt(4, usuario.getNumRes());
			statement.setInt(5, usuario.getCEP());
			statement.setString(6, usuario.getTelefone());
			statement.setString(7, usuario.getCartao());
			statement.setString(8, usuario.getCVC());
			statement.setDate(9, usuario.getValidade());
			statement.setInt(10, usuario.getId());

			return statement.executeUpdate() > 0;
		}
	}
	
	
	public boolean delete(int id) throws SQLException {
		try (PreparedStatement statement = dao.getConexao().prepareStatement(
				"DELETE FROM usuario WHERE id = ?"
		)) {
			statement.setInt(1, id);

			return statement.executeUpdate() > 0;
		}
	}

	public Usuario authenticate(String email, String senha) throws SQLException {
		List<Usuario> usuarios;

		try (PreparedStatement statement = dao.getConexao().prepareStatement(
				"SELECT * FROM usuario WHERE email = ? AND senha = ? LIMIT 1"
		)) {
			statement.setString(1, email);
			statement.setString(2, senha);

			usuarios = Usuario.parseUsuario(statement.executeQuery());
		}

		return (usuarios.size() > 0)? usuarios.get(0) : null;
	}

	public static String hashSenha(String senha) {
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		m.update("SALT".getBytes());
		return new BigInteger(m.digest(senha.getBytes())).abs().toString(16);

	}
}