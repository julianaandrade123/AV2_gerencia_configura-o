
package com.gestao_habitos_saudaveis.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("sono")
public class HabitoSono extends Habito {

    private double horasMeta;

    public HabitoSono() {
    }

    public double getHorasMeta() {
        return horasMeta;
    }

    public void setHorasMeta(double horasMeta) {
        this.horasMeta = horasMeta;
    }

    @Override
    public String resumo() {
        return String.format("%s - meta horas: %.1f",
                getNome(), horasMeta);
    }
}
