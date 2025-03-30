package poo.banco;

import java.util.UUID;

public abstract class Conta {

    private final String id = UUID.randomUUID().toString();
    protected double saldo;
    protected final Cliente cliente;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0;
    }

    public abstract void sacar(double valor);

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        }
    }

    public String getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "[" + this.id + "]: " + this.saldo + " | Cliente: " + cliente.getName();
    }
}
