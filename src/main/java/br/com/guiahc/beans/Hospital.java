package br.com.guiahc.beans;

public  class Hospital {

    private String nomeUnidade;
    private int numeroUnidade;
    private Endereco enderecoUnidade;

    //Não é necessário construtor pois a informação de nome e número da unidade é sempre a mesma

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public int getNumeroUnidade() {
        return numeroUnidade;
    }

    public void setNumeroUnidade(int numeroUnidade) {
        this.numeroUnidade = numeroUnidade;
    }

    public Endereco getEnderecoUnidade() {
        return enderecoUnidade;
    }

    public void setEnderecoUnidade(Endereco enderecoUnidade) {
        this.enderecoUnidade = enderecoUnidade;
    }

    @Override
    public String toString() {
        return "\n\nINFORMAÇÕES DO HOSPITAL" +
                "\nNome da Unidade: " + nomeUnidade +
                "\nNúmero da unidade: " + numeroUnidade +
                "\n\n-------ENDEREÇO DA UNIDADE-------" + enderecoUnidade;
    }
}
