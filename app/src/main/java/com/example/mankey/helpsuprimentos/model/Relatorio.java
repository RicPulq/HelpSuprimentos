package com.example.mankey.helpsuprimentos.model;

public class Relatorio {
    private String usuarioUUID;
    private String tipo;
    private String conteudo;
    private String dataCriaocao;

    public Relatorio(String usuarioUUID, String tipo, String conteudo, String dataCriaocao) {
        this.usuarioUUID = usuarioUUID;
        this.tipo = tipo;
        this.conteudo = conteudo;
        this.dataCriaocao = dataCriaocao;
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
        return dataCriaocao;
    }

    public void setDataCriaocao(String dataCriaocao) {
        this.dataCriaocao = dataCriaocao;
    }
}
