package ProgramFilesPackage;

import com.google.gson.Gson;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Appointment {
    private String dateString;
    private ArrayList<String> reasons;
    private ArrayList<Measurement> measurementsTaken;
    private ArrayList<String> notes;

    public Appointment() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        Date date = new Date();
        dateString = date.toString();
        reasons = new ArrayList<>();
        measurementsTaken = new ArrayList<>();
        notes = new ArrayList<>();
    }


    public String getDate() {
        return dateString;
    }


    public ArrayList<String> getReasons() {
        return reasons;
    }

    public ArrayList<Measurement> getMeasurementsTaken() {
        return measurementsTaken;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void runAppointment(Patient patient) throws IOException {
        System.out.println("\nNew Appointment\n");
        //show patient information
        patient.displayPatientDetails();
        //menu select
        Scanner scan = new Scanner(System.in);
        boolean appointmentRunning = true;
        while (appointmentRunning) {
            String menuChoice = "0";
            boolean choiceValid = false;
            while (!choiceValid) {
                System.out.println("Appointment - ");
                System.out.println("Enter number to select:\n1. Enter reasons for appointment\n2. Enter a new measurement\n3. Enter a new note\n4. Finish appointment");
                menuChoice = scan.nextLine();

                //run menu choice
                switch (menuChoice) {
                    case "1":
                        enterReason();
                        break;
                    case "2":
                        Measurement getMeasurements = new Measurement();
                        getMeasurements.enterMeasurement(measurementsTaken);
                        break;
                    case "3":
                        enterNote();
                        break;
                    case "4":
                        appointmentRunning = false;
                        choiceValid = true;
                        String save = "";
                        System.out.println(this.toString());
                        while (!save.equals("y") && !save.equals("x")) {
                            System.out.println("Save Appointment? (y to confirm, x to exit)");
                            save = scan.next();
                            if (!save.equals("y") && !save.equals("x")) {
                                System.out.println("Please enter either y or x.");
                            }
                        }
                        if (save.equals("y")) {
                            patient.saveToAppointmentsHistory(this);
                            System.out.println("Appointment Saved");
                        }
                        else if (save.equals("x")) {
                            System.out.println("Appointment Not Saved");
                        }

                        break;
                    default:
                        System.out.println("Please enter a number from 1-4.");
                        break;
                }
            }
        }

    }

        private void enterReason() {
            //get reasons
            Scanner scan = new Scanner(System.in);
            String reason = "";
            while (!reason.equals("x")) {
                System.out.println("Enter a reason that the patient came in for the appointment (or x to stop adding reasons):");
                reason = scan.nextLine();
                if (!reason.equals("x")) {
                    this.reasons.add(reason);
                }
            }

            //modify or save reasons
            int menuSelect = 0;
            int change = -1;
            while (menuSelect != 3) {
                printList("Reasons", this.reasons);
                while (menuSelect != 1 && menuSelect != 2 && menuSelect != 3) {
                    System.out.println("Enter number to select:\n1. Delete a reason\n2. Edit a reason\n3. Confirm and Exit");
                    menuSelect = Checker.IntegerInput(menuSelect);
                }

                switch (menuSelect) {
                    case 1:
                        //delete reason
                        printList("Reasons", this.reasons);
                        while (change != 0) {
                            System.out.println("Enter the number of the reason to delete (or 0 to stop): ");
                            change = Checker.IntegerInput(change);
                            if (change > 0 && change <= reasons.size()) {
                                reasons.remove(change - 1);
                                System.out.println("Reason removed.\nCurrent Reasons:");
                                printList("Reasons", this.reasons);
                            } else if (change != 0) {
                                System.out.println("Please enter a number within range.");
                            }
                        }
                        //reset variables
                        change = -1;
                        menuSelect = -1;
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
                            } else if (change != 0) {
                                System.out.println("Please enter a number within range.");
                            }
                        }
                        System.out.println("Reasons saved");

                        //reset variables
                        change = -1;
                        menuSelect = -1;
                        break;
                    case 3:
                        //confirm reason list
                        System.out.println("Confirmed");
                        break;
                }
            }
        }

        private void printList(String title, ArrayList list) {
            System.out.println(title + ":");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
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

        public String toString() {
            String appointmentString = "Appointment Overview:\n" +  this.dateString + "\nReasons:\n";
            for (int i = 0; i < this.reasons.size(); i++) {
                appointmentString += (i + 1) + ". " + this.reasons.get(i) + "\n";
            }
            appointmentString += "Measurements Taken:\n";
            for (int i = 0; i < this.measurementsTaken.size(); i++) {
                appointmentString += (i + 1) + ". " + this.measurementsTaken.get(i).getName() + ": " + this.measurementsTaken.get(i).getMeasurement()
                + this.measurementsTaken.get(i).getUnits() + "\n";
            }
            appointmentString += "Notes:\n";
            for (int i = 0; i < this.notes.size(); i++) {
                appointmentString += (i + 1) + ". " + this.notes.get(i);
            }
            return appointmentString;
        }
    }
