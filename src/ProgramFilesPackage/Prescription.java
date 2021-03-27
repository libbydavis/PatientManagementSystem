package ProgramFilesPackage;

public class Prescription {
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
}
