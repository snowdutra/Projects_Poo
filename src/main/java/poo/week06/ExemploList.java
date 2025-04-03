package poo.week06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import poo.banco.Cliente;
import poo.banco.PessoaFisica;

public class ExemploList {

    public static void main(String[] args) {

        // CPFs corrigidos (apenas números)
        PessoaFisica pf1 = new PessoaFisica("01", "Marcelo", "31412312312");
        PessoaFisica pf2 = new PessoaFisica("01", "Marcelo", "31412312312");
        PessoaFisica pf3 = new PessoaFisica("02", "Marcelo", "31412312312");
        PessoaFisica pf4 = new PessoaFisica("02", "Teodoro", "33344455566");
        
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(pf1);
        clientes.add(pf2);
        clientes.add(pf3);
        clientes.add(pf4);
        
        // Exibe a lista de clientes
        System.out.println(Arrays.toString(clientes.toArray()));
    }
}