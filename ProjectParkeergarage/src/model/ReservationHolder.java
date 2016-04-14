package model;

public class ReservationHolder extends Car {
	
	//when this object is created, hasReservation is set to true and the car object is recocnised as a reservation. 
	public ReservationHolder(){
		setHasReservation();
		minutesStay = (int) (randInt(120, 360));
	}

}
