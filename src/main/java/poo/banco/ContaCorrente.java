package poo.banco;

public class ContaCorrente extends Conta {

    private double limite;

    public ContaCorrente(Cliente cliente) {
        super(cliente);
        this.limite = 0;
    }

    public ContaCorrente(Cliente cliente, double limite) {
        super(cliente);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    @Override
    public void sacar(double valor) {
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
        saldo += valor;
        adicionarTransacao(new Transacao("Depósito", valor));
    }

    @Override
    public String toString() {
        return super.toString() + " (Conta Corrente - Limite de Saque: R$ " + limite + ")";
    }
}

