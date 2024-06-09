package com.example.mankey.helpsuprimentos.model;

public class Armazem {
    private String id;
    private String usuarioUUID;
    private String nome;
    private String localizacao;

    public Armazem(String id, String usuarioUUID, String nome, String localizacao) {
        this.nome = nome;
        this.usuarioUUID = usuarioUUID;
        this.localizacao = localizacao;
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuarioUUID() {
        return usuarioUUID;
    }

    public void setUsuarioUUID(String usuarioUUID) {
        this.usuarioUUID = usuarioUUID;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localozacao) {
        this.localizacao = localozacao;
    }
}
