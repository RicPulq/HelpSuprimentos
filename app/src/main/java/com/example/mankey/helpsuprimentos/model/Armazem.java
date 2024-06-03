package com.example.mankey.helpsuprimentos.model;

public class Armazem {
    private String usuarioUUID;
    private String localozacao;
    private String dataAtualizacao;

    public Armazem(String usuarioUUID, String localozacao, String dataAtualizacao) {
        this.usuarioUUID = usuarioUUID;
        this.localozacao = localozacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getUsuarioUUID() {
        return usuarioUUID;
    }

    public void setUsuarioUUID(String usuarioUUID) {
        this.usuarioUUID = usuarioUUID;
    }

    public String getLocalozacao() {
        return localozacao;
    }

    public void setLocalozacao(String localozacao) {
        this.localozacao = localozacao;
    }

    public String getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(String dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
