package ProgramFilesPackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

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
}
