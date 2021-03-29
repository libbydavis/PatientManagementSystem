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
    private ArrayList<String> notes;

    public Appointment(Patient patient) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        date = new Date();
        this.patient = patient;
        reasons = new ArrayList<>();
        measurementsTaken = new ArrayList<>();
        notes = new ArrayList<>();
    }

    public void runAppointment() {
        System.out.println("\nNew Appointment\n");
        //menu select
        Scanner scan = new Scanner(System.in);
        boolean appointmentRunning = true;
        while (appointmentRunning) {
            String menuChoice = "0";
            boolean choiceValid = false;
            while (!choiceValid) {
                System.out.println("Appointment - " + patient.getName());
                System.out.println("Enter number to select:\n1. Enter reasons for appointment\n2. Enter a new measurement\n3. Enter a new note\n4. Finish appointment");
                menuChoice = scan.nextLine();

                //run menu choice
                switch (menuChoice) {
                    case "1":
                        enterReason();
                        break;
                    case "2":
                        enterMeasurement();
                        break;
                    case "3":
                        enterNote();
                        break;
                    case "4":
                        appointmentRunning = false;
                        choiceValid = true;
                        System.out.println("Appointment Finished");
                        break;
                    default:
                        System.out.println("Please enter a number from 1-4.");
                        break;
                }
            }
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
            printList("Reasons", this.reasons);

            int change = 0;
            System.out.println("Enter number to select:\n1. Delete a reason\n2. Edit a reason\n3. Confirm and Exit");
            change = Checker.IntegerInput(change);
            switch (change) {
                case 1:
                    //delete reason
                    printList("Reasons", this.reasons);
                    while (change != 0) {
                        System.out.println("Enter the number of the reason to delete (or 0 to stop): ");
                        change = Checker.IntegerInput(change);
                        if (change > 0 && change < reasons.size()) {
                            reasons.remove(change - 1);
                            System.out.println("Reason removed.\nCurrent Reasons:");
                            printList("Reasons", this.reasons);
                        }
                        else if (change != 0) {
                            System.out.println("Please enter a number within range.");
                        }
                        System.out.println("Reasons saved");
                    }

                    break;
                case 2:
                    //modify reason
                    printList("Reasons", this.reasons);
                    String newReason = "";
                    while (change != 0) {
                        System.out.println("Enter the number of the reason to edit (or 0 to stop): ");
                        change = Checker.IntegerInput(change);
                        if (change > 0 && change <= reasons.size()) {
                            reasons.remove(change - 1);
                            System.out.println("What would you like to change the reason to?");
                            while (newReason.length() < 1) {
                                newReason = scan.nextLine();
                            }
                            reasons.add(change - 1, newReason);
                            System.out.println("Current Reasons: ");
                            printList("Reasons", this.reasons);
                        }
                        else if (change != 0) {
                            System.out.println("Please enter a number within range.");
                        }
                    }
                    System.out.println("Reasons saved");
                    break;
                case 3:
                    //confirm reason list
                    System.out.println("Confirmed");
                    break;
            }
        }

        private void printList(String title, ArrayList list) {
            System.out.println(title + ":");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }

        private void enterMeasurement() {
            Measurement measure = new Measurement();
            boolean takingMeasurements = true;
            boolean inputValid = true;
            String addAnother = "";
            Scanner scan = new Scanner(System.in);

            do {
                if (inputValid == true) {
                    measure.createMeasurement();
                    measurementsTaken.add(measure);
                }
                System.out.println("Would you like to add another measurement?\n1. Add another\n2. Finished");
                addAnother = scan.nextLine();
                switch (addAnother) {
                    case "1":
                        inputValid = true;
                        break;
                    case "2":
                        takingMeasurements = false;
                        break;
                    default:
                        inputValid = false;
                        System.out.println("Please enter a number from 1-2.");
                        break;
                }
            } while (takingMeasurements);
        }

        private void enterNote() {
            Scanner scan = new Scanner(System.in);
            String note = "";
            while (!note.equals("x")) {
                System.out.println("Enter a note (or x to stop adding notes):");
                note = scan.nextLine();
                if (!note.equals("x")) {
                    this.notes.add(note);
                }
            }
            printList("Notes", this.notes);
        }

    }
