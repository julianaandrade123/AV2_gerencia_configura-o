
package com.gestao_habitos_saudaveis.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gestao_habitos_saudaveis.model.Habito;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JsonHabitoRepository implements HabitoRepository {

    private final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    private final File externalDbFile = Paths.get(
            "C:/Users/gabri/Documents/Umprojetodep2/habitos.json"
    ).toFile();

    private final List<Habito> habitos = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            externalDbFile.getParentFile().mkdirs();

            if (!externalDbFile.exists()) {
                mapper.writeValue(externalDbFile, habitos);
            }

            load();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar repositório JSON", e);
        }
    }

    @Override
    public List<Habito> findAll() {
        return new ArrayList<>(habitos);
    }

    @Override
    public Optional<Habito> findById(String id) {
        return habitos.stream().filter(h -> h.getId().equals(id)).findFirst();
    }

    @Override
    public Habito save(Habito habito) {
        findById(habito.getId()).ifPresent(habitos::remove);
        habitos.add(habito);
        persist();
        return habito;
    }

    @Override
    public void deleteById(String id) {
        findById(id).ifPresent(h -> {
            habitos.remove(h);
            persist();
        });
    }

    @Override
    public void load() {
        try {
            List<Habito> lista = mapper.readValue(
                    externalDbFile,
                    new TypeReference<List<Habito>>() {}
            );

            habitos.clear();
            if (lista != null) habitos.addAll(lista);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar hábitos JSON", e);
        }
    }

    @Override
    public void persist() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(externalDbFile, habitos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar hábitos JSON", e);
        }
    }
}
