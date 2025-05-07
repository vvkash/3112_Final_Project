/*
 * Utils.java
 * Helper functions for input validation and reading numbers safely from Scanner.
 */

import java.util.Scanner;

public class Utils {
    // Read an integer from the scanner, handling invalid input
    public static int readInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please enter an integer: ");
            }
        }
    }

    // Read a double from the scanner, handling invalid input
    public static double readDouble(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please enter a decimal number: ");
            }
        }
    }
}
