package poo.banco;

import java.util.Scanner;

public class Terminal {

    private Scanner scanner;
    private Banco banco;

    public void run() {
        this.scanner = new Scanner(System.in);
        this.banco = new Banco("Mogi International Bank");
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("exit")) {
                break;
            } else if (line.equalsIgnoreCase("help")) {
                printHelp();
            } else if (line.equals("1")) {
                createCustomer();
            } else if (line.equals("2")) {
                listCustomers();
            } else if (line.equals("3")) {
                createAccount();
            }
        }
        scanner.close();
        System.out.println("bye bye!");
    }

    private void listCustomers() {
        System.out.println("Clientes:");
        banco.getClientes().forEach(c -> System.out.println(c));
    }

    private void createCustomer() {
        System.out.print("Nome: ");
        String name = scanner.nextLine().trim();

        System.out.print("Tipo Fisica|Juridica [f|j]: ");
        String tipo = scanner.nextLine().trim();

        try {
            Cliente cliente;
            if (tipo.equals("f")) {
                System.out.print("CPF: ");
                String cpf = scanner.nextLine().trim();
                cliente = new PessoaFisica(name, cpf);
            } else if (tipo.equals("j")) {
                System.out.print("CNPJ: ");
                String cnpj = scanner.nextLine().trim();
                cliente = new PessoaJuridica(name, cnpj);
            } else {
                throw new RuntimeException("Tipo inválido!");
            }
            banco.getClientes().add(cliente);
            System.out.println("Cliente criado com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createAccount() {
        System.out.print("Informe o id do cliente: ");
        String id = scanner.nextLine().trim();
        Cliente cliente = banco.getClienteById(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        Conta conta = new Conta();
        cliente.getContas().add(conta);
        System.out.println("Conta criada com sucesso!");
    }

    private void printHelp() {
        StringBuilder help = new StringBuilder();
        help.append("\n  1. criar cliente");
        help.append("\n  2. listar clientes");
        help.append("\n  3. criar conta");
        System.out.println(help.toString());
    }
}

