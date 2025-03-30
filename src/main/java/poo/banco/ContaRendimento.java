package poo.banco;

public class ContaRendimento extends Conta {

    private final double taxaRendimento;

    public ContaRendimento(Cliente cliente, double taxaRendimento) {
        super(cliente);
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public void sacar(double valor) {
        throw new RuntimeException("Conta Rendimento não permite saques.");
    }

    public void aplicarRendimento() {
        double rendimento = saldo * taxaRendimento;
        saldo += rendimento;
        transacoes.add(new Transacao("RENDIMENTO", rendimento));
    }

    @Override
    public String toString() {
        return super.toString() + " (Conta Rendimento)";
    }
}
