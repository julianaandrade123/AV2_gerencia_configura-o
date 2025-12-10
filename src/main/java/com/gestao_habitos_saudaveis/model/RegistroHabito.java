
package com.gestao_habitos_saudaveis.model;

public class RegistroHabito {
    private String id;
    private Usuario usuario;
    private String idHabito;
    private String data;
    private String observacao;
    private boolean concluido;

    public RegistroHabito() {}

    public RegistroHabito(String id, Usuario usuario, String idHabito, String data, String observacao, boolean concluido) {
        this.id = id;
        this.usuario = usuario;
        this.idHabito = idHabito;
        this.data = data;
        this.observacao = observacao;
        this.concluido = concluido;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getIdHabito() { return idHabito; }
    public void setIdHabito(String idHabito) { this.idHabito = idHabito; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public boolean isConcluido() { return concluido; }
    public void setConcluido(boolean concluido) { this.concluido = concluido; }
}
