package br.com.guiahc.api;

import java.util.Arrays;

public class VerificaSenha {

    private String senha;
    private int pontuacao;
    private String nivel;
    private String[] relatorio;

    public VerificaSenha() {
    }

    public VerificaSenha(String senha, int pontuacao, String nivel, String[] relatorio) {
        this.senha = senha;
        this.pontuacao = pontuacao;
        this.nivel = nivel;
        this.relatorio = relatorio;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String[] getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String[] relatorio) {
        this.relatorio = relatorio;
    }

    @Override
    public String toString() {
        return "\nRELATÓRIO SOBRE SEGURANÇA DA SENHA" +
                "\nSenha:'" + senha +
                "\nPontuação: " + pontuacao +
                "\nNivel: " + nivel +
                "\nRelatorio:\n " + Arrays.toString(relatorio);
    }
}

