package br.com.guiahc.beans;

public class Medico extends Pessoa{

    private String crm;
    private String especialidadeMedico;

    public Medico() {
    }

    public Medico(int id, String nome, int idade, String email, String cpf, String dtNascimento, String telefone, String crm, String especialidadeMedico) {
        super(id, nome, idade, email, cpf, dtNascimento, telefone);
        this.crm = crm;
        this.especialidadeMedico = especialidadeMedico;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidadeMedico() {
        return especialidadeMedico;
    }

    public void setEspecialidadeMedico(String especialidadeMedico) {
        this.especialidadeMedico = especialidadeMedico;
    }

    @Override
    public String toString() {
        return "\n\nINFORMAÇÕES DO MÉDICO" +
                "\nCRM: " + crm +
                "\nEspecialidade do Médico: " + especialidadeMedico +
                super.toString();
    }
}
