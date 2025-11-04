package br.com.guiahc.DAO;
import br.com.guiahc.beans.Colaborador;
import br.com.guiahc.conexao.ConexaoFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColaboradorDAO {

    public Connection cn;

    public ColaboradorDAO() throws SQLException, ClassNotFoundException {
        this.cn = new ConexaoFactory().conexao();
    }

    public String insert(Colaborador colaborador) throws SQLException {
        String sqlCodigo = "INSERT INTO colaborador (id_colaborador, nm_colaborador, idade_colaborador, email_colaborador, cpf_colaborador, dt_nascimento_colaborador, tel_colaborador, num_cracha_colaborador) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = cn.prepareStatement(sqlCodigo);

        pstmt.setInt(1, colaborador.getId());
        pstmt.setString(2, colaborador.getNome());
        pstmt.setInt(3, colaborador.getIdade());
        pstmt.setString(4, colaborador.getEmail());
        pstmt.setString(5, colaborador.getCpf());
        pstmt.setString(6, colaborador.getDtNascimento());
        pstmt.setString(7, colaborador.getTelefone());
        pstmt.setInt(8, colaborador.getNumCracha());

        pstmt.execute();
        pstmt.close();
        return "Colaborador inserido com sucesso!";
    }

    public String update(Colaborador colaborador) throws SQLException {
        String sqlCodigo = "UPDATE colaborador SET nm_colaborador=?, idade_colaborador=?, email_colaborador=?, cpf_colaborador=?, dt_nascimento_colaborador=?, tel_colaborador=?, num_cracha_colaborador=? WHERE id_colaborador=?";

        PreparedStatement pstmt = cn.prepareStatement(sqlCodigo);

        pstmt.setString(1, colaborador.getNome());
        pstmt.setInt(2, colaborador.getIdade());
        pstmt.setString(3, colaborador.getEmail());
        pstmt.setString(4, colaborador.getCpf());
        pstmt.setString(5, colaborador.getDtNascimento());
        pstmt.setString(6, colaborador.getTelefone());
        pstmt.setInt(7, colaborador.getNumCracha());
        pstmt.setInt(8, colaborador.getId());

        pstmt.execute();
        pstmt.close();
        return "Colaborador atualizado com sucesso!";
    }

    public String delete(int id) throws SQLException {
        String sqlCodigo = "DELETE FROM colaborador WHERE id_colaborador=?";
        PreparedStatement pstmt = cn.prepareStatement(sqlCodigo);
        pstmt.setInt(1, id);

        pstmt.execute();
        pstmt.close();

        return "Colaborador removido com sucesso!";
    }

    public List<Colaborador> select() throws SQLException {
        List<Colaborador> listaColaboradores = new ArrayList<Colaborador>();
        String sqlCodigo = "SELECT * FROM colaborador";
        PreparedStatement pstmt = cn.prepareStatement(sqlCodigo);

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {

            Colaborador c = new Colaborador();

            c.setId(rs.getInt(1));
            c.setNome(rs.getString(2));
            c.setIdade(rs.getInt(3));
            c.setEmail(rs.getString(4));
            c.setCpf(rs.getString(5));
            c.setDtNascimento(rs.getString(6));
            c.setTelefone(rs.getString(7));
            c.setNumCracha(rs.getInt(8));

            listaColaboradores.add(c);
        }

        rs.close();
        pstmt.close();
        return listaColaboradores;
    }
}
