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
                    listarAutoresVivosNoAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        }
    }

public void buscarPorLivroTitulo() {
    System.out.print("Digite o titulo do livro: ");
    var titulo = leitor.nextLine();
    System.out.println("Carregando...");

    var json = consumoApi.obterDados(ENDERECO + "books/?search=" + titulo.replace(" ", "+"));
    ResultadoApi resposta = conversor.obterDados(json, ResultadoApi.class);
    var listaLivros = resposta.resposta();

    if (listaLivros == null || listaLivros.isEmpty()) {
        System.out.println("Nenhum livro encontrado para o título pesquisado.");
        return;
    }

    var primeiroLivroDados = listaLivros.get(0);

    if (primeiroLivroDados.autor() == null || primeiroLivroDados.autor().isEmpty()) {
        System.out.println("Resposta da API não contém autor para esse livro.");
        return;
    }

    var primeiroAutorDados = primeiroLivroDados.autor().get(0);

    Livro livro = new Livro(primeiroLivroDados, primeiroAutorDados);

    if (livroRepositorio.existsByTitulo(livro.getTitulo())) {
        System.out.println("Livro já existe no sistema!");
        return;
    }

    var opcionalAutor = autorRepositorio.findByNome(primeiroAutorDados.nome());
    Autor autorEntity = opcionalAutor.orElseGet(() -> new Autor(primeiroAutorDados));

    autorEntity.addLivro(livro);

    try {
        autorRepositorio.save(autorEntity);
        System.out.println("Livro e autor salvos no sistema!");
    } catch (DataIntegrityViolationException e) {
        System.out.println("Erro ao salvar (provável conflito de integridade): " + e.getMessage());
    }

    System.out.println(livro);
}

    public void listarLivrosRegistrados(){
        List<Livro> livros;
        livros = livroRepositorio.findAll();
        livros.forEach(System.out::println);
    }
    public void listarAutoresRegistrados(){
        List<Autor> autores;
        autores = autorRepositorio.findAllComLivros();
        autores.forEach(System.out::println);
    }
    public void listarAutoresVivosNoAno() {
        System.out.print("Digite o ano (ex: 1900): ");
        String entrada = leitor.nextLine().trim();
        int ano;
        try {
            ano = Integer.parseInt(entrada);
        } catch (NumberFormatException ex) {
            System.out.println("Ano inválido.");
            return;
        }

        List<Autor> todos = autorRepositorio.findAllComLivros();
        List<Autor> vivos = new ArrayList<>();

        for (Autor a : todos) {
            Integer nascimento = parseAno(a.getDataNascimento());
            Integer morte = parseAno(a.getDataMorte());

            if (nascimento == null) continue;

            boolean nasceuAntesOuNoAno = nascimento <= ano;
            boolean morreuAntesDoAno = (morte != null && morte < ano);

            if (nasceuAntesOuNoAno && !morreuAntesDoAno) {
                vivos.add(a);
            }
        }

        if (vivos.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo no ano " + ano);
        } else {
            vivos.forEach(System.out::println);
        }
    }

    private Integer parseAno(String data) {
        if (data == null || data.isBlank()) return null;
        String digits = data.trim();
        if (digits.length() >= 4) {
            try {
                return Integer.parseInt(digits.substring(0,4));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        try {
            return Integer.parseInt(digits);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
    public void listarLivrosPorIdioma() {
        System.out.print("""
                Insira o idioma para realizar a busca:
                
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        System.out.print("\n" + "Escolha:");
        String idioma = leitor.nextLine().trim();

        if (idioma.isEmpty()) {
            System.out.println("Idioma inválido.");
            return;
        }

        List<Livro> livros = livroRepositorio.findByIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma " + idioma);
        } else {
            livros.forEach(System.out::println);
        }
    }

}
