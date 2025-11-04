package com.gestao_habitos_saudaveis.controller;

import com.gestao_habitos_saudaveis.model.Habito;
import com.gestao_habitos_saudaveis.service.HabitoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/habitos")
public class HabitoController {

    private final HabitoService service;

    public HabitoController(HabitoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Habito> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Habito buscar(@PathVariable String id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Habito> criar(@RequestBody Habito habito) {
        Habito criado = service.criar(habito);
        return ResponseEntity.status(201).body(criado);
    }

    @PutMapping("/{id}")
    public Habito atualizar(@PathVariable String id, @RequestBody Habito habito) {
        return service.atualizar(id, habito);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
