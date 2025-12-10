package com.gestao_habitos_saudaveis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration; // Importe esta
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration; // Importe esta
import org.springframework.context.annotation.ComponentScan;

// CORREÇÃO FINAL: Exclui a tentativa automática de configurar o DB
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = "com.gestao_habitos_saudaveis")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}