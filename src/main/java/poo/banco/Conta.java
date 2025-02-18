package poo.banco;

import java.util.Random;

public class Conta {

    private String id;
    private double saldo = 0;

    public Conta() {
        this.id = String.format("%04d", new Random().nextInt(10000));
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        }
    }

    /**
     * Realiza um saque da conta do usuário
     * restrito ao saldo da conta e não permite
     * valores negativos.
     */
    public void sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            this.saldo -= valor;
        }
    } 

    public String getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "[" + this.id + "]: " + this.saldo;
    }
}

