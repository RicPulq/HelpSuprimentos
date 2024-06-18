package com.example.mankey.helpsuprimentos.model;

public class ServicoMedico extends Localizacao {
    private String id;
    private String usuarioUUID;
    private String localizacao;
    private String tipoAtendimento;

    public ServicoMedico(String id, String usuarioUUID, String localizacao, String tipoAtendimento, double latitude, double longitude) {
        this.usuarioUUID = usuarioUUID;
//        this.localizacao = localizacao;
        this.tipoAtendimento = tipoAtendimento;
        this.setLocalizacao(localizacao);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
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
    public String getLocalizacaoString() {
        return getLocalizacao();
    }

}
