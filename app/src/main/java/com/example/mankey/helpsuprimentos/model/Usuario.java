package com.example.mankey.helpsuprimentos.model;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String cpf;
    private String role_uuid;
    private String senha;

    public Usuario() {
    }

    public Usuario(String id, String nome, String email, String role_uuid, String senha, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.role_uuid = role_uuid;
        this.senha = senha;
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole_uuid() {
        return role_uuid;
    }

    public void setRole_uuid(String role_uuid) {
        this.role_uuid = role_uuid;
    }
}
