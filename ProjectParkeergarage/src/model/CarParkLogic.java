package model;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random; 

/**
 * This class stores all the data with respect to the placing of the cars
 * in the car park. Also it has methods to change this data.
 *
 * @author Femke Hoornveld, Thomas Lambregts
 * @version 3.0 (12-04-2016)
 */
public class CarParkLogic extends AbstractModel {
    private static int numberOfFloors;
    private static int numberOfRows;
    private static int numberOfPlaces;
    private static CarQueue entranceCarQueue;
    private static CarQueue paymentCarQueue;
    private static CarQueue membersCarQueue;
    private static CarQueue exitCarQueue;
    private Car[][][] cars;

    // TODO Moeten onderstaande instance variabelen ook nog static, dan wel static final?
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;

    int weekDayArrivals= 50; // average number of arriving cars per hour
    int weekendArrivals = 90; // average number of arriving cars per hour

    int enterSpeed;
    int paymentSpeed;
    int exitSpeed; 
    
    // Keeping track of statistics
    int numberOfEnteringCars; // how many cars are in entranceCarQueue
    int numberOfPayingCars; // how many cars are in paymentCarQueue
    int numberOfExitingCars; // how many cars are in exitCarQueue
    int totalCars; // amount of no member cars in car park 
    int totalPassHolders; // amount of members in car park

    /**
     * Constructor of the CarPark. It initializes the fields and creates the
     * three dimensional array to represent the parking spots in the car park.
     *
     * @param numberOfFloors The number of floors of the car park
     * @param numberOfRows   The number of rows per floor of the car park
     * @param numberOfPlaces The number of parking spots per row of the car park
     */
    public CarParkLogic(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;

        entranceCarQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        membersCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        
        enterSpeed = 3; // number of cars that can enter per minute
        paymentSpeed = 10; // number of cars that can pay per minute
        exitSpeed = 9; // number of cars that can leave per minute
        
        numberOfEnteringCars = 0;
        numberOfPayingCars = 0;
        numberOfExitingCars = 0;
        totalCars = 0;
        totalPassHolders = 0;

        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        
    }

    /**
     * Get a car from a specific location in the car park.
     *
     * @param location A Location object which stores where the car is parked.
     * @return car The car that is parked at the given location.
     */
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * Put a car in a certain parking spot, a certain Location in the car park.
     *
     * @param location Location object where the car should be parked
     * @param car The car that needs to be parked
     * @return boolean Return true if the car is successfully parked, if not return false
     */
    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            return true;
        }
        return false;
    }
    
    //week 2 a random number
    /**
     * 
     * @param min
     * @param max
     * @return returns a random number between the min and max value 
     */
    public int randInt(int min, int max){
    	
    	Random rand;
    	rand = new Random();
    	
    	int randNum = rand.nextInt((max - min) + min); 
    	
    	return randNum;
    }

    /**
     * Get number of floors of the car park.
     *
     * @return numberOfFloors Number of floors of the car park
     */
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * Get number of rows of the car park.
     *
     * @return numberOfRows Number of rows of the car park
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Get number of parking spots in the car park.
     *
     * @return numberOfPlaces Number of parking spots in the car park
     */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * Get the three dimensional array that represents the car park.
     *
     * @return cars Return the array cars
     */
    public Car[][][] getCars() {
        return cars;
    }

    /**
     * Get queue of all the cars are entering the car park.
     *
     * @return cars Return the queue with all cars entering
     */
    public CarQueue getEntranceCarQueue() {
        return entranceCarQueue;
    }

    /**
     * Get queue of all the cars that need to pay at the car park.
     *
     * @return cars Return the queue with all cars that need to pay
     */
    public CarQueue getPaymentCarQueue() {
        return paymentCarQueue;
    }

    /**
     * Get queue of all the cars are exiting the car park.
     *
     * @return cars Return the queue with all cars exiting
     */
    public CarQueue getExitCarQueue() {
        return exitCarQueue;
    }
    
    /**
     * Change entering speed.
     * 
     * @param newSpeed The speed the user wants to change the entering speed to.
     */
    public void setEnterSpeed(int newSpeed) {
    	enterSpeed = newSpeed;
    }
    
    /**
     * Change entering speed.
     * 
     * @param newSpeed The speed the user wants to change the payment speed to.
     */
    public void setPaySpeed(int newSpeed) {
    	paymentSpeed = newSpeed;
    }
    
    /**
     * Change entering speed.
     * 
     * @param newSpeed The speed the user wants to change the exiting speed to.
     */
    public void setExitSpeed(int newSpeed) {
    	exitSpeed = newSpeed;
    }
    
    /**
     * @return enterSpeed The speed with which cars currently are entering per minute.
     */
	public int getEnterSpeed() {
		return enterSpeed;
	}
	
    /**
     * @return paymentSpeed The speed with which car owners currently are paying per minute.
     */
	public int getPaySpeed() {
		return paymentSpeed;
	}
	
    /**
     * @return exitSpeed The speed with which cars currently areexiting per minute.
     */
	public int getExitSpeed(){
		return exitSpeed;
	}

    /**
     * Executes one simulation step, it advances the time by one minute.
     */
    public void tick() {
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDayArrivals
                : weekendArrivals;

        // Calculate the number of cars of people who are not members that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.1;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfCarsPerMinute = (int)Math.round(numberOfCarsPerHour / 60);
        
        /*amount of reservable spaces: 50
         * max stay time: 360 minutes (6 hour)
         * min stay time: 120 minutes (2 hour)
         * 
         * 50 spaces can support a maximum of 200 visitors a day
         * (24 h / 6 h = 4 customers per space per day * 50 spaces = 200 customers max)
         * However places probably won't be reserved late at night and people probably won't stay the max time every time.
         * Estimate 90 to 130 customers a day
         * 
         * (to be safe we should only allow 90% of the spaces to be reserved (so 45)) 
         */
        
        int amountOfReservations = randInt(110, 130);
        double numberOfReservationsPerHour = (amountOfReservations / 4) + random.nextGaussian() * standardDeviation;
        int numberOfReservationsPerMinute = (int)Math.round(numberOfReservationsPerHour / 60);


   
        // Calculates the number of members that arrive each minute.
        int amountOfPassHolders = 150;
        double numberOfMembersPerHour = (amountOfPassHolders / 6) + random.nextGaussian() * standardDeviation;
        int numberOfMembersPerMinute = (int)Math.round(numberOfMembersPerHour / 60);
        int numberTotalCarsPerMinute = numberOfCarsPerMinute + numberOfMembersPerMinute;
        
        

        // Add the cars of people who are not members to the back of the entrance queue.
        for (int j = 0; j < numberTotalCarsPerMinute; j++) { 
        	
            for (int i = 0; i < numberOfCarsPerMinute; i++) {
                Car car = new AdHocCar();
                numberOfEnteringCars++;
                totalCars++;
                entranceCarQueue.addCar(car);
            }
            
          //voor de reservatie (opdracht week 2)
            //will be added to the membersExitQueue
            for (int i = 0; i < numberOfReservationsPerMinute ; i++) {
                Car car = new ReservationHolder();
                numberOfEnteringCars++;
                totalCars++;
                entranceCarQueue.addCar(car);
            }
            
            
            // Add the cars of members to the back of the entrance queue.
            for (int i = 0; i < numberOfMembersPerMinute ; i++) {
                Car car = new ParkingPassHolder();
                numberOfEnteringCars++;
                totalPassHolders++;
                entranceCarQueue.addCar(car);
            }

            super.notifyViews(); // TODO wil ik dit toevoegen? Koen had dit
        }

        // Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < enterSpeed; i++) {
            Car car = entranceCarQueue.removeCar();

            super.notifyViews(); // TODO wil ik dit toevoegen? Koen had dit

            if (car == null) {
                break;
            } else {
            	numberOfEnteringCars--;
            }
            // Find a space for this car.
            Location freeLocation = this.getFirstFreeLocation();
            if (freeLocation != null) {
                this.setCarAt(freeLocation, car);
                //voor week 2 
                if(car instanceof ReservationHolder){
                	int stayMinutes = (int) (randInt(120, 360));
                	 car.setMinutesLeft(stayMinutes);
                	 
                } else if(car instanceof AdHocCar || car instanceof ParkingPassHolder) {
                	int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
                	car.setMinutesLeft(stayMinutes);
              }
            }
            super.notifyViews(); // TODO wil ik dit toevoegen? Koen had dit
        }

        this.tickCars();

        // Add leaving cars to the exit queue.
        while (true) {
            Car car = this.getFirstLeavingCar();
            if (car == null) {
                break;
            }

            //if a car isn't a member it goes to the payment queue else it goes to the members queue,
            // members pay monthly so they don't need to pay.
            if(car instanceof AdHocCar){
            	numberOfPayingCars++;
            	paymentCarQueue.addCar(car);
            	break;
            	
            	
            } else if(car instanceof ParkingPassHolder || car instanceof ReservationHolder) { 
                membersCarQueue.addCar(car);
                this.removeCarAt(car.getLocation());
                break;
            }
            

            super.notifyViews(); // TODO wil ik dit toevoegen? Koen had dit
        }

         // Let cars pay.
        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();
            super.notifyViews(); // TODO wil ik dit toevoegen? Koen had dit
            if (car == null) {
                break;
            } else {
                numberOfPayingCars--;
            }

            this.removeCarAt(car.getLocation());
            super.notifyViews(); // TODO wil ik dit toevoegen? Koen had dit
            exitCarQueue.addCar(car);
            numberOfExitingCars++;

            super.notifyViews(); // TODO wil ik dit toevoegen? Koen had dit
        }
        

        // Let non members leave.
        for (int i = 0; i < exitSpeed; i++) {
            Car car = exitCarQueue.removeCar();
            if (car == null) {
                break;
            } else {
                totalCars--;
                numberOfExitingCars--;	
            }
            super.notifyViews(); // TODO wil ik dit toevoegen? Koen had dit
            // Bye!
        }

        // Let members leave.
        for (int i = 0; i < exitSpeed; i++) {
            Car car = membersCarQueue.removeCar();
            if (car == null) {
                break;
            } else {
                totalPassHolders--;
            }
            super.notifyViews(); // TODO wil ik dit toevoegen? Koen had dit
            // Bye!
        }

        // Update the car park view.
        super.notifyViews();
    }

    /**
     * For every car call the thick method by looping through the car park.
     */
    private void tickCars() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = this.getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    /**
     * Check if a specific location is empty in the car park.
     *
     * @param location Location to check
     * @return boolean Return true if location is empty, if not return
     */
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }

    /**
     * Gets the first free parking spot that is empty in the car park.
     *
     * @return location | null If empty location is found return location, if nog return null 
     */
    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Get the first leaving car in the car park.
     *
     * @return car | null | If a leaving car is found return this car, if not return null
     */
    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Remove a car at a specific location in the car park.
     *
     * @param location Location of the car that needs to be removed
     * @return car | null | If the location is not valid or there is no car at the 
     *                      specified location return null, else return the car
     */
    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        return car;
    }
    
    /**
     * @return numberOfEnteringCars
     */
    public int getNumberOfEntering() {
        return numberOfEnteringCars;
    }

    /**
     * @return numberOfPayingCars
     */
    public int getNumberOfPaying() {
        return numberOfPayingCars;
    }

    /**
     * @return numberOfExitingCars
     */
    public int getNumberOfExiting() {
        return numberOfExitingCars;
    }
    
    /**
     * @return totalCars Return total number of no member cars in car park
     */
    public int getTotalCars() {
        return totalCars;
    }

    /**
     * @return totalCars Return total number of members in car park
     */
    public int getTotalPassHolders() {
        return totalPassHolders;
    }
}

