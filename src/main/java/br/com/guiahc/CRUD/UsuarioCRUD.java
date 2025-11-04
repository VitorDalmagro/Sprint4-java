package br.com.guiahc.CRUD;

import br.com.guiahc.DAO.UsuarioDAO;
import br.com.guiahc.beans.Usuario;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioCRUD {

    static String texto(String j) {
        return JOptionPane.showInputDialog(j);
    }

    static int inteiro(String i) {
        return Integer.parseInt(JOptionPane.showInputDialog(i));
    }

    static double real(String r) {
        return Double.parseDouble(JOptionPane.showInputDialog(r));
    }

    // INSERIR
    public static void inserir() throws SQLException, ClassNotFoundException, SQLException {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario objUsuario = new Usuario();

        objUsuario.setIdUsuario(inteiro("Digite o ID do usuário"));
        objUsuario.setEmail(texto("Digite o email"));
        objUsuario.setSenha(texto("Digite a senha"));
        objUsuario.setNomeCompleto(texto("Digite o nome completo"));
        objUsuario.setDataNascimento(texto("Digite a data de nascimento (DD/MM/AAAA)"));
        objUsuario.setGenero(texto("Digite o gênero"));
        objUsuario.setTelefone(texto("Digite o telefone (formato (XX) XXXXX-XXXX)"));
        objUsuario.setCpf(texto("Digite o CPF (formato 000.000.000-00)"));

        System.out.println(dao.inserir(objUsuario));
    }

    // ATUALIZAR
    public static void atualizar() throws SQLException, ClassNotFoundException {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = new Usuario();

        usuario.setIdUsuario(inteiro("Informe o ID do usuário a ser atualizado"));
        usuario.setEmail(texto("Novo email"));
        usuario.setSenha(texto("Nova senha"));
        usuario.setNomeCompleto(texto("Novo nome completo"));
        usuario.setDataNascimento(texto("Nova data de nascimento (DD/MM/AAAA)"));
        usuario.setGenero(texto("Novo gênero"));
        usuario.setTelefone(texto("Novo telefone (formato (XX) XXXXX-XXXX)"));
        usuario.setCpf(texto("Novo CPF (formato 000.000.000-00)"));

        System.out.println(dao.atualizar(usuario));
    }

    // DELETAR
    public static void deletar() throws SQLException, ClassNotFoundException {
        UsuarioDAO dao = new UsuarioDAO();
        int id = inteiro("Informe o ID do usuário a ser deletado");

        System.out.println(dao.deletar(id));
    }

    // SELECIONAR
    public static void selecionar() throws SQLException, ClassNotFoundException {
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> lista = (ArrayList<Usuario>) dao.selecionar();

        if (lista.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            for (Usuario u : lista) {
                System.out.println(
                        "\nID: " + u.getIdUsuario() +
                                "\nEmail: " + u.getEmail() +
                                "\nSenha: " + u.getSenha() +
                                "\nNome: " + u.getNomeCompleto() +
                                "\nData de nascimento: " + u.getDataNascimento() +
                                "\nGênero: " + u.getGenero() +
                                "\nTelefone: " + u.getTelefone() +
                                "\nCPF: " + u.getCpf() +
                                "\n--------------------------"
                );
            }
        }
    }
}
