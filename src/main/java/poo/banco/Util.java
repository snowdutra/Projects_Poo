package poo.banco;

public final class Util {

    private Util() {}

    public static boolean isCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf.length() == 11;
    }
}
