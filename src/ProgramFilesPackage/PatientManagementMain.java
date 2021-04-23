package ProgramFilesPackage;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Runs the program
 * @author Raj and Libby
 */
public class PatientManagementMain {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to The Patient Management System");
        User userStart = new Doctor();
        userStart.userMenu(); 
    }
}
