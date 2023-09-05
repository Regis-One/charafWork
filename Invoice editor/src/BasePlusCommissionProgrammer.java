
// THIS IS A CODE FILE FOR LAB 6. THIS CODE FILE REQUIRES SOME MODIFICATIONS!!!


//BasePlusCommissionProgrammer.java
//BasePlusCommissionProgrammer class extends CommissionProgrammer.

public class BasePlusCommissionProgrammer extends CommissionProgrammer { 

private double baseSalary; // base salary per week
public  static final double SALARYINCREASE = 0.1;

//constructor
public BasePlusCommissionProgrammer(String firstName, String lastName, 
String socialSecurityNumber, int month, int year, 
double grossSales, double commissionRate, double baseSalary) {
super(firstName, lastName, socialSecurityNumber, 
  month, year, grossSales, commissionRate);

if (baseSalary < 0.0) { // validate baseSalary                  
  throw new IllegalArgumentException("Base salary must be >= 0.0");
}

this.baseSalary = baseSalary;                
}

//set base salary
public void setBaseSalary(double baseSalary) {
if (baseSalary < 0.0) { // validate baseSalary                  
  throw new IllegalArgumentException("Base salary must be >= 0.0");
}

this.baseSalary = baseSalary;                
} 

//return base salary
public double getBaseSalary() {
return baseSalary;
}

//calculate earnings; override method earnings in CommissionProgrammer

//MODIFY THIS PORTION. WHY WILL THIS PORTION OF YOUR CODE REQUIRE MODIFICATION?
//PROVIDE THE ANSWER TO THIS QUESTION TO YOUR PROFESSOR DURING DEMO

@Override                                                            
public double getPaymentAmount() {                                             
return  getBaseSalary() + super.getPaymentAmount();                        
} 

//return String representation of BasePlusCommissionProgrammer object
@Override
public String toString() {
    String className = "Base-plus Commission Programmer";
    return String.format("%s: %s %s%n%s: %s%n%s: $%.2f; %s: %.2f; %s: $%.2f%n%s: $%.2f%n%s: $%.2f",
        className, getFirstName(), getLastName(), "Social Security Number", getSocialSecurityNumber(),
        "Gross Sales", getGrossSales(), "Commission Rate", getCommissionRate(), "Base Salary", getBaseSalary(),
        "New Base Salary with 10% increase is", getBaseSalary(), "Payment Due", getPaymentAmount());
}

}
                                                      
//TO DO: COMEPLETE THIS PORTION. Format your solution according to sample output.     
//START     
// INSERT YOUR CODE
//END






