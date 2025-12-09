package com.gestao_habitos_saudaveis.config;

import com.gestao_habitos_saudaveis.model.HabitoAlimentacao;
import com.gestao_habitos_saudaveis.service.HabitoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class ScannerDataLoader implements CommandLineRunner {

    private final HabitoService service;

    public ScannerDataLoader(HabitoService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        if (System.getenv("CLI_MODE") == null) return;

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Inserção de hábitos via console ===");
        System.out.print("Tipo (alimentacao): ");
        String tipo = scanner.nextLine();

        if (tipo.equalsIgnoreCase("alimentacao")) {
            HabitoAlimentacao habito = new HabitoAlimentacao();
            System.out.print("Título: ");
            habito.setTitulo(scanner.nextLine());
            System.out.print("Descrição: ");
            habito.setDescricao(scanner.nextLine());
            System.out.print("Calorias meta: ");
            habito.setCaloriasMeta(Integer.parseInt(scanner.nextLine()));
            service.criar(habito);
            System.out.println("Hábito salvo com sucesso!");
        }
    }
}
