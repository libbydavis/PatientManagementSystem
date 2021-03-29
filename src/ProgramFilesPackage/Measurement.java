package ProgramFilesPackage;

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
}
