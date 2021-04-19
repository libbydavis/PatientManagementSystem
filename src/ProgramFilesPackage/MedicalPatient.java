package ProgramFilesPackage;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class MedicalPatient implements Patient{
    private String fName;
    private String lName;
    private int age;
    private String phoneNumber;
    private String address;
    private final String NHI;
    private HashSet<MedicalCondition> conditions;
    private HashSet<Medication> currentMedications;
    private ArrayList<Measurement> measurements;
    private ArrayList<Prescription> prescriptions;
    private ArrayList<Appointment> appointmentsHistory;


    public MedicalPatient(String nhi) { 
        NHI = nhi;
        conditions = new HashSet<>();
        currentMedications = new HashSet<>();
        measurements = new ArrayList<>();
        prescriptions = new ArrayList<>();
        appointmentsHistory = new ArrayList<>();
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @author -LibbyDavis
     * @param nhi
     * @return boolean
     * uses findPatientInDatabase() to return a new patient,
     * checks if the patient is valid by checking if the details are null,
     * prints error message if needed and returns true or false to show if the NHI was valid or not
     */
    public boolean validateNHI(String nhi) throws IOException {
        Patient patient = findPatientInDatabase(nhi);
        if (patient.getName() != null) {
            return true;
        }
        System.out.println("Please enter a valid NHI");
        return false;
    }

    /**
     * @author -LibbyDavis
     * @return String
     * gets an NHI input that fits the criteria
     */
    public String getValidNHI() {
        Scanner scan = new Scanner(System.in);
        boolean valid = false;
        String nhi = "";
        while (!valid) {
            System.out.println("Please enter the NHI of the next patient: ");
            nhi = scan.next();
            if (nhi.length() == 6) {
                valid = true;
            }
            else {
                System.out.println("Please enter a valid NHI");
            }
        }
        return nhi;
    }

    @Override
    public void saveToAppointmentsHistory(Appointment a) throws IOException {
        this.appointmentsHistory.add(a);

        replacePatient(this);
    }

    @Override
    public String getName() {
        return fName;
    }

    /**
     * @author -LibbyDavis
     * @param nhi
     * @return Patient
     * gets array of all patients from database using deserializePatients(), then searches for the target NHI using a for loop
     */
    @Override
    public Patient findPatientInDatabase(String nhi) throws IOException {
        //get Patients from database
        MedicalPatient[] allPatients = deserializePatients();

        //find patient
        Patient p1 = new MedicalPatient(nhi);
        for (int i = 0; i < allPatients.length; i++) {
            if (allPatients[i].NHI.equals(nhi)) {
                p1 = allPatients[i];
                return p1;
            }
        }
        return p1;
    }

    /**
     * @author -LibbyDavis
     * gets all information for a new patient, then saves it to the database using addPatientToDatabase()
     */
    public void addPatient() throws IOException {
        String nhi = getValidNHI();
        MedicalPatient currentPatient = new MedicalPatient(nhi);

        //get details
        Scanner scan = new Scanner(System.in);
        //first name
        System.out.println("First name:");
        String fName = scan.nextLine();
        currentPatient.setfName(fName);

        //last name
        System.out.println("Last name:");
        String lName = scan.nextLine();
        currentPatient.setlName(lName);

        //age
        System.out.println("Age:");
        int age = 0;
        age = Checker.IntegerInput(age);
        currentPatient.setAge(age);

        //phone number
        System.out.println("Phone number:");
        String phoneNumber = scan.nextLine();
        currentPatient.setPhoneNumber(phoneNumber);

        //address
        System.out.println("Address:");
        String address = scan.nextLine();
        currentPatient.setAddress(address);

        //add to conditions, etc.
        String addChoice = "";
        while (!addChoice.equals("4")) {
            System.out.println("Would you like to add to any of these (enter number to select):\n1. Conditions\n2. Current Medications\n3. Measurements\n4. None");
            addChoice = scan.nextLine();
            switch (addChoice) {
                case "1":
                    MedicalCondition getConditions = new MedicalCondition();
                    getConditions.enterConditions(currentPatient.conditions);
                    break;
                case "2":
                    Medication.printMedInfo();
                    //add in medications
                    break;
                case "3":
                    Measurement getMeasurements = new Measurement();
                    getMeasurements.enterMeasurement(currentPatient.measurements);
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Please enter a number from 1-4.");
                    break;
            }
        }

        currentPatient.displayPatientDetails();
        System.out.println("Enter y to confirm or x to exit: ");
        String confirm = scan.next();
        if (confirm.equals("y")) {
            addPatientToDatabase(currentPatient);
        }
        else if (confirm.equals("x")) {
            System.out.println("Add Patient Cancelled");
        }
    }

    /**
     * @author -LibbyDavis
     * @param patientReplace
     * gets patients from database using deserializePatients() in the form of an array,
     * then finds matching patient that is going to be replaced in the array,
     * sets the matching patient in the array to the new patient,
     * saves this using savePatientsToDatabase()
     */
    public void replacePatient(MedicalPatient patientReplace) throws IOException {
        //read in all patients to memory
        MedicalPatient[] allPatients = deserializePatients();

        //find matching item
        int removeIndex = -1;
        int i = 0;
        while (removeIndex == -1){
            if (allPatients[i].NHI.equals(patientReplace.NHI)) {
                removeIndex = i;
            }
            else if ((i + 1) >= allPatients.length) {
                removeIndex = 0;
            }
            i++;
        }
        //replace
        allPatients[removeIndex] = patientReplace;

        //add back to database
        savePatientsToDatabase(allPatients);
        System.out.println("Saved to Database");

    }

    /**
     * @author -LibbyDavis
     * @param currentPatient
     * @return void
     * reads in patients into array from patient database using deserializePatients(),
     * then adds new patient to array
     * and then writes array back out to patient database file using savePatientsToDatabase()
     */
    public void addPatientToDatabase(MedicalPatient currentPatient) throws IOException {

        //read in all patients to memory
        MedicalPatient[] allPatients = deserializePatients();

        //create bigger array
        MedicalPatient[] newPatientList = new MedicalPatient[allPatients.length + 1];
        for (int i = 0; i < allPatients.length; i++) {
            newPatientList[i] = allPatients[i];
        }

        //add patient to array
        newPatientList[newPatientList.length - 1] = currentPatient;

        //save patients to database
        savePatientsToDatabase(newPatientList);
        System.out.println("Saved Patient");
    }


    /**
     * @author -LibbyDavis
     * gets patients from database using deserializePatients() and then prints out basic details of every patient,
     * then asks user if they want to see more details and uses displayPatientDetails() to print out all the details of a patient as needed
     */
    public void listAllPatients() throws IOException {
        //get patients
        MedicalPatient[] allPatients = deserializePatients();

        //display patients
        for (MedicalPatient mp : allPatients) {
            System.out.println(mp.fName + " " + mp.lName + ", age: " + mp.age + ", NHI: " + mp.NHI);
        }

        //print more details
        Scanner scan = new Scanner(System.in);
        boolean validNHI = false;
        String patientNHI = "";
        while (!patientNHI.equals("x")) {
            System.out.println("Would you like to see more details?");
            while (!validNHI && !patientNHI.equals("x")) {
                System.out.println("Enter the NHI for the patient that you want to see more (or x to exit)");
                patientNHI = scan.nextLine();
                if (!patientNHI.equals("x"))
                    validNHI = validateNHI(patientNHI);
            }
            if (!patientNHI.equals("x")) {
                Patient more = findPatientInDatabase(patientNHI);
                more.displayPatientDetails();
                validNHI = false;
            }
        }

    }

    /**
     * @author -LibbyDavis
     * @param listPatients
     * @return void
     * converts a MedicalPatient[] array to json and writes to the patient database
     */
    public void savePatientsToDatabase(MedicalPatient[] listPatients) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(listPatients);

        BufferedWriter bWrite = new BufferedWriter(new FileWriter(new File("src/ProgramFilesPackage/Database.txt")));
        bWrite.write(json);
        bWrite.close();
    }

    /**
     * @author -LibbyDavis
     * @return MedicalPatient[]
     * reads patient information from the database and converts it from json to an array to be used by other methods
     */
    public MedicalPatient[] deserializePatients() throws IOException {
        //read in all patients
        BufferedReader bread = new BufferedReader(new FileReader(new File("src/ProgramFilesPackage/Database.txt")));
        Gson gson = new Gson();
        String allText = bread.readLine();
        MedicalPatient[] patientsList = gson.fromJson(allText, MedicalPatient[].class);
        bread.close();

        return patientsList;
    }

    /**
     * @author -LibbyDavis
     * displays all the patient details in an easy to read format
     */
    public void displayPatientDetails() {
        System.out.println("NHI: " + this.NHI);
        System.out.println("Name: " + this.fName + " " + this.lName);
        System.out.println("Age: " + this.age);
        System.out.println("Contact Details:\nPhone: " + this.phoneNumber + "\nAddress: " + this.address);
        if (conditions.size() > 0) {
            System.out.println("Conditions: ");
            for (MedicalCondition med : conditions) {
                System.out.println(med.getName() + ": " + med.getDescription());
            }
        }
        if (currentMedications.size() > 0) {
            System.out.println("Current Medications: ");
            Iterator<Medication> iterate = currentMedications.iterator();
            while(iterate.hasNext()){
                Medication thisOne = iterate.next();
                System.out.println(thisOne.getName() + ": " + thisOne.getDosage());
            }
        }
        if (measurements.size() > 0) {
            System.out.println("Measurements: ");
            Iterator<Measurement> iterator = measurements.iterator();
            while(iterator.hasNext()){
                Measurement thisOne = iterator.next();
                System.out.println(thisOne.getName() + ": " + thisOne.getMeasurement() + thisOne.getUnits());
            }
        }
    }
}
