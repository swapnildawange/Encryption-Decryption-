package encryptdecrypt;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.next();
        scanner.nextLine();
        StringBuilder input = new StringBuilder(scanner.nextLine());
        int n = scanner.nextInt();
        switch (operation) {
            case ("enc"):
                System.out.println(encrypt(input, n));

                break;
            case ("dec"):
                System.out.println(decrypt(encrypt(input, n), n));
                break;
        }

    }

    public static StringBuilder encrypt(StringBuilder input, int n) {

        StringBuilder outputString = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char toUnicode = input.charAt(i);
            int j = (int) toUnicode;
            char toOriginal = (char) (j + n);

            outputString.append(toOriginal);
        }
        return outputString;
    }

    public static StringBuilder decrypt(StringBuilder input, int n) {

        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char toUnicode = input.charAt(i);
            int j = (int) toUnicode;
            // System.out.print((j - n*2));
            char toOriginal = (char) (j - n * 2);
            outputString.append(toOriginal);

        }
        return outputString;

    }
}
