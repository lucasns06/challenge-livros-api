package com.alura.lucans.livroapi.modelo;

public class Autor {
    private String nome;
    private String dataNascimento;
    private String dataMorte;

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.dataNascimento = dadosAutor.dataNascimento();
        this.dataMorte = dadosAutor.dataMorte();
    }

    public String getDataMorte() {
        return dataMorte;
    }

    public void setDataMorte(String dataMorte) {
        this.dataMorte = dataMorte;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Autor: " + nome;
    }
}
