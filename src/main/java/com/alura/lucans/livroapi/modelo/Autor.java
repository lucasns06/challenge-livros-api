package com.alura.lucans.livroapi.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private String dataNascimento;
    private String dataMorte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {
    }

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.dataNascimento = dadosAutor.dataNascimento();
        this.dataMorte = dadosAutor.dataMorte();
    }
    public void addLivro(Livro livro) {
        livros.add(livro);
        livro.setAutor(this);
    }
    public void removeLivro(Livro livro) {
        livros.remove(livro);
        livro.setAutor(null);
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        String livrosStr = (livros == null || livros.isEmpty())
                ? "Nenhum livro registrado"
                : livros.stream()
                .map(Livro::getTitulo)
                .collect(Collectors.joining(", "));

        return  "\n" +
                "Autor: " + nome + "\n" +
                "Data de Nascimento: " + dataNascimento + "\n" +
                "Data da Morte: " + dataMorte + "\n" +
                "Livros: " + livrosStr + "\n";
    }
}
