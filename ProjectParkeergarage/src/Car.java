public abstract class Car {

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
    
    //sets member to either true of false
    public void setIsMember(){
    	isMember = true; 
    }
    
       //check if ismember is true in the simulator class, then let it skip payment if true. 
	public boolean getIsMember(){
		return isMember; 
	}
	    
	    

}