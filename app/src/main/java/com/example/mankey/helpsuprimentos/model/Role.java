package com.example.mankey.helpsuprimentos.model;

public class Role {
    private String id;
    private String nome;
    private int nivelAcesso;

    public Role(String id, String nome, int nivelAcesso) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
