package poo.week06;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import poo.banco.Cliente;
import poo.banco.PessoaFisica;

public class ExemploSet {

    public static void main(String[] args) {
        PessoaFisica pf1 = new PessoaFisica("01", "Marcelo", "31412312312");
        PessoaFisica pf2 = new PessoaFisica("01", "Marcelo", "31412312312");
        PessoaFisica pf3 = new PessoaFisica("02", "Marcelo", "31412312312");
        PessoaFisica pf4 = new PessoaFisica("02", "Teodoro", "33344455566");
        
        Set<Cliente> clientes = new HashSet<>();
        clientes.add(pf1);
        clientes.add(pf2);
        clientes.add(pf3);
        clientes.add(pf4);
        System.out.println(Arrays.toString(clientes.toArray()));

    }

}

