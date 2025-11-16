package com.gestao_habitos_saudaveis.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.LocalDate;
import java.util.UUID;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "tipo"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = HabitoAlimentacao.class, name = "alimentacao"),
    @JsonSubTypes.Type(value = HabitoSono.class, name = "sono"),
    @JsonSubTypes.Type(value = HabitoAtividadeFisica.class, name = "atividade")
})
public abstract class Habito {

    protected String id;
    protected String titulo;
    protected String descricao;
    protected String categoria;
    protected LocalDate dataCriacao;
    protected boolean ativo;

    public Habito() {
        this.id = UUID.randomUUID().toString();
        this.dataCriacao = LocalDate.now();
        this.ativo = true;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public abstract String resumo();
}
