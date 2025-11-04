package br.com.guiahc.DAO;

import br.com.guiahc.beans.Medico;
import br.com.guiahc.conexao.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {
    public Connection cn;

    public MedicoDAO() throws SQLException, ClassNotFoundException {
        this.cn = new ConexaoFactory().conexao();
    }

    public String insert(Medico medico) throws SQLException {
        String sqlCodigo = "INSERT INTO medico (id_medico, nm_medico, idade_medico, email_medico, cpf_medico, dt_nascimento_medico, crm_medico, especialidade_medico, tel_medico) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = cn.prepareStatement(sqlCodigo);

        pstmt.setInt(1, medico.getId());
        pstmt.setString(2, medico.getNome());
        pstmt.setInt(3, medico.getIdade());
        pstmt.setString(4, medico.getEmail());
        pstmt.setString(5, medico.getCpf());
        pstmt.setString(6, medico.getDtNascimento());
        pstmt.setString(7, medico.getCrm());
        pstmt.setString(8, medico.getEspecialidadeMedico());
        pstmt.setString(9, medico.getTelefone());

        pstmt.execute();
        pstmt.close();
        return "Médico inserido com sucesso!";
    }

    public String update(Medico medico) throws SQLException {
        String sqlCodigo = "UPDATE medico SET nm_medico=?, idade_medico=?, email_medico=?, cpf_medico=?, dt_nascimento_medico=?, crm_medico=?, especialidade_medico=?, tel_medico=? " +
                "WHERE id_medico=?";
        PreparedStatement pstmt = cn.prepareStatement(sqlCodigo);

        pstmt.setString(1, medico.getNome());
        pstmt.setInt(2, medico.getIdade());
        pstmt.setString(3, medico.getEmail());
        pstmt.setString(4, medico.getCpf());
        pstmt.setString(5, medico.getDtNascimento());
        pstmt.setString(6, medico.getCrm());
        pstmt.setString(7, medico.getEspecialidadeMedico());
        pstmt.setString(8, medico.getTelefone());
        pstmt.setInt(9, medico.getId());

        pstmt.executeUpdate();
        pstmt.close();
        return "Médico atualizado com sucesso!";
    }

    // Deletar
    public String delete(int id) throws SQLException {
        String sqlCodigo = "DELETE FROM medico WHERE id_medico=?";

        PreparedStatement pstmt = cn.prepareStatement(sqlCodigo);

        pstmt.setInt(1, id);

        pstmt.execute();
        pstmt.close();

        return "Médico removido com sucesso!";
    }

    public List<Medico> select() throws SQLException{

        List<Medico> listaMedicos = new ArrayList<Medico>();

        String sqlCodigo = "SELECT * FROM medico";
        PreparedStatement pstms = cn.prepareStatement(sqlCodigo);

        ResultSet rs = pstms.executeQuery();
        while (rs.next()) {

            Medico m = new Medico();
            m.setId(rs.getInt(1));
            m.setNome(rs.getString(2));
            m.setIdade(rs.getInt(3));
            m.setEmail(rs.getString(4));
            m.setCpf(rs.getString(5));
            m.setDtNascimento(rs.getString(6));
            m.setCrm(rs.getString(7));
            m.setEspecialidadeMedico(rs.getString(8));
            m.setTelefone(rs.getString(9));

            listaMedicos.add(m);
        }

        rs.close();
        pstms.close();
        return listaMedicos;
    }
}
