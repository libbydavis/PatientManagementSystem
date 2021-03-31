package ProgramFilesPackage;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class MedicalPatient implements Patient{
    private String fName;
    private String lName;
    private int age;
    private String phoneNumber;
    private String address;
    private final String NHI;
    private HashSet<MedicalCondition> conditions;
    private HashSet<Medication> currentMedications;
    private ArrayList<Measurement> measurements;
    private ArrayList<Prescription> prescriptions;
    private ArrayList<Appointment> appointmentsHistory;


    public MedicalPatient(String nhi) {
        NHI = nhi;
        conditions = new HashSet<>();
        currentMedications = new HashSet<>();
        measurements = new ArrayList<>();
        prescriptions = new ArrayList<>();
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static void testDatabaseIn() throws IOException {
        //using test data to see function with Gson
        MedicalPatient p1 = new MedicalPatient("abc123");
        p1.fName = "Abby";
        p1.age = 15;
        p1.conditions.add(new MedicalCondition("Allergy", "anaphylaxis to peanuts"));

        MedicalPatient p2 = new MedicalPatient("def456");
        p2.fName = "David";
        p2.age = 45;
        p2.conditions.add(new MedicalCondition("Allergy", "anaphylaxis to peanuts"));
        p2.measurements.add(new Measurement("Blood Pressure", 4.5, "bpm"));

        BufferedWriter bwrite = new BufferedWriter(new FileWriter(new File("src/ProgramFilesPackage/Database.txt")));
        Gson gson = new Gson();

        String json = gson.toJson(p1);
        bwrite.write(json);

        json = gson.toJson(p2);
        bwrite.write(json);

        bwrite.close();
    }

    public static void testDatabaseOut() throws IOException {
        //testing deserializing
        BufferedReader bread = new BufferedReader(new FileReader(new File("src/ProgramFilesPackage/Database.txt")));
        Gson gson = new Gson();
        String allText = bread.readLine();

        MedicalPatient[] p2 = gson.fromJson(allText, MedicalPatient[].class);
        System.out.println("done");
    }

    @Override
    public String getName() {
        return fName;
    }

    @Override
    public Patient findPatientInDatabase(String nhi) throws IOException {
        //get Patients from database
        MedicalPatient[] allPatients = deserializePatients();

        //find patient
        Patient p1 = new MedicalPatient(nhi);
        for (int i = 0; i < allPatients.length; i++) {
            if (allPatients[i].NHI.equals(nhi)) {
                p1 = allPatients[i];
                return p1;
            }
        }
        return p1;
    }

    public void addPatient() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the NHI of the new patient: ");
        String nhi = scan.nextLine();
        MedicalPatient currentPatient = new MedicalPatient(nhi);

        //get details
        //first name
        System.out.println("First name:");
        String fName = scan.nextLine();
        currentPatient.setfName(fName);

        //last name
        System.out.println("Last name:");
        String lName = scan.nextLine();
        currentPatient.setlName(lName);

        //age
        System.out.println("Age:");
        int age = 0;
        age = Checker.IntegerInput(age);
        currentPatient.setAge(age);

        //phone number
        System.out.println("Phone number:");
        String phoneNumber = scan.nextLine();
        currentPatient.setPhoneNumber(phoneNumber);

        //address
        System.out.println("Address:");
        String address = scan.nextLine();
        currentPatient.setAddress(address);

        //add to conditions, etc.
        String addChoice = "";
        while (!addChoice.equals("4")) {
            System.out.println("Would you like to add to any of these (enter number to select):\n1. Conditions\n2. Current Medications\n3. Measurements\n4. None");
            addChoice = scan.nextLine();
            switch (addChoice) {
                case "1":
                    MedicalCondition getConditions = new MedicalCondition();
                    getConditions.enterConditions(currentPatient.conditions);
                    break;
                case "2":
                    System.out.println("medications add");
                    //add in medications
                    break;
                case "3":
                    Measurement getMeasurements = new Measurement();
                    getMeasurements.enterMeasurement(currentPatient.measurements);
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Please enter a number from 1-4.");
                    break;
            }
        }


        //read in all patients to memory
        MedicalPatient[] allPatients = deserializePatients();

        //create bigger array
        MedicalPatient[] newPatientList = new MedicalPatient[allPatients.length + 1];
        for (int i = 0; i < allPatients.length; i++) {
            newPatientList[i] = allPatients[i];
        }

        //add patient to array
        newPatientList[newPatientList.length - 1] = currentPatient;

        //save patients to database
        savePatientsToDatabase(newPatientList);
        System.out.println("Saved Patient");
    }

    public void listAllPatients() throws IOException {
        //get patients
        MedicalPatient[] allPatients = deserializePatients();

        //display patients
        for (MedicalPatient mp : allPatients) {
            System.out.println(mp.fName + " " + mp.lName + ", age: " + mp.age + ", NHI: " + mp.NHI);
        }

        //print more details
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to see more details? Enter the NHI for the patient that you want to see more");
        String patientNHI = scan.nextLine();
        Patient more = findPatientInDatabase(patientNHI);
        more.displayPatientDetails();
    }

    public void savePatientsToDatabase(MedicalPatient[] listPatients) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(listPatients);

        BufferedWriter bWrite = new BufferedWriter(new FileWriter(new File("src/ProgramFilesPackage/Database.txt")));
        bWrite.write(json);
        bWrite.close();
    }

    public MedicalPatient[] deserializePatients() throws IOException {
        //read in all patients
        BufferedReader bread = new BufferedReader(new FileReader(new File("src/ProgramFilesPackage/Database.txt")));
        Gson gson = new Gson();
        String allText = bread.readLine();
        MedicalPatient[] patientsList = gson.fromJson(allText, MedicalPatient[].class);
        bread.close();

        return patientsList;
    }

    public void displayPatientDetails() {
        System.out.println("NHI: " + this.NHI);
        System.out.println("Name: " + this.fName + " " + this.lName);
        System.out.println("Age: " + this.age);
        System.out.println("Contact Details:\nPhone: " + this.phoneNumber + "\nAddress: " + this.address);
        if (conditions.size() > 0) {
            System.out.println("Conditions: ");
            for (MedicalCondition med : conditions) {
                System.out.println(med.getName() + ": " + med.getDescription());
            }
        }
        if (currentMedications.size() > 0) {
            System.out.println("Current Medications: ");
            Iterator<Medication> iterate = currentMedications.iterator();
            while(iterate.hasNext()){
                System.out.println(iterate.next().getName() + ": " + iterate.next().getDosage());
            }
        }
        if (measurements.size() > 0) {
            System.out.println("Measurements: ");
            Iterator<Measurement> iterator = measurements.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next().getName() + ": " + iterator.next().getMeasurement() + iterator.next().getUnits());
            }
        }
    }
}
