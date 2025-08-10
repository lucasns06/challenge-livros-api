package com.alura.lucans.livroapi.principal;
import com.alura.lucans.livroapi.modelo.Autor;
import com.alura.lucans.livroapi.modelo.DadosAutor;
import com.alura.lucans.livroapi.modelo.Livro;
import com.alura.lucans.livroapi.modelo.ResultadoApi;
import com.alura.lucans.livroapi.repository.AutorRepository;
import com.alura.lucans.livroapi.repository.LivroRepository;
import com.alura.lucans.livroapi.service.ConsumoApi;
import com.alura.lucans.livroapi.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitor = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private int resposta = -1;
    private final String ENDERECO = "https://gutendex.com/";
    private LivroRepository livroRepositorio;
    private AutorRepository autorRepositorio;
    public Principal(LivroRepository livroRepositorio, AutorRepository autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void exibirMenu (){
        while (resposta != 0) {
            System.out.println("""
                       Escolha o número de sua opção
                    1 - buscar livro pelo título
                    2 - listar livros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos em um determinado ano
                    5 - listar livros em um determinado idioma
                    0 - sair
                    """);
            System.out.print("Resposta: ");
            if (!leitor.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número.");
                leitor.nextLine();
                return;
            }
            resposta = leitor.nextInt();
            leitor.nextLine();
            switch (resposta) {
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1:
                    buscarPorLivroTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    System.out.println("b");
                    break;
                case 5:
                    System.out.println("c");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        }
    }
    public void buscarPorLivroTitulo(){
        System.out.print("Digite o titulo do livro: ");
        var titulo = leitor.nextLine();
        System.out.println("Carregando...");

        var json = consumoApi.obterDados(ENDERECO + "books/?search=" + titulo.replace(" ", "+"));

        ResultadoApi resposta = conversor.obterDados(json, ResultadoApi.class);

        var listaLivros = resposta.resposta();

        if (listaLivros == null || listaLivros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o título pesquisado.");
        } else {
            var primeiroLivro = listaLivros.get(0);
            var primeiroAutor = primeiroLivro.autor().get(0);
            Livro livro = new Livro(primeiroLivro, primeiroAutor);
            Autor autor = new Autor(primeiroAutor);
            try {
                livroRepositorio.save(livro);
                autorRepositorio.save(autor);
                System.out.println("Livro e autor salvos no sistema!");
            } catch (DataIntegrityViolationException error){
                System.out.println("Livro já existe no sistema!");
            }
            System.out.println(livro);
        }
    }

    public void listarLivrosRegistrados(){
        List<Livro> livros;
        livros = livroRepositorio.findAll();
        livros.forEach(System.out::println);
    }
    public void listarAutoresRegistrados(){
        List<Autor> autores;
        autores = autorRepositorio.findAll();
        autores.forEach(System.out::println);
    }

}
