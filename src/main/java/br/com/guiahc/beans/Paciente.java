package br.com.guiahc.beans;

public class Paciente extends Pessoa{


    private String sexo;
    private PlanoSaude planoSaude;

    public Paciente() {
    }


    public Paciente(int id, String nome, int idade, String email, String cpf, String dtNascimento, String telefone,String sexo) {
        super(id, nome, idade, email, cpf, dtNascimento, telefone);
        this.sexo = sexo;
    }


    public PlanoSaude getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(PlanoSaude planoSaude) {
        this.planoSaude = planoSaude;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return
                super.toString() +
                "\nSexo: " +  sexo  +
                 planoSaude;
    }
}
