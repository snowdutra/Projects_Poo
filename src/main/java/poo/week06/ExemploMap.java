package poo.week06;

import java.util.HashMap;
import java.util.Map;

import poo.banco.Cliente;
import poo.banco.PessoaFisica;

public class ExemploMap {

    public static void main(String[] args) {
        // CPFs corrigidos (apenas números)
        PessoaFisica pf1 = new PessoaFisica("01", "Marcelo", "31412312312");
        PessoaFisica pf2 = new PessoaFisica("01", "Marcelo", "31412312312");
        PessoaFisica pf3 = new PessoaFisica("02", "Marcelo", "31412312312");
        PessoaFisica pf4 = new PessoaFisica("02", "Teodoro", "33344455566");
        
        // Mapeamento de clientes por ID
        Map<String, Cliente> clientesById = new HashMap<>();
        clientesById.put(pf1.getId(), pf1);
        clientesById.put(pf2.getId(), pf1); // Sobrescreve o valor anterior
        clientesById.put(pf3.getId(), pf3);
        clientesById.put(pf4.getId(), pf4);
        System.out.println(clientesById);

        // Mapeamento de clientes por CPF
        Map<String, Cliente> clientesByCpf = new HashMap<>();
        clientesByCpf.put(pf1.getCpf(), pf1);
        clientesByCpf.put(pf2.getCpf(), pf1); // Sobrescreve o valor anterior
        clientesByCpf.put(pf3.getCpf(), pf3);
        clientesByCpf.put(pf4.getCpf(), pf4);
        System.out.println(clientesByCpf);

        // Busca cliente pelo CPF
        System.out.println(clientesByCpf.get("33344455566"));
    }
}