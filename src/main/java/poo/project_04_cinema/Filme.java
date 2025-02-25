package poo.project_04_cinema;

import java.util.List;

public class Filme{

    private String titulo;
    private int duracao;
    private String classificacao;
    // encapsualmento
    private Genero genero;
    private List<Ator> atores;

    public String getTitulo() {
        return titulo;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public int getDuracao() {
        return duracao;
    }

    public Genero getGenero() {
        return genero;
    }


}