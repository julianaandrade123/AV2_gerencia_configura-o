package com.gestao_habitos_saudaveis.service;

import com.gestao_habitos_saudaveis.exception.DadosInvalidosException;
import com.gestao_habitos_saudaveis.exception.RecursoNaoEncontradoException;
import com.gestao_habitos_saudaveis.model.RegistroDiario;
import com.gestao_habitos_saudaveis.model.RegistroHabito;
import com.gestao_habitos_saudaveis.repository.RegistroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroService {

    private final RegistroRepository repository;

    public RegistroService(RegistroRepository repository) {
        this.repository = repository;
    }

    // REGISTRO DIÁRIO

    public List<RegistroDiario> listarRegistrosDiarios() {
        return repository.listarRegistrosDiarios();
    }

    public RegistroDiario buscarRegistroDiarioPorId(String id) {
        return repository.buscarRegistroDiarioPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro diário não encontrado."));
    }

    public RegistroDiario salvarRegistroDiario(RegistroDiario registro) {
        if (registro.getData() == null || registro.getData().isBlank()) {
            throw new DadosInvalidosException("Data do registro diário inválida.");
        }
        return repository.salvarRegistroDiario(registro);
    }

    public void deletarRegistroDiario(String id) {
        boolean removido = repository.deletarRegistroDiario(id);
        if (!removido) {
            throw new RecursoNaoEncontradoException("Registro diário não encontrado para exclusão.");
        }
    }

    // REGISTRO HÁBITO

    public List<RegistroHabito> listarRegistrosHabitos() {
        return repository.listarRegistrosHabitos();
    }

    public RegistroHabito buscarRegistroHabitoPorId(String id) {
        return repository.buscarRegistroHabitoPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de hábito não encontrado."));
    }

    public RegistroHabito salvarRegistroHabito(RegistroHabito registro) {
        if (registro.getData() == null || registro.getData().isBlank()) {
            throw new DadosInvalidosException("Data do registro de hábito inválida.");
        }
        return repository.salvarRegistroHabito(registro);
    }

    public void deletarRegistroHabito(String id) {
        boolean removido = repository.deletarRegistroHabito(id);
        if (!removido) {
            throw new RecursoNaoEncontradoException("Registro de hábito não encontrado para exclusão.");
        }
    }
}
