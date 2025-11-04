package br.com.guiahc.main;
import br.com.guiahc.CRUD.ColaboradorCRUD;
import br.com.guiahc.CRUD.MedicoCRUD;
import br.com.guiahc.CRUD.PacienteCRUD;
import br.com.guiahc.beans.*;
import br.com.guiahc.conexao.ConexaoFactory;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.Scanner;


public class TesteSistema {
    static String texto(String j) {
        return JOptionPane.showInputDialog(j);
    }

    static int inteiro(String i) {
        return Integer.parseInt(JOptionPane.showInputDialog(i));
    }

    static double real(String r) {
        return Double.parseDouble(JOptionPane.showInputDialog(r));
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        JOptionPane.showMessageDialog(null, "Bem vindo(a)");

        Scanner scanner = new Scanner(System.in);

        boolean saida = false;

        do {
            System.out.println("\n** MENU PRINCIPAL **\n1 - GUIA\n2 - Área do colaborador\n3 - Ajuda\n4 - Sair");
            System.out.print("Escolha: ");
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                JOptionPane.showMessageDialog(null, "Bem-vindo ao GUIA");

                JOptionPane.showMessageDialog(null, "A seguir, teremos um formulário para se preparar e entender o processo de cadastro do Hospital das Clínicas.");

                Paciente objPaciente = new Paciente();

                        objPaciente.setNome(texto("Digite seu nome"));
                        objPaciente.setIdade(inteiro("Digite sua idade"));
                        objPaciente.setEmail(texto("Digite seu e-mail"));
                        objPaciente.setCpf(texto("Digite seu RG"));
                        objPaciente.setDtNascimento(texto("Digite seu CPF"));
                        objPaciente.setTelefone(texto("Digite sua data de nascimento no formato DD/MM/AAAA"));
                        objPaciente.setTelefone(texto("Digite seu número de telefone EX: (XX) XXXXX-XXXX"));
                        objPaciente.setSexo(texto("Digite o sexo do paciente (M/F)"));

                        PlanoSaude objPlanoSaude = new PlanoSaude(
                        texto("Digite o nome do plano de saúde (Indivídual, Família, etc.)"),
                        real("Digite o preço mensal do plano"),
                        texto("Digite o tipo do plano (ex: básico, executivo, premium)"));


                        objPaciente.setPlanoSaude(objPlanoSaude);

                System.out.println("\n\nSuas informações estão logo abaixo, lembre-se de anotar tudo para não esquecer de nada!!!\n" + objPaciente + "\n\nSe precisar de qualquer ajuda, no menu, escolha a opção ajuda que todas as informações de contatos estarão lá, ficaremos felizes e, te ajudar!!");


            } else if (escolha == 2) {

                String usuarioDefinido = "admin";
                int senhaDefinida = 1234;

                boolean saidaMenuColaborador = false;

                System.out.println("Digite o usuário e a senha.");
                System.out.print("Usuário: ");
                String usuario = scanner.next();

                System.out.print("Senha: ");
                int senha = scanner.nextInt();

                if (usuarioDefinido.equals(usuario) && senhaDefinida == senha) {
                    System.out.println("Login realizado com sucesso!");

                    do {
                        System.out.println("\n1 - CRUD PACIENTE\n2 - CRUD COLABORADOR\n3 - CRUD MÉDICO\n4 - Voltar");
                        System.out.print("Escolha: ");
                        int escolhaSubMenu = scanner.nextInt();

                        if (escolhaSubMenu == 1) {

                            boolean saidaMenuPaciente = false;
                            int escolhaMenuPacienteCRUD;
                            do {
                                System.out.println("\n\n** MENU PACIENTE **");
                                System.out.println("1 - Testar Conexão com o banco de dados");
                                System.out.println("2 - Inserir novo Paciente no banco de dados");
                                System.out.println("3 - Remover um paciente do banco de dados");
                                System.out.println("4 - Listar os pacientes cadastrados no banco de dados");
                                System.out.println("5 - Atualizar dados do paciente");
                                System.out.println("6 - Voltar");
                                System.out.print("Escolha: ");
                                escolhaMenuPacienteCRUD = scanner.nextInt();


                                if (escolhaMenuPacienteCRUD == 1) {
                                    Connection cn = new ConexaoFactory().conexao();
                                    System.out.println("\nConectado com o banco de dados");
                                    cn.close();
                                } else if (escolhaMenuPacienteCRUD == 2) {
                                    PacienteCRUD.inserir();
                                } else if (escolhaMenuPacienteCRUD == 3) {
                                    PacienteCRUD.deletar();
                                } else if (escolhaMenuPacienteCRUD == 4) {
                                    PacienteCRUD.selecionar();
                                } else if (escolhaMenuPacienteCRUD == 5) {
                                    PacienteCRUD.atualizar();
                                } else if (escolhaMenuPacienteCRUD == 6) {
                                    System.out.println("Saindo!");
                                    saidaMenuPaciente = true;
                                }else{
                                        System.out.println("Opção inválida!");
                                }
                            } while (!saidaMenuPaciente);

                        } else if (escolhaSubMenu == 2) {
                        boolean saidaMenuColaboradorCrud = false;
                        do {
                            System.out.println("\n\n** MENU COLABORADOR **");
                            System.out.println("1 - Testar conexão com o banco de dados");
                            System.out.println("2 - Inserir colaborador no banco de dados");
                            System.out.println("3 - Remover colaborador do banco de dados");
                            System.out.println("4 - Listar os colaboradores cadastrados no banco de dados");
                            System.out.println("5 - Atualizar dados do colaborador");
                            System.out.println("6 - Voltar");
                            System.out.print("Escolha: ");
                            int escolhaMenuColaborador = scanner.nextInt();

                            if (escolhaMenuColaborador == 1) {
                                Connection cn = new ConexaoFactory().conexao();
                                System.out.println("\nConectado com o banco de dados");
                                cn.close();
                            } else if (escolhaMenuColaborador == 2) {
                                ColaboradorCRUD.inserir();
                            } else if (escolhaMenuColaborador == 3) {
                                ColaboradorCRUD.deletar();
                            } else if (escolhaMenuColaborador == 4) {
                                ColaboradorCRUD.selecionar();
                            } else if (escolhaMenuColaborador == 5) {
                                ColaboradorCRUD.atualizar();
                            } else if (escolhaMenuColaborador == 6) {
                                saidaMenuColaboradorCrud = true;
                            } else {
                                System.out.println("Opção inválida!");
                            }

                        } while (!saidaMenuColaboradorCrud);


                    } else if (escolhaSubMenu == 3) {
                            boolean saidaMenuMedico = false;
                            do {
                                System.out.println("\n\n** MENU MÉDICO **");
                                System.out.println("1 - Testar conexão com o banco de dados");
                                System.out.println("2 - Inserir médico no banco de dados");
                                System.out.println("3 - Remover médico do banco de dados");
                                System.out.println("4 - Listar os médicos cadastrados no banco de dados");
                                System.out.println("5 - Atualizar dados do médico");
                                System.out.println("6 - Voltar");
                                System.out.print("Escolha: ");
                                int escolhaMenuMedico = scanner.nextInt();

                                if (escolhaMenuMedico == 1) {
                                    Connection cn = new ConexaoFactory().conexao();
                                    System.out.println("\nConectado com o banco de dados");
                                    cn.close();
                                } else if (escolhaMenuMedico == 2) {
                                    MedicoCRUD.inserir();
                                } else if (escolhaMenuMedico == 3) {
                                    MedicoCRUD.deletar();
                                } else if (escolhaMenuMedico == 4) {
                                    MedicoCRUD.selecionar();
                                } else if (escolhaMenuMedico == 5) {
                                    MedicoCRUD.atualizar();
                                } else if (escolhaMenuMedico == 6) {
                                    saidaMenuMedico = true;
                                } else {
                                    System.out.println("Opção inválida!");
                                }

                            } while (!saidaMenuMedico);

                        } else if (escolhaSubMenu == 4) {
                            saidaMenuColaborador = true;
                        } else {
                            System.out.println("Opção inválida!");
                        }

                    } while (!saidaMenuColaborador);

                } else {
                    System.out.println("Usuário ou senha incorretos!!");
                }

            } else if (escolha == 3) {

                boolean saidaMenuAjuda = false;
                do {
                    System.out.println("\n\n** MENU AJUDA **");
                    System.out.println("1 - DADOS DE CONTATO HOSPITAL DAS CLÍNICAS");
                    System.out.println("2 - ENDEREÇO HOSPITAL DAS CLÍNICAS");
                    System.out.println("3 - Voltar");
                    System.out.print("Escolha: ");
                    int escolhaMenuMedico = scanner.nextInt();

                    if (escolhaMenuMedico == 1) {
                        System.out.println("\nSite IMREA: https://redelucymontoro.org.br/site");
                        System.out.println("Área para contato: https://redelucymontoro.org.br/site/fale-conosco");
                    } else if (escolhaMenuMedico == 2) {

                        Hospital objHospital = new Hospital();
                        Endereco objEndereco = new Endereco();

                        objHospital.setNomeUnidade("IMREA Vila Mariana");
                        objHospital.setNumeroUnidade(1);
                        objEndereco.setLogradouro("Rua Domingo de Soto");
                        objEndereco.setNumero(100);
                        objEndereco.setBairro("Jardim Vila Mariana");
                        objEndereco.setCidade("São Paulo");

                        objHospital.setEnderecoUnidade(objEndereco);

                        System.out.println(objHospital);
                    } else if (escolhaMenuMedico == 3) {
                        saidaMenuAjuda = true;
                    } else {
                        System.out.println("Opção inválida!");
                    }

                } while (!saidaMenuAjuda);
                System.out.println();
            } else if (escolha == 4) {
                saida = true;
                System.out.println("Sistema encerrado, muito obrigado por utilizar nosso sistema GUIA HC.");
            } else {
                System.out.println("Opção inválida!");
            }

        } while (!saida);

        scanner.close();
    }
}

