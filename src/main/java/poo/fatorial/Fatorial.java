package poo.fatorial;

public class Fatorial {

    public static long fatorial(long n) {
        long resultado = 1;
        for (long i = n; i > 1; i--) {
            resultado *= i;
        }
        return resultado;
    }

    public static void main(String[] args) {
        int n = args.length == 0 ? 0 : Integer.parseInt(args[0]);
        long f = fatorial(n);
        System.out.println(n + "! = " + f);
    }
}
