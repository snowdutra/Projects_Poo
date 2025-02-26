package poo.project_05_cinema;

import java.util.UUID;

public class Conta {
    
    private String id = UUID.randomUUID().toString();
    private double saldo = 0;
    private final double LIMITE = 100;

    public String getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
