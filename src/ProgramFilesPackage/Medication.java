package ProgramFilesPackage;

import java.util.ArrayList;
import java.util.HashSet;

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

	public void setName(String name) 
	{
		this.name = name;
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

	public void setSideEffects(HashSet<String> sideEffects) 
	{
		this.sideEffects = sideEffects;
	}

	public HashSet<String> getConditions() 
	{
		return conditions;
	}

	public void setConditions(HashSet<String> conditions) 
	{
		this.conditions = conditions;
	}
	
	/**
	 	 *This method is supposed to be a storage for medicine, containing it's name, dosage, conditions, side effects etc. 
	 	 *@return returns an Array List of medications 
	 	 **/
	public static ArrayList<Medication> medicationList()
	{
		// The variable that stores all the medication.
		ArrayList<Medication> meds = new ArrayList<Medication>();
		
		//Panadol Info 
		HashSet<String> panadolSideEff = new HashSet<String>();
		HashSet<String> panadolConditions = new HashSet<String>();
		panadolConditions.add("Store in room temperature");
		panadolConditions.add("Store out of children aged 5 and under's reach");
		panadolSideEff.add("Nausea");
		panadolSideEff.add("Lethargy");
		panadolSideEff.add("Skin Rash");
		
		//Ibuprofen Info
		HashSet<String> ibuprofenSideEff = new HashSet<String>();
		HashSet<String> ibuprofenConditions = new HashSet<String>();
		ibuprofenConditions.add("Store in room temperature");
		ibuprofenConditions.add("Store out of children aged 5 and under's reach");
		ibuprofenSideEff.add("Difficulty Breathing");
		ibuprofenSideEff.add("Blood in Vomit");
		ibuprofenSideEff.add("Stomach Pain");
	
		// TODO: How would you specify dosage amount when it's liquids/tablets/powder e.g, 2.5ml, 2.5mg, 3 tablets etc. - should I bother with this?
		meds.add(new Medication("Panadol", new Dosage(2.5, "After every meal for 3 days"), panadolSideEff, panadolConditions));
		meds.add(new Medication("Ibuprofen", new Dosage(3, "Once a day for a week"), ibuprofenSideEff, ibuprofenConditions));
		
		return meds;
	}
	
	//TODO: Make a method to retrieve medication information by looking it up by name.
	//TODO: Make a method to show a list of all the medication that the doctor can pick from.

	public String toString() 
	{
		return "Medication Name: " +name + "\n" +dosage + "\nSide Effects: " + sideEffects + "\nConditions: " + conditions;		
	}
    
    
    
}
