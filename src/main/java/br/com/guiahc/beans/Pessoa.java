package br.com.guiahc.beans;

public abstract class Pessoa {

    private int id;
    private String nome;
    private int idade;
    private String email;
    private String cpf;
    private String dtNascimento;
    private String telefone;


    public Pessoa() {
    }

    public Pessoa(int id, String nome, int idade, String email, String cpf, String dtNascimento, String telefone) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "\nNome: " + nome +
                        "\nIdade: " + idade +
                        "\nE-mail: " + email +
                        "\nCPF: " + cpf +
                        "\nData de nascimento: " + dtNascimento +
                        "\nTelefone: " + telefone;

    }

}