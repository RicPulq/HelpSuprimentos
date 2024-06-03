package com.example.mankey.helpsuprimentos.model;

public class Role {
    private String nome;
    private int nivelAcesso;

    public Role(String nome, int nivelAcesso) {
        this.nome = nome;
        this.nivelAcesso = nivelAcesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}
