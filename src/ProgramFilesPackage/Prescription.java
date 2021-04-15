package ProgramFilesPackage;

public class Prescription 
{
    String date;
    String time;
    Medication meds;
    String doctorName;
    Patient patientDetails;
    Boolean repeat;
	
    public Prescription(String date, String time, Medication meds, String doctorName, Patient patientDetails, Boolean repeat) 
    {	
        this.date = date;
        this.time = time;
        this.meds = meds;
        this.doctorName = doctorName;
        this.patientDetails = patientDetails;
        this.repeat = repeat;
    }
    
    // Be able to assign a medication to a prescription. 
    // E.g., putting on ibuprofen, panadol 'into' a prescription. Under the meds attribute
    // Be able to add a 'dosage' to meds.
    // Be able to edit a patients details once they've entered it. E.g., their address, phone no, age etc.
}
