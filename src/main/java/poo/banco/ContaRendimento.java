package poo.banco;

public class ContaRendimento extends Conta {

    private final double taxaRendimento;

    public ContaRendimento(Cliente cliente, double taxaRendimento) {
        super(cliente);
        if (taxaRendimento < 0) {
            throw new IllegalArgumentException("A taxa de rendimento não pode ser negativa.");
        }
        this.taxaRendimento = taxaRendimento / 100; 
    }

    @Override
    public void sacar(double valor) {
        // Lança uma exceção para qualquer tentativa de saque
        throw new UnsupportedOperationException("Conta Rendimento não permite saques.");
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
        double rendimento = saldo * taxaRendimento;
        depositar(rendimento);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Conta Rendimento - Taxa de Rendimento: %.2f%%)", taxaRendimento * 100);
    }
}