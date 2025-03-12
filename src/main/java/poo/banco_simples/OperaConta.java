package poo.banco_simples;

public class OperaConta {

    public static void main(String[] args) {
        
        Conta contaNova = new Conta();
        System.out.println("SeuMini saldo: " + contaNova.getSaldo());

        contaNova.depositar(1000);
        System.out.println("SeuMini saldo: " + contaNova.getSaldo());

        contaNova.sacar(1500);
        System.out.println("SeuMini saldo: " + contaNova.getSaldo());

        Conta contaFlavio = new Conta();
        contaFlavio.depositar(70);
        System.out.println("Flavio [" + contaFlavio.getId() + "] saldo: " + contaFlavio.getSaldo());
    }
    
}
