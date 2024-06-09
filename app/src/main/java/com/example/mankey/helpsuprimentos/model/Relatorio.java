package com.example.mankey.helpsuprimentos.model;

public class Relatorio {
    private String usuarioUUID;
    private String tipo;
    private String conteudo;
    private String dataCriacao;

    public Relatorio(String usuarioUUID, String tipo, String conteudo, String dataCriacao) {
        this.usuarioUUID = usuarioUUID;
        this.tipo = tipo;
        this.conteudo = conteudo;
        this.dataCriacao = dataCriacao;
    }

    public String getUsuarioUUID() {
        return usuarioUUID;
    }

    public void setUsuarioUUID(String usuarioUUID) {
        this.usuarioUUID = usuarioUUID;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getDataCriaocao() {
        return dataCriacao;
    }

    public void setDataCriaocao(String dataCriaocao) {
        this.dataCriacao = dataCriaocao;
    }
}
