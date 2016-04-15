package model;

import java.util.Random;

/**
 * This class is used to create a certain type of Car; AdHocCar.
 *
 * @author Femke Hoornveld, Thomas lambregts
 * @version 3.0 (14-04-2016)
 */
public class AdHocCar extends Car {
	
	/**
	 * When constructing this instance, isNormalCustomer will be set to true in Car. 
	 * Also the time the specific car will be parking is created.
	 */
	public AdHocCar(){
		setIsNormalCustomer(); 
		
		Random random = new Random(); 
		
		minutesStay = (int) (15 + random.nextFloat() * 10 * 60);
	}
}