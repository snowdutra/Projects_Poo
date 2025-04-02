package poo.banco;

public class PessoaFisica extends Cliente {

    private final String cpf;

    public PessoaFisica(String name, String cpf) {
        super(name);
        validarCpf(cpf);
        this.cpf = cpf;
    }

    public PessoaFisica(String id, String name, String cpf) {
        super(id, name);
        validarCpf(cpf);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTipo() {
        return "Pessoa Física";
    }

    private void validarCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido! Deve conter 11 dígitos numéricos.");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]: %s (CPF: %s)", getId(), getName(), cpf);
    }
}

