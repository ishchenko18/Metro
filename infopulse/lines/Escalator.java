package infopulse.lines;

import infopulse.people.Passenger;

import java.util.LinkedList;

/**
 * Class describe a escalator in subway
 *
 * @author Ishchenko Vladyslav
 * @version 1.0
 * @since 1.0
 */
public class Escalator {
    /**
     * count of escalators in subway
     */
    private static int count = 0;

    /**
     * ID of escalator
     */
    private int id;

    /**
     * list of passengers on the escalator
     */
    private LinkedList<Passenger> passengers;

    /**
     * Default constructor
     */
    public Escalator() {
        this.id = ++count;
        passengers = new LinkedList<>();
    }

    /**
     * Method for adding passenger on the escalator
     *
     * @param passenger passenger, who wanna ride
     */
    public void passengerWantRide(Passenger passenger) {
        passengers.offerLast(passenger);
    }

    /**
     * Method for removing passenger from the escalator
     *
     * @return passenger, who wanna leave escalator
     */
    public Passenger passengerCameToThePlatform() {
        return passengers.pollFirst();
    }
}
