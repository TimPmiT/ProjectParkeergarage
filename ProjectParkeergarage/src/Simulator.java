import java.util.Random;

public class Simulator {

    private CarQueue entranceCarQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private CarQueue membersCarQueue;
    private SimulatorView simulatorView;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;

    int weekDayArrivals= 50; // average number of arriving cars per hour
    int weekendArrivals = 90; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 10; // number of cars that can pay per minute
    int exitSpeed = 9; // number of cars that can leave per minute

    public Simulator() {
        entranceCarQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        //voor de 1e opdracht de carque voor members
        membersCarQueue = new CarQueue();
        simulatorView = new SimulatorView(3, 6, 30);
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }
    
    //week 2 a random number
    final int randInt(int min, int max){
    	
    	Random rand;
    	rand = new Random();
    	
    	int randNum = rand.nextInt((max - min) + min); 
    	
    	return randNum;
    }
    
    //week 1
    //main method (makes us able to actually run the project) 
    public static void main (String[] args) {
    	Simulator sim = new Simulator();
    	sim.run();
    }


    private void tick() {
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
        
        
        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.1;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfCarsPerMinute = (int)Math.round(numberOfCarsPerHour / 60);
   
        // Calculates the number of members that arrive each minute.
        // voor opdracht week 1
        int amountOfPassHolders = 150;
        double numberOfMembersPerHour = (amountOfPassHolders / 6) + random.nextGaussian() * standardDeviation;
        int numberOfMembersPerMinute = (int)Math.round(numberOfMembersPerHour / 60);
        
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
        
        	
        

        // Add the cars to the back of the queue.
        for (int i = 0; i < numberOfCarsPerMinute; i++) {
            Car car = new AdHocCar();
            entranceCarQueue.addCar(car);
        }
        
        //voor de member (opdracht week 1) 
        for (int i = 0; i < numberOfMembersPerMinute ; i++) {
            Car car = new ParkingPassHolder();
            entranceCarQueue.addCar(car);
        }
        
        //voor de reservatie (opdracht week 2)
        //will be added to the membersExitQueue
        for (int i = 0; i < numberOfReservationsPerMinute ; i++) {
            Car car = new ReservationHolder();
            entranceCarQueue.addCar(car);
        }
        
        // Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < enterSpeed; i++) {
            Car car = entranceCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // Find a space for this car.
            Location freeLocation = simulatorView.getFirstFreeLocation();
            if (freeLocation != null) {
                simulatorView.setCarAt(freeLocation, car);
                //voor week 2 
                if(car instanceof ReservationHolder){
                	int stayMinutes = (int) (randInt(120, 360));
                	 car.setMinutesLeft(stayMinutes);
                } else {
                int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
                
                car.setMinutesLeft(stayMinutes);
                }
            }
        }

        // Perform car park tick.
        simulatorView.tick();

        // Add leaving cars to the exit queue.
        while (true) {
            Car car = simulatorView.getFirstLeavingCar();
            if (car == null) {
                break;
            }
        
            car.setIsPaying(true);
            //if a car isn't a member it goes to the payment queue else it goes to the members queue.
            //opgaven voor week 1
            //instanceof ReservationHolder is for week2 
            if(!(car instanceof ParkingPassHolder || car instanceof ReservationHolder)){
            paymentCarQueue.addCar(car);
            } else { 
            	membersCarQueue.addCar(car);
            	simulatorView.removeCarAt(car.getLocation());
            }
        }
      //the members queue 
        //opgavn voor week 1 
        for (int i = 0; i < exitSpeed; i++) {
            Car car = membersCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // Bye!
        }
        // Let cars pay.
        
        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // TODO Handle payment.
            simulatorView.removeCarAt(car.getLocation());
            exitCarQueue.addCar(car);
        }
        

        // Let cars leave.
        
        for (int i = 0; i < exitSpeed; i++) {
            Car car = exitCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // Bye!
        }

        // Update the car park view.
        simulatorView.updateView();

        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
