package poo.week06;

public class ExemploException {

    public static void main(String[] args) {

        int x = 3;
        int y = 0;

        if (y != 0) {
            System.out.println(x / y);
        } else {
            System.out.println("Erro: Divisão por zero não é permitida.");
        }
    }
}