package poo.project_05_cinema;

public class Genero {

    private String nome;

    public Genero(String nome) {
        this.nome = nome;
    }

    public static Genero registrar(String nome) {
        return new Genero(nome);
    }

    public String getNome() {
        return nome;
    }
    
}