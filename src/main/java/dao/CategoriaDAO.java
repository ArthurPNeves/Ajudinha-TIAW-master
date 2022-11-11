package dao;

import model.Categoria;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CategoriaDAO {


    private DAO dao;

    public CategoriaDAO(DAO dao) {
        this.dao = dao;
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public List<Categoria> list() throws SQLException {
        try (PreparedStatement statement = dao.getConexao().prepareStatement(
                "SELECT * FROM categoria"
        )) {
            return  Categoria.parseCategoria(statement.executeQuery());
        }
    }



}
