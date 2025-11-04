package br.com.guiahc.beans;

public class Endereco {

    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;

    //Não é necessário construtor pois a informação do endereço é sempre a mesma.

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    @Override
    public String toString() {
        return "\nLogradouro: " + logradouro +
                "\nNúmero: " + numero +
                "\nBairro: " + bairro +
                "\nCidade: " + cidade;
    }
}
