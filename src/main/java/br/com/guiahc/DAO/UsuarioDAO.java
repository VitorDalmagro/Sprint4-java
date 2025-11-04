package br.com.guiahc.DAO;

import br.com.guiahc.beans.Usuario;
import br.com.guiahc.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private  Connection cn;

    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        super();
        this.cn = new ConexaoFactory().conexao();
    }

    // INSERT
    public String inserir(Usuario usuario) throws SQLException {
        PreparedStatement stmt = cn.prepareStatement(
                "INSERT INTO T_FIAP_USUARIO (ID_USUARIO, EMAIL, SENHA, NOME_COMPLETO, DATA_NASCIMENTO, GENERO, TELEFONE, CPF) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
        );

        stmt.setInt(1, usuario.getIdUsuario());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getNomeCompleto());
        stmt.setString(5, usuario.getDataNascimento());
        stmt.setString(6, usuario.getGenero());
        stmt.setString(7, usuario.getTelefone());
        stmt.setString(8, usuario.getCpf());

        stmt.execute();
        stmt.close();

        return "Usuário cadastrado com sucesso!";
    }

    // DELETE
    public String deletar(int idUsuario) throws SQLException {
        PreparedStatement stmt = cn.prepareStatement(
                "DELETE FROM T_FIAP_USUARIO WHERE ID_USUARIO = ?"
        );
        stmt.setInt(1, idUsuario);

        stmt.execute();
        stmt.close();

        return "Usuário deletado com sucesso!";
    }

    // UPDATE
    public String atualizar(Usuario usuario) throws SQLException {
        PreparedStatement stmt = cn.prepareStatement(
                "UPDATE T_FIAP_USUARIO SET EMAIL=?, SENHA=?, NOME_COMPLETO=?, DATA_NASCIMENTO=?, GENERO=?, TELEFONE=?, CPF=? WHERE ID_USUARIO=?"
        );

        stmt.setString(1, usuario.getEmail());
        stmt.setString(2, usuario.getSenha());
        stmt.setString(3, usuario.getNomeCompleto());
        stmt.setString(4, usuario.getDataNascimento());
        stmt.setString(5, usuario.getGenero());
        stmt.setString(6, usuario.getTelefone());
        stmt.setString(7, usuario.getCpf());
        stmt.setInt(8, usuario.getIdUsuario());

        stmt.executeUpdate();
        stmt.close();

        return "Usuário atualizado com sucesso!";
    }

    // SELECT
    public List<Usuario> selecionar() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        PreparedStatement stmt = cn.prepareStatement(
                "SELECT * FROM T_FIAP_USUARIO"
        );
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt(1));
            usuario.setEmail(rs.getString(2));
            usuario.setSenha(rs.getString(3));
            usuario.setNomeCompleto(rs.getString(4));
            usuario.setDataNascimento(rs.getString(5));
            usuario.setGenero(rs.getString(6));
            usuario.setTelefone(rs.getString(7));
            usuario.setCpf(rs.getString(8));
            lista.add(usuario);
        }

        return lista;
    }
}

