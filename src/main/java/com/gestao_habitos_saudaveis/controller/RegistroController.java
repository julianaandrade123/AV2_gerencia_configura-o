package com.gestao_habitos_saudaveis.controller;

import com.gestao_habitos_saudaveis.model.RegistroDiario;
import com.gestao_habitos_saudaveis.model.RegistroHabito;
import com.gestao_habitos_saudaveis.service.RegistroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros")
@CrossOrigin(origins = "*")
public class RegistroController {

    private final RegistroService service;

    public RegistroController(RegistroService service) {
        this.service = service;
    }

    // ───────────────────────────────────────────────
    // REGISTRO DIÁRIO
    // ───────────────────────────────────────────────

    @GetMapping("/diarios")
    public List<RegistroDiario> listarRegistrosDiarios() {
        return service.listarRegistrosDiarios();
    }

    @GetMapping("/diarios/{id}")
    public RegistroDiario buscarRegistroDiario(@PathVariable String id) {
        return service.buscarRegistroDiarioPorId(id);
    }

    @PostMapping("/diarios")
    public RegistroDiario criarRegistroDiario(@RequestBody RegistroDiario registro) {
        return service.salvarRegistroDiario(registro);
    }

    @PutMapping("/diarios/{id}")
    public RegistroDiario atualizarRegistroDiario(
            @PathVariable String id,
            @RequestBody RegistroDiario registroAtualizado
    ) {
        RegistroDiario existente = service.buscarRegistroDiarioPorId(id);

        existente.setData(registroAtualizado.getData());
        existente.setHabitos(registroAtualizado.getHabitos());
        existente.setUsuario(registroAtualizado.getUsuario());

        return service.salvarRegistroDiario(existente);
    }

    @DeleteMapping("/diarios/{id}")
    public void deletarRegistroDiario(@PathVariable String id) {
        service.deletarRegistroDiario(id);
    }

    // ───────────────────────────────────────────────
    // REGISTRO HABITO
    // ───────────────────────────────────────────────

    @GetMapping("/habitos")
    public List<RegistroHabito> listarRegistrosHabitos() {
        return service.listarRegistrosHabitos();
    }

    @GetMapping("/habitos/{id}")
    public RegistroHabito buscarRegistroHabito(@PathVariable String id) {
        return service.buscarRegistroHabitoPorId(id);
    }

    @PostMapping("/habitos")
    public RegistroHabito criarRegistroHabito(@RequestBody RegistroHabito registroHabito) {
        return service.salvarRegistroHabito(registroHabito);
    }

    @PutMapping("/habitos/{id}")
    public RegistroHabito atualizarRegistroHabito(
            @PathVariable String id,
            @RequestBody RegistroHabito atualizado
    ) {
        RegistroHabito existente = service.buscarRegistroHabitoPorId(id);

        existente.setData(atualizado.getData());
        existente.setIdHabito(atualizado.getIdHabito());

        return service.salvarRegistroHabito(existente);
    }

    @DeleteMapping("/habitos/{id}")
    public void deletarRegistroHabito(@PathVariable String id) {
        service.deletarRegistroHabito(id);
    }
}
