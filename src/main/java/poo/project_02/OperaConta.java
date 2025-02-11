package poo.project_02;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OperaConta {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Conta conta1 = new Conta();
        Conta conta2 = new Conta();
        Conta conta3 = new Conta();
        
        System.out.print("Digite o valor do deposito para a conta 1: R$ ");
        double valorDeposito1 = scanner.nextDouble();
        System.out.print("Digite o valor do saque para a conta 1: R$ ");
        double valorSaque1 = getValorSaque(scanner, valorDeposito1);
        
        System.out.println("Depositando R$ " + valorDeposito1 + " na conta 1");
        conta1.depositar(valorDeposito1);
        
        System.out.println("Sacando R$ " + valorSaque1 + " da conta 1");
        conta1.sacar((float) valorSaque1);
        
        System.out.println("Saldo restante na conta 1: R$ " + conta1.getSaldo());
        
        System.out.print("Digite o valor do deposito para a conta 2: R$ ");
        double valorDeposito2 = scanner.nextDouble();
        System.out.print("Digite o valor do saque para a conta 2: R$ ");
        double valorSaque2 = getValorSaque(scanner, valorDeposito2);
        
        System.out.println("Depositando R$ " + valorDeposito2 + " na conta 2");
        conta2.depositar(valorDeposito2);
        
        System.out.println("Sacando R$ " + valorSaque2 + " da conta 2");
        conta2.sacar((float) valorSaque2);
        
        System.out.println("Saldo restante na conta 2: R$ " + conta2.getSaldo());
        
        System.out.print("Digite o valor do deposito para a conta 3: R$ ");
        double valorDeposito3 = scanner.nextDouble();
        System.out.print("Digite o valor do saque para a conta 3: R$ ");
        double valorSaque3 = getValorSaque(scanner, valorDeposito3);
        
        System.out.println("Depositando R$ " + valorDeposito3 + " na conta 3");
        conta3.depositar(valorDeposito3);
        
        System.out.println("Sacando R$ " + valorSaque3 + " da conta 3");
        conta3.sacar((float) valorSaque3);
        
        System.out.println("Saldo restante na conta 3: R$ " + conta3.getSaldo());
    }
    
    private static double getValorSaque(Scanner scanner, double valorDeposito) {
        double valorSaque = 0;
        while (true) {
            try {
                valorSaque = scanner.nextDouble();
                if (valorSaque > valorDeposito) {
                    System.out.println("Valor do saque maior que o deposito! Tente novamente");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Valor invalido! Tente novamente");
                scanner.nextLine();
            }
        }
        return valorSaque;
    }
}

