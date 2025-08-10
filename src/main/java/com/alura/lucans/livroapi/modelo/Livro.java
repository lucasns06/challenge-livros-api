package com.alura.lucans.livroapi.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String autorLivro;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "livro_idiomas", joinColumns = @JoinColumn(name = "livro_id"))
    @Column(name = "idioma")
    private List<String> idiomas = new ArrayList<>();
    private Integer downloads;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    public Livro() {
    }

    public Livro(DadosLivro dadosLivro, DadosAutor dadosAutor) {
        this.titulo = dadosLivro.titulo();
        this.downloads = dadosLivro.downloads();
        this.idiomas = dadosLivro.idiomas();
        this.autorLivro = dadosAutor.nome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutorLivro() {
        return autorLivro;
    }

    public void setAutorLivro(String autorLivro) {
        this.autorLivro = autorLivro;
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
                "Autor: " + autorLivro + "\n" +
                "Número de downloads: " + downloads + "\n" +
                "idiomas: " + String.join(", ", idiomas) + "\n" +
                "========Livro========"
                + "\n";
    }
}
