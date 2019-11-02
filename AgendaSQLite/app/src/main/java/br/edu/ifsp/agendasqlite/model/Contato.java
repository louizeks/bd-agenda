package br.edu.ifsp.agendasqlite.model;

import java.io.Serializable;

public class Contato implements Serializable {

    private int id;
    private String nome;
    private String fone;
    private String celular;
    private String email;
    private String dataNascimento;
    private int favorito;

    public Contato() {
    }

    public Contato(String nome, String fone, String email, String celular, String dataNascimento, int favorito) {
        this.nome = nome;
        this.fone = fone;
        this.email = email;
        this.celular = celular;
        this.dataNascimento = dataNascimento;
        this.favorito = favorito;
    }

    public boolean equals(Object obj) {
        Contato c2 = (Contato) obj;
        if (this.id == c2.getId())
            return true;
        else
            return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() { return celular; }

    public void setCelular(String celular) { this.celular = celular; }

    public String getDataNascimento() { return dataNascimento; }

    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public int getFavorito() { return favorito; }

    public void setFavorito(int favorito) { this.favorito = favorito; }

}
