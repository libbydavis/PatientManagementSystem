package ProgramFilesPackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

public class Appointment {
    private Date date;
    private Patient patient;
    private ArrayList<String> reasons;
    private ArrayList<Measurement> measurementsTaken;
    private HashSet<String> notes;

    public Appointment(Patient patient) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        date = new Date();
        this.patient = patient;
        reasons = new ArrayList<>();
        measurementsTaken = new ArrayList<>();
        notes = new HashSet<>();
    }

    public void runAppointment() {
        //menu select
        Scanner scan = new Scanner(System.in);
        int menuChoice = 0;
        boolean choiceValid = false;
        while (!choiceValid) {
            try {
                System.out.println("Enter number to select:\n1. Enter reasons for appointment\n2. Enter a new measurement\n3. Enter a new note");
                menuChoice = scan.nextInt();
                if (menuChoice == 1 || menuChoice == 2 || menuChoice == 3) {
                    choiceValid = true;
                }
                else {
                    System.out.println("Please enter a number from 1-3.");
                }
            } catch (Exception E) {
                System.out.println("Please enter a number.");
                scan.next();
                menuChoice = 0;
            }
        }

        //run menu choice
        switch (menuChoice) {
            case 1:
                enterReason();
                break;
            case 2:
                enterMeasurement();
                break;
            case 3:
                enterNote();
                break;
            default:
                break;
            }
        }

        private void enterReason() {
            Scanner scan = new Scanner(System.in);
            String reason = "";
            while (!reason.equals("x")) {
                System.out.println("Enter a reason that the patient came in for the appointment (or x to stop adding reasons):");
                reason = scan.nextLine();
                if (!reason.equals("x")) {
                    this.reasons.add(reason);
                }
            }
            System.out.println("Reasons: ");
            for (String r : this.reasons) {
                System.out.println(r);
            }
            String confirm;
            System.out.println("Enter c to confirm list of reasons (or any key to edit):");
            confirm = scan.nextLine();
            if (!confirm.equals("c")) {
                System.out.println("Enter number to select:\n1. Delete a reason\n2. Edit a reason\n3. exit and confirm");
            }
        }

        private void enterMeasurement() {
        }

        private void enterNote() {
        }
    }
