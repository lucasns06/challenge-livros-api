package com.alura.lucans.livroapi.modelo;

import java.util.List;

public class Livro {
    private String titulo;
    private String autor;
    private List<String> idiomas;
    private Integer downloads;

    public Livro(DadosLivro dadosLivro, DadosAutor dadosAutor) {
        this.titulo = dadosLivro.titulo();
        this.downloads = dadosLivro.downloads();
        this.idiomas = dadosLivro.idiomas();
        this.autor = dadosAutor.nome();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return "========Livro========" +"\n" +
                "Titulo: " + titulo + "\n" +
                "Autor: " + autor + "\n" +
                "Número de downloads: " + downloads + "\n" +
                "idiomas: " + String.join(", ", idiomas) + "\n" +
                "========Livro========";
    }
}
