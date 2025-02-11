package poo.project_02;

public class FatorialFuncional {
    public static void main(String[] args) {
        // Linguagem declarativa - funcional

        // Operador ternario 
        int n = args.length == 0
                ? 0
                : Integer.parseInt(args[0]);
        long f = 1;
        for (int c = n; c > 1; c--) {
            f *= c;
        }
        System.out.println(n + "! = " + fatorial(n));
    }

    public static long fatorial(long n) {   
        return n == 0 ? 1 : n * fatorial(n - 1);
    }
}
