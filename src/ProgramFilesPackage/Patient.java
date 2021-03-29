package ProgramFilesPackage;

import java.io.IOException;

public interface Patient {
    String getName();
    Patient findPatientInDatabase(String value) throws IOException;
}
