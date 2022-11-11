package app;

import static spark.Spark.*;

import com.google.gson.GsonBuilder;
import dao.DAO;
import responses.RespostaSucesso;
import service.CategoriaService;
import service.SubcategoriaService;
import service.UsuarioService;

import java.sql.SQLException;


public class Aplicacao {

    private static final DAO dao = new DAO(
            "tutoritec.postgres.database.azure.com",
            "postgres",
            "adminTutoritec",
            "123456!A"
    );

    private static final CategoriaService categoriaService = new CategoriaService(dao);
	private static final UsuarioService usuarioService = new UsuarioService(dao);

    private static final SubcategoriaService subcategoriaService = new SubcategoriaService(dao);

	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public/Front-end/");

        defaultResponseTransformer(new GsonBuilder().serializeNulls().create()::toJson);

        get("/",(request, response) -> {
            response.redirect("/inicio-login/login.html");
            return new RespostaSucesso(null);
        });

        path("/api", () -> {

            path("/usuario", () -> {
                    before("/*", (request, response) -> {
                        if (request.session().attribute("id") == null) halt(403);
                    });

                    path("/:id", () -> {
                        put("/", usuarioService::update);
                        get("/", usuarioService::get);
                        delete("/", usuarioService::delete);
                    });

                    path("/categorias", () -> {
                        get("/", categoriaService::list);
                        get("/:id/subcategorias/", subcategoriaService::list);
                    });
            });

            post("/login", usuarioService::login);
            post("/logon", usuarioService::logon);
            post("/logout", usuarioService::logout);
        });

        exception(IllegalArgumentException.class, (e, request, response) ->
                response.status(400));

        exception(SQLException.class, (e, request, response) -> {
            response.status(503);

        } );

        exception(RuntimeException.class, (e, request, response) -> {
            response.status(500);

        });
    }
}