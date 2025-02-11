package poo.project_02;

public class Conta {
    private String id;
    private double saldo = 0;

    public void depositar(double valor) {
        this.saldo += valor;
    }
    /* realiza um saque da conta do usúario
     * restrito ao saldo da conta e não permite 
     * valores negativos
     */
    public void sacar(float valor) {
       if (saldo >= valor) {
        this.saldo -= valor;
       }
    }

    public double getSaldo() {
        return this.saldo;
    }

    public String getId() { 
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}