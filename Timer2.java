/**
 * The Timer class controls mostly everything that happens in the simulation.
 * From starting the timer to filling each queue with the planes
 * @author Jonathan Moreira Alsina
 * @version 12/7/2022
 */
import java.io.IOException;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;

public class Timer2 {
	
	 private int high =10, low = 1, range = high - low;
	 public static int minutes = 0;
	 int runwayNumber = 1;
	
	 Timer timer = new Timer();
	 PriorityQueue <Plane> onAir = new PriorityQueue<Plane>();
	 PriorityQueue <Plane> readyToLand = new PriorityQueue<Plane>();
	 Plane [] runways = new Plane[3];
	 
	 TimerTask task = new TimerTask() {
		/**
		 * This methods has a chance to generate a plane.
		 * It also updates the priority queues, and the runways
		 */
		public void run()
	    {
			clear();
			minutes++;
			System.out.println();
	        System.out.println("\nMinutes passed: " + minutes);
	        
	        int interval = (int) ((Math.random() * range) +low);
	        
	        if (interval < 9) {
	        		        	        	
	        	Plane plane = new Plane();
	        	plane.createEmergency();
	        	onAir.add(plane);
		        System.out.println(plane);
		        
			}
	        
	        for( Plane plane : onAir) {
	        	if(plane.getDistance() > 20)
	        		plane.distanceDecreased();
	        }
	        
	        Iterator<Plane> itr = onAir.iterator();
	        while(itr.hasNext() ) {
	        	Plane plane = itr.next();
	        	
	        	if(plane.getEmergency() == true && plane.getDistance() <= 20) {
	        		plane.setDistance(0);
	     	        readyToLand.add(plane);
	        		itr.remove();
	        	}
	        	else if (plane.getDistance() <= 20){
	        		plane.setDistance(0);
	     	        readyToLand.add(plane);
	        		itr.remove();
	        	}
	   	        
	        }
	        		
	        
	        System.out.println("\n ========AIRPLANES ON AIR=======\n"+ onAir);
	        System.out.println("\n ========AIRPLANES READY TO LAND=======\n"+ readyToLand);     
	        
	        
	        for(int index = 0; index < runways.length; index++) {
	        	if(runways[index] == null) {
	        		runways[index]= readyToLand.poll();
	        		       		
	       
	        		if(runways[index] != null) {
	        			runways[index].setOnRunway(true);
	        			runways[index].setOnRunwayTime(0);
	        			 
	        		}
	        	}
	        	else {
	        		runways[index].incOnRunwayTime();
	        		if(runways[index].getOnRunwayTime()>= 5)
	        			runways[index] = null;
	        	}
	        	if(runways[index] != null)
	        		System.out.println("\n ========RUNWAYS=======\n" +runways[index]+ "\n RUNWAY IS FULL");
	        	else 
	        		System.out.println("\n ========RUNWAY=======\n EMPTY");
	        }	          
	     }
	 };
	
	 /**
	  * Start the global timer and its repeated every 10 secs
	  */
	public void start() {
		timer.scheduleAtFixedRate( task, 3000, 10000);
		
	}
	
	/**
	 * Gets the minutes passed by the global timer
	 * @return int minutes 
	 */
	public static int getMinutes() {
		return minutes;
	}
	
	/**
	 * Clears the console when run in cmd
	 */
	public static void clear()

    {
        try
        {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
	

}
