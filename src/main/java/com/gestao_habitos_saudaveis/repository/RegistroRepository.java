
package com.gestao_habitos_saudaveis.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gestao_habitos_saudaveis.model.RegistroDiario;
import com.gestao_habitos_saudaveis.model.RegistroHabito;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Paths;
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

    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private final File diarioFile = Paths.get("registro_diario.json").toFile();
    private final File habitoFile = Paths.get("registro_habito.json").toFile();

    @PostConstruct
    public void init() {
        try {
            if (!diarioFile.exists()) {
                diarioFile.createNewFile();
                objectMapper.writeValue(diarioFile, registrosDiarios);
            } else {
                List<RegistroDiario> carregados = objectMapper.readValue(diarioFile,
                        new TypeReference<List<RegistroDiario>>() {});
                registrosDiarios.addAll(carregados);

                carregados.stream()
                        .map(r -> r.getId().replace("DIARIO-", ""))
                        .mapToLong(Long::parseLong)
                        .max()
                        .ifPresent(idDiarioGenerator::set);
            }

            if (!habitoFile.exists()) {
                habitoFile.createNewFile();
                objectMapper.writeValue(habitoFile, registrosHabitos);
            } else {
                List<RegistroHabito> carregados = objectMapper.readValue(habitoFile,
                        new TypeReference<List<RegistroHabito>>() {});
                registrosHabitos.addAll(carregados);

                carregados.stream()
                        .map(r -> r.getId().replace("REGHABITO-", ""))
                        .mapToLong(Long::parseLong)
                        .max()
                        .ifPresent(idHabitoGenerator::set);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvarDiariosNoArquivo() {
        try {
            objectMapper.writeValue(diarioFile, registrosDiarios);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvarHabitosNoArquivo() {
        try {
            objectMapper.writeValue(habitoFile, registrosHabitos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String gerarIdDiario() {
        return "DIARIO-" + idDiarioGenerator.getAndIncrement();
    }

    private String gerarIdHabito() {
        return "REGHABITO-" + idHabitoGenerator.getAndIncrement();
    }

    // Minha parte do registro diário:

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
        salvarDiariosNoArquivo();
        return registro;
    }

    public boolean deletarRegistroDiario(String id) {
        boolean removido = registrosDiarios.removeIf(r -> r.getId().equals(id));
        if (removido) salvarDiariosNoArquivo();
        return removido;
    }

    // pra registro hábito:

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
        salvarHabitosNoArquivo();
        return registro;
    }

    public boolean deletarRegistroHabito(String id) {
        boolean removido = registrosHabitos.removeIf(r -> r.getId().equals(id));
        if (removido) salvarHabitosNoArquivo();
        return removido;
    }
}
