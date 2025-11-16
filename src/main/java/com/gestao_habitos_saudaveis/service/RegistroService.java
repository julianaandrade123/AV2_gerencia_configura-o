package com.gestao_habitos_saudaveis.service;

import com.gestao_habitos_saudaveis.exception.DadosInvalidosException;
import com.gestao_habitos_saudaveis.exception.RecursoNaoEncontradoException;
import com.gestao_habitos_saudaveis.model.RegistroDiario;
import com.gestao_habitos_saudaveis.model.RegistroHabito;
import com.gestao_habitos_saudaveis.repository.RegistroRepository;
import java.util.List;

public class RegistroService {

    private final RegistroRepository repository = new RegistroRepository();

    public List<RegistroDiario> listarRegistrosDiarios() {
        return repository.listarRegistrosDiarios();
    }

    public RegistroDiario buscarRegistroDiarioPorId(Long id) {
        return repository.buscarRegistroDiarioPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro diário não encontrado."));
    }

    public void salvarRegistroDiario(RegistroDiario registro) {
        if (registro.getData() == null || registro.getData().isBlank()) {
            throw new DadosInvalidosException("Data do registro diário inválida.");
        }
        repository.salvarRegistroDiario(registro);
    }

    public void deletarRegistroDiario(Long id) {
        boolean removido = repository.deletarRegistroDiario(id);
        if (!removido) throw new RecursoNaoEncontradoException("Registro diário não encontrado para exclusão.");
    }

    public List<RegistroHabito> listarRegistrosHabitos() {
        return repository.listarRegistrosHabitos();
    }

    public RegistroHabito buscarRegistroHabitoPorId(Long id) {
        return repository.buscarRegistroHabitoPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de hábito não encontrado."));
    }

    public void salvarRegistroHabito(RegistroHabito registro) {
        if (registro.getData() == null || registro.getData().isBlank()) {
            throw new DadosInvalidosException("Data do registro de hábito inválida.");
        }
        repository.salvarRegistroHabito(registro);
    }

    public void deletarRegistroHabito(Long id) {
        boolean removido = repository.deletarRegistroHabito(id);
        if (!removido) throw new RecursoNaoEncontradoException("Registro de hábito não encontrado para exclusão.");
    }
}

