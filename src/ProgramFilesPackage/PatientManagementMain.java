package ProgramFilesPackage;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PatientManagementMain {
    public static void main(String[] args) throws IOException {
        FileWriter writer1 = new FileWriter(new File("src/ProgramFilesPackage/Database.txt"));
        Doctor doc1 = new Doctor();
        doc1.name = "Steve";
        doc1.age = 51;

        Gson gson = new Gson();
        String json = gson.toJson(doc1);

        writer1.write(json);
        writer1.close();
    }
}
