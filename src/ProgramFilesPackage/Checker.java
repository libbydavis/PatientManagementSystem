package ProgramFilesPackage;

import java.util.Scanner;

public class Checker {
    /**
     * @author -LibbyDavis
     * @param input
     * @return int
     * Gets an int input
     */
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

    /**
     * @author -LibbyDavis
     * @param input
     * @return double
     * Gets a double input
     */
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
