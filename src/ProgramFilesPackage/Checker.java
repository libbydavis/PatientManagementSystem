package ProgramFilesPackage;

import java.util.Scanner;

public class Checker {
    public static int IntegerInput(int input) {
        Scanner scan = new Scanner(System.in);
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

    public static double DoubleInput(double input) {
        Scanner scan = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            try {
                input = scan.nextDouble();
                valid = true;
            } catch (Exception E) {
                System.out.println("Please enter a number.");
                scan.next();
            }
        }
        return input;
    }
}
