package model;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class for creating Queues that can hold collections of Car objects.
 * This class is used for a queue of cars that need to enter the
 * car park, a queue of cars that need to pay their fee or 
 * a queue of cars that want to exit the car park.
 *
 * @author Femke Hoornveld
 * @version 2.0 (11-04-2016)
 */

public class CarQueue extends AbstractModel {
    private Queue<Car> queue = new LinkedList<>();

    /**
     * Checking whether a Car object has been added to the specified Queue.
     * @param Car added to the specified Queue
     * @return true or false Returns true if the car is successfully added to the specified Queue,
     * 						returns false if the car was not successfully added.
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     * Removing a Car object from the specified Queue.
     * @return Car from the specified Queue
     */
    public Car removeCar() {
        return queue.poll();
    }
    
    /**
     * Count the Cars in the specified Queue.
     * @return int Return the number of cars currently stored in the specified Queue.
     */
    public int countCars(){
        return queue.size();
    }

}
