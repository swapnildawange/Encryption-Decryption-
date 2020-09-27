

import java.util.*;

public class Stage2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder(scanner.nextLine());
        int n = scanner.nextInt();
        encrypt(input, n);

    }

    public static void encrypt(StringBuilder input, int n) {

        StringBuilder mySimple = new StringBuilder("abcdefghijklmnopqrstuvwxyz");

        StringBuilder outputString = new StringBuilder();
        int index = 0;
        for (int i = 0; i < input.length(); i++) {
            if (String.valueOf(input.charAt(i)).matches("[a-z]")) {
                index = mySimple.indexOf(String.valueOf(input.charAt(i)));

                if (index + n >= 25) {
                    int remain = (index + n) - 26;
                    index = remain;
                } else {
                    index += n;
                }
            }
            if (String.valueOf(input.charAt(i)).matches("\\W")) {
                outputString.append(input.charAt(i));
            } else {
                outputString.append(mySimple.charAt(index));
            }

        }

        System.out.println(outputString);
    }
}