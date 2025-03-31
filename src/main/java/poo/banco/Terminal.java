package poo.banco;

import java.util.Scanner;

public class Terminal {

    private Scanner scanner;
    private Banco banco;

    public void run() {
        try {
            this.scanner = new Scanner(System.in);
            this.banco = new Banco("ESPM Bank");
            System.out.println("\n" + banco.getName() + "\n");

            Cliente atualCliente = null;
            Conta atualConta = null;

            while (true) {
                String prompt = (atualCliente == null) ? "" : atualCliente.getName() + " > ";
                System.out.print(prompt);
                String line = scanner.nextLine().trim();

                switch (line) {
                    case "exit":
                        return;
                    case "help":
                        printHelp();
                        break;
                    case "1":
                        atualCliente = createCustomer();
                        if (atualCliente != null) {
                            banco.adicionarCliente(atualCliente);
                        }
                        break;
                    case "2":
                        banco.listarClientes();
                        break;
                    case "3":
                        if (atualCliente == null) {
                            System.err.println("Erro: Nenhum cliente selecionado.");
                            break;
                        }
                        atualConta = createAccount(atualCliente);
                        if (atualConta != null) {
                            banco.adicionarConta(atualConta);
                        }
                        break;
                    case "4":
                        if (atualConta == null) {
                            System.err.println("Erro: Nenhuma conta selecionada.");
                            break;
                        }
                        realizarDeposito(atualConta);
                        break;
                    case "5":
                        if (atualConta == null) {
                            System.err.println("Erro: Nenhuma conta selecionada.");
                            break;
                        }
                        realizarSaque(atualConta);
                        break;
                    case "6":
                        if (atualConta == null) {
                            System.err.println("Erro: Nenhuma conta selecionada.");
                            break;
                        }
                        atualConta.listarTransacoes();
                        break;
                    default:
                        System.err.println("Comando inválido.");
                        break;
                }
            }
        } finally {
            if (scanner != null) scanner.close();
            System.out.println("bye bye!");
        }
    }

    private Cliente createCustomer() {
        System.out.print("Nome: ");
        String name = scanner.nextLine().trim();

        System.out.print("Tipo [f] Física | [j] Jurídica: ");
        String tipo = scanner.nextLine().trim().toLowerCase();

        if (tipo.equals("f")) {
            return new PessoaFisica(name, solicitarCpf());
        } else if (tipo.equals("j")) {
            System.out.print("CNPJ: ");
            return new PessoaJuridica(name, scanner.nextLine().trim());
        } else {
            System.err.println("Tipo inválido. Cliente não criado.");
            return null;
        }
    }

    private String solicitarCpf() {
        String cpf;
        do {
            System.out.print("CPF: ");
            cpf = scanner.nextLine().trim();
            if (!Util.isCpf(cpf)) {
                System.out.println("CPF inválido. Tente novamente.");
            }
        } while (!Util.isCpf(cpf));
        return cpf;
    }

    private Conta createAccount(Cliente cliente) {
        System.out.print("Tipo [(P) Poupança | (C) Corrente | (R) Rendimento]: ");
        String tipo = scanner.nextLine().trim().toLowerCase();

        switch (tipo) {
            case "p":
                return new ContaPoupanca(cliente);
            case "c":
                return criarContaCorrente(cliente);
            case "r":
                return criarContaRendimento(cliente);
            default:
                System.err.println("Erro: Tipo de conta inválido.");
                return null;
        }
    }

    private ContaCorrente criarContaCorrente(Cliente cliente) {
        System.out.print("Deseja limite? (S/N): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
            System.out.print("Informe o limite: ");
            double limite = Double.parseDouble(scanner.nextLine().trim());
            return new ContaCorrente(cliente, limite);
        }
        return new ContaCorrente(cliente);
    }

    private ContaRendimento criarContaRendimento(Cliente cliente) {
        System.out.print("Informe a taxa de rendimento (%): ");
        double taxa = Double.parseDouble(scanner.nextLine().trim());
        return new ContaRendimento(cliente, taxa);
    }

    private void realizarDeposito(Conta conta) {
        System.out.print("Valor do depósito: ");
        double valor = Double.parseDouble(scanner.nextLine().trim());
        conta.depositar(valor);
        System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
    }

    private void realizarSaque(Conta conta) {
        System.out.print("Valor do saque: ");
        double valor = Double.parseDouble(scanner.nextLine().trim());
        try {
            conta.sacar(valor);
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } catch (RuntimeException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private void printHelp() {
        System.out.println("\n 1. Criar cliente");
        System.out.println(" 2. Listar clientes");
        System.out.println(" 3. Criar conta");
        System.out.println(" 4. Depositar");
        System.out.println(" 5. Sacar");
        System.out.println(" 6. Listar transações");
    }
}

