package com.gestao_habitos_saudaveis.service;

import com.gestao_habitos_saudaveis.exception.ResourceNotFoundException;
import com.gestao_habitos_saudaveis.exception.BadRequestException;
import com.gestao_habitos_saudaveis.model.Habito;
import com.gestao_habitos_saudaveis.repository.HabitoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitoService {

    private final HabitoRepository repository;

    public HabitoService(HabitoRepository repository) {
        this.repository = repository;
    }

    public List<Habito> listarTodos() {
        return repository.findAll();
    }

    public Habito buscarPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hábito não encontrado: " + id));
    }

    public Habito criar(Habito habito) {
        validar(habito);
        habito.resumo(); // polimorfismo (exemplo de chamada)
        return repository.save(habito);
    }

    public Habito atualizar(String id, Habito atualizado) {
        validar(atualizado);

        Habito existente = buscarPorId(id);

        existente.setTitulo(atualizado.getTitulo());
        existente.setDescricao(atualizado.getDescricao());
        existente.setAtivo(atualizado.isAtivo());

        existente.resumo();

        return repository.save(existente);
    }

    public void deletar(String id) {
        buscarPorId(id); // valida existência
        repository.deleteById(id);
    }

    private void validar(Habito habito) {
        if (habito == null) {
            throw new BadRequestException("O hábito não pode ser nulo.");
        }
        if (habito.getTitulo() == null || habito.getTitulo().isBlank()) {
            throw new BadRequestException("Título é obrigatório.");
        }
    }
}