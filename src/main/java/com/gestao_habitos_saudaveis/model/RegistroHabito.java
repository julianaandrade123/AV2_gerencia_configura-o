package com.gestao_habitos_saudaveis.model;

public class RegistroHabito {
    private Long id;
    private Long usuarioId;
    private Long habitoId;
    private String data;
    private String observacao;
    private boolean concluido;

    public RegistroHabito() {}

    public RegistroHabito(Long id, Long usuarioId, Long habitoId, String data, String observacao, boolean concluido) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.habitoId = habitoId;
        this.data = data;
        this.observacao = observacao;
        this.concluido = concluido;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Long getHabitoId() { return habitoId; }
    public void setHabitoId(Long habitoId) { this.habitoId = habitoId; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
    public boolean isConcluido() { return concluido; }
    public void setConcluido(boolean concluido) { this.concluido = concluido; }
}
