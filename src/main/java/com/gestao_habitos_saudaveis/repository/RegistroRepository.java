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

    // REGISTRO DIÁRIO

    public List<RegistroDiario> listarRegistrosDiarios() {
        return new ArrayList<>(registrosDiarios);
    }

    public Optional<RegistroDiario> buscarRegistroDiarioPorId(Long id) {
        return registrosDiarios.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    public RegistroDiario salvarRegistroDiario(RegistroDiario registro) {
        if (registro.getId() == null) {
            registro.setId(idDiarioGenerator.getAndIncrement());
            registrosDiarios.add(registro);  // criação
        } else {
            // atualização
            registrosDiarios.removeIf(r -> r.getId().equals(registro.getId()));
            registrosDiarios.add(registro);
        }
        return registro;
    }

    public boolean deletarRegistroDiario(Long id) {
        return registrosDiarios.removeIf(r -> r.getId().equals(id));
    }

    // REGISTRO DIÁRIO

    public List<RegistroHabito> listarRegistrosHabitos() {
        return new ArrayList<>(registrosHabitos);
    }

    public Optional<RegistroHabito> buscarRegistroHabitoPorId(Long id) {
        return registrosHabitos.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    public RegistroHabito salvarRegistroHabito(RegistroHabito registro) {
        if (registro.getId() == null) {
            registro.setId(idHabitoGenerator.getAndIncrement());
            registrosHabitos.add(registro); // criação
        } else {

            registrosHabitos.removeIf(r -> r.getId().equals(registro.getId()));
            registrosHabitos.add(registro);
        }
        return registro;
    }

    public boolean deletarRegistroHabito(Long id) {
        return registrosHabitos.removeIf(r -> r.getId().equals(id));
    }
}
