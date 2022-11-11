package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Subcategoria {

    private Integer id;
    private String titulo;
    private String text;
    private Integer categoria;
    private String url;

    public Subcategoria(Integer id, String titulo, String text, Integer categoria, String url) {
        this.id = id;
        this.titulo = titulo;
        this.text = text;
        this.categoria = categoria;
        this.url = url;
    }

    public Subcategoria() {
    }


    public static List<Subcategoria> parseSubcategoria(ResultSet resultSet) throws SQLException {
        List<Subcategoria> subcategorias = new ArrayList<>();

        while (resultSet.next()) {
            subcategorias.add(new Subcategoria(
                    resultSet.getInt("id"),
                    resultSet.getString("titulo"),
                    resultSet.getString("text"),
                    resultSet.getInt("categoria_id"),
                    resultSet.getString("url")
            ));
        }
        return subcategorias;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Subcategoria{" +
                "titulo='" + titulo + '\'' +
                ", text='" + text + '\'' +
                ", categoria=" + categoria +
                ", url='" + url + '\'' +
                '}';
    }
}
