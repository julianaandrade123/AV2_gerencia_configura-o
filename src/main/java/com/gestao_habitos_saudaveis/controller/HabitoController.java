package com.gestao_habitos_saudaveis.controller;

import com.gestao_habitos_saudaveis.model.Habito;
import com.gestao_habitos_saudaveis.model.HabitoAlimentacao;
import com.gestao_habitos_saudaveis.model.HabitoSono;
import com.gestao_habitos_saudaveis.model.HabitoAtividadeFisica;
import com.gestao_habitos_saudaveis.service.HabitoService;
import com.gestao_habitos_saudaveis.exception.BadRequestException;
import com.gestao_habitos_saudaveis.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/habitos")
public class HabitoController {

    private final HabitoService service;

    @Autowired
    public HabitoController(HabitoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Habito> criar(@RequestBody Habito habito) {
        Habito criado = service.criar(habito);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    public ResponseEntity<List<Habito>> listar(
            @RequestParam(required = false) String tipo) {

        if (tipo == null || tipo.isBlank()) {
            return ResponseEntity.ok(service.listarTodos());
        }

        List<Habito> filtrados = service.listarTodos().stream()
                .filter(h -> {
                    switch (tipo.toLowerCase()) {
                        case "alimentacao":
                            return h instanceof HabitoAlimentacao;
                        case "sono":
                            return h instanceof HabitoSono;
                        case "atividade":
                            return h instanceof HabitoAtividadeFisica;
                        default:
                            throw new BadRequestException("Tipo de hábito inválido: " + tipo);
                    }
                })
                .toList();

        return ResponseEntity.ok(filtrados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habito> buscarPorId(@PathVariable String id) {
        Habito habito = service.buscarPorId(id);
        return ResponseEntity.ok(habito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habito> atualizar(@PathVariable String id,
                                            @RequestBody Habito atualizacao) {
        Habito atualizado = service.atualizar(id, atualizacao);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
