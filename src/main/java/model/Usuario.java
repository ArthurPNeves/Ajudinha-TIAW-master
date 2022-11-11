package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String email;
    private String senha;
    private String nome;
    private Integer numRes;
    private Integer CEP;
    private String telefone;
    private String cartao;
    private String CVC;
    private Date validade;

    private Integer id;


    public Usuario() {
    }

    public Usuario(Integer id, String email, String senha, String nome, Integer numRes, Integer CEP, String telefone, String cartao, String CVC, Date validade) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.numRes = numRes;
        this.CEP = CEP;
        this.telefone = telefone;
        this.cartao = cartao;
        this.CVC = CVC;
        this.validade = validade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumRes() {
        return numRes;
    }

    public void setNumRes(Integer numRes) {
        this.numRes = numRes;
    }


    public Integer getCEP() {
        return CEP;
    }

    public void setCEP(Integer CEP) {
        this.CEP = CEP;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", numRes=" + numRes +
                ", CEP=" + CEP +
                ", telefone='" + telefone + '\'' +
                ", cartao='" + cartao + '\'' +
                ", CVC='" + CVC + '\'' +
                ", validade='" + validade + '\'' +
                ", id=" + id +
                '}';
    }

    public static List<Usuario> parseUsuario(ResultSet resultSet) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        while (resultSet.next()) {
            usuarios.add(new Usuario(
                    resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("senha"),
                    resultSet.getString("usuarionome"),
                    resultSet.getInt("numerosres"),
                    resultSet.getInt("cep"),
                    resultSet.getString("telefone"),
                    resultSet.getString("numerocartao"),
                    resultSet.getString("cvc"),
                    resultSet.getDate("datavalidade")));
        }
        return usuarios;
    }
}
