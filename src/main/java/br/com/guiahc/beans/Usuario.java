package br.com.guiahc.beans;

import br.com.guiahc.api.VerificaSenha;

public class Usuario {

    private int idUsuario;
    private String email;
    private String senha;
    private String nomeCompleto;
    private String dataNascimento;
    private String genero;
    private String telefone;
    private String cpf;
    private VerificaSenha verificaSenha;


    public Usuario() {
    }

    public Usuario(int idUsuario, String email, String senha, String nomeCompleto, String dataNascimento, String genero, String telefone, String cpf) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.senha = senha;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public VerificaSenha getVerificaSenha() {
        return verificaSenha;
    }

    public void setVerificaSenha(VerificaSenha verificaSenha) {
        this.verificaSenha = verificaSenha;
    }

}
