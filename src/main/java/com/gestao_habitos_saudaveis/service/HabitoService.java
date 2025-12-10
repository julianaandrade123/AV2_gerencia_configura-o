
package com.gestao_habitos_saudaveis.service;

import com.gestao_habitos_saudaveis.model.Habito;
import com.gestao_habitos_saudaveis.repository.HabitoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HabitoService {

    private final HabitoRepository repository;

    public HabitoService(HabitoRepository repository) {
        this.repository = repository;
    }

    public List<Habito> listar() {
        return repository.findAll();
    }

    public Habito buscarHabitoPorId(String id) {
        return repository.findById(id).orElse(null);
    }

    public Habito criar(Habito h) {
        if (h.getId() == null || h.getId().isBlank()) {
            h.setId(UUID.randomUUID().toString());
        }
        return repository.save(h);
    }

    public Habito atualizar(String id, Habito h) {
        h.setId(id);
        return repository.save(h);
    }

    public void deletar(String id) {
        repository.deleteById(id);
    }
}
