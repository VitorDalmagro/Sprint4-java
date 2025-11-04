package br.com.guiahc.CRUD;

import br.com.guiahc.DAO.ColaboradorDAO;
import br.com.guiahc.beans.Colaborador;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColaboradorCRUD {


    static String texto(String j) {
        return JOptionPane.showInputDialog(j);
    }

    static int inteiro(String i) {
        return Integer.parseInt(JOptionPane.showInputDialog(i));
    }

    static double real(String r) {
        return Double.parseDouble(JOptionPane.showInputDialog(r));
    }

    // Inserir colaborador
    public static void inserir() throws SQLException, ClassNotFoundException {
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
        Colaborador objColaborador = new Colaborador();

        objColaborador.setId(inteiro("Digite o ID do colaborador"));
        objColaborador.setNome(texto("Digite o nome do colaborador"));
        objColaborador.setIdade(inteiro("Digite a idade do colaborador"));
        objColaborador.setEmail(texto("Digite o email do colaborador"));
        objColaborador.setCpf(texto("Digite o CPF do colaborador"));
        objColaborador.setDtNascimento(texto("Digite a data de nascimento no formato DD/MM/AAAA"));
        objColaborador.setTelefone(texto("Digite o telefone do colaborador no formato (XX) XXXXX-XXXX"));
        objColaborador.setNumCracha(inteiro("Digite o número do crachá do colaborador"));

        System.out.println(colaboradorDAO.insert(objColaborador));
    }

    public static void atualizar() throws SQLException, ClassNotFoundException {
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
        Colaborador objColaborador = new Colaborador();

        objColaborador.setId(inteiro("Informe o ID do colaborador a ser atualizado"));
        objColaborador.setNome(texto("Novo nome"));
        objColaborador.setIdade(inteiro("Nova idade"));
        objColaborador.setEmail(texto("Novo email"));
        objColaborador.setCpf(texto("Novo CPF"));
        objColaborador.setDtNascimento(texto("Nova data de nascimento no formato DD/MM/AAAA"));
        objColaborador.setTelefone(texto("Novo telefone no formato (XX) XXXXX-XXXX"));
        objColaborador.setNumCracha(inteiro("Novo número de crachá"));

        System.out.println(colaboradorDAO.update(objColaborador));
    }

    public static void deletar() throws SQLException, ClassNotFoundException {
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
        Colaborador objColaborador = new Colaborador();

        objColaborador.setId(inteiro("Informe o ID do colaborador a ser deletado"));

        System.out.println(colaboradorDAO.delete(objColaborador.getId()));
    }

    public static void selecionar() throws SQLException, ClassNotFoundException {
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
        List<Colaborador> listaColaboradores = (ArrayList<Colaborador>) colaboradorDAO.select();

        if (listaColaboradores != null) {
            for (Colaborador c : listaColaboradores) {
                System.out.println(
                        "\nID: " + c.getId() +
                                "\nNome: " + c.getNome() +
                                "\nIdade: " + c.getIdade() +
                                "\nEmail: " + c.getEmail() +
                                "\nCPF: " + c.getCpf() +
                                "\nNascimento: " + c.getDtNascimento() +
                                "\nTelefone: " + c.getTelefone() +
                                "\nNúmero Crachá: " + c.getNumCracha() +
                                "\n------------------------"
                );
            }
        } else {
            System.out.println("Nenhum colaborador encontrado.");
        }
    }
}
