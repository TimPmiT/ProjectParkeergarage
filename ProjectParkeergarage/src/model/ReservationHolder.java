package model;

/**
 * This class is used to create a certain type of Car; ReservationHolder car.
 *
 * @author Thomas Lambregts
 * @version 2.0 (14-04-2016)
 */
public class ReservationHolder extends Car {
	
	/**
   * The constructor creates an instance of a reservation holder. 
   * When it does, it changes the field 'hasReservation' in it's superclass Car to true.
   */
	//when this object is created, hasReservation is set to true and the car object is recognized as a reservation. 
	public ReservationHolder(){
		setHasReservation();
		minutesStay = (int) (randInt(360, 120));
	}

}
