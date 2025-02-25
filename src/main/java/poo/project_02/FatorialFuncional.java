package poo.project_02;

public class FatorialFuncional {
    public static void main(String[] args) {
        // Linguagem declarativa - funcional

        // Operador ternário com tratamento de exceção
        int n = 0;
        try {
            n = args.length == 0 ? 0 : Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Argumento inválido. Por favor, insira um número inteiro.");
            System.exit(1);
        }

        System.out.println(n + "! = " + fatorial(n));
    }

    public static long fatorial(long n) {
        return n == 0 ? 1 : n * fatorial(n - 1);
    }
}