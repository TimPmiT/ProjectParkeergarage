package model;

/**
 * This class creates in stances of the object car. A car can enter, pay and leave the car park.
 * The car has a certain amount of time it is parked. The car has a specific location where the car
 * is parked in the car park.
 * 
 * @author Femke Hoornveld
 * @version 2.0 (11-04-2016)
 */
public abstract class Car extends AbstractModel {
	private Location location;
    private int minutesLeft;
    private boolean isPaying;
    //voor de opdracht
    private boolean isMember;

    /**
     * Constructor for objects of class Car
     */
    public Car() {
    	//default van het membership is false (voor normalCustomer)
    	isMember = false; 
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public void tick() {
        minutesLeft--;
    }
    
    //sets member to either true
    public void setIsMember(){
    	isMember = true; 
    }
    
       //check if ismember is true in the simulator class, then let it skip payment if true. 
	public boolean getIsMember(){
		return isMember; 
	}
	    
	    

}
