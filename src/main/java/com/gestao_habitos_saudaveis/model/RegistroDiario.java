package com.gestao_habitos_saudaveis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistroDiario {

    private Long id;
    private Usuario usuario;
    private String data;
    private List<RegistroHabito> habitos = new ArrayList<>();

    public RegistroDiario() {}

    public RegistroDiario(Long id, Usuario usuario, String data) {
        this.id = id;
        this.usuario = usuario;
        this.data = data;
    }

    public void adicionarHabito(RegistroHabito registroHabito) {
        this.habitos.add(registroHabito);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public List<RegistroHabito> getHabitos() { return habitos; }
    public void setHabitos(List<RegistroHabito> habitos) { this.habitos = habitos; }
}
