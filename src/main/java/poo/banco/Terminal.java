package poo.banco;

import javax.swing.*;
import java.util.List;

public class Terminal {

    private Banco banco;

    public void run() {
        try {
            this.banco = new Banco("ESPM Bank");
            JOptionPane.showMessageDialog(null, banco.getName(), "Bem-vindo", JOptionPane.INFORMATION_MESSAGE);

            Cliente atualCliente = null;
            Conta atualConta = null;

            while (true) {
                String prompt = (atualCliente == null) ? "" : atualCliente.getName() + " > ";
                String line = JOptionPane.showInputDialog(null, prompt + "Digite um comando (help para ajuda):");

                if (line == null || line.trim().equalsIgnoreCase("exit")) {
                    break;
                }

                switch (line.trim()) {
                    case "help":
                        printHelp();
                        break;
                    case "1": // Criar cliente
                        atualCliente = createCustomer();
                        if (atualCliente != null) {
                            banco.adicionarCliente(atualCliente);
                            JOptionPane.showMessageDialog(null, "Cliente criado com sucesso!");
                        }
                        break;
                    case "2": // Listar clientes
                        listarClientes();
                        break;
                    case "3": // Criar conta
                        if (atualCliente == null) {
                            JOptionPane.showMessageDialog(null, "Selecione um cliente antes de criar uma conta.", "Erro", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        atualConta = createAccount(atualCliente);
                        if (atualConta != null) {
                            banco.adicionarConta(atualConta);
                            JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                        }
                        break;
                    case "4": // Depositar
                        if (atualConta == null) {
                            JOptionPane.showMessageDialog(null, "Selecione uma conta antes de depositar.", "Erro", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        deposit(atualConta, atualCliente);
                        break;
                    case "5": // Sacar
                        if (atualConta == null) {
                            JOptionPane.showMessageDialog(null, "Selecione uma conta antes de sacar.", "Erro", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        withdraw(atualConta, atualCliente);
                        break;
                    case "6": // Listar transações
                        if (atualConta == null) {
                            JOptionPane.showMessageDialog(null, "Selecione uma conta antes de listar transações.", "Erro", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        listarTransacoes(atualConta, atualCliente);
                        break;
                    case "7": // Listar contas
                        listarContas();
                        break;
                    case "8": // Remover cliente
                        removeClient();
                        break;
                    case "9": // Remover conta
                        removeAccount();
                        break;
                    case "10": // Selecionar cliente
                        atualCliente = selectCustomer();
                        if (atualCliente != null) {
                            JOptionPane.showMessageDialog(null, "Cliente " + atualCliente.getName() + " selecionado.");
                        }
                        break;
                    case "11": // Selecionar conta
                        atualConta = selectAccount();
                        if (atualConta != null) {
                            JOptionPane.showMessageDialog(null, "Conta " + atualConta.getId() + " selecionada.");
                        }
                        break;
                    case "12": // Exibir informações do cliente
                        if (atualCliente == null) {
                            JOptionPane.showMessageDialog(null, "Selecione um cliente antes de exibir informações.", "Erro", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        exibirInformacoes(atualCliente);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Comando inválido. Digite 'help' para ver os comandos disponíveis.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } finally {
            JOptionPane.showMessageDialog(null, "bye bye!", "Encerrando", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void exibirInformacoes(Identificavel identificavel) {
        if (identificavel instanceof Cliente) {
            Cliente cliente = (Cliente) identificavel;
            JOptionPane.showMessageDialog(null, "Cliente: " + cliente.getName() + "\nID: " + cliente.getId());
        } else {
            JOptionPane.showMessageDialog(null, "Objeto não é um cliente.");
        }
    }

    private Cliente createCustomer() {
        String name = JOptionPane.showInputDialog(null, "Nome do cliente:");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome inválido. Cliente não criado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String[] options = {"Física", "Jurídica"};
        int tipo = JOptionPane.showOptionDialog(null, "Tipo de cliente:", "Criar Cliente",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (tipo == 0) {
            String cpf = getCPF();
            if (banco.cpfJaCadastrado(cpf)) {
                JOptionPane.showMessageDialog(null, "Erro: CPF já cadastrado. Não é possível criar este cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return new PessoaFisica(name, cpf);
        } else if (tipo == 1) {
            String cnpj = JOptionPane.showInputDialog(null, "CNPJ:");
            if (cnpj == null || cnpj.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "CNPJ inválido. Cliente não criado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (banco.cnpjJaCadastrado(cnpj)) {
                JOptionPane.showMessageDialog(null, "Erro: CNPJ já cadastrado. Não é possível criar este cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return new PessoaJuridica(name, cnpj);
        } else {
            JOptionPane.showMessageDialog(null, "Tipo inválido. Cliente não criado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private String getCPF() {
        String cpf;
        do {
            cpf = JOptionPane.showInputDialog(null, "CPF:");
            if (cpf == null || cpf.trim().isEmpty() || !Util.isCpf(cpf)) {
                JOptionPane.showMessageDialog(null, "CPF inválido. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } while (cpf == null || cpf.trim().isEmpty() || !Util.isCpf(cpf));
        return cpf;
    }

    private Conta createAccount(Cliente cliente) {
        String[] options = {"Poupança", "Corrente", "Rendimento"};
        int tipo = JOptionPane.showOptionDialog(null, "Tipo de conta:", "Criar Conta",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (tipo) {
            case 0:
                return createSavingsAccount(cliente);
            case 1:
                return createCurrentAccount(cliente);
            case 2:
                return createYieldAccount(cliente);
            default:
                JOptionPane.showMessageDialog(null, "Tipo de conta inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
        }
    }

    private ContaCorrente createCurrentAccount(Cliente cliente) {
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja limite?", "Criar Conta Corrente", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            String limiteStr = JOptionPane.showInputDialog(null, "Informe o limite:");
            double limite = Double.parseDouble(limiteStr.trim());
            return new ContaCorrente(cliente, limite);
        }
        return new ContaCorrente(cliente);
    }

    private ContaPoupanca createSavingsAccount(Cliente cliente) {
        String taxaStr = JOptionPane.showInputDialog(null, "Informe a taxa de rendimento (%):");
        double taxa = Double.parseDouble(taxaStr.trim());
        return new ContaPoupanca(cliente, taxa);
    }

    private ContaRendimento createYieldAccount(Cliente cliente) {
        String taxaStr = JOptionPane.showInputDialog(null, "Informe a taxa de rendimento (%):");
        double taxa = Double.parseDouble(taxaStr.trim());
        return new ContaRendimento(cliente, taxa);
    }

    private void deposit(Conta conta, Cliente atualCliente) {
        if (!conta.getCliente().equals(atualCliente)) {
            JOptionPane.showMessageDialog(null, "Erro: Você não tem permissão para acessar esta conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String valorStr = JOptionPane.showInputDialog(null, "Valor do depósito:");
        double valor = Double.parseDouble(valorStr.trim());
        conta.depositar(valor);
        JOptionPane.showMessageDialog(null, "Depósito de R$" + valor + " realizado com sucesso.");
    }

    private void withdraw(Conta conta, Cliente atualCliente) {
        if (!conta.getCliente().equals(atualCliente)) {
            JOptionPane.showMessageDialog(null, "Erro: Você não tem permissão para acessar esta conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String valorStr = JOptionPane.showInputDialog(null, "Valor do saque:");
        double valor = Double.parseDouble(valorStr.trim());
        try {
            conta.sacar(valor);
            JOptionPane.showMessageDialog(null, "Saque de R$" + valor + " realizado com sucesso.");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarTransacoes(Conta conta, Cliente atualCliente) {
        if (!conta.getCliente().equals(atualCliente)) {
            JOptionPane.showMessageDialog(null, "Erro: Você não tem permissão para acessar esta conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        StringBuilder transacoes = new StringBuilder("Transações da conta " + conta.getId() + ":\n");
        for (Transacao transacao : conta.getTransacoes()) {
            transacoes.append(transacao).append("\n");
        }
        JOptionPane.showMessageDialog(null, transacoes.toString(), "Transações", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarClientes() {
        List<Cliente> clientes = banco.getClientes();
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.", "Clientes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder("Clientes cadastrados:\n");
        for (Cliente cliente : clientes) {
            sb.append(cliente).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Clientes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarContas() {
        List<Conta> contas = banco.getContas();
        if (contas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma conta cadastrada.", "Contas", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder("Contas cadastradas:\n");
        for (Conta conta : contas) {
            sb.append(conta).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Contas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void removeClient() {
        Cliente cliente = selectCustomer();
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        banco.removerCliente(cliente);
        JOptionPane.showMessageDialog(null, "Cliente removido com sucesso.");
    }

    private void removeAccount() {
        Conta conta = selectAccount();
        if (conta == null) {
            JOptionPane.showMessageDialog(null, "Nenhuma conta selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        banco.removerConta(conta);
        JOptionPane.showMessageDialog(null, "Conta removida com sucesso.");
    }

    private Cliente selectCustomer() {
        List<Cliente> clientes = banco.getClientes();
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String[] options = clientes.stream().map(Cliente::getName).toArray(String[]::new);
        int index = JOptionPane.showOptionDialog(null, "Selecione um cliente:", "Selecionar Cliente",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (index >= 0) {
            return clientes.get(index);
        }
        return null;
    }

    private Conta selectAccount() {
        List<Conta> contas = banco.getContas();
        if (contas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma conta cadastrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        // Exibir ID, tipo da conta e nome do cliente, sem o saldo
        String[] options = contas.stream()
                .map(conta -> String.format("ID: %s - Tipo: %s - Cliente: %s", 
                        conta.getId(), 
                        conta.getClass().getSimpleName(), 
                        conta.getCliente().getName()))
                .toArray(String[]::new);
        int index = JOptionPane.showOptionDialog(null, "Selecione uma conta:", "Selecionar Conta",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (index >= 0) {
            return contas.get(index);
        }
        return null;
    }

    private void printHelp() {
        JOptionPane.showMessageDialog(null, """
                1. Criar cliente
                2. Listar clientes
                3. Criar conta
                4. Depositar
                5. Sacar
                6. Listar transações
                7. Listar contas
                8. Remover cliente
                9. Remover conta
                10. Selecionar cliente
                11. Selecionar conta
                12. Exibir informações do cliente
                exit. Sair
                """, "Ajuda", JOptionPane.INFORMATION_MESSAGE);
    }
}