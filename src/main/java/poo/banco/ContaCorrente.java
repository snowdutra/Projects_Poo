package poo.banco;

public class ContaCorrente extends Conta {

    private double limite;

    public ContaCorrente(Cliente cliente) {
        super(cliente);
        this.limite = 0;
    }

    public ContaCorrente(Cliente cliente, double limite) {
        super(cliente);
        if (limite < 0) {
            throw new IllegalArgumentException("O limite não pode ser negativo.");
        }
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    @Override
    public void sacar(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor do saque não pode ser negativo.");
        }
        if (valor > saldo) {
            throw new RuntimeException("Erro: Saldo insuficiente para realizar o saque.");
        }
        if (valor > limite) {
            throw new RuntimeException("Erro: O valor do saque excede o limite permitido.");
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

    @Override
    public String toString() {
        return super.toString() + " (Conta Corrente - Limite de Saque: R$ " + limite + ")";
    }
}