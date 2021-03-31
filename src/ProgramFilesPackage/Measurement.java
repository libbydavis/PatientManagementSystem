package ProgramFilesPackage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Measurement {
	String name;
	Double measurement;
	String units;

	public Measurement() {
		name = "";
		measurement = 0.0;
		units = "";
	}

	public Measurement(String name, Double measurement, String units) {
		this.name = name;
		this.measurement = measurement;
		this.units = units;
	}

	public String getName() {
		return name;
	}

	public Double getMeasurement() {
		return measurement;
	}

	public String getUnits() {
		return units;
	}

	public void createMeasurement() {
		Scanner scan = new Scanner(System.in);

		//name
		System.out.println("Enter a name for the measurement: ");
		name = scan.nextLine();

		//measurement
		System.out.println("Enter the number you have measured: ");
		measurement = Checker.DoubleInput(measurement);

		//units
		System.out.println("Enter the units for this measurement: ");
		units = scan.nextLine();
	}

	public void enterMeasurement(ArrayList measurementList) {
		Measurement measure = new Measurement();
		boolean takingMeasurements = true;
		boolean inputValid = true;
		String addAnother = "";
		Scanner scan = new Scanner(System.in);

		do {
			if (inputValid == true) {
				measure.createMeasurement();
				measurementList.add(measure);
			}
			System.out.println("Would you like to add another measurement?\n1. Add another\n2. Finished");
			addAnother = scan.nextLine();
			switch (addAnother) {
				case "1":
					inputValid = true;
					break;
				case "2":
					takingMeasurements = false;
					break;
				default:
					inputValid = false;
					System.out.println("Please enter a number from 1-2.");
					break;
			}
		} while (takingMeasurements);
	}

}
