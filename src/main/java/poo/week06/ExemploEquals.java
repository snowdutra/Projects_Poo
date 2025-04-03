package poo.week06;

import poo.banco.PessoaFisica;

public class ExemploEquals {

    public static void main(String[] args) {
        PessoaFisica pf1 = new PessoaFisica("01", "Marcelo", "31412312312"); // CPF corrigido
        PessoaFisica pf2 = new PessoaFisica("01", "Marcelo", "31412312312"); // CPF corrigido
        PessoaFisica pf3 = new PessoaFisica("02", "Marcelo", "31412312312"); // CPF corrigido
        System.out.println(pf1 == pf2); // false: compara end. memoria
        System.out.println(pf1.equals(pf2)); // true
        System.out.println(pf1.equals(pf3)); // false
        PessoaFisica pf4 = new PessoaFisica("02", "Teodoro", "33344455566"); // CPF corrigido
        System.out.println(pf3.equals(pf4)); // true
    }
}