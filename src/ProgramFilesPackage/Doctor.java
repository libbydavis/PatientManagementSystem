package ProgramFilesPackage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Doctor implements User{
    String name;
    int age;
    
    // Need a doctor constructor and a getter for the doctor's name so their name can be added to prescriptions.
    @Override
    public void userMenu() throws IOException 
    {
        boolean runningUser = true;
        String input;
        Scanner scan = new Scanner(System.in);

        while (runningUser) {
            System.out.println("Enter a number to select:\n1. See a list of all patients\n2. Start an appointment\n3. Create a prescription\n4. Add a patient\n5. Edit a patient's details\n6. Exit program");
            input = scan.nextLine();
            switch (input) {
                case "1":
                    Patient allPatients = new MedicalPatient("allPatients");
                    allPatients.listAllPatients();
                    break;
                case "2":
                    boolean valid = false;
                    Patient currentPatient = new MedicalPatient("");
                    String currentNhi = "";
                    while(!valid) {
                        System.out.println("Enter NHI of the patient: ");
                        currentNhi = scan.nextLine();
                        valid = currentPatient.validateNHI(currentNhi);
                    }
                    currentPatient = currentPatient.findPatientInDatabase(currentNhi);
                    Appointment currentAppointment = new Appointment();
                    currentAppointment.runAppointment(currentPatient);
                    break;
                case "3":
                    // ask the user the NHI number of the patient they'd like to make a prescription for.
                    MedicalPatient medPatObj = new MedicalPatient("");
                    System.out.println("Enter the NHI of the patient you'd like to make a prescription for:");
                    String prescription = "";
                       do
                    {
                        prescription = scan.nextLine();
                    }while(medPatObj.validateNHI(prescription) == false);
                    
                    medPatObj = (MedicalPatient) medPatObj.findPatientInDatabase(prescription);
                    medPatObj.getPrescriptions().add(Prescription.generatePrescription(prescription));
                    medPatObj.replacePatient(medPatObj);
                    break;
                case "4":
                    MedicalPatient addPatient = new MedicalPatient("currentNhi");
                    addPatient.addPatient();
                    break;
                case "5":
                    String patientNHI = "";
                    MedicalPatient medPatientObj = new MedicalPatient("");
                    System.out.println("What is the NHI of the patient you want to edit?");
                    do
                    {
                        patientNHI = scan.nextLine();
                    }while(medPatientObj.validateNHI(patientNHI) == false);
                    //medPatientObj.validateNHI(patientNHI);
                    medPatientObj.editPatient(patientNHI);
                    break;
                case "6":
                    runningUser = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Please enter a number from 1-4.");
                    break;
            }
        }
    }
}
