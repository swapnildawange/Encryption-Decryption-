package encryptdecrypt;


import java.util.*;

public class Stage4 {
    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < args.length - 1; i += 2) {
            map.put(args[i], args[i + 1]);

        }

        String operation = map.get("-mode");
        int n = Integer.parseInt(map.get("-key"));
        StringBuilder input = new StringBuilder(map.get("-data"));
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
