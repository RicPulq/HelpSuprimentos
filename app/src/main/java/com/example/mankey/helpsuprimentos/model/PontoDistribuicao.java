package com.example.mankey.helpsuprimentos.model;

public class PontoDistribuicao {
    private String localizacao;
    private String coordenadas;

    public PontoDistribuicao(String localizacao, String coordenadas) {
        this.localizacao = localizacao;
        this.coordenadas = coordenadas;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
}
