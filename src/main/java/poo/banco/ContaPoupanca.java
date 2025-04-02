package poo.banco;

public class ContaPoupanca extends Conta {

    private double taxaRendimento;

    public ContaPoupanca(Cliente cliente, double taxaRendimento) {
        super(cliente);
        this.taxaRendimento = taxaRendimento / 100;
    }

    @Override
    public void sacar(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("Saque negativo");
        } else if (valor > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        saldo -= valor;
        adicionarTransacao(new Transacao("Saque", valor));
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            adicionarTransacao(new Transacao("Depósito", valor));
        } else {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo.");
        }
    }

    public void aplicarRendimento() {
        saldo += saldo * taxaRendimento;
    }

    @Override
    public String toString() {
        return super.toString() + " (Conta Poupança - Taxa de Rendimento: " + (taxaRendimento * 100) + "%)";
    }
}

