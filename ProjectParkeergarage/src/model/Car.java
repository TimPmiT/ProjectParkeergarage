package model;

import java.util.Random;

/**
 * This class creates in stances of the object car. A car can enter, pay and leave the car park.
 * The car has a certain amount of time it is parked. The car has a specific location where the car
 * is parked in the car park.
 * 
 * @author Femke Hoornveld, Thomas Lambregts
 * @version 3.0 (14-04-2016)
 */
public abstract class Car extends AbstractModel {
	private Location location;
    private int minutesLeft;
    private boolean isPaying;
    //voor de opdracht
    private boolean isMember;
    private boolean hasReservation;
    
    //test to see if this fixes crashing
    private boolean isNormalCustomer;

    protected int minutesStay; 
    
    /**
     * Constructor for objects of class Car
     */
    public Car() {
    	//default van het membership is false (voor normalCustomer)
    	isMember = false; 
    }

    /**
     * @return location Return the location where the particular car is parked
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Store the location the particular car is parked in.
     * @param location The location the particular car is parked
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return minutesLeft Return how much minutes a particular car will be parked.
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * Keep track of how many minutes a car should be parked.
     * @param minutesLeft The amount of minutes the car should be parked
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    /**
     * @return isPaying	Return whether the particular car is paying
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * Change when a particular car is paying.
     * @param isPaying
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    @Override
    /**
     * Whenever a minute (tick) passes, the car will stay one minute less before leaving.
     */
    public void tick() {
        minutesLeft--;
    }
    
    /**
     * Make a car a member.
     */
    public void setIsMember(){
    	isMember = true; 
    }
    
    /**
    * Check if isMember is true in the simulator class, then let it skip payment if true. 
    */
	public boolean getIsMember(){
		return isMember; 
	}
	
	/**
	 * Make a car have a reservation.
	 */
	public void setHasReservation(){
		hasReservation = true; 
	}
	
	/**
	 * @return hasReservation	Return whether the particular car has a reservation
	 */
	public boolean getHasReservation(){
		return hasReservation; 
	}
	
	/**
	 * If it is not a pass holder or somebody with a reservation, use this method.
	 */
	public void setIsNormalCustomer(){
		isNormalCustomer = true;
	}
	
	/**
	 * @return isNormalCustomer Return whether a particular car is a normal customer
	 */
	public boolean getIsNormalCustomer(){
		return isNormalCustomer; 
	}

	/**
	 * @return minutesStay The amount of time a particular car is parked
	 */
	public int getStayTime(){
		return minutesStay; 
	}
	   
    /**
     * This method creates a random number, where min and max are the boundries between which the
     * random number should lie.
     * @param max
     * @param min
     * @return returns a random number between the max and min value 
     */
	public int randInt(int max, int min){
	    	Random rand;
	    	rand = new Random();
	    	
	    	int randNum = rand.nextInt(max - min) + min; 
	    	
	    	return randNum;
	    }

}
