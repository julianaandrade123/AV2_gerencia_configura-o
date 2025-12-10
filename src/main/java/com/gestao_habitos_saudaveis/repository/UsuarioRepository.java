
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

    public UsuarioRepository() {
        List<Usuario> carregados;
        try {
            carregados = JsonUtil.carregarUsuarios(); // agora sem argumento
        } catch (Exception e) {
            carregados = new ArrayList<>();
            e.printStackTrace();
        }
        this.usuarios = carregados;

        this.idCounter = usuarios.stream()
                .filter(u -> u.getId() != null)
                .mapToLong(Usuario::getId)
                .max()
                .orElse(0) + 1;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        if (buscarPorEmail(usuario.getEmail()) != null)
            throw new DadosInvalidosException("Email já cadastrado: " + usuario.getEmail());

        if (usuario.getId() == null) usuario.setId(idCounter++);
        usuarios.add(usuario);

        try {
            JsonUtil.salvarUsuarios(usuarios); // agora sem argumento
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

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
                try {
                    JsonUtil.salvarUsuarios(usuarios); // sem argumento
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    public boolean excluirUsuario(String email) {
        boolean removido = usuarios.removeIf(u -> u.getEmail().equals(email));
        if (removido) {
            try {
                JsonUtil.salvarUsuarios(usuarios); // sem argumento
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return removido;
    }

    public Usuario buscarPorId(Long id) {
        if (id == null) return null;
        return usuarios.stream()
                .filter(u -> id.equals(u.getId()))
                .findFirst()
                .orElse(null);
    }
}
