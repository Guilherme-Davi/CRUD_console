package model;

import java.time.LocalDate;

public class Filme {

    private int id;
    private String titulo;
    private String descricao;
    private LocalDate dataConclusao; // data pra assistir (ou data que assistiu)

    public Filme() {
    }

    public Filme(String titulo, String descricao, LocalDate dataConclusao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataConclusao = dataConclusao;
    }

    public int getId() {
        return id;
    }

    // id só é setado pelo repositório
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String toString() {
        return "Filme {" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataConclusao=" + dataConclusao +
                '}';
        }
    }