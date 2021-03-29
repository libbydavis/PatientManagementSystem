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
	
	/*
	 * 
	 */
	public static HashMap<String, Medication> fileToHashMap() throws FileNotFoundException
	{
		Gson gson = new Gson();
		JsonReader jr = new JsonReader(new FileReader("src/ProgramFilesPackage/MedicationList.txt"));
		HashMap<String, Medication> medList = gson.fromJson(jr, new TypeToken<HashMap<String, Medication>>(){}.getType()); // Converting  the JSON file into a HashMap object 
		
		return medList;
	}
	
	/*
	 * 
	 */
	public static void printMedInfo() throws FileNotFoundException
	{
		Scanner scan = new Scanner(System.in);
		printMedList(); // Retrieves the names of all medication in the Json file.
		// Scans for user input, asking the doctor what details of the medication they want to see.
		System.out.println("\nEnter the number of the medication you want to see details of");
		String uInput = "";
		uInput = scan.nextLine();
		
		while(!fileToHashMap().containsKey(uInput))
		{	
			System.out.println("Incorrect input, please try again");
			uInput = scan.nextLine();
		}
		
		System.out.println(fileToHashMap().get(uInput));
		scan.close();
	}
	
	/*
	 * 
	 */
	public static void printMedList() throws FileNotFoundException
	{
		int medNo = 1;
		System.out.println("List of all Medication:");
		for(Map.Entry<String, Medication> s : fileToHashMap().entrySet())
		{
			System.out.println(medNo + ". " + s.getValue().getName());
			medNo++;
		}
	}

	public String toString() 
	{
		return "Medication Name: " +name + "\n" +dosage + "\nSide Effects: " + sideEffects + "\nConditions: " + conditions;		
	}
}
