package ProgramFilesPackage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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
  
    public static ArrayList<Prescription> generatePrescription() throws IOException 
    {
        ArrayList<Prescription> patientPrescs = null;
        String uInput = "";
        boolean loop = true;
        
        while(loop)
        {
            Scanner scan = new Scanner(System.in);
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
                    boolean medRepeat = false;
                    Date currentDate = new Date();
                    SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                    prescribedTime = timeFormatter.format(currentDate);
                    prescribedDate = dateFormatter.format(currentDate);
                    // Prompts to fill in prescription
                    // Prompt for the patient
                    System.out.println("NHI number of the patient you want to prescribe this medicine to:");
                    do
                    {
                        prescPatient = scan.nextLine();
                    }while(patients.validateNHI(prescPatient) == false);
                    //TODO: validate if patient exists by comparing NHI numbers
                    // Prompt for the medication
                    System.out.println("Enter the MedNo# of the medicine you'd like to prescribe:");
                    medChoice = scan.nextLine();
                    Medication.validateMeds(medChoice); 
                    patientMeds = Medication.getMeds(medChoice);
                    // Prompt for Dosage
                    double doseAmount = 0.0;
                    System.out.println("Enter the dosage amount for the patient (2 d.p):");
                    doseAmount = Checker.DoubleInput(doseAmount);
                    System.out.println("Enter how often the patient should be taking this medication: (.e.g, 5 times a week for breakfast, lunch and dinner)");
                    String doseFrequency = scan.nextLine();
                    while(doseFrequency.length() == 0)
                    {
                        System.out.println("A patient's dosage frequency must be specified");
                        doseFrequency = scan.nextLine();
                    }
                    patientMeds.setDosage(new Dosage(doseAmount, doseFrequency));
                    // Prompt for repeat prescription
                    System.out.println("Will the patient need to be prescribed this medication again? (Enter a boolean (true/false))");
                    boolean medRepeatLoop = true;
                    
                    while(medRepeatLoop)
                    {
                        try
                        {
                            medRepeat = scan.nextBoolean();
                            medRepeatLoop = false;
                        }
                        catch(InputMismatchException e)
                        {
                            scan.next();
                            System.out.println("Invalid input, please try again");
                            medRepeatLoop = true;
                        }
                    }
                    patientPrescs = new ArrayList<Prescription>();
                    patientPrescs.add(new Prescription(prescribedDate, prescribedTime, patientMeds, docName, patients.findPatientInDatabase(prescPatient), medRepeat));

                    //add to database -Libby
                    MedicalPatient tempPatient = new MedicalPatient("");
                    tempPatient = (MedicalPatient) tempPatient.findPatientInDatabase(prescPatient);
                    tempPatient.setPrescriptions(patientPrescs);
                    //add to current medications
                    HashSet<Medication> holdMedications = tempPatient.getCurrentMedications();
                    holdMedications.add(patientMeds);
                    tempPatient.setCurrentMedications(holdMedications);
                    //save to database
                    tempPatient.replacePatient(tempPatient);


                    /*
                    MedicalPatient tempPatient = (MedicalPatient) patients.findPatientInDatabase(prescPatient);
                    tempPatient.setPrescriptions(patientPrescs);
                    MedicalPatient[] tempPatientList = tempPatient.deserializePatients();
                    */
                    scan.skip("");
                    break;
                case "4":
                    loop = false;
                    break;
                default:
                    System.out.println("Please enter a number between 1-4.");
                    break;
            }
        } 
        return patientPrescs;
    }

    public String toString() 
    {
        return "\nPrescripted Date: " + date + "\nPrescripted Time: " + time + "\nPrescribed Medication: " + meds + "\nDoctor: " + doctorName + "\nPatient Details:" + "\nRepeat: " + repeat;
    }
}

// Be able to edit a patients details once they've entered it. E.g., their address, phone no, age etc.