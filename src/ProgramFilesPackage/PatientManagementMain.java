package ProgramFilesPackage;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PatientManagementMain {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to The Patient Management System");
        User userStart = new Doctor();
        userStart.userMenu();
        
        // Testing ways to retrieve medicine 
        // System.out.println(Medication.medicationList().get(0));
    }
}
