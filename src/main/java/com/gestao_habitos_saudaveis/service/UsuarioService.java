package com.gestao_habitos_saudaveis.service;

import com.gestao_habitos_saudaveis.model.Usuario;
import com.gestao_habitos_saudaveis.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import com.gestao_habitos_saudaveis.exception.RecursoNaoEncontradoException;


import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return repository.salvarUsuario(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return repository.listarUsuarios();
    }

    public Usuario buscarPorEmail(String email) {
        Usuario usuario = repository.buscarPorEmail(email);
        if (usuario == null) {
            throw new RecursoNaoEncontradoException("Usuário com email " + email + " não encontrado");
        }
        return usuario;
    }

    public Usuario atualizarUsuario(Usuario usuarioAtualizado) {
        boolean atualizado = repository.atualizarUsuario(usuarioAtualizado);
        if (!atualizado) {
            throw new RecursoNaoEncontradoException("Usuário com email " + usuarioAtualizado.getEmail() + " não encontrado");
        }
        return usuarioAtualizado;
    }

    public void excluirUsuario(String email) {
        boolean removido = repository.excluirUsuario(email);
        if (!removido) {
            throw new RecursoNaoEncontradoException("Usuário com email " + email + " não encontrado");
        }
    }
}
