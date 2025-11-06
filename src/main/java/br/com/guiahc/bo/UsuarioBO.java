package br.com.guiahc.bo;

import br.com.guiahc.DAO.UsuarioDAO;
import br.com.guiahc.api.VerificaSenha;
import br.com.guiahc.beans.Usuario;

import java.util.ArrayList;

public class UsuarioBO {

    public ArrayList<Usuario> selecionarUsuarioBo() {
        try{ UsuarioDAO usuarioDAO = new UsuarioDAO();
            return new ArrayList<>(usuarioDAO.selecionar());
        } catch (Exception e) {
            System.out.println("Erro ao selecionar usuários: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    public void inserirUsuarioBo(Usuario usuario, VerificaSenha verificaSenha) {
        try{ UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuario.setVerificaSenha(verificaSenha);
            usuarioDAO.inserir(usuario);
        } catch (Exception e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public void atualizarUsuarioBo(Usuario usuario) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            usuarioDAO.atualizar(usuario);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        } finally {
            usuarioDAO.fecharConexao();
        }
    }

    public void deletarUsuarioBo(int idUsuario) {
        try{ UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.deletar(idUsuario);
        } catch (Exception e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}
