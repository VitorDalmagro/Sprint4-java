package br.com.guiahc.CRUD;

import br.com.guiahc.DAO.PacienteDAO;
import br.com.guiahc.beans.Paciente;
import br.com.guiahc.beans.PlanoSaude;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteCRUD {

    static String texto(String j) {
        return JOptionPane.showInputDialog(j);
    }

    static int inteiro(String i) {
        return Integer.parseInt(JOptionPane.showInputDialog(i));
    }

    static double real(String r) {
        return Double.parseDouble(JOptionPane.showInputDialog(r));
    }
    public static void inserir() throws SQLException, ClassNotFoundException {

        PacienteDAO dao = new PacienteDAO();
        Paciente objPaciente = new Paciente();
        PlanoSaude objPlanoSaude = new PlanoSaude();

        objPaciente.setId(inteiro("Digite o ID do paciente a ser inserido"));
        objPaciente.setNome(texto("Digite o nome do paciente"));
        objPaciente.setIdade(inteiro("Digite a idade do paciente"));
        objPaciente.setEmail(texto("Digite o e-mail do paciente"));
        objPaciente.setCpf(texto("Digite o cpf do paciente"));
        objPaciente.setDtNascimento(texto("Digite a data de nascimento do paciente no formato DD/MM/AAAA"));
        objPaciente.setTelefone(texto("Digite o numero de telefone do paciente no formato (XX) XXXXX-XXXX"));
        objPaciente.setSexo(texto("Digite o sexo do paciente (M/F)"));

        objPlanoSaude.setNomeDoPlano(texto("Digite o nome do plano de saúde (Indivídual, Família, etc.)"));
        objPlanoSaude.setPrecoMensal(real("Digite o preço mensal do plano"));
        objPlanoSaude.setTipoDePlano(texto("Digite o tipo de plano (Básico, Premium, etc.)"));

        System.out.println(dao.insert(objPaciente, objPlanoSaude));
    }

    public static void atualizar() throws SQLException, ClassNotFoundException {
        PacienteDAO pacienteDAO = new PacienteDAO();
        Paciente objPaciente = new Paciente();
        PlanoSaude objPlanoSaude = new PlanoSaude();

        objPaciente.setId(inteiro("Informe o ID do paciente a ser atualizado"));
        objPaciente.setNome(texto("Novo Nome"));
        objPaciente.setIdade(inteiro("Nova Idade"));
        objPaciente.setEmail(texto("Novo Email"));
        objPaciente.setCpf(texto("Novo CPF"));
        objPaciente.setDtNascimento(texto("Nova Data de Nascimento no formato DD/MM/AAAA"));
        objPaciente.setTelefone(texto("Novo Telefone no formato (XX) XXXXX-XXXX"));
        objPaciente.setSexo(texto("Novo Sexo (M/F)"));

        objPlanoSaude.setNomeDoPlano(texto("Novo nome do plano de saúde (Indivídual, Família, etc.)"));
        objPlanoSaude.setPrecoMensal(real("Novo preço mensal do plano"));
        objPlanoSaude.setTipoDePlano(texto("Novo tipo de plano (Básico, Premium, etc.)"));

        System.out.println(pacienteDAO.update(objPaciente, objPlanoSaude));
    }

    public static void deletar() throws SQLException, ClassNotFoundException {

        PacienteDAO pacienteDAO = new PacienteDAO();
        Paciente objPaciente = new Paciente();

        objPaciente.setId(inteiro("Informe o ID do paciente a ser deletado"));

        System.out.println(pacienteDAO.delete(objPaciente.getId()));

    }

    public static void selecionar() throws SQLException, ClassNotFoundException {
        PacienteDAO objPacienteDAO = new PacienteDAO();

        List<Paciente> listaPacientes = (ArrayList<Paciente>) objPacienteDAO.select();

        if (listaPacientes != null) {

            for (Paciente p : listaPacientes) {
                System.out.println(
                        "\nID: " + p.getId() +
                                "\nNome: " + p.getNome() +
                                "\nIdade: " + p.getIdade() +
                                "\nEmail: " + p.getEmail() +
                                "\nCPF: " + p.getCpf() +
                                "\nNascimento: " + p.getDtNascimento() +
                                "\nTelefone: " + p.getTelefone() +
                                "\nSexo: " + p.getSexo() +
                                "\nPlano de Saúde: " + p.getPlanoSaude().getNomeDoPlano() +
                                "\nPreço Mensal: " + p.getPlanoSaude().getPrecoMensal() +
                                "\nTipo do Plano: " + p.getPlanoSaude().getTipoDePlano() +
                                "\n------------------------"
                );
            }
        } else {
            System.out.println("Nenhum paciente encontrado.");
        }
    }
}
