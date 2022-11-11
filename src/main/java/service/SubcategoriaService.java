package service;

import dao.DAO;
import dao.SubcategoriaDAO;
import responses.RespostaSucesso;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class SubcategoriaService {

    private SubcategoriaDAO subcategoriaDAO;

    public SubcategoriaService(SubcategoriaDAO subcategoriaDAO) {
        this.subcategoriaDAO = subcategoriaDAO;
    }

    public SubcategoriaService(DAO dao) { this(new SubcategoriaDAO(dao)); }

    public Object list(Request request, Response response) throws SQLException {
        int id = Integer.parseInt(request.params(":id"));

        return new RespostaSucesso(subcategoriaDAO.list(id));
    }
}
