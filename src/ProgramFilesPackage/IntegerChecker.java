package ProgramFilesPackage;

import java.util.Scanner;

public class IntegerChecker {
    public static int IntegerInput(int input, Scanner scan) {
        boolean valid = false;
        while (!valid) {
            try {
                input = scan.nextInt();
                valid = true;
            } catch (Exception E) {
                System.out.println("Please enter a number.");
                scan.next();
            }
        }
        return input;
    }
}
