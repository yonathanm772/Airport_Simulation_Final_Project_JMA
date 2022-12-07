/**
 * This class instantiates the global timer and starts the simulation
 * @author Jonathan Moreira Alsina
 * @version 12/07/2022
 */
           

public class Airport {
	
	Timer2 globalTimer;
	
	
	/**
	 * creates an airport
	 */
	public Airport() {
		globalTimer = new Timer2();
		globalTimer.start();
		
	}
	
}
