package com.wells.desafio_literalura;

import com.wells.desafio_literalura.principal.Principal;
import com.wells.desafio_literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioLiteraluraApplication implements CommandLineRunner {
	@Autowired
	private LivroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.exibeMenu();
	}
}
