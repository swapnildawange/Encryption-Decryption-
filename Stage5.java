package encryptdecrypt;

import java.io.File;
import java.util.*;
import java.io.FileWriter;

public class Stage5 {
    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < args.length - 1; i += 2) {
            map.put(args[i], args[i + 1]);

        }
        String operation = map.get("-mode");
        int n = Integer.parseInt(map.get("-key"));
        StringBuilder input = new StringBuilder();

        File inFile = new File(map.get("-in"));

        try (Scanner scanner = new Scanner(inFile)) {
            if (scanner.hasNext()) {
          
                input.append(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Input File not found " + e.getMessage());
        }

   \

        switch (operation) {
            case ("enc"):
                try {
                    File outFile = new File(map.get("-out"));
                    if (outFile.createNewFile()) {
                        System.out.println("File created: " + outFile.getName());
                    }
                    FileWriter myWritter = new FileWriter(outFile);

                    myWritter.write(encrypt(input, n).toString());
                    myWritter.close();

                } catch (Exception e) {
                    System.out.println("Output File not found " + e.getMessage());
                }
                System.out.println(encrypt(input, n));

                break;
            case ("dec"):
                try {
                    File outFile = new File(map.get("-out"));
                    if (outFile.createNewFile()) {
                        System.out.println("File created: " + outFile.getName());
                    }
                    FileWriter myWritter = new FileWriter(outFile);

                    myWritter.write(decrypt(encrypt(input, n), n).toString());
                    myWritter.close();

                } catch (Exception e) {
                    System.out.println("Output File not found " + e.getMessage());
                }
          
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

            char toOriginal = (char) (j - n * 2);
            outputString.append(toOriginal);

        }
        return outputString;

    }
}
