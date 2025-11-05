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

        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuarioDAO.selecionar();
        return usuarios;
    }

    public void inserirUsuarioBo(Usuario usuario, VerificaSenha verificaSenha) throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();
        usuarioDAO.inserir(usuario);
    }

    public void atualizarUsuarioBo(Usuario usuario) throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();
        usuarioDAO.atualizar(usuario);
    }


    public void deletarUsuarioBo(int idUsuario) throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();
        usuarioDAO.deletar(idUsuario);
    }

}
