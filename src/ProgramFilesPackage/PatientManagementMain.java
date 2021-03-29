package ProgramFilesPackage;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PatientManagementMain {
    public static void main(String[] args) throws IOException {
        Appointment appointmentTest = new Appointment(new MedicalPatient("Poppy","ABC123"));
        //appointmentTest.runAppointment();
        
        // Testing ways to retrieve medicine 
        Medication.printMedInfo();  
    }
}
