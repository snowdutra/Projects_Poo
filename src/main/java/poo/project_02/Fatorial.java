package poo.project_02;

public class Fatorial {
    public static void main(String[] args) {
        int n = 5;
        int f = 1;
        for (int i = 1; i <= n; i++) {
            f = f * i;
        }
        System.out.println(n + "! = " + f);
    }
}