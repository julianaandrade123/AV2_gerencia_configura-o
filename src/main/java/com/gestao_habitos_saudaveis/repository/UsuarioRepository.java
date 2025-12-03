package com.gestao_habitos_saudaveis.repository;

import com.gestao_habitos_saudaveis.exception.DadosInvalidosException;
import com.gestao_habitos_saudaveis.model.Usuario;
import com.gestao_habitos_saudaveis.util.JsonUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    private final List<Usuario> usuarios;
    private long idCounter;
    private final String arquivoJson = "usuarios.json";

    public UsuarioRepository() {
        this.usuarios = JsonUtil.carregarUsuarios(arquivoJson);
        this.idCounter = usuarios.stream()
                .mapToLong(Usuario::getId)
                .max()
                .orElse(0) + 1;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        if (buscarPorEmail(usuario.getEmail()) != null)
            throw new DadosInvalidosException("Email já cadastrado: " + usuario.getEmail());

        if (usuario.getId() == null) usuario.setId(idCounter++);
        usuarios.add(usuario);
        JsonUtil.salvarUsuarios(usuarios, arquivoJson);
        return usuario;
    }

    public List<Usuario> listarUsuarios() { return new ArrayList<>(usuarios); }

    public Usuario buscarPorEmail(String email) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public boolean atualizarUsuario(Usuario usuarioAtualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getEmail().equals(usuarioAtualizado.getEmail())) {
                usuarios.set(i, usuarioAtualizado);
                JsonUtil.salvarUsuarios(usuarios, arquivoJson);
                return true;
            }
        }
        return false;
    }

    public boolean excluirUsuario(String email) {
        boolean removido = usuarios.removeIf(u -> u.getEmail().equals(email));
        if (removido) JsonUtil.salvarUsuarios(usuarios, arquivoJson);
        return removido;
    }

    public Usuario buscarPorId(Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
