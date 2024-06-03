package com.example.mankey.helpsuprimentos.model;

public class Entrega {
    private String pontoDistribuicaoUUID;
    private String suprimentoUUID;
    private String data;
    private String status;

    public Entrega(String pontoDistribuicaoUUID, String suprimentoUUID, String data, String status) {
        this.pontoDistribuicaoUUID = pontoDistribuicaoUUID;
        this.suprimentoUUID = suprimentoUUID;
        this.data = data;
        this.status = status;
    }

    public String getPontoDistribuicaoUUID() {
        return pontoDistribuicaoUUID;
    }

    public void setPontoDistribuicaoUUID(String pontoDistribuicaoUUID) {
        this.pontoDistribuicaoUUID = pontoDistribuicaoUUID;
    }

    public String getSuprimentoUUID() {
        return suprimentoUUID;
    }

    public void setSuprimentoUUID(String suprimentoUUID) {
        this.suprimentoUUID = suprimentoUUID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
