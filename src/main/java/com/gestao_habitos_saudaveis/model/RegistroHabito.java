package com.gestao_habitos_saudaveis.model;

public class RegistroHabito {
    private Long id;
    private Usuario usuario;
    private Habito habito;
    private String data;
    private String observacao;
    private boolean concluido;

    public RegistroHabito() {}

    public RegistroHabito(Long id, Usuario usuario, Habito habito, String data, String observacao, boolean concluido) {
        this.id = id;
        this.usuario = usuario;
        this.habito = habito;
        this.data = data;
        this.observacao = observacao;
        this.concluido = concluido;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Habito getHabito() { return habito; }
    public void setHabito(Habito habito) { this.habito = habito; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public boolean isConcluido() { return concluido; }
    public void setConcluido(boolean concluido) { this.concluido = concluido; }
}
