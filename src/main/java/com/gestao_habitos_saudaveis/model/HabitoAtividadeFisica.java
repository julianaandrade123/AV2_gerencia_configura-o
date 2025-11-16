package com.gestao_habitos_saudaveis.model;

public class HabitoAtividadeFisica extends Habito {
    private String tipoAtividade;
    private int duracaoMinutos;

    public HabitoAtividadeFisica() {
        this.categoria = "atividade";
    }

    public String getTipoAtividade() { return tipoAtividade; }
    public void setTipoAtividade(String tipoAtividade) { this.tipoAtividade = tipoAtividade; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(int duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }

    @Override
    public String resumo() {
        return String.format("%s - %s por %d min", getTitulo(), tipoAtividade, duracaoMinutos);
    }
}
