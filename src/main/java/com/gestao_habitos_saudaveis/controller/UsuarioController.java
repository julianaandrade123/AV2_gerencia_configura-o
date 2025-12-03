package com.gestao_habitos_saudaveis.controller;

import com.gestao_habitos_saudaveis.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestao_habitos_saudaveis.model.Usuario;
import com.gestao_habitos_saudaveis.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario>criarUsuario(@RequestBody Usuario usuario) {
        Usuario criado = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>>listarTodos(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Usuario> atualizar(
            @PathVariable String email,
            @RequestBody Usuario usuarioAtualizado) {

        usuarioAtualizado.setEmail(email);

        Usuario usuario = usuarioService.atualizarUsuario(usuarioAtualizado);
        return ResponseEntity.ok(usuario);
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> excluir(@PathVariable String email) {
        usuarioService.excluirUsuario(email);
        return ResponseEntity.noContent().build();
    }
}


