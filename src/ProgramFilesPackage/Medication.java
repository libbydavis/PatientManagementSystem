package ProgramFilesPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Medication 
{

    private String name;
    private Dosage dosage;
    private HashSet<String> sideEffects;
    private HashSet<String> conditions;

    public Medication(String name, Dosage dosage, HashSet<String> sideEffects, HashSet<String> conditions) 
    {
        this.name = name;
        this.dosage = dosage;
        this.sideEffects = sideEffects;
        this.conditions = conditions;
    }

    public String getName() 
    {
        return name;
    }

    public Dosage getDosage() 
    {
        return dosage;
    }

    public void setDosage(Dosage dosage) 
    {
        this.dosage = dosage;
    }

    public HashSet<String> getSideEffects() 
    {
        return sideEffects;
    }

    public HashSet<String> getConditions() 
    {
        return conditions;
    }
    /**
     * 
     * @param meds
     * @return
     * @throws FileNotFoundException 
     */
    
    public static void validateMeds(String medChoice, Scanner scanner) throws FileNotFoundException
    {
        while(!Medication.fileToHashMap().containsKey(medChoice))
        {
            System.out.println("This does not exist in the list of medication, please try again");
            
            medChoice = scanner.nextLine();
            
            if(medChoice.equalsIgnoreCase("x"))
            {
                break;
            }
        }
    }
    public static Medication getMeds(String meds) throws FileNotFoundException 
    {
       return Medication.fileToHashMap().get(meds);
    }
    /**
     * 
     * @return
     * @throws FileNotFoundException 
     */
    public static HashMap<String, Medication> fileToHashMap() throws FileNotFoundException 
    {
        Gson gson = new Gson();
        JsonReader jr = new JsonReader(new FileReader("src/ProgramFilesPackage/MedicationList.txt"));
        HashMap<String, Medication> medList = gson.fromJson(jr, new TypeToken<HashMap<String, Medication>>(){}.getType()); // Converting  the JSON file into a HashMap object 

        return medList;
    }
    /**
     * 
     * @throws FileNotFoundException 
     */
    public static void printMedInfo() throws FileNotFoundException 
    {
        Scanner scan = new Scanner(System.in);
         printMedList(); // Retrieves the names of all medication in the Json file.
            
        // Scans for user input, asking the doctor what details of the medication they want to see.
        String uInput = "";
        while(!uInput.equalsIgnoreCase("x"))
        {
            System.out.println("\nEnter the MedNo# of the medication you want to see details of: (x) to exit");
            uInput = scan.nextLine();
            
            if(uInput.equalsIgnoreCase("x"))
            {
                break;
            }
            
            while(!fileToHashMap().containsKey(uInput)) 
            {
                System.out.println("Incorrect input, please try again");
                uInput = scan.nextLine();
            }

            System.out.println(fileToHashMap().get(uInput));
        }
    }
    /**
     * 
     * @throws FileNotFoundException 
     */
    public static void printMedList() throws FileNotFoundException 
    {
        System.out.println("\nMedNo#:\tMedication Name:");
        for (Map.Entry<String, Medication> s : fileToHashMap().entrySet()) 
        {
            System.out.println(" (" + s.getKey() + ")\t  " + s.getValue().getName());
        }
    }

    public String toString() 
    {
        return "Medication Name: " + name + "\n" + dosage + "\nSide Effects: " + sideEffects + "\nConditions: " + conditions;
    }
}
