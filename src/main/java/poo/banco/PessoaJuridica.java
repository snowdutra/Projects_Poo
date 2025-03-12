package poo.banco;

public class PessoaJuridica extends Cliente {

    private final String cnpj;

    public PessoaJuridica(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String toString() {
        return String.format("[%s] - CNPJ: %s", super.toString(), cnpj);
    }
}

