package ProgramFilesPackage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Prescription 
{
    String date;
    String time;
    Medication meds;
    String doctorName;
    Patient patientDetails;
    Boolean repeat;

    public String getDate() 
    {
        return date;
    }
    
    public Prescription(String date, String time, Medication meds, String doctorName, Patient patientDetails, Boolean repeat) 
    {	
        this.date = date;
        this.time = time;
        this.meds = meds;
        this.doctorName = doctorName;
        this.patientDetails = patientDetails;
        this.repeat = repeat;
    }
  
    public static Prescription generatePrescription() throws IOException 
    {
        
        Scanner scan = new Scanner(System.in);
        String uInput = "";
        boolean loop = true;
        
        while(loop)
        {
            System.out.println("Enter a number to select:");
            System.out.println("1. List of all current patients");
            System.out.println("2. List of all current medicine");
            System.out.println("3. Create a prescription");
            System.out.println("4. Go back to main menu");
            uInput = scan.nextLine();
            MedicalPatient patients = new MedicalPatient("Patients List");
            
            switch(uInput)
            {
                case "1":
                    patients.listAllPatients(); // displays the patient list and prompts the doctor to see more details of each patient.
                    break;
                case "2":
                    Medication.printMedInfo(); // displays the medication list and prompts the doctor to see more details of each medication.
                    break;
                case "3":
                    // Variables needed to instantiate a prescription
                    Medication patientMeds;
                    String docName = "John Smith";
                    String prescribedDate, prescribedTime, /*docName,*/ prescPatient, medChoice;
                    boolean medRepeat;
                    Date currentDate = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    prescribedTime = tf.format(currentDate);
                    prescribedDate = df.format(currentDate);
                    // Prompts to fill in prescription
                    // Prompt for the patient
                    System.out.println("NHI number of the patient you want to prescribe this medicine to:");
                    prescPatient = scan.nextLine();
                    patients.findPatientInDatabase(prescPatient).toString();
                    //TODO: validate if patient exists by comparing NHI numbers
                    
                    // Prompt for the medication
                    System.out.println("Enter the MedNo# of the medicine you'd like to prescribe:");
                    medChoice = scan.nextLine();
                    Medication.validateMeds(medChoice, scan); //medication validation
                    patientMeds = Medication.getMeds(medChoice);
                    // Prompt for Dosage
                     double doseAmount;
                    double temp = 0.1;
                    boolean userDosage = true;

                    while (userDosage) 
                    {
                        try 
                        {
                            System.out.println("Enter the dosage amount for the patient (2 d.p):");
                            temp = scan.nextDouble();
                            userDosage = false;
                        } catch (InputMismatchException e) 
                        {
                            scan.next();
                            System.out.println("Invalid Input, please try again");
                            scan.next();
                            userDosage = true;
                        }
                    }
                    System.out.println("Enter how often the patient should be taking this medication: (.e.g, 5 times a week for breakfast, lunch and dinner)");
                    String doseFrequency = scan.nextLine();
                    patientMeds.setDosage(new Dosage(temp, doseFrequency));
                    // Prompt for repeat prescription
                    System.out.println("Will the patient need to be prescribed this medication again?");
                    medRepeat = scan.nextBoolean();
                    Prescription patientPresc = new Prescription(prescribedDate, prescribedTime, patientMeds, docName, patients.findPatientInDatabase(prescPatient), medRepeat);
                    break;
                case "4":
                    loop = false;
                    break;
                default:
                    System.out.println("Please enter a number between 1-4.");
                    break;
            }
        } 
        return null;
    }

    public String toString() 
    {
        return "\nPrescripted Date: " + date + "\nPrescripted Time: " + time + "\nPrescribed Medication: " + meds + "\nDoctor: " + doctorName + "\nPatient Details:" + "\nRepeat: " + repeat;
    }
}
    // Be able to edit a patients details once they've entered it. E.g., their address, phone no, age etc.