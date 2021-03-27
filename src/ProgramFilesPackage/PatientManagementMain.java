package ProgramFilesPackage;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PatientManagementMain {
    public static void main(String[] args) throws IOException {
        Appointment appointmentTest = new Appointment(new MedicalPatient("ABC123"));
        appointmentTest.runAppointment();
        
        // Testing ways to retrieve medicine 
        // System.out.println(Medication.medicationList().get(0));
    }
}
