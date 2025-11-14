package com.gestao_habitos_saudaveis.repository;

import java.util.ArrayList;
import java.util.List;

import com.gestao_habitos_saudaveis.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
    private final List<Usuario> usuarios = new ArrayList<>();

    public Usuario salvarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("O usuário " + usuario.getNome() + " foi cadastrado");
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public Usuario buscarPorEmail(String email){
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null; // se não encontrar
    }
    public boolean atualizarUsuario(Usuario usuarioAtualizado){
        for (int i = 0; i < usuarios.size(); i++){
            if (usuarios.get(i).getEmail().equals(usuarioAtualizado.getEmail())){
                usuarios.set(i, usuarioAtualizado);
                System.out.println("O usuário " + usuarioAtualizado.getNome() + " foi atualizado");
                return true;
            }
        }
        return false;
    }

    public boolean excluirUsuario(String email){
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getEmail().equals(email)) {
                usuarios.remove(i);
                System.out.println("Usuário " + email + " removido");
                return true;
            }
        }
        return false;
    }
}