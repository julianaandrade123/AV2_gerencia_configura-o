package com.gestao_habitos_saudaveis.repository;

import com.gestao_habitos_saudaveis.model.RegistroDiario;
import com.gestao_habitos_saudaveis.model.RegistroHabito;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RegistroRepository {

    private final List<RegistroDiario> registrosDiarios = new ArrayList<>();
    private final List<RegistroHabito> registrosHabitos = new ArrayList<>();

    private final AtomicLong idDiarioGenerator = new AtomicLong(1);
    private final AtomicLong idHabitoGenerator = new AtomicLong(1);

    private String gerarIdDiario() {
        return "DIARIO-" + idDiarioGenerator.getAndIncrement();
    }

    private String gerarIdHabito() {
        return "REGHABITO-" + idHabitoGenerator.getAndIncrement();
    }

    // REGISTRO DIÁRIO

    public List<RegistroDiario> listarRegistrosDiarios() {
        return new ArrayList<>(registrosDiarios);
    }

    public Optional<RegistroDiario> buscarRegistroDiarioPorId(String id) {
        return registrosDiarios.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    public RegistroDiario salvarRegistroDiario(RegistroDiario registro) {

        if (registro.getId() == null || registro.getId().isBlank()) {
            registro.setId(gerarIdDiario());
            registrosDiarios.add(registro);
        } else {
            registrosDiarios.removeIf(r -> r.getId().equals(registro.getId()));
            registrosDiarios.add(registro);
        }

        return registro;
    }

    public boolean deletarRegistroDiario(String id) {
        return registrosDiarios.removeIf(r -> r.getId().equals(id));
    }

    // REGISTRO HÁBITO

    public List<RegistroHabito> listarRegistrosHabitos() {
        return new ArrayList<>(registrosHabitos);
    }

    public Optional<RegistroHabito> buscarRegistroHabitoPorId(String id) {
        return registrosHabitos.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    public RegistroHabito salvarRegistroHabito(RegistroHabito registro) {

        if (registro.getId() == null || registro.getId().isBlank()) {
            registro.setId(gerarIdHabito());
            registrosHabitos.add(registro);
        } else {
            registrosHabitos.removeIf(r -> r.getId().equals(registro.getId()));
            registrosHabitos.add(registro);
        }

        return registro;
    }

    public boolean deletarRegistroHabito(String id) {
        return registrosHabitos.removeIf(r -> r.getId().equals(id));
    }
}
