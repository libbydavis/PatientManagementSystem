package ProgramFilesPackage;

import java.util.HashSet;
import java.util.Scanner;

public class MedicalCondition {
    private String name;
    private String description;

    public MedicalCondition() {
        this.name = "";
        this.description = "";
    }

    public MedicalCondition(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void enterConditions(HashSet conditions) {
        Scanner scan = new Scanner(System.in);
        String name = "";

        while (!name.equals("x")) {
            System.out.println("Enter the name of the condition (or x to stop): ");
            name = scan.nextLine();
            if (!name.equals("x")) {
                this.name = name;

                System.out.println("Enter the name of the condition: ");
                String description = scan.nextLine();
                this.description = description;

                conditions.add(this);
            }
        }
    }
}
