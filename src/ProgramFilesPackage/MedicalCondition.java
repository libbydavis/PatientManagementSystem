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

                System.out.println("Enter the description of the condition: ");
                String description = scan.nextLine();
                this.description = description;

                conditions.add(this);
            }
        }
    }
    
    /**
     * Compares the description and name of medical conditions to ensure that they're not the same.
     * @param o The medical condition that is being compared.
     * @return A Boolean letting you know whether a MedicalCondition object is the same as another.
     * @author Raj
     */
    public boolean equals(Object o) 
    {
        if(o != null && o instanceof MedicalCondition)
        {
            String conditionName = ((MedicalCondition)o).getName();
            String conditionDescription = ((MedicalCondition)o).getDescription();
            
            if((conditionDescription != null) && (conditionName != null) && (conditionName.equals(this.name) && (conditionDescription.equals(this.description))))
            {
                return true;
            }     
        }
        return false;
    }
    
    /**
     * Randomly generated hashcode for MedicalCondition objects.
     * @return Randomly generated number.
     * @author Raj
     */
    public int hashCode()
    {
        int hashCode = 1;
        hashCode = 180 * hashCode + 563;
        return hashCode;
    }
}
