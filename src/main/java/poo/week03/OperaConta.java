package poo.week03;

import poo.banco.Cliente;
import poo.banco.Conta;
import poo.banco.ContaPoupanca;
import poo.banco.PessoaFisica;

public class OperaConta {

    public static void main(String[] args) {

        // CPF corrigido para conter 11 dígitos
        Cliente cliente = new PessoaFisica("teodoro", "12345678901");

        // Adicionando a taxa de rendimento ao criar a ContaPoupanca
        Conta c1 = new ContaPoupanca(cliente, 1.5); // Taxa de rendimento de 1.5%
        System.out.println(c1);

        Conta c2 = new ContaPoupanca(cliente, 2.0); // Taxa de rendimento de 2.0%
        System.out.println(c2);
        
    }
    
}