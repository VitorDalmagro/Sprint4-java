package br.com.guiahc.beans;

public class PlanoSaude {

    private String nomeDoPlano;
    private double precoMensal;
    private String tipoDePlano; // "Individual", "Familiar", etc.


    public PlanoSaude() {
    }

    public PlanoSaude(String nomeDoPlano, double precoMensal, String tipoDePlano) {
        this.nomeDoPlano = nomeDoPlano;
        this.precoMensal = precoMensal;
        this.tipoDePlano = tipoDePlano;
    }

    public String getNomeDoPlano() {
        return nomeDoPlano;
    }

    public void setNomeDoPlano(String nomeDoPlano) {
        this.nomeDoPlano = nomeDoPlano;
    }

    public double getPrecoMensal() {
        return precoMensal;
    }

    public void setPrecoMensal(double precoMensal) {
        this.precoMensal = precoMensal;
    }

    public String getTipoDePlano() {
        return tipoDePlano;
    }

    public void setTipoDePlano(String tipoDePlano) {
        this.tipoDePlano = tipoDePlano;
    }

    @Override
    public String toString() {
        return "\n\n** INFORMAÇÕES DO PLANO DE SAÚDE **" +
                "\nNome do plano: " + nomeDoPlano +
                "\nTipo do plano: " + tipoDePlano +
                "\nPreço mensal do plano: " + precoMensal +
                "\nPreço Anual: " + valorAnualPlano()
         ;
    }

    public double valorAnualPlano(){
        return precoMensal * 12;
    }
}
