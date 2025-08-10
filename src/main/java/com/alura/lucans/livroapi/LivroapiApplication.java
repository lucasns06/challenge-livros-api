package com.alura.lucans.livroapi;

import com.alura.lucans.livroapi.modelo.Autor;
import com.alura.lucans.livroapi.principal.Principal;
import com.alura.lucans.livroapi.repository.AutorRepository;
import com.alura.lucans.livroapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LivroapiApplication implements CommandLineRunner {
	@Autowired
	private LivroRepository livroRepositorio;
	@Autowired
	private AutorRepository autorRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(LivroapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		Principal principal = new Principal(livroRepositorio, autorRepositorio);
		principal.exibirMenu();
	}

}
