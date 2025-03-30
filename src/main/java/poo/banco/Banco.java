package poo.banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public final class Banco {

    private final String name;
    private final List<Cliente> clientes;
    private final List<Conta> contas;

    public Banco(String name) {
        this.name = name;
        this.clientes = new ArrayList<>();
        this.contas = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public String getName() {
        return name;
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    public void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            contas.forEach(System.out::println);
        }
    }

    public void removerCliente(String idCliente) {
        Iterator<Cliente> iteratorCliente = clientes.iterator();
        while (iteratorCliente.hasNext()) {
            Cliente c = iteratorCliente.next();
            if (c.getId().equals(idCliente)) {
                iteratorCliente.remove();
                removerContasDoCliente(idCliente);
                System.out.println("Cliente removido com sucesso.");
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }

    private void removerContasDoCliente(String idCliente) {
        contas.removeIf(conta -> conta.getCliente().getId().equals(idCliente));
    }
}
