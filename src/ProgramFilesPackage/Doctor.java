package ProgramFilesPackage;

import java.io.IOException;
import java.util.Scanner;

public class Doctor implements User{
    String name;
    int age;

    @Override
    public void userMenu() throws IOException {
        boolean runningUser = true;
        String input;
        Scanner scan = new Scanner(System.in);

        while (runningUser) {
            System.out.println("Enter a number to select:\n1. Start an appointment\n2. Create a prescription\n3. Add a patient\n4. Edit a patient's details\n5. Exit program");
            input = scan.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Enter NHI of the patient: ");
                    String currentNhi = scan.nextLine();
                    Patient currentPatient = new MedicalPatient(currentNhi);
                    currentPatient.findPatientInDatabase();
                    Appointment currentAppointment = new Appointment(currentPatient);
                    currentAppointment.runAppointment();
                    break;
                case "2":
                    System.out.println("prescription");
                    break;
                case "3":
                    System.out.println("add patient");
                    MedicalPatient.testDatabaseIn();
                    break;
                case "4":
                    System.out.println("edit patient");
                    MedicalPatient.testDatabaseOut();
                    break;
                case "5":
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
