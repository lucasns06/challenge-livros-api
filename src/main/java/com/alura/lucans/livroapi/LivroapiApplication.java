package com.alura.lucans.livroapi;

import com.alura.lucans.livroapi.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LivroapiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LivroapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		Principal principal = new Principal();
		principal.exibirMenu();
	}

}
