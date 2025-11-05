package br.com.guiahc.bo;

import br.com.guiahc.DAO.UsuarioDAO;
import br.com.guiahc.DAO.VerificarSenhaDAO;
import br.com.guiahc.api.VerificaSenha;
import br.com.guiahc.beans.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioBO {
    UsuarioDAO usuarioDAO;
    VerificarSenhaDAO verificaSenhaDAO;

    public ArrayList<Usuario> selecionarUsuarioBo() throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();
        verificaSenhaDAO = new VerificarSenhaDAO();
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuarioDAO.selecionar();

        for (Usuario u : usuarios) {
            VerificaSenha vs = verificaSenhaDAO.buscarPorUsuario(u.getIdUsuario());
            if (vs != null) {
                u.setSenhaPontuacao(vs.getPontuacao());
                u.setSenhaNivel(vs.getNivel());
                u.setSenhaRelatorio(vs.getRelatorio());
            }
        }

        return usuarios;
    }

    public void inserirUsuarioBo(Usuario usuario, VerificaSenha verificaSenha) throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();
        usuarioDAO.inserir(usuario);

        verificaSenhaDAO = new VerificarSenhaDAO();
        verificaSenhaDAO.inserir(usuario.getIdUsuario(), verificaSenha);
    }

    public void atualizarUsuarioBo(Usuario usuario, VerificaSenha verificaSenha) throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();
        usuarioDAO.atualizar(usuario);

        verificaSenhaDAO = new VerificarSenhaDAO();
        verificaSenhaDAO.inserir(usuario.getIdUsuario(), verificaSenha); // salva novo relatório
    }

    public void deletarUsuarioBo(int idUsuario) throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();
        usuarioDAO.deletar(idUsuario);
        // opcional: deletar relatórios da senha
    }
}
