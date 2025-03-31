package poo.banco;

public class ContaRendimento extends Conta {

    private final double taxaRendimento;

    public ContaRendimento(Cliente cliente, double taxaRendimento) {
        super(cliente);
        this.taxaRendimento = taxaRendimento / 100;
    }

    @Override
    public void sacar(double valor) {
        throw new UnsupportedOperationException("Conta Rendimento não permite saques.");
    }

    public void aplicarRendimento() {
        double rendimento = saldo * taxaRendimento;
        depositar(rendimento);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Conta Rendimento - Taxa: %.2f%%)", taxaRendimento * 100);
    }
}

