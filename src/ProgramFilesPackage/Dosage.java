package ProgramFilesPackage;

public class Dosage 
{  
	double amount;
    String howOften;
    
    public Dosage()
    {
    	this.amount = 0.0;
    	this.howOften = "UNDEFINED";
    }
	
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
