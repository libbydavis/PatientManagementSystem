package ProgramFilesPackage;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

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
    public void findPatientInDatabase() {

    }
}
