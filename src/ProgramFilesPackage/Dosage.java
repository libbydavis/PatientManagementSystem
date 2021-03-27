package ProgramFilesPackage;

public class Dosage {
    
	double amount;
    String howOften;
	
    public Dosage(double amount, String howOften) 
    {
		this.amount = amount;
		this.howOften = howOften;
	}

	public String toString() 
	{
		return "Dosage amount: " +amount + ", Frequency: " + howOften;
	}   
}
