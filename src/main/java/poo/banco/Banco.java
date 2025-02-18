package poo.banco;


import java.util.List;

public class Banco {
    private final String name;
    private List<Cliente> clientes;

    public Banco(String name) {
        this.name = name;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public String getName() {
        return name;
    }
    
}
