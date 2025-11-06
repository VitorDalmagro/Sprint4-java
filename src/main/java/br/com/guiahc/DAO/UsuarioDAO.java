package br.com.guiahc.DAO;

import br.com.guiahc.api.VerificaSenha;
import br.com.guiahc.beans.Usuario;
import br.com.guiahc.conexao.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsuarioDAO {

    private Connection cn;

    public UsuarioDAO() {
        try {
            this.cn = new ConexaoFactory().conexao();
            System.out.println("Conectado como: " + cn.getMetaData().getUserName());

        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public String inserir(Usuario usuario) {
        if (usuario == null) {
            return "Usuário inválido.";
        }

        String sql = "INSERT INTO T_FIAP_USUARIO (" +
                "ID_USUARIO, EMAIL, SENHA, NOME_COMPLETO, DATA_NASCIMENTO, GENERO, TELEFONE, CPF, " +
                "SENHA_PONTUACAO, SENHA_NIVEL, SENHA_RELATORIO" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = cn.prepareStatement(sql)) {

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
            return "Usuário cadastrado com sucesso!";

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Já existe um usuário com esse ID ou CPF!";
        } catch (SQLException e) {
            return "Falha ao inserir usuário: " + e.getMessage();
        } finally {
            fecharConexao();
        }
    }

    public String deletar(int idUsuario) {
        if (idUsuario <= 0) {
            return "ID de usuário inválido.";
        }

        String sql = "DELETE FROM T_FIAP_USUARIO WHERE ID_USUARIO = ?";

        try (PreparedStatement stmt = cn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                return "Nenhum usuário encontrado com o ID informado.";
            }
            return "Usuário deletado com sucesso!";

        } catch (SQLException e) {
            return "Falha ao deletar usuário: " + e.getMessage();
        } finally {
            fecharConexao();
        }
    }

    public String atualizar(Usuario usuario) {
        if (usuario == null || usuario.getIdUsuario() <= 0) {
            return "Usuário inválido para atualização.";
        }

        String sql = "UPDATE T_FIAP_USUARIO SET " +
                "EMAIL=?, SENHA=?, NOME_COMPLETO=?, DATA_NASCIMENTO=?, GENERO=?, TELEFONE=?, CPF=?, " +
                "SENHA_PONTUACAO=?, SENHA_NIVEL=?, SENHA_RELATORIO=? WHERE ID_USUARIO=?";

        try (PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNomeCompleto());
            stmt.setString(4, usuario.getDataNascimento());
            stmt.setString(5, usuario.getGenero());
            stmt.setString(6, usuario.getTelefone());
            stmt.setString(7, usuario.getCpf());

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
            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                return "Nenhum usuário encontrado com esse ID.";
            }
            return "Usuário atualizado com sucesso!";

        } catch (SQLException e) {
            return "Falha ao atualizar: " + e.getMessage();
        } finally {
            fecharConexao();
        }
    }

    public List<Usuario> selecionar() {
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        String sql = "SELECT * FROM T_FIAP_USUARIO";

        try (PreparedStatement stmt = cn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
                usuario.setEmail(rs.getString("EMAIL"));
                usuario.setSenha(rs.getString("SENHA"));
                usuario.setNomeCompleto(rs.getString("NOME_COMPLETO"));
                usuario.setDataNascimento(rs.getString("DATA_NASCIMENTO"));
                usuario.setGenero(rs.getString("GENERO"));
                usuario.setTelefone(rs.getString("TELEFONE"));
                usuario.setCpf(rs.getString("CPF"));

                VerificaSenha verificaSenha = new VerificaSenha();
                verificaSenha.setPontuacao(rs.getInt("SENHA_PONTUACAO"));
                verificaSenha.setNivel(rs.getString("SENHA_NIVEL"));
                verificaSenha.setSenha(usuario.getSenha());

                String relatorioStr = rs.getString("SENHA_RELATORIO");
                if (relatorioStr != null && !relatorioStr.isEmpty()) {

                    // Removendo os colchetes do retorno para fican mais bonitinho :)
                    relatorioStr = relatorioStr.replace("[", "").replace("]", "");

                    String[] partes = relatorioStr.split(", "); // divide o retorno da API pelo ponto final
                    verificaSenha.setRelatorio(partes);
                } else {
                    verificaSenha.setRelatorio(new String[0]);
                }

                usuario.setVerificaSenha(verificaSenha);
                listaUsuario.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuários: " + e.getMessage());
        } finally {
            fecharConexao();
        }

        return listaUsuario;
    }


    // Fecha a conexão + tratamento
    public void fecharConexao() {
        if (cn != null) {
            try {
                cn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

}
