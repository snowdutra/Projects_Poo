package poo.cinema;

public class ExemploConta {

    public static void main(String[] args) {
        
        Conta c1 = new Conta();
        c1.setSaldo(50);
        // erro, pois a variavel eh final
        // c1.setLimite(1000);

    }
    
}
