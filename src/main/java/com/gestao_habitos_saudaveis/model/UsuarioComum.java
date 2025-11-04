package com.gestao_habitos_saudaveis.model;



public class UsuarioComum extends Usuario {

    private int nivelMotivacao;


    public UsuarioComum(long id, String nome, String email, String senha, int nivelMotivacao) {

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