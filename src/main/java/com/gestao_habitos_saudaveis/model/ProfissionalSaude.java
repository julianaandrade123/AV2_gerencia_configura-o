package com.gestao_habitos_saudaveis.model;



public class ProfissionalSaude extends Usuario{

    private String registroProfissional;



    public ProfissionalSaude(long id, String nome, String email, String senha, String registroProfissional) {

        super(id, nome, email, senha);

        this.registroProfissional = registroProfissional;

    }



    public String getRegistroProfissional() {

        return registroProfissional;

    }



    public void setRegistroProfissional(String registroProfissional) {

        this.registroProfissional = registroProfissional;

    }

}