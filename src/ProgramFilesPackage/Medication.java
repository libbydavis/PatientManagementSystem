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

public class Medication {

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
        System.out.println("\nEnter the number of the medication you want to see details of");
        String uInput = "";
        uInput = scan.nextLine();

        while (!fileToHashMap().containsKey(uInput)) 
        {
            System.out.println("Incorrect input, please try again");
            uInput = scan.nextLine();
        }

        System.out.println(fileToHashMap().get(uInput));
        scan.close();
    }
    /**
     * 
     * @throws FileNotFoundException 
     */
    public static void printMedList() throws FileNotFoundException 
    {
        System.out.println("List of all Medication:");
        for (Map.Entry<String, Medication> s : fileToHashMap().entrySet()) 
        {
            System.out.println(s.getKey() + ". " + s.getValue().getName());
        }
    }

    public String toString() 
    {
        return "Medication Name: " + name + "\n" + dosage + "\nSide Effects: " + sideEffects + "\nConditions: " + conditions;
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {
        HashMap<String, Medication> medhm = fileToHashMap();
        HashSet<String> vicSideEff = new HashSet<String>();
        HashSet<String> vicConditions = new HashSet<String>();
        vicSideEff.add("lightheadedness");
        vicSideEff.add("constipation");
        vicSideEff.add("vocal cord swelling");
        vicSideEff.add("slow heartbeat");
        vicConditions.add("can be taken with or without food (taken with food may be helpful)");
        vicConditions.add("stored in a locked cabinet");
        medhm.put("3", new Medication("Vicodin", new Dosage(), vicSideEff, vicConditions));
        
        HashSet<String> oxySideEff = new HashSet<String>();
        HashSet<String> oxyConditions = new HashSet<String>();
        oxySideEff.add("dry mouth");
        oxySideEff.add("sweating");
        oxySideEff.add("muscle aches");
        oxyConditions.add("Tightly sealed in the container it comes in");
        oxyConditions.add("Stored in a cool, dry place, away from direct heat and light");
        medhm.put("4", new Medication("Oxycontin", new Dosage(), oxySideEff, oxyConditions));
        
        HashSet<String> percSideEff = new HashSet<String>();
        HashSet<String> percConditions = new HashSet<String>();
        percSideEff.add("dark urine");
        percSideEff.add("shallow breathing");
        percSideEff.add("sighing");
        percSideEff.add("unusual bruising or bleeding");
        percConditions.add("Out of reach from children");
        percConditions.add("Stored in place kept at room temperature");
        medhm.put("5", new Medication("Percocet", new Dosage(), percSideEff, percConditions));
        
        HashSet<String> valSideEff = new HashSet<String>();
        HashSet<String> valConditions = new HashSet<String>();
        valSideEff.add("hallucinations");
        valSideEff.add("anxiety");
        valSideEff.add("confusion");
        valConditions.add("Should not be taken for more than 4 weeks");
        valConditions.add("Cannot drive or operate any heavy machinery under it's influence");
        medhm.put("6", new Medication("Valium", new Dosage(), valSideEff, valConditions));
        
        HashSet<String> xanSideEff = new HashSet<String>();
        HashSet<String> xanConditions = new HashSet<String>();
        xanSideEff.add("Sleepiness");
        xanSideEff.add("Memory Problems");
        xanSideEff.add("Poor balance or coordination");
        xanConditions.add("In a dry place as moisture and humidity can cause it to break down sooner");
        xanConditions.add("keep out of direct sunlight");
        medhm.put("7", new Medication("Xanax", new Dosage(), xanSideEff, xanConditions));
        
        try
        {
            Gson g = new Gson();
            FileWriter writer = new FileWriter("src/ProgramFilesPackage/MedicationList.txt");
            String json = g.toJson(medhm);
            writer.write(json);
            writer.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: file not found");
        }
        catch(IOException e)
        {
            System.out.println("Error: file could not be opened for reading/writing");
        }
    }
}
