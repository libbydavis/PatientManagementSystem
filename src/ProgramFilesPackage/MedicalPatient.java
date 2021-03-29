package ProgramFilesPackage;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class MedicalPatient implements Patient{
    private String fName;
    private int age;
    private final String NHI;
    private HashSet<MedicalCondition> conditions;
    private HashSet<Medication> currentMedications;
    private HashSet<Measurement> measurements;
    private ArrayList<Prescription> prescriptions;


    public MedicalPatient(String nhi) {
        NHI = nhi;
        conditions = new HashSet<>();
        currentMedications = new HashSet<>();
        measurements = new HashSet<>();
        prescriptions = new ArrayList<>();
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setAge(int age) {
        this.age = age;
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
        //name
        System.out.println("Name:");
        String name = scan.nextLine();
        currentPatient.setfName(name);

        //age
        System.out.println("Age:");
        int age = 0;
        age = Checker.IntegerInput(age);
        currentPatient.setAge(age);

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

}
