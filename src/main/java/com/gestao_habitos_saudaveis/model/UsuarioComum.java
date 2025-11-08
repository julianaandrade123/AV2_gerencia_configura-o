package com.gestao_habitos_saudaveis.model;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("COMUM")


public class UsuarioComum extends Usuario {
    private int nivelMotivacao;


    public UsuarioComum(Long id, String nome, String email, String senha, int nivelMotivacao) {
        super(id, nome, email, senha);
        this.nivelMotivacao = nivelMotivacao;

    }


    public int getNivelMotivacao() {
        return nivelMotivacao;

    }


    public void setNivelMotivacao(int nivelMotivacao) {
        this.nivelMotivacao = nivelMotivacao;

    }
}