package model;

import java.util.Random;

import model.Car;

/**
 * This class is used to create a certain type of Car; ParkingPassHolder car.
 *
 * @author Thomas Lambregts
 * @version 2.0 (14-04-2016)
 */
public class ParkingPassHolder extends Car {

	  
	  /**
	   * The constructor creates an instance of a parking pass holder. 
	   * When it does, it changes the field 'isMember' in it's superclass Car to true.
	   */
	//the parkingpass holder, this customer can swoowsh to the end of the que without playing locally
	  public ParkingPassHolder() {
		setIsMember(); 
		  
		Random random = new Random(); 
			
		minutesStay = (int) (15 + random.nextFloat() * 10 * 60);
	  }

}