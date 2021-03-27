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
        System.out.println("New Appointment");
        //menu select
        Scanner scan = new Scanner(System.in);
        int menuChoice = 0;
        boolean choiceValid = false;
        while (!choiceValid) {
            try {
                System.out.println("Enter number to select:\n1. Enter reasons for appointment\n2. Enter a new measurement\n3. Enter a new note");
                menuChoice = IntegerChecker.IntegerInput(menuChoice, scan);
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
        
        /* Could you put this switch case into a try catch as above and make the default output "please enter a number from 1-3", and the switch case catches an InputMismatchException
         * like this...
         */
        
//        try {
//        	 switch (menuChoice) {
//             case 1:
//                 enterReason();
//                 break;
//             case 2:
//                 enterMeasurement();
//                 break;
//             case 3:
//                 enterNote();
//                 break;
//             default:
//            	 System.out.println("Please enter a number from 1-3.");
//                 break;
//             }	
//        }
//        catch (InputMisMatchException e) {
//        	System.out.println("Please enter a number");
//        	scan.next();
//        }
        

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
            printReasons();

            int change = 0;
            System.out.println("Enter number to select:\n1. Delete a reason\n2. Edit a reason\n3. Confirm and Exit");
            change = IntegerChecker.IntegerInput(change, scan);
            switch (change) {
                case 1:
                    printReasons();
                    while (change != 0) {
                        System.out.println("Enter the number of the reason to delete (or 0 to stop): ");
                        change = IntegerChecker.IntegerInput(change, scan);
                        if (change != 0) {
                            reasons.remove(change - 1);
                            System.out.println("Reason removed.\nCurrent Reasons:");
                            printReasons();
                        }
                    }

                    break;
                case 2:
                    printReasons();
                    System.out.println("Enter the number of the reason to edit: ");
                    change = scan.nextInt();
                    break;
                case 3:
                    System.out.println("Confirmed");
                    break;
            }
        }
        private void printReasons() {
            System.out.println("Reasons: ");
            int i = 1;
            for (String r : this.reasons) {
                System.out.println(i + ". " + r);
                i++;
            }
        }

        private void enterMeasurement() {
        }

        private void enterNote() {
        }
    }
