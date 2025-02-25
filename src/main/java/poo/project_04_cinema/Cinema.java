package poo.project_04_cinema;

public class Cinema {
    
    private Cinema cinema = new Cinema();
    
    // construtor foi escondido 
    private Cinema() {
    }

    public Cinema getInstance() {
        return this.cinema;
    }
}