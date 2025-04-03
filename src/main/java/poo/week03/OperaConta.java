package poo.week03;

import poo.banco.Cliente;
import poo.banco.Conta;
import poo.banco.ContaPoupanca;
import poo.banco.PessoaFisica;

public class OperaConta {

    public static void main(String[] args) {

        // Criação de um cliente do tipo Pessoa Física
        Cliente cliente = new PessoaFisica("Teodoro", "123");

        // Criação de uma conta poupança para o cliente
        Conta contaPoupanca = new ContaPoupanca(cliente);

        // Exibição das informações da conta
        System.out.println(contaPoupanca);

        // Caso seja necessário criar outra conta, certifique-se de que faz sentido no contexto
        // Exemplo: Criando outra conta para o mesmo cliente (se permitido)
        Conta outraContaPoupanca = new ContaPoupanca(cliente);
        System.out.println(outraContaPoupanca);
    }
}