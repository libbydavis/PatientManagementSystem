package ProgramFilesPackage;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MedicalPatient implements Patient
{
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

    public MedicalPatient(String nhi) 
    { 
        NHI = nhi;
        conditions = new HashSet<>();
        currentMedications = new HashSet<>();
        measurements = new ArrayList<>();
        prescriptions = new ArrayList<>();
        appointmentsHistory = new ArrayList<>();
    }

    public String getNHI()
    {
        return NHI;
    }

    public void setfName(String fName) 
    {
        this.fName = fName;
    }

    public void setlName(String lName) 
    {
        this.lName = lName;
    }

    public void setAge(int age) 
    {
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public void setPrescriptions(ArrayList<Prescription> prescriptions) 
    {
        this.prescriptions = prescriptions;
    }
    
     public ArrayList<Prescription> getPrescriptions() 
    {
        return prescriptions;
    }
    
    public HashSet<Medication> getCurrentMedications() 
    {
        return currentMedications;
    }
    
    public void setCurrentMedications(HashSet<Medication> currentMedications) 
    {
        this.currentMedications = currentMedications;
    }

    public ArrayList<Measurement> getMeasurements() 
    {
        return measurements;
    }

    public void setMeasurements(ArrayList<Measurement> measurements) 
    {
        this.measurements = measurements;
    }
    
    public HashSet<MedicalCondition> getConditions() 
    {
        return conditions;
    }

    public void setConditions(HashSet<MedicalCondition> conditions) 
    {
        this.conditions = conditions;
    }
    @Override
    public String getName() {
        return fName;
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

    public String getPatientStringDetail(String text) {
        Scanner scan = new Scanner(System.in);
        String input = "";
        while (input.length() < 2) {
            System.out.println(text + ":");
            input = scan.nextLine();
            if (input.length() < 2) {
                System.out.println("Please enter at least 2 characters.");
            }
        }
        return input;
    }

    public int getPatientIntDetail(String text) {
        int num = 0;
        System.out.println(text + ":");
        num = Checker.IntegerInput(num);
        return num;
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
        String fName = getPatientStringDetail("First name");
        currentPatient.setfName(fName);

        //last name
        String lName = getPatientStringDetail("Last name");
        currentPatient.setlName(lName);

        //age
        int age = getPatientIntDetail("Age");
        currentPatient.setAge(age);

        //phone number
        String phoneNumber = getPatientStringDetail("Phone number");
        currentPatient.setPhoneNumber(phoneNumber);

        //address
        String address = getPatientStringDetail("Address");
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
                    String existingMeds = "";
                    Medication.printMedList();
                    System.out.println();
                    while(true)
                    {
                        System.out.println("Enter the MedNo# of the current medication the patient is prescribed (press x to exit)");
                        existingMeds = scan.nextLine();
                        
                        if(existingMeds.equalsIgnoreCase("x"))
                        {
                            break;
                        }
                        
                        Medication.validateMeds(existingMeds);
                        Medication temp = Medication.getMeds(existingMeds);
                        currentMedications.add(temp);
                        currentPatient.setCurrentMedications(currentMedications);
                    }
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
     * This prompts the doctor to edit all the attributes of a MedicalPatient and then
     * updates the changes within the database.
     * @param NHI This is the NHI of the patient that the doctor wants to edit the details of.
     * @throws IOException 
     * @author Raj
     */
    public void editPatient(String NHI) throws IOException
    {
        Scanner scan = new Scanner(System.in);
        Patient[] allPatients = deserializePatients();
        MedicalPatient medPatientObj = (MedicalPatient) allPatients[0].findPatientInDatabase(NHI);
        HashSet<Medication> currentMeds = medPatientObj.getCurrentMedications(); //  Stores the Medications of the MedicalPatient the doctor wants to edit.
        ArrayList<Measurement> currentMeasurements = medPatientObj.getMeasurements(); // Stores the Measurements of the MedicalPatient the doctor wants to edit.
        ArrayList<Prescription> currentPrescriptions = medPatientObj.getPrescriptions(); // Stores the Prescriptions of the MedicalPatient the doctor wants to edit.
        HashSet<MedicalCondition> currentConditions = medPatientObj.getConditions(); // Stores the Conditions of the MedicalPatient the doctor wants to edit.
        allPatients[0].findPatientInDatabase(NHI).displayPatientDetails(); // Shows the current values of each attribute of the Medical Patient.
        boolean runningEdit = true;
        
        while (runningEdit) 
        {
            // Prompt for all the attributes of a MedicalPatient that can be edited
            System.out.println("\nWhat details of this patient would you like to edit? Enter a number to select:");
            System.out.println("1. First Name" + "\n2. Last Name" + "\n3. Age" + "\n4. Phone Number" + "\n5. Address" + 
                    "\n6. Current Medication" + "\n7. Measurements" + "\n8. Prescriptions" + "\n9. Conditions" + "\n10. Exit Edit Menu");
            String patientAttribute = scan.nextLine();
            switch (patientAttribute) 
            {
                case "1":
                    String updatedFName = getPatientStringDetail("Enter updated first name");
                    medPatientObj.setfName(updatedFName);
                    runningEdit = false;
                    break;
                case "2":
                    String updatedLName = getPatientStringDetail("Enter updated last name");
                    medPatientObj.setlName(updatedLName);
                    runningEdit = false;
                    break;
                case "3":
                    int age = getPatientIntDetail("Enter updated age");
                    medPatientObj.setAge(age);
                    runningEdit = false;
                    break;
                case "4":
                    String phoneNumber = getPatientStringDetail("Enter updated phone number");
                    medPatientObj.setPhoneNumber(phoneNumber);
                    runningEdit = false;
                    break;
                case "5":
                    String address = getPatientStringDetail("Enter updated address");
                    medPatientObj.setAddress(address);
                    runningEdit = false;
                    break;
                case "6":
                    boolean updatingMeds = true;
                    
                    while (updatingMeds) 
                    {
                        // Prompts to either add, remove, or exit the medication editing UI 
                        System.out.println("Enter a number to select:");
                        System.out.println("1. Add a medication" + "\n2. Remove a medication" + "\n3. Exit medication editing");
                        String updatedMeds = scan.nextLine();
                        switch (updatedMeds) 
                        {
                            case "1": // What happens when the user wants to add a medication.
                                // Asks for the medication that the doctor wants to add.
                                System.out.println("What medication would you like to add?");
                                String newMeds;
                                Medication.printMedList(); // Shows the doctor a list of medication to choose from.
                                boolean enteringMeds = true;
                                do 
                                {
                                    // Allows the doctor to select the medicatioin by the key value within MedicationList.txt
                                    System.out.println("Enter the MedNo# of the medication you'd like to add to the patient (press x to stop)");
                                    newMeds = scan.nextLine();
                                    // If the doctor does not want to add more medication they can press 'x' to stop.
                                    if (newMeds.equalsIgnoreCase("x")) 
                                    {
                                        enteringMeds = false;
                                        continue;                    
                                    }
                                    // validates meds and stores it back into the MedicalPatient 
                                    // that is currently being edited.
                                    while(enteringMeds)
                                    {
                                        Medication.validateMeds(newMeds);
                                        Medication addedMeds = Medication.getMeds(newMeds);
                                        currentMeds.add(addedMeds);
                                        medPatientObj.setCurrentMedications(currentMeds);
                                        enteringMeds = false;
                                    }
                                    enteringMeds = true;
                                }while (enteringMeds);
                                break;
                            case "2":
                                // Displays all the Medication that the patient that is being edited currently has.
                                Iterator<Medication> it = currentMeds.iterator();
                                System.out.println(medPatientObj.getName() + "'s current medication:\n");
                                int j = 1;
                                while (it.hasNext()) 
                                {
                                    System.out.println(j + ". " + it.next().getName());
                                    j++;
                                }
                                // Asks the doctor to enter the name of the medication they want to remove from the patient
                                // that is being edited.
                                System.out.println("\nEnter the name of the medication you'd like to remove");
                                String uInput = scan.nextLine();
                                while(Medication.searchMedsByName(uInput) == null)
                                {
                                    System.out.println("This does not exist, please try again");
                                    uInput = scan.nextLine();
                                }
                                // Stores the updated medication back into the patient.
                                currentMeds.remove(Medication.searchMedsByName(uInput));
                                medPatientObj.setCurrentMedications(currentMeds);
                                break;
                            case "3":
                                updatingMeds = false;
                                System.out.println("Exited medication editing");
                                break;
                            default:
                                System.out.println("Incorrect input, please try again");
                                break;
                        }
                    }
                    runningEdit = false;
                    break;
                case "7":
                    boolean updatingMeasurements = true;
                    while(updatingMeasurements)
                    {   // Prompts the doctor for all the ways they can manipulate the current
                        // MedicalPatient's measurement data.
                        System.out.println("Enter a number to select:");
                        System.out.println("1. Add a measurement");
                        System.out.println("2. Remove a measurement");
                        System.out.println("3. Exit measurement editing");
                        String updateMeasurements = scan.nextLine();
                        
                        switch (updateMeasurements) 
                        {
                            case "1":
                                Measurement newMeasurement = new Measurement();
                                newMeasurement.enterMeasurement(currentMeasurements); // Prompts the doctor for the measurement they can enter.
                                break;
                            case "2":
                                int indexDisplay = 1;
                                // Displays all current measurements that belong to the MedicalPatient that is being edited.
                                for (Measurement m : currentMeasurements) 
                                {
                                    System.out.println(indexDisplay + ". " + m.getName());
                                    indexDisplay++;
                                }
                                
                                // Prompts the doctor for the measuremnt they want to remove.
                                System.out.println("Enter the number of the measurement you'd like to remove");
                                String indexChosen = scan.nextLine();
                                int index = Integer.parseInt(indexChosen);
                                currentMeasurements.remove(index - 1);
                                break;
                            case "3":
                                updatingMeasurements = false;
                                System.out.println("Exited measurement editing");
                                break;
                            default:
                                System.out.println("Incorrect input, please select either 1 or 2.");
                                break;
                        }
                    }
                    runningEdit = false;
                    break;
                case "8":
                    boolean updatingPrescriptions = true;
                    while(updatingPrescriptions)
                    {
                        // Prompts the doctor for all the ways they can manipulate the current
                        // MedicalPatient's prescription data.
                        System.out.println("Enter a number to select:");
                        System.out.println("1. Add a prescription");
                        System.out.println("2. Remove a prescription");
                        System.out.println("3. Exit prescription editing");
                        String updatedPrescriptions = scan.nextLine();
                        switch (updatedPrescriptions) 
                        {
                            case "1": // Prompts the doctor to fill out all the details for a Prescription Object
                                currentPrescriptions.add(Prescription.generatePrescription(medPatientObj.getNHI())); 
                                break;
                            case "2":
                                int indexDisplay = 1;
                                // Displays all current prescriptions
                                for (Prescription p : currentPrescriptions) 
                                {
                                    System.out.println(indexDisplay + ". Prescription" + p + "\n");
                                    indexDisplay++;
                                }
                                // Prompts user  to enter the number of the prescription they'd like to remove.
                                System.out.println("Enter the number of the prescription you'd like to remove:");
                                boolean catchException = true;
                                while(catchException)
                                {
                                    try
                                    {
                                        String indexChosen = scan.nextLine();
                                        int index = Integer.parseInt(indexChosen);
                                        currentPrescriptions.remove(index - 1);
                                        catchException = false;
                                    }
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Incorrect input, please try again");
                                    }
                                }
                                break;
                            case "3":
                                System.out.println("Exited prescription editing");
                                updatingPrescriptions = false;
                            default:
                                System.out.println("Incorrect input, please try again");
                                break;
                        }
                    }
                    runningEdit = false;
                    break;
                case "9":
                    boolean updatingConditions = true;
                    while(updatingConditions)
                    {    // Prompts the doctor for all the ways they can manipulate the current
                        // MedicalPatient's conditions data.
                        System.out.println("Enter a number to select:");
                        System.out.println("1. Add a condition");
                        System.out.println("2. Remove a condition");
                        System.out.println("3. Exit conditions editing");
                        String updateConditions = scan.nextLine();
                        switch (updateConditions) 
                        {
                            case "1":
                                MedicalCondition addedCondition = new MedicalCondition();
                                addedCondition.enterConditions(currentConditions);
                                medPatientObj.setConditions(currentConditions);
                                break;
                            case "2":
                                Iterator it = currentConditions.iterator();
                                HashMap<String, MedicalCondition> medCondReference = new HashMap<String, MedicalCondition>();
                                int c = 1;
                                //Stores current conditions into a HashMap and prints out the current conditions
                                while (it.hasNext()) 
                                {
                                    MedicalCondition currentCond = (MedicalCondition) it.next();
                                    System.out.println(c + ". Condition Name: " + currentCond.getName() + ", Condition Description: " + currentCond.getDescription());
                                    medCondReference.put(String.valueOf(c), new MedicalCondition(currentCond.getName(), currentCond.getDescription()));
                                    c++;
                                }
                                // Prompts to remove conditions
                                System.out.println("Enter the number that corresponds to the condition you want to remove:");
                                String removeCondition = scan.nextLine();
                                // checks whether the condition exists and verifies user input
                                while (!medCondReference.containsKey(removeCondition)) 
                                {
                                    System.out.println("This does not exist within the list of conditions, please try again");
                                    removeCondition = scan.nextLine();
                                }

                                currentConditions.remove(medCondReference.get(removeCondition));
                                medPatientObj.setConditions(currentConditions);
                                break;
                            case "3":
                                updatingConditions = false;
                                break;
                            default:
                                System.out.println("Incorrect input, please try again");
                                break;
                        }
                    }
                    runningEdit = false;
                    break;
                case "10":
                    runningEdit = false;
                    System.out.println("Exited edit menu");
                    return;      
                default:
                    System.out.println("Incorrect input, please try again");
                    break;
            }
        }
         
        //confirm that the updates are wanted.
        System.out.println("Do you want to update these details? (y/n)");
        String confirmUpdate = scan.nextLine();
        
        if(confirmUpdate.equalsIgnoreCase("y"))
        {
            System.out.println("Updated patient details:\n");
            replacePatient(medPatientObj);
            medPatientObj.displayPatientDetails();
            
        }
        else if(confirmUpdate.equalsIgnoreCase("n"))
        {
            System.out.println("Cancelled Update");
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
        if(prescriptions.size() > 0)
        {
            Iterator<Prescription> it = prescriptions.iterator();
            System.out.println("Prescriptions: ");
            while(it.hasNext())
            {
                Prescription presc = it.next();
                System.out.println("Date: " + presc.getDate() + "\nTime: " + presc.getTime() + "\nMedication: " +presc.getMeds().getName()+ "\nRepeat Prescription: " + presc.getRepeat() + "\n");
            }    
        }
    }
}
