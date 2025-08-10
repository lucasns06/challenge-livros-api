package com.alura.lucans.livroapi.modelo;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name="series")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String autor;
    private List<String> idiomas;
    private Integer downloads;

    public Livro() {
    }

    public Livro(DadosLivro dadosLivro, DadosAutor dadosAutor) {
        this.titulo = dadosLivro.titulo();
        this.downloads = dadosLivro.downloads();
        this.idiomas = dadosLivro.idiomas();
        this.autor = dadosAutor.nome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return
                "\n" +
                "========Livro========" +"\n" +
                "Titulo: " + titulo + "\n" +
                "Autor: " + autor + "\n" +
                "Número de downloads: " + downloads + "\n" +
                "idiomas: " + String.join(", ", idiomas) + "\n" +
                "========Livro========"
                + "\n";
    }
}
