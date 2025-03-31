package poo.banco;

public class ContaCorrente extends Conta {

    private double limite;

    public ContaCorrente(Cliente cliente) {
        this(cliente, 100000.00); 
    }

    public ContaCorrente(Cliente cliente, double limite) {
        super(cliente);
        this.limite = limite;
    }

    @Override
    public void sacar(double valor) {
        if (valor < 0) {
            throw new RuntimeException("Saque negativo");
        }
        if (valor <= (saldo + limite)) {
            saldo -= valor;
        } else {
            throw new RuntimeException("Limite excedido");
        }
    }
}

