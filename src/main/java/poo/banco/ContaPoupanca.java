package poo.banco;

public class ContaPoupanca extends Conta {

    private double taxaRendimento;

    public ContaPoupanca(Cliente cliente, double taxaRendimento) {
        super(cliente);
        if (taxaRendimento < 0) {
            throw new IllegalArgumentException("A taxa de rendimento não pode ser negativa.");
        }
        this.taxaRendimento = taxaRendimento / 100;
    }

    @Override
    public void sacar(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor do saque não pode ser negativo.");
        }
        if (valor > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        saldo -= valor;
        adicionarTransacao(new Transacao("Saque", valor));
    }

    @Override
    public void depositar(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor do depósito não pode ser negativo.");
        }
        saldo += valor;
        adicionarTransacao(new Transacao("Depósito", valor));
    }

    public void aplicarRendimento() {
        saldo += saldo * taxaRendimento;
    }

    @Override
    public String toString() {
        return super.toString() + " (Conta Poupança - Taxa de Rendimento: " + (taxaRendimento * 100) + "%)";
    }
}