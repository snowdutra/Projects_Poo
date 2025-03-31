package poo.banco;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Conta {

    private final String id = UUID.randomUUID().toString();
    protected double saldo;
    protected final Cliente cliente;
    private final List<Transacao> transacoes = new ArrayList<>(); // Lista de transa es

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            transacoes.add(new Transacao("Deposito", valor));
        }
    }

    public abstract void sacar(double valor);

    public void listarTransacoes() {
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação encontrada.");
        } else {
            transacoes.forEach(System.out::println);
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

