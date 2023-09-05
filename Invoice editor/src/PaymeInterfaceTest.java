
// LAB 6: PaymeInterfaceTest.java

//This is the test for your interface Payme.

public class PaymeInterfaceTest  {

public static void main(String[] args) {
	
 //TO DO: IN THIS PORTION OF THE CODE, SEVERAL CHANGES SHOULD BE MADE:
// CREATE SIX-ELEMENTS Payme array HERE 
	
 //START CODE 
	Payme[] payme = new Payme[6];
	
	payme[0] = new Invoice("2276", "brake", 3, 300.00);
	payme[1] = new Invoice("2300", "gear", 5, 90.99);
	payme[2] = new SalariedProgrammer("Chioma", "Chidima", "345-67-0001", 10, 2000, 320);
	payme[3] = new HourlyProgrammer("Amara", "Chukuwu", "234-56-7770", 10, 2000, 18.95, 40);
	payme[4] = new CommissionProgrammer("Peter", "Goodman", "123-45-6999", 10, 2000, 16500, 0.44);
	payme[5] = new BasePlusCommissionProgrammer("Esther", "Patel", "123-45-6599", 10, 2000, 1200, 0.04, 720);
	
	

 System.out.println(
    "Payment for Invoices and Programmers are processed polymorphically:\n"); 

 // generically process each element in array paymeObjects
 
 for (Payme pay : payme) {
	 
	 if (pay instanceof BasePlusCommissionProgrammer) {
		 BasePlusCommissionProgrammer programmer = (BasePlusCommissionProgrammer) pay;
         ((BasePlusCommissionProgrammer) pay).setBaseSalary(programmer.getBaseSalary()*(1+ BasePlusCommissionProgrammer.SALARYINCREASE) );
         
     }

     System.out.println(pay.toString());
     System.out.println();
 

// TO DO: INSERT YOUR PRINT STATEMENT HERE: ENSURE THAT YOUR 
// OUTPUT FOLLOWS THE OUTPUT SAMPLE PROVIDED

// START CODE 

// END CODE

 }
}
}


