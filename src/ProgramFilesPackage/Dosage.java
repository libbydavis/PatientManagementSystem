package ProgramFilesPackage;

public class Dosage 
{  
    double amount;
    String howOften;

    public Dosage() 
    {
        setAmount(0.0);
        setHowOften("UNDEFINED");
    }
    
    public void setAmount(double amount) 
    {
        this.amount = amount;
    }
    
    public void setHowOften(String howOften) 
    {
        this.howOften = howOften;
    }
    
    public String toString() 
    {
        return "Dosage amount: " + amount + ", Frequency: " + howOften;
    }
}
