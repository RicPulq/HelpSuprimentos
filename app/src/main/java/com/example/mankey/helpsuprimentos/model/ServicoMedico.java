package com.example.mankey.helpsuprimentos.model;

public class ServicoMedico {
    private String usuarioUUID;
    private String localizacao;
    private String tipoAtendimento;

    public ServicoMedico(String usuarioUUID, String localizacao, String tipoAtendimento) {
        this.usuarioUUID = usuarioUUID;
        this.localizacao = localizacao;
        this.tipoAtendimento = tipoAtendimento;
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

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(String tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }
}
