package br.com.guiahc.bo;

import br.com.guiahc.DAO.UsuarioDAO;
import br.com.guiahc.beans.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioBO {
    UsuarioDAO usuarioDAO;

    // Selecionar (Listar todos os usuários)
    public ArrayList<Usuario> selecionarBo() throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();
        // Aqui poderiam ser aplicadas regras de negócio, validações, etc.
        return (ArrayList<Usuario>) usuarioDAO.selecionar();
    }

    // Inserir novo usuário
    public void inserirBo(Usuario usuario) throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();

        // Exemplo de regra de negócio: não permitir e-mail vazio
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O e-mail não pode ser vazio.");
        }

        // Exemplo de regra de negócio: senha deve ter no mínimo 6 caracteres
        if (usuario.getSenha().length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
        }

        usuarioDAO.inserir(usuario);
    }

    // Atualizar usuário existente
    public void atualizarBo(Usuario usuario) throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();

        // Exemplo de validação: verificar se o ID foi informado
        if (usuario.getIdUsuario() <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido para atualização.");
        }

        usuarioDAO.atualizar(usuario);
    }

    // Deletar usuário
    public void deletarBo(int idUsuario) throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();

        if (idUsuario <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }

        usuarioDAO.deletar(idUsuario);
    }
}
