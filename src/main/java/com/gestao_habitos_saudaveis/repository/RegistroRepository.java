package com.gestao_habitos_saudaveis.repository;

import com.gestao_habitos_saudaveis.model.RegistroDiario;
import com.gestao_habitos_saudaveis.model.RegistroHabito;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistroRepository {

    private final List<RegistroDiario> registrosDiarios = new ArrayList<>();
    private final List<RegistroHabito> registrosHabitos = new ArrayList<>();

    public List<RegistroDiario> listarRegistrosDiarios() {
        return registrosDiarios;
    }

    public Optional<RegistroDiario> buscarRegistroDiarioPorId(Long id) {
        return registrosDiarios.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public void salvarRegistroDiario(RegistroDiario registro) {
        registrosDiarios.add(registro);
    }

    public boolean deletarRegistroDiario(Long id) {
        return registrosDiarios.removeIf(r -> r.getId().equals(id));
    }

    public List<RegistroHabito> listarRegistrosHabitos() {
        return registrosHabitos;
    }

    public Optional<RegistroHabito> buscarRegistroHabitoPorId(Long id) {
        return registrosHabitos.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public void salvarRegistroHabito(RegistroHabito registro) {
        registrosHabitos.add(registro);
    }

    public boolean deletarRegistroHabito(Long id) {
        return registrosHabitos.removeIf(r -> r.getId().equals(id));
    }
}
