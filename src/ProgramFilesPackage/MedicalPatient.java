package ProgramFilesPackage;

import java.util.ArrayList;
import java.util.HashSet;

public class MedicalPatient implements Patient{
    private String name;
    private int age;
    private final String NHI;
    private HashSet<MedicalCondition> conditions;
    private HashSet<Medication> currentMedications;
    private HashSet<Measurement> measurements;
    private ArrayList<Prescription> prescriptions;

    public MedicalPatient(String nhi) {
        NHI = nhi;
    }
}
