package com.alura.lucans.livroapi.principal;
import com.alura.lucans.livroapi.modelo.DadosAutor;
import com.alura.lucans.livroapi.modelo.Livro;
import com.alura.lucans.livroapi.modelo.ResultadoApi;
import com.alura.lucans.livroapi.service.ConsumoApi;
import com.alura.lucans.livroapi.service.ConverteDados;

import java.util.Scanner;

public class Principal {
    private Scanner leitor = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://gutendex.com/";

    public void exibirMenu (){
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
        int resposta = leitor.nextInt();
        leitor.nextLine();
        switch (resposta){
            case 0:
                    System.out.println("Saindo...");
                    break;
            case 1:
                buscarPorLivroTitulo();
                break;
            case 2:
                System.out.println("dois digitado");
                break;
            case 3:
                System.out.println("aa");
                break;
            case 4:
                System.out.println("b");
                break;
            case 5:
                System.out.println("c");
                break;
            default:
                System.out.println("erro!");
                break;
        }
    }
    public void buscarPorLivroTitulo(){
        System.out.print("Digite o titulo do livro: ");
        var titulo = leitor.nextLine();

        var json = consumoApi.obterDados(ENDERECO + "books/?search=" + titulo.replace(" ", "+"));

        ResultadoApi resposta = conversor.obterDados(json, ResultadoApi.class);

        resposta.resposta().stream()
                .limit(1)
                .map(d -> new Livro(d, d.autor().get(0)))
                .forEach(System.out::println);
    }
//    public void buscarPorLivroTituloT(){
//        System.out.println("pesquisando dom casmurro: ");
//
//        var json = consumoApi.obterDados(ENDERECO + "books/?search=dom+casmurro");
//
//        ResultadoApi resposta = conversor.obterDados(json, ResultadoApi.class);
////        System.out.println(resposta);
//        resposta.resposta().stream()
//                .map(d -> new Livro(d, d.autor().get(0)))
//                .forEach(System.out::println);
//
//    }
}
