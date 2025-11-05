package br.com.guiahc.DAO;

import br.com.guiahc.api.VerificaSenha;
import br.com.guiahc.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class VerificarSenhaDAO {

    private Connection conn;

    public VerificarSenhaDAO() throws SQLException, ClassNotFoundException {
        this.conn = new ConexaoFactory().conexao();
    }

    public VerificaSenha buscarPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT senha, pontuacao, nivel, relatorio FROM VerificacaoSenha WHERE id_usuario = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idUsuario);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            VerificaSenha vs = new VerificaSenha();
            vs.setSenha(rs.getString("senha"));
            vs.setPontuacao(rs.getInt("pontuacao"));
            vs.setNivel(rs.getString("nivel"));
            vs.setRelatorio(rs.getString("relatorio").replace("[", "").replace("]", "").split(", "));
            return vs;
        }
        return null;
    }

    public void inserir(int idUsuario, VerificaSenha verificaSenha) throws SQLException {
        String sql = "INSERT INTO VerificacaoSenha (id_usuario, senha, pontuacao, nivel, relatorio) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idUsuario);
        pstmt.setString(2, verificaSenha.getSenha());
        pstmt.setInt(3, verificaSenha.getPontuacao());
        pstmt.setString(4, verificaSenha.getNivel());
        pstmt.setString(5, Arrays.toString(verificaSenha.getRelatorio()));
        pstmt.execute();
        pstmt.close();
    }
}
