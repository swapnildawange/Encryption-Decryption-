package encryptdecrypt;

import java.io.File;
import java.util.*;
import java.io.FileWriter;

public class Stage6 {
    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < args.length - 1; i += 2) {
            map.put(args[i], args[i + 1]);

        }
        String operation = map.get("-mode");
        int n = Integer.parseInt(map.get("-key"));
        StringBuilder input = new StringBuilder();
        String algoritham = "shift";
        algoritham = map.get("-algo");
        File inFile = new File(map.get("-in"));

        try (Scanner scanner = new Scanner(inFile)) {
            if (scanner.hasNext()) {
                String s = scanner.nextLine();
                System.out.println(s);
                input.append(s);
            }
        } catch (Exception e) {
            System.out.println("Input File not found " + e.getMessage());
        }

        switch (operation) {
            case ("enc"):
                try {
                    File outFile = new File(map.get("-out"));
                    if (outFile.createNewFile()) {
                        System.out.println("File created: " + outFile.getName());
                    }
                    FileWriter myWritter = new FileWriter(outFile);
                    if (map.get("-alg") == "unicode") {
                        myWritter.write(encryptUnicode(input, n).toString());
                    } else {
                        myWritter.write(encryptShift(input, n).toString());
                    }
                    myWritter.close();

                } catch (Exception e) {
                    System.out.println("Output File not found " + e.getMessage());
                }

                break;
            case ("dec"):
                try {
                    File outFile = new File(map.get("-out"));
                    if (outFile.createNewFile()) {
                        System.out.println("File created: " + outFile.getName());
                    }
                    FileWriter myWritter = new FileWriter(outFile);
                    if (map.get("-alg") == "unicode")
                        myWritter.write(decryptUnicode(encryptUnicode(input, n), n).toString());
                    else
                        myWritter.write(decryptShift(input, n).toString());
                    myWritter.close();

                } catch (Exception e) {
                    System.out.println("Output File not found " + e.getMessage());
                }

                break;
        }

    }

    public static StringBuilder encryptUnicode(StringBuilder input, int n) {

        StringBuilder outputString = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char toUnicode = input.charAt(i);
            int j = (int) toUnicode;
            char toOriginal = (char) (j + n);

            outputString.append(toOriginal);
        }
        return outputString;
    }

    public static StringBuilder decryptUnicode(StringBuilder input, int n) {

        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char toUnicode = input.charAt(i);
            int j = (int) toUnicode;

            char toOriginal = (char) (j - n * 2);
            outputString.append(toOriginal);

        }
        return outputString;

    }

    public static StringBuilder decryptShift(StringBuilder input, int n) {
        StringBuilder mySimpleSmall = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        StringBuilder mySimpleCap = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuilder outputString = new StringBuilder();
        int index = 0;
        for (int i = 0; i < input.length(); i++) {
            if (String.valueOf(input.charAt(i)).matches("[a-z]")) {
                index = mySimpleSmall.indexOf(String.valueOf(input.charAt(i)));

                if (index - n < 0) {
                    int remain = 26 - Math.abs(index - n);
                    index = remain;
                } else {
                    index -= n;
                }

                outputString.append(mySimpleSmall.charAt(index));

            } else if (String.valueOf(input.charAt(i)).matches("[A-Z]")) {
                index = mySimpleCap.indexOf(String.valueOf(input.charAt(i)));

                if (index - n < 0) {
                    int remain = 26 - Math.abs(index - n);
                    index = remain;
                } else {
                    index -= n;
                }

                outputString.append(mySimpleCap.charAt(index));

            } else if (String.valueOf(input.charAt(i)).matches("\\W")) {
                outputString.append(input.charAt(i));
            }

        }

        return outputString;

    }

    public static StringBuilder encryptShift(StringBuilder input, int n) {

        StringBuilder mySimpleSmall = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        StringBuilder mySimpleCap = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuilder outputString = new StringBuilder();
        int index = 0;
        for (int i = 0; i < input.length(); i++) {
            if (String.valueOf(input.charAt(i)).matches("[a-z]")) {
                index = mySimpleSmall.indexOf(String.valueOf(input.charAt(i)));

                if (index + n >= 25) {
                    int remain = (index + n) - 26;
                    index = remain;
                } else {
                    index += n;
                }

                outputString.append(mySimpleSmall.charAt(index));

            } else if (String.valueOf(input.charAt(i)).matches("[A-Z]")) {
                index = mySimpleCap.indexOf(String.valueOf(input.charAt(i)));

                if (index + n >= 25) {
                    int remain = (index + n) - 26;
                    index = remain;
                } else {
                    index += n;
                }

                outputString.append(mySimpleCap.charAt(index));

            } else if (String.valueOf(input.charAt(i)).matches("\\W")) {
                outputString.append(input.charAt(i));
            }

        }

        return outputString;
    }

}
