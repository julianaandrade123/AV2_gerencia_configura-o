package com.gestao_habitos_saudaveis.controller;

import com.gestao_habitos_saudaveis.exception.BadRequestException;
import com.gestao_habitos_saudaveis.exception.ResourceNotFoundException;
import com.gestao_habitos_saudaveis.model.Habito;
import com.gestao_habitos_saudaveis.service.HabitoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/habitos")
public class HabitoController {

    private final HabitoService service;

    @Autowired
    public HabitoController(HabitoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Habito>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        try {
            Habito habito = service.buscarPorId(id);
            return ResponseEntity.ok(habito);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Habito habito) {
        try {
            Habito criado = service.criar(habito);
            return ResponseEntity.status(HttpStatus.CREATED).body(criado);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody Habito habito) {
        try {
            Habito atualizado = service.atualizar(id, habito);
            return ResponseEntity.ok(atualizado);
        } catch (ResourceNotFoundException | BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}