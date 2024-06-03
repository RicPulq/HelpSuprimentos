package com.example.mankey.helpsuprimentos.model;

public class Suprimento {
    private String nome;
    private int quantidade;
    private String armazemUUID;

    public Suprimento(String nome, int quantidade, String armazemUUID) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.armazemUUID = armazemUUID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getArmazemUUID() {
        return armazemUUID;
    }

    public void setArmazemUUID(String armazemUUID) {
        this.armazemUUID = armazemUUID;
    }
}
