package com.gestao_habitos_saudaveis.model;

public class HabitoAlimentacao extends Habito {
    private int caloriasMeta;

    public HabitoAlimentacao() {
        this.categoria = "alimentacao";
    }

    public int getCaloriasMeta() { return caloriasMeta; }
    public void setCaloriasMeta(int caloriasMeta) { this.caloriasMeta = caloriasMeta; }

    @Override
    public String resumo() {
        return String.format("%s - meta calorias: %d", getTitulo(), getCaloriasMeta());
    }
}
