package model;

import java.util.Random;



/**
 * This class is used to create a certain type of Car; AdHocCar.
 *
 * @author Femke Hoornveld & Thomas lambregts
 * @version 2.0 (11-04-2016)
 */
public class AdHocCar extends Car {
	
	/**
	 * when constructing this instance, isNormalCustomer will be set to true in Car. 
	 */
	public AdHocCar(){
	setIsNormalCustomer(); 

	Random random = new Random(); 
	
	minutesStay = (int) (15 + random.nextFloat() * 10 * 60);
	
	}
	

		
	
}