package br.com.guiahc.CRUD;

import br.com.guiahc.DAO.MedicoDAO;
import br.com.guiahc.beans.Medico;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoCRUD {

    static String texto(String j) {
        return JOptionPane.showInputDialog(j);
    }

    static int inteiro(String i) {
        return Integer.parseInt(JOptionPane.showInputDialog(i));
    }

    public static void inserir() throws SQLException, ClassNotFoundException {
        MedicoDAO dao = new MedicoDAO();
        Medico objMedico = new Medico();

        objMedico.setId(inteiro("Digite o ID do médico"));
        objMedico.setNome(texto("Digite o nome do médico"));
        objMedico.setIdade(inteiro("Digite a idade do médico"));
        objMedico.setEmail(texto("Digite o e-mail do médico"));
        objMedico.setCpf(texto("Digite o CPF do médico"));
        objMedico.setDtNascimento(texto("Digite a data de nascimento do médico no formato DD/MM/AAAA"));
        objMedico.setCrm(texto("Digite o CRM do médico"));
        objMedico.setEspecialidadeMedico(texto("Digite a especialidade do médico"));
        objMedico.setTelefone(texto("Digite o telefone do médico no formato (XX) XXXXX-XXXX"));

        System.out.println(dao.insert(objMedico));
    }

    public static void atualizar() throws SQLException, ClassNotFoundException {
        MedicoDAO dao = new MedicoDAO();
        Medico objMedico = new Medico();

        objMedico.setId(inteiro("Informe o ID do médico a ser atualizado"));
        objMedico.setNome(texto("Novo nome"));
        objMedico.setIdade(inteiro("Nova idade"));
        objMedico.setEmail(texto("Novo email"));
        objMedico.setCpf(texto("Novo CPF"));
        objMedico.setDtNascimento(texto("Nova data de nascimento no formato DD/MM/AAAA"));
        objMedico.setCrm(texto("Novo CRM"));
        objMedico.setEspecialidadeMedico(texto("Nova especialidade"));
        objMedico.setTelefone(texto("Novo telefone no formato (XX) XXXXX-XXXX"));

        System.out.println(dao.update(objMedico));
    }

    public static void deletar() throws SQLException, ClassNotFoundException {
        MedicoDAO medicoDAO = new MedicoDAO();
        Medico objMedico = new Medico();

        objMedico.setId(inteiro("Informe o ID do médico a ser deletado"));
        System.out.println(medicoDAO.delete(objMedico.getId()));
    }

    public static void selecionar() throws SQLException, ClassNotFoundException {
        MedicoDAO medicoDAO = new MedicoDAO();
        List<Medico> listaMedicos = (ArrayList<Medico>) medicoDAO.select();

        if (listaMedicos != null) {

            for (Medico m : listaMedicos) {
                System.out.println(
                        "\nID: " + m.getId() +
                                "\nNome: " + m.getNome() +
                                "\nIdade: " + m.getIdade() +
                                "\nEmail: " + m.getEmail() +
                                "\nCPF: " + m.getCpf() +
                                "\nNascimento: " + m.getDtNascimento() +
                                "\nCRM: " + m.getCrm() +
                                "\nEspecialidade: " + m.getEspecialidadeMedico() +
                                "\nTelefone: " + m.getTelefone() +
                                "\n------------------------"
                );
            }
        } else {
            System.out.println("Nenhum médico encontrado.");
        }
    }
}
