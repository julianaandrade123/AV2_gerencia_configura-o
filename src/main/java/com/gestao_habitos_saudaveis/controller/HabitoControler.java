package com.gestao_habitos_saudaveis.controller;

import com.gestao_habitos_saudaveis.model.Habito;
import com.gestao_habitos_saudaveis.service.HabitoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/habitos")
    public class HabitoController {

        private final HabitoService service;

        public HabitoController(HabitoService service) {
            this.service = service;
        }

        @GetMapping
        public List<Habito> listar() {
            return service.listar();
        }

        @PostMapping
        public Habito criar(@RequestBody Habito h) {
            return service.criar(h);
        }

        @PutMapping("/{id}")
        public Habito atualizar(@PathVariable String id, @RequestBody Habito h) {
            return service.atualizar(id, h);
        }

        @DeleteMapping("/{id}")
        public void deletar(@PathVariable String id) {
            service.deletar(id);
        }

}
