package poo.banco;

import java.util.Scanner;

public final class Util {

    private Util() {}

    public static String askValidCpf(Scanner scanner) {
        String cpf;
        do {
            System.out.print("CPF: ");
            cpf = scanner.nextLine().trim();
        } while (!isValidCpf(cpf));
        return cpf;
    }

    public static boolean isValidCpf(String cpf) {
        if (cpf == null) {
            return false;
        }
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            return false;
        }
        return isCpfValid(cpf);
    }

    private static boolean isCpfValid(String cpf) {
        int[] weights = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int firstDigit = calculateCpfDigit(cpf, weights);
        int secondDigit = calculateCpfDigit(cpf, new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2}, firstDigit);
        return cpf.equals(cpf.substring(0, 9) + firstDigit + secondDigit);
    }

    private static int calculateCpfDigit(String cpf, int[] weights) {
        return calculateCpfDigit(cpf, weights, 0);
    }

    private static int calculateCpfDigit(String cpf, int[] weights, int extraDigit) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * weights[i];
        }
        sum += extraDigit * weights[weights.length - 1];
        int mod = sum % 11;
        return (mod < 2) ? 0 : 11 - mod;
    }
}

