package br.com.guiahc.DAO;

import br.com.guiahc.api.VerificaSenha;
import br.com.guiahc.beans.Usuario;
import br.com.guiahc.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsuarioDAO {

    private Connection cn;

    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        this.cn = new ConexaoFactory().conexao();
    }

    // INSERT
    public String inserir(Usuario usuario) throws SQLException {
        String sqlCodigo = "INSERT INTO T_FIAP_USUARIO (ID_USUARIO, EMAIL, SENHA, NOME_COMPLETO, DATA_NASCIMENTO, GENERO, TELEFONE, CPF, SENHA_PONTUACAO, SENHA_NIVEL, SENHA_RELATORIO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = cn.prepareStatement(sqlCodigo);

        stmt.setInt(1, usuario.getIdUsuario());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getNomeCompleto());
        stmt.setString(5, usuario.getDataNascimento());
        stmt.setString(6, usuario.getGenero());
        stmt.setString(7, usuario.getTelefone());
        stmt.setString(8, usuario.getCpf());

        if (usuario.getVerificaSenha() != null) {
            stmt.setInt(9, usuario.getVerificaSenha().getPontuacao());
            stmt.setString(10, usuario.getVerificaSenha().getNivel());
            stmt.setString(11, Arrays.toString(usuario.getVerificaSenha().getRelatorio()));
        } else {
            stmt.setInt(9, 0);
            stmt.setString(10, null);
            stmt.setString(11, null);
        }

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
                "UPDATE T_FIAP_USUARIO SET EMAIL=?, SENHA=?, NOME_COMPLETO=?, DATA_NASCIMENTO=?, GENERO=?, TELEFONE=?, CPF=?, SENHA_PONTUACAO=?, SENHA_NIVEL=?, SENHA_RELATORIO=? WHERE ID_USUARIO=?"
        );

        stmt.setString(1, usuario.getEmail());
        stmt.setString(2, usuario.getSenha());
        stmt.setString(3, usuario.getNomeCompleto());
        stmt.setString(4, usuario.getDataNascimento());
        stmt.setString(5, usuario.getGenero());
        stmt.setString(6, usuario.getTelefone());
        stmt.setString(7, usuario.getCpf());

        // Verificação básica '-'
        if (usuario.getVerificaSenha() != null) {
            stmt.setInt(8, usuario.getVerificaSenha().getPontuacao());
            stmt.setString(9, usuario.getVerificaSenha().getNivel());
            stmt.setString(10, Arrays.toString(usuario.getVerificaSenha().getRelatorio()));
        } else {
            stmt.setInt(8, 0);
            stmt.setString(9, null);
            stmt.setString(10, null);
        }

        stmt.setInt(11, usuario.getIdUsuario());
        stmt.execute();
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

            VerificaSenha verificaSenha = new VerificaSenha();
            verificaSenha.setPontuacao(rs.getInt(9));
            verificaSenha.setNivel(rs.getString(10));

            String relatorioStr = rs.getString(11); // pode ser null
            if (relatorioStr != null) {
                verificaSenha.setRelatorio(relatorioStr.replace("[","").replace("]","").split(", "));
            } else {
                verificaSenha.setRelatorio(new String[0]); // ou null, dependendo de como você quer tratar
            }


            usuario.setVerificaSenha(verificaSenha);

            lista.add(usuario);
        }

        return lista;
    }
}

