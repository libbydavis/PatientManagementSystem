package ProgramFilesPackage;

import java.io.IOException;
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
                    System.out.println("Enter NHI of the patient: ");
                    String currentNhi = scan.nextLine();
                    Patient currentPatient = new MedicalPatient(currentNhi);
                    currentPatient = currentPatient.findPatientInDatabase(currentNhi);
                    Appointment currentAppointment = new Appointment(currentPatient);
                    currentAppointment.runAppointment();
                    break;
                case "3":
                    Prescription.generatePrescription();
                    break;
                case "4":
                    MedicalPatient addPatient = new MedicalPatient("currentNhi");
                    addPatient.addPatient();
                    //MedicalPatient.testDatabaseIn();
                    break;
                case "5":
                    System.out.println("edit patient");
                    //MedicalPatient.testDatabaseOut();
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
