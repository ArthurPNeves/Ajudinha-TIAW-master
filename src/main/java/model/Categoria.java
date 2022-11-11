package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private Integer id;
    private String Nome;

    public Categoria() {
    }

    public Categoria(Integer id, String nome) {
        this.id = id;
        Nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + id +
                ", Nome='" + Nome + '\'' +
                '}';
    }


    public static List<Categoria> parseCategoria(ResultSet resultSet) throws SQLException {
        List<Categoria> categorias = new ArrayList<>();

        while (resultSet.next()) {
            categorias.add(new Categoria(
                    resultSet.getInt("id"),
                    resultSet.getString("nome")
            ));
        }
        return categorias;
    }
}
