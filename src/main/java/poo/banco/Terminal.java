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
                    case "2": // Selecionar cliente
                        atualCliente = selectCustomer();
                        if (atualCliente != null) {
                            JOptionPane.showMessageDialog(null, "Cliente " + atualCliente.getName() + " selecionado.");
                        }
                        break;
                    case "3": // Remover cliente
                        removeClient();
                        break;
                    case "4": // Listar clientes
                        listarClientes();
                        break;
                    case "5": // Criar conta
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
                    case "6": // Selecionar conta
                        atualConta = selectAccount();
                        if (atualConta != null) {
                            JOptionPane.showMessageDialog(null, "Conta " + atualConta.getId() + " selecionada.");
                        }
                        break;
                    case "7": // Remover conta
                        removeAccount();
                        break;
                    case "8": // Listar contas
                        listarContas();
                        break;
                    case "9": // Depositar
                        if (atualConta == null) {
                            JOptionPane.showMessageDialog(null, "Selecione uma conta antes de depositar.", "Erro", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        deposit(atualConta, atualCliente);
                        break;
                    case "10": // Sacar
                        if (atualConta == null) {
                            JOptionPane.showMessageDialog(null, "Selecione uma conta antes de sacar.", "Erro", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        withdraw(atualConta, atualCliente);
                        break;
                    case "11": // Transferir
                        transferir();
                        break;
                    case "12": // Listar transações
                        if (atualConta == null) {
                            JOptionPane.showMessageDialog(null, "Selecione uma conta antes de listar transações.", "Erro", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        listarTransacoes(atualConta, atualCliente);
                        break;
                    case "13": // Exibir informações do cliente
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

    private void printHelp() {
        JOptionPane.showMessageDialog(null, """
                1. Criar cliente
                2. Selecionar cliente
                3. Remover cliente
                4. Listar clientes
                5. Criar conta
                6. Selecionar conta
                7. Remover conta
                8. Listar contas
                9. Depositar
                10. Sacar
                11. Transferir
                12. Listar transações
                13. Exibir informações do cliente
                exit. Sair
                """, "Ajuda", JOptionPane.INFORMATION_MESSAGE);
    }

    public void exibirInformacoes(Identificavel identificavel) {
        if (identificavel instanceof Cliente) {
            Cliente cliente = (Cliente) identificavel;
            JOptionPane.showMessageDialog(null, "Cliente: " + cliente.getName() + "\nID: " + cliente.getId());
        } else {
            JOptionPane.showMessageDialog(null, "Objeto não é um cliente.");
        }
    }

    private void transferir() {
        String chavePixOrigem = JOptionPane.showInputDialog(null, "Digite o CPF ou CNPJ do remetente:");
        String chavePixDestino = JOptionPane.showInputDialog(null, "Digite o CPF ou CNPJ do destinatário:");
        String valorStr = JOptionPane.showInputDialog(null, "Digite o valor da transferência:");

        try {
            double valor = Double.parseDouble(valorStr);
            banco.transferir(chavePixOrigem, chavePixDestino, valor);
            JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar transferência: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Cliente createCustomer() {
        String name = JOptionPane.showInputDialog(null, "Nome do cliente:");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome inválido. Cliente não criado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String[] options = {"Física", "Jurídica", "Voltar"};
        int tipo = JOptionPane.showOptionDialog(null, "Tipo de cliente:", "Criar Cliente",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (tipo == 2 || tipo == JOptionPane.CLOSED_OPTION) {
            return null;
        }

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
        }
        return null;
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
        String[] options = {"Poupança", "Corrente", "Rendimento", "Voltar"};
        int tipo = JOptionPane.showOptionDialog(null, "Tipo de conta:", "Criar Conta",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (tipo == 3 || tipo == JOptionPane.CLOSED_OPTION) {
            return null;
        }

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
            try {
                double limite = Double.parseDouble(limiteStr.trim());
                if (limite < 0) {
                    JOptionPane.showMessageDialog(null, "O limite não pode ser negativo.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
                return new ContaCorrente(cliente, limite);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor inválido para o limite.", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return new ContaCorrente(cliente);
    }

    private ContaPoupanca createSavingsAccount(Cliente cliente) {
        String taxaStr = JOptionPane.showInputDialog(null, "Informe a taxa de rendimento (%):");
        try {
            double taxa = Double.parseDouble(taxaStr.trim());
            if (taxa < 0) {
                JOptionPane.showMessageDialog(null, "A taxa de rendimento não pode ser negativa.", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return new ContaPoupanca(cliente, taxa);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido para a taxa de rendimento.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private ContaRendimento createYieldAccount(Cliente cliente) {
        String taxaStr = JOptionPane.showInputDialog(null, "Informe a taxa de rendimento (%):");
        try {
            double taxa = Double.parseDouble(taxaStr.trim());
            if (taxa < 0) {
                JOptionPane.showMessageDialog(null, "A taxa de rendimento não pode ser negativa.", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return new ContaRendimento(cliente, taxa);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido para a taxa de rendimento.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void deposit(Conta conta, Cliente atualCliente) {
        if (!conta.getCliente().equals(atualCliente)) {
            JOptionPane.showMessageDialog(null, "Erro: Você não tem permissão para acessar esta conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String valorStr = JOptionPane.showInputDialog(null, "Valor do depósito:");
        try {
            double valor = Double.parseDouble(valorStr.trim());
            if (valor <= 0) {
                JOptionPane.showMessageDialog(null, "O valor do depósito deve ser maior que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            conta.depositar(valor);
            JOptionPane.showMessageDialog(null, "Depósito de R$" + valor + " realizado com sucesso.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido para o depósito.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void withdraw(Conta conta, Cliente atualCliente) {
        if (!conta.getCliente().equals(atualCliente)) {
            JOptionPane.showMessageDialog(null, "Erro: Você não tem permissão para acessar esta conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String valorStr = JOptionPane.showInputDialog(null, "Valor do saque:");
        try {
            double valor = Double.parseDouble(valorStr.trim());
            if (valor <= 0) {
                JOptionPane.showMessageDialog(null, "O valor do saque deve ser maior que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            conta.sacar(valor);
            JOptionPane.showMessageDialog(null, "Saque de R$" + valor + " realizado com sucesso.");
        } catch (UnsupportedOperationException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido para o saque.", "Erro", JOptionPane.ERROR_MESSAGE);
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
        String[] optionsWithBack = new String[options.length + 1];
        System.arraycopy(options, 0, optionsWithBack, 0, options.length);
        optionsWithBack[options.length] = "Voltar";

        int index = JOptionPane.showOptionDialog(null, "Selecione um cliente:", "Selecionar Cliente",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsWithBack, optionsWithBack[0]);

        if (index == options.length || index == JOptionPane.CLOSED_OPTION) {
            return null;
        }
        return clientes.get(index);
    }

    private Conta selectAccount() {
        List<Conta> contas = banco.getContas();
        if (contas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma conta cadastrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String[] options = contas.stream()
                .map(conta -> String.format("ID: %s - Tipo: %s - Cliente: %s",
                        conta.getId(),
                        conta.getClass().getSimpleName(),
                        conta.getCliente().getName()))
                .toArray(String[]::new);
        String[] optionsWithBack = new String[options.length + 1];
        System.arraycopy(options, 0, optionsWithBack, 0, options.length);
        optionsWithBack[options.length] = "Voltar";

        int index = JOptionPane.showOptionDialog(null, "Selecione uma conta:", "Selecionar Conta",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsWithBack, optionsWithBack[0]);

        if (index == options.length || index == JOptionPane.CLOSED_OPTION) {
            return null;
        }
        return contas.get(index);
    }
}