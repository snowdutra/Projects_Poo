package poo.banco;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta {

    protected Cliente cliente;
    protected double saldo;
    protected List<Transacao> transacoes;
    protected String id;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0.0;
        this.transacoes = new ArrayList<>();
        this.id = java.util.UUID.randomUUID().toString();
    }

    public abstract void sacar(double valor);
    public abstract void depositar(double valor);

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public void setSaldo(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("O saldo não pode ser negativo.");
        }
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return cliente.getName() + " (Saldo: R$ " + saldo + ")";
    }
}