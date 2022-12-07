/**
 * Plane class that creates a plane with random random ID, model and distance.
 * @author Jonathan Moreira Alsina
 * @version 12/07/2022
 */

import java.util.Arrays;
import java.util.Random;
import java.util.List;

public class Plane implements Comparable<Plane> {
	
	int max = 9999;
	int min = 1000;
	int range = max - min;
	int minDistance = 50;
	int maxDistance = 100;
	int rangeDistance = maxDistance - minDistance;
	
	int planeID;
	String planeModel;
	public boolean emergency;
	public int distance = 0;
	Random rand = new Random();
	boolean onRunway;
	public int onRunwayTime;
	
	/**
	 * Creates a plane with random ID, model and distance
	 * from the airport
	 */
	public Plane() {
		
		planeID = (int)(Math.random() * range) + min;
		planeModel = randomModel(); 
		distance = (int)(Math.random() * rangeDistance) + minDistance;
		emergency = false;
		onRunway = false;
		onRunwayTime= 0;
	}
	
	/**
	 * It picks a random model for an airplane from a list
	 * @return random model of type string
	 */
	public String randomModel() {
	    List<String> model = Arrays.asList("Boeing", "Airbus");
	    String randomElement = model.get(rand.nextInt(model.size()));
	    return randomElement;
	}
	
	/**
	 * Prints the attributes of a plane
	 */
	public String toString() {
		String str;
		return str = ("" + planeModel+ " " +planeID+ " (" +distance+"-miles)" + " Emergency: "+emergency);
	}
	
	/**
	 * Gets the distance of the plane
	 * @return distance of type int
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * Gives a plane a new distance from the airport
	 * @param new int distance
	 * @return int distance
	 */
	public void setDistance(int distance) {
		 this.distance = distance;
	}
	
	/**
	 * Creates an emergency for the plane at random
	 * @return true if the emergency is created
	 */
	public boolean createEmergency() {
		if(emergency == false) {
			int chances = rand.nextInt(9);
			if( chances > 3) {
				emergency = true;
				System.out.println("-----AN EMERGENCY HAS BEEN CREATED-----"
						+ "");
								
			}
		}
		return  true;
		
	}
	
	/**
	 * Gets the emergency attribute of the plane
	 * @return boolean emergency 
	 */
	public boolean getEmergency (){
		return emergency;
	}
	
	/**
	 * Decreases the distance of the plane
	 * @return int type distance
	 */
	public int distanceDecreased() {
		return distance = distance - 20;
	}
	
	/**
	 * Tells if the plane is in a runway
	 * @param bool onRunway
	 */
	public void setOnRunway( boolean onRunway) {
		this.onRunway = onRunway;
	}

	/**
	 * Returns the time spent by the plane on the runway
	 * @return int time on runway
	 */
	public int getOnRunwayTime() {
		return onRunwayTime;
	}
	
	/**
	 * Sets the time spent by the plane on the runway
	 * @param int onRunwayTime
	 */
	public void setOnRunwayTime(int onRunwayTime) {
		this.onRunwayTime = onRunwayTime;
	}
	
	/**
	 * ISncreases the time by the plane on the runway
	 */
	public void incOnRunwayTime() {
		onRunwayTime++;
	}
	
	/**
	 * Overrides the compareTo method to create a priority of planes
	 * in a priority queue
	 */
	@Override
	public int compareTo(Plane o) {
		if(o.getEmergency() == true || (o.getEmergency() == true && this.getEmergency() == true )) 
			return 1;
		else if(o.getEmergency() == false && this.getEmergency() == false )
			return 0;
		else 
			return -1;
	}
	
}