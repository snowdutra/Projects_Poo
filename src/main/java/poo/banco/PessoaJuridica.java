package poo.banco;

public class PessoaJuridica extends Cliente {

    private final String cnpj;

    public PessoaJuridica(String name, String cnpj) {
        super(name);
        validarCnpj(cnpj);
        this.cnpj = cnpj;
    }

    public PessoaJuridica(String id, String name, String cnpj) {
        super(id, name);
        validarCnpj(cnpj);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTipo() {
        return "Pessoa Jurídica";
    }

    private void validarCnpj(String cnpj) {
        if (cnpj == null || !cnpj.matches("\\d{14}")) {
            throw new IllegalArgumentException("CNPJ inválido! Deve conter 14 dígitos numéricos.");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]: %s (CNPJ: %s)", getId(), getName(), cnpj);
    }
}

