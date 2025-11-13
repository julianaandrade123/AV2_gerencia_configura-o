package com.gestao_habitos_saudaveis.service;

import com.gestao_habitos_saudaveis.model.RegistroDiario;
import com.gestao_habitos_saudaveis.model.RegistroHabito;
import com.gestao_habitos_saudaveis.model.Usuario;
import com.gestao_habitos_saudaveis.model.Habito;
import com.gestao_habitos_saudaveis.repository.RegistroRepository;
import com.gestao_habitos_saudaveis.exception.RecursoNaoEncontradoException;
import java.util.List;

public class RegistroIntegradoService {

    private final RegistroRepository registroRepository;
    private final UsuarioService usuarioService;
    private final HabitoService habitoService;

    public RegistroIntegradoService(RegistroRepository registroRepository, UsuarioService usuarioService, HabitoService habitoService) {
        this.registroRepository = registroRepository;
        this.usuarioService = usuarioService;
        this.habitoService = habitoService;
    }

    public RegistroDiario criarRegistroParaUsuario(Long idUsuario, List<Long> idsHabitos, RegistroDiario registro) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado.");
        }

        for (Long idHabito : idsHabitos) {
            Habito habito = habitoService.buscarHabitoPorId(idHabito);
            if (habito != null) {
                RegistroHabito registroHabito = new RegistroHabito();
                registroHabito.setHabito(habito);
                registro.getHabitos().add(registroHabito);
            }
        }

        registro.setUsuario(usuario);
        registroRepository.salvarRegistroDiario(registro);
        return registro;
    }
}
