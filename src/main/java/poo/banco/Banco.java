package poo.banco;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String name;
    private List<Cliente> clientes;
    private List<Conta> contas;

    public Banco(String name) {
        this.name = name;
        this.clientes = new ArrayList<>();
        this.contas = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void removerCliente(Cliente cliente) {
        this.clientes.remove(cliente);
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        clientes.forEach(cliente -> System.out.println(cliente.getName()));
    }

    public List<Cliente> getClientes() {
        return this.clientes;
    }

    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
    }

    public void removerConta(Conta conta) {
        this.contas.remove(conta);
    }

    public void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        contas.forEach(conta -> System.out.println(conta));
    }

    public List<Conta> getContas() {
        return this.contas;
    }

    public boolean cpfJaCadastrado(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente instanceof PessoaFisica) {
                PessoaFisica pf = (PessoaFisica) cliente;
                if (pf.getCpf().equals(cpf)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean cnpjJaCadastrado(String cnpj) {
        for (Cliente cliente : clientes) {
            if (cliente instanceof PessoaJuridica) {
                PessoaJuridica pj = (PessoaJuridica) cliente;
                if (pj.getCnpj().equals(cnpj)) {
                    return true;
                }
            }
        }
        return false;
    }
}