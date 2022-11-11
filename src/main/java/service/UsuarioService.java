package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;
import responses.RespostaERRO;
import responses.RespostaSucesso;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;


public class UsuarioService {

	private UsuarioDAO usuarioDAO;

	public UsuarioService(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public UsuarioService(DAO dao) { this(new UsuarioDAO(dao)); }

	public Object delete(Request request, Response response) throws SQLException {
		int id = request.session().attribute("id");
		usuarioDAO.delete(id);
		request.session(false);

		return new RespostaSucesso("Usuário removido com sucesso");
	}

	public Object get(Request request, Response response) throws SQLException {
		int id = request.session().attribute("id");

		Usuario usuario = usuarioDAO.getByID(id);

		if (usuario == null) {
			return new RespostaERRO("Usuario não encontrado");
		}

		usuario.setSenha(null);
		usuario.setId(null);
		return new RespostaSucesso(usuario);
	}

	public Object update(Request request, Response response) throws SQLException {
		int id = request.session().attribute("id");

		Usuario usuario = usuarioDAO.getByID(id);

		if (usuarioDAO.update(usuario)) {
			return new RespostaSucesso("Usuario atualizado");
		} else {
			return new RespostaERRO("Erro ao atualizar usuario");
		}


	}

	public Object login(Request request, Response response) throws SQLException {
		var body = new Gson().fromJson(request.body(), HashMap.class);
	System.out.println("TESTE1");
		Usuario usuario = usuarioDAO.authenticate(body.get("email").toString(), body.get("senha").toString());


		if (usuario != null) {
			System.out.println(usuario.toString());
			request.session().attribute("id", usuario.getId());
			return new RespostaSucesso("Logado");
		}
		else {

			response.status(404);
			return new RespostaERRO("Usuário inexistente");
		}


	}

	public Object logon(Request request, Response response) {
		System.out.println(request.body());
		var usuario = new GsonBuilder().setDateFormat("dd/MM").create().fromJson(request.body(), Usuario.class);
		System.out.println(usuario);
		try  {
			usuarioDAO.addUser(usuario);
			return new RespostaSucesso("Sucesso ao criar usuário");
		} catch (SQLException e) {
			e.printStackTrace();
			response.status(400);
			return new RespostaERRO("Erro ao criar usuário");
		}

	}

	public Object logout(Request request, Response ignoredResponse) {
		request.session().invalidate();
		return new RespostaSucesso("Logout efetuado!");
	}
}