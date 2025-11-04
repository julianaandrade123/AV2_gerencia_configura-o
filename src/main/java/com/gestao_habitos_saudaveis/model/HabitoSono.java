package com.gestao_habitos_saudaveis.model;

public class HabitoSono extends Habito {
    private double horasMeta;

    public HabitoSono() {
        this.categoria = "sono";
    }

    public double getHorasMeta() { return horasMeta; }
    public void setHorasMeta(double horasMeta) { this.horasMeta = horasMeta; }

    @Override
    public String resumo() {
        return String.format("%s - meta horas: %.1f", getTitulo(), getHorasMeta());
    }
}
