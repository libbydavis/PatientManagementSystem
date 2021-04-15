package ProgramFilesPackage;

import java.io.IOException;

public interface Patient {
    String getName();
    Patient findPatientInDatabase(String value) throws IOException;
    void displayPatientDetails();
    void listAllPatients() throws IOException;
    void saveToAppointmentsHistory(Appointment a) throws IOException;
}
