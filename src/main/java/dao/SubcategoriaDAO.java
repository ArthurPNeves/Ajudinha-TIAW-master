package dao;

import model.Subcategoria;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SubcategoriaDAO {

    private final DAO dao;

    public SubcategoriaDAO(DAO dao) {
        this.dao = dao;
    }


    public List<Subcategoria> list(int categoria_id) throws SQLException {
        try (PreparedStatement statement = dao.getConexao().prepareStatement(
                "SELECT * FROM subcategoria WHERE categoria_id = ?"
        )) {
            statement.setInt(1, categoria_id);
            return Subcategoria.parseSubcategoria(statement.executeQuery());
        }
    }
}
