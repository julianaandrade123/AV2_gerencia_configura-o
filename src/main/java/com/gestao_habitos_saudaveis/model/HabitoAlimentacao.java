
package com.gestao_habitos_saudaveis.model;

public class HabitoAlimentacao extends Habito {

    private String titulo;
    private int caloriasMeta;

    public HabitoAlimentacao() {}

    public HabitoAlimentacao(String id, String nome, String descricao, boolean ativo,
                             String titulo, int caloriasMeta) {
        super(id, nome, descricao, ativo);
        this.titulo = titulo;
        this.caloriasMeta = caloriasMeta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCaloriasMeta() {
        return caloriasMeta;
    }

    public void setCaloriasMeta(int caloriasMeta) {
        this.caloriasMeta = caloriasMeta;
    }

    @Override
    public String resumo() {
        return String.format("%s - meta calorias: %d",
                getNome(), caloriasMeta);
    }
}
