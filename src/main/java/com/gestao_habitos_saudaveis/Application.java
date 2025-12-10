package com.gestao_habitos_saudaveis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan; // Importe esta linha

@SpringBootApplication
@ComponentScan(basePackages = "com.gestao_habitos_saudaveis")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}