package service;

import dao.CategoriaDAO;
import dao.DAO;
import model.Categoria;
import responses.RespostaSucesso;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.List;

public class CategoriaService {


    private CategoriaDAO categoriaDAO;

    public CategoriaService(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public CategoriaService(DAO dao) { this(new CategoriaDAO(dao)); }

    public Object list(Request request, Response response) throws SQLException {
        return new RespostaSucesso(categoriaDAO.list());
    }
}
