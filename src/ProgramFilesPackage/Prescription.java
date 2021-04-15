package ProgramFilesPackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Prescription 
{
    String date;
    String time;
    Medication meds;
    String doctorName;
    Patient patientDetails;
    Boolean repeat;
    
    public Prescription(String date, String time, Medication meds, String doctorName, Patient patientDetails, Boolean repeat) 
    {	
        this.date = date;
        this.time = time;
        this.meds = meds;
        this.doctorName = doctorName;
        this.patientDetails = patientDetails;
        this.repeat = repeat;
    }
  
    public Prescription generatePrescription() throws IOException 
    {
        Scanner scan = new Scanner(System.in);
        MedicalPatient patients = new MedicalPatient("Patients List");
        patients.listAllPatients();
        Medication.printMedInfo();
        System.out.println("Enter the MedNo# of the medicine you'd like to prescribe:");
        String medChoice = scan.nextLine();
        
        while(!Medication.fileToHashMap().containsKey(medChoice))
        {
            System.out.println("This does not exist in the list of medication, please try again");
            
            medChoice = scan.nextLine();
            
            if(medChoice.equalsIgnoreCase("x"))
            {
                break;
            }
        }
        
        System.out.println("NHI number of the patient you want to prescribe this medicine to:");
        String prescPatient = scan.nextLine();
        
        String doseAmount = scan.nextLine();
        String doseFrequency = scan.nextLine();
        double temp = Double.parseDouble(doseAmount);
        
        Medication tempMeds = Medication.getMeds(medChoice);
        tempMeds.setDosage(new Dosage(temp, doseFrequency));
        
        Prescription prescObj = new Prescription("23/04/21", "12:35 AM", tempMeds, "John Smith", patients.findPatientInDatabase(prescPatient), false);
        System.out.println("Prescription has been made successfully for:");
        System.out.println(prescObj.patientDetails.getName() + prescObj.patientDetails);
        System.out.println(prescObj);
        
        return prescObj;
    }

    public String toString() 
    {
        return "\nPrescripted Date: " + date + "\nPrescripted Time: " + time + "\nPrescribed Medication: " + meds + "\nDoctor: " + doctorName + "\nPatient Details:" + "\nRepeat: " + repeat;
    }
}
    // Be able to add a 'dosage' to meds.
    // Be able to edit a patients details once they've entered it. E.g., their address, phone no, age etc.