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
        clientes.add(cliente);
    }

    public void removerCliente(Cliente cliente) {
        contas.removeIf(conta -> conta.getCliente().equals(cliente));
        clientes.remove(cliente);
    }

    public void listarClientes() {
        clientes.forEach(System.out::println);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void removerConta(Conta conta) {
        contas.remove(conta);
    }

    public void listarContas() {
        contas.forEach(System.out::println);
    }

    public List<Conta> getContas() {
        return contas;
    }

    public Cliente buscarClientePorChavePix(String chavePix) {
        for (Cliente cliente : clientes) {
            if (cliente instanceof PessoaFisica && ((PessoaFisica) cliente).getCpf().equals(chavePix)) {
                return cliente;
            }
            if (cliente instanceof PessoaJuridica && ((PessoaJuridica) cliente).getCnpj().equals(chavePix)) {
                return cliente;
            }
        }
        throw new IllegalArgumentException("Chave Pix (CPF ou CNPJ) não encontrada.");
    }

    public void transferir(String chavePixOrigem, String chavePixDestino, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser positivo.");
        }

        Cliente clienteOrigem = buscarClientePorChavePix(chavePixOrigem);
        Conta contaOrigem = contas.stream()
            .filter(conta -> conta.getCliente().equals(clienteOrigem))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Conta de origem não encontrada."));

        Cliente clienteDestino = buscarClientePorChavePix(chavePixDestino);
        Conta contaDestino = contas.stream()
            .filter(conta -> conta.getCliente().equals(clienteDestino))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Conta de destino não encontrada."));

        if (contaOrigem.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar a transferência.");
        }

        contaOrigem.sacar(valor);
        contaDestino.depositar(valor);

        contaOrigem.adicionarTransacao(new Transacao("Transferência Enviada para " + clienteDestino.getName(), -valor));
        contaDestino.adicionarTransacao(new Transacao("Transferência Recebida de " + clienteOrigem.getName(), valor));
    }

    public boolean cpfJaCadastrado(String cpf) {
        return clientes.stream()
            .filter(cliente -> cliente instanceof PessoaFisica)
            .map(cliente -> (PessoaFisica) cliente)
            .anyMatch(pessoaFisica -> pessoaFisica.getCpf().equals(cpf));
    }

    public boolean cnpjJaCadastrado(String cnpj) {
        return clientes.stream()
            .filter(cliente -> cliente instanceof PessoaJuridica)
            .map(cliente -> (PessoaJuridica) cliente)
            .anyMatch(pessoaJuridica -> pessoaJuridica.getCnpj().equals(cnpj));
    }
}