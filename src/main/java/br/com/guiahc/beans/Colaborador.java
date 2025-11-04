package br.com.guiahc.beans;

public class Colaborador extends Pessoa{

    private int numCracha;

    public Colaborador() {
    }

    public Colaborador(int id, String nome, int idade, String email, String cpf, String dtNascimento, String telefone, int numCracha) {
        super(id, nome, idade, email, cpf, dtNascimento, telefone);
        this.numCracha = numCracha;
    }

    public int getNumCracha() {
        return numCracha;
    }

    public void setNumCracha(int numCracha) {
        this.numCracha = numCracha;
    }

    @Override
    public String toString() {
        return "\n\nINFORMAÇÕES DO COLABORADOR" +
                "\nNúmero do crachá: " + numCracha+
                super.toString();
    }
}
