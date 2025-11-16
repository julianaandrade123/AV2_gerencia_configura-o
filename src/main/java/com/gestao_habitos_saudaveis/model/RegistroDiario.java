package com.gestao_habitos_saudaveis.model;

import java.util.ArrayList;
import java.util.List;

public class RegistroDiario {
    private Long id;
    private Long usuarioId;
    private String data;
    private List<RegistroHabito> registros = new ArrayList<>();

    public RegistroDiario() {}

    public RegistroDiario(Long id, Long usuarioId, String data) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.data = data;
    }

    public void adicionarRegistro(RegistroHabito r) {
        this.registros.add(r);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public List<RegistroHabito> getRegistros() { return registros; }
    public void setRegistros(List<RegistroHabito> registros) { this.registros = registros; }
}
