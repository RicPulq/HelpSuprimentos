package com.example.mankey.helpsuprimentos.model;

public class PontoDistribuicao {
    private String id;
    private String nome;
    private String coordenadas;

    public PontoDistribuicao(String id, String nome, String coordenadas) {
        this.nome = nome;
        this.coordenadas = coordenadas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
}
