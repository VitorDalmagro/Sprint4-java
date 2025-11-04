package br.com.guiahc.DAO;

import br.com.guiahc.beans.Paciente;
import br.com.guiahc.beans.PlanoSaude;
import br.com.guiahc.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    public Connection cn;

    public PacienteDAO() throws SQLException, ClassNotFoundException {
        this.cn = new ConexaoFactory().conexao();
    }

    public String insert(Paciente paciente, PlanoSaude planoSaude) throws SQLException {
        String sqlCodigo = "INSERT INTO paciente (id_paciente, nm_paciente, idade_paciente, email_paciente, num_cpf_paciente, dt_nascimento_paciente, num_tel_paciente, sexo_paciente, nm_plano_saude_paciente, preco_plano, tipo_plano) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = cn.prepareStatement(sqlCodigo);

        pstmt.setInt(1, paciente.getId());
        pstmt.setString(2, paciente.getNome());
        pstmt.setInt(3, paciente.getIdade());
        pstmt.setString(4, paciente.getEmail());
        pstmt.setString(5, paciente.getCpf());
        pstmt.setString(6, paciente.getDtNascimento());
        pstmt.setString(7, paciente.getTelefone());
        pstmt.setString(8, paciente.getSexo());
        pstmt.setString(9, planoSaude.getNomeDoPlano());
        pstmt.setDouble(10, planoSaude.getPrecoMensal());
        pstmt.setString(11, planoSaude.getTipoDePlano());

        pstmt.execute();
        pstmt.close();
        return "Paciente inserido com sucesso!";
    }

    public String update(Paciente paciente,PlanoSaude planoSaude) throws SQLException {
        String sqlCodigo = "UPDATE paciente SET nm_paciente=?, idade_paciente=?, email_paciente=?, num_cpf_paciente=?, " +
                "dt_nascimento_paciente=?, num_tel_paciente=?, sexo_paciente=?, " +
                "nm_plano_saude_paciente=?, preco_plano=?, tipo_plano=? " +
                "WHERE id_paciente=?";

        PreparedStatement pstmt = cn.prepareStatement(sqlCodigo);

        pstmt.setString(1, paciente.getNome());
        pstmt.setInt(2, paciente.getIdade());
        pstmt.setString(3, paciente.getEmail());
        pstmt.setString(4, paciente.getCpf());
        pstmt.setString(5, paciente.getDtNascimento());
        pstmt.setString(6, paciente.getTelefone());
        pstmt.setString(7, paciente.getSexo());
        pstmt.setString(8, planoSaude.getNomeDoPlano());
        pstmt.setDouble(9, planoSaude.getPrecoMensal());
        pstmt.setString(10, planoSaude.getTipoDePlano());
        pstmt.setInt(11, paciente.getId());

        pstmt.executeUpdate();
        pstmt.close();
        return "Paciente atualizado com sucesso!";
    }

    // Deletar
    public String delete(int id) throws SQLException {
        String sqlCodigo = "DELETE FROM paciente WHERE ID_PACIENTE=?";

        PreparedStatement pstmt = cn.prepareStatement(sqlCodigo);

        pstmt.setInt(1, id);

        pstmt.execute();
        pstmt.close();

        return "Paciente removido com sucesso!";
    }


    public List<Paciente> select() throws SQLException {

        List<Paciente> listaPacientes = new ArrayList<Paciente>();

        String sql = "SELECT * FROM paciente";

        PreparedStatement pstmt = cn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            Paciente p = new Paciente();
            PlanoSaude plano = new PlanoSaude();

            p.setId(rs.getInt(1));
            p.setNome(rs.getString(2));
            p.setIdade(rs.getInt(3));
            p.setEmail(rs.getString(4));
            p.setCpf(rs.getString(5));
            p.setDtNascimento(rs.getString(6));
            p.setTelefone(rs.getString(7));
            p.setSexo(rs.getString(8));
            plano.setNomeDoPlano(rs.getString(9));
            plano.setPrecoMensal(rs.getDouble(10));
            plano.setTipoDePlano(rs.getString(11));

            p.setPlanoSaude(plano);

            listaPacientes.add(p);
        }

        rs.close();
        pstmt.close();
        return listaPacientes;
    }
}


