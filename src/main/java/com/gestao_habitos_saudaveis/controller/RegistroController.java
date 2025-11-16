package com.gestao_habitos_saudaveis.controller;

import com.gestao_habitos_saudaveis.model.RegistroDiario;
import com.gestao_habitos_saudaveis.model.RegistroHabito;
import com.gestao_habitos_saudaveis.service.RegistroService;
import java.util.List;

public class RegistroController {

    private final RegistroService service = new RegistroService();

    public List<RegistroDiario> listarRegistrosDiarios() {
        return service.listarRegistrosDiarios();
    }

    public RegistroDiario buscarRegistroDiario(Long id) {
        return service.buscarRegistroDiarioPorId(id);
    }

    public void criarRegistroDiario(RegistroDiario registro) {
        service.salvarRegistroDiario(registro);
    }

    public void deletarRegistroDiario(Long id) {
        service.deletarRegistroDiario(id);
    }

    public List<RegistroHabito> listarRegistrosHabitos() {
        return service.listarRegistrosHabitos();
    }

    public RegistroHabito buscarRegistroHabito(Long id) {
        return service.buscarRegistroHabitoPorId(id);
    }

    public void criarRegistroHabito(RegistroHabito registro) {
        service.salvarRegistroHabito(registro);
    }

    public void deletarRegistroHabito(Long id) {
        service.deletarRegistroHabito(id);
    }
}
