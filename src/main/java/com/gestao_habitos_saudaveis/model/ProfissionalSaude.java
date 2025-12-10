
package com.gestao_habitos_saudaveis.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("PROFISSIONAL")
public class ProfissionalSaude extends Usuario {
    private String registroProfissional;

    public ProfissionalSaude(){
        super();
    }

    public ProfissionalSaude(String nome, String email, String senha, String registroProfissional) {
        super(null, nome, email, senha);
        this.registroProfissional = registroProfissional;
    }

    public ProfissionalSaude(Long id, String nome, String email, String senha, String registroProfissional) {
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
