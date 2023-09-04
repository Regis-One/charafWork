package application;
public class ClocksDemo {
    public static void main(String[] args) {
    	
        ClockWork[] clocks = new ClockWork[3];

        clocks[0] =  new Clock();
        clocks[1] =  new WorldClock(4);
        clocks[2] =  new AlarmClock("8", "30");

        for (ClockWork clock : clocks) {
            String className = clock.getClass().getSimpleName();
            String time = clock.getTime();
            System.out.println("_" + className + "_");
            System.out.println(time);
            System.out.println();

        }
            
        
    }
    
}