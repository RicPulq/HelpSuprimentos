package com.example.mankey.helpsuprimentos.model;

public class Suprimento {
    private String id;
    private String nome;
    private String quantidade;
    private String armazemUUID;
    private String data;

    public Suprimento(String id, String nome, String quantidade, String armazemUUID, String data) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.armazemUUID = armazemUUID;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getArmazemUUID() {
        return armazemUUID;
    }

    public void setArmazemUUID(String armazemUUID) {
        this.armazemUUID = armazemUUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
