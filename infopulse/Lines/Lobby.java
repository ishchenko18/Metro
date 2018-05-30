package infopulse.Lines;

import infopulse.people.Passenger;

import java.util.LinkedList;

/**
 * Class, which describe a Lobby of station
 *
 * @author Ishchenko Vladyslav
 * @version 1.0
 * @since 1.0
 */
public class Lobby {
    /**
     * Name of the lobby
     */
    private String name;

    /**
     * List of passengers into Lobby
     */
    private LinkedList<Passenger> passengers;

    /**
     * Constructor of initializing
     *
     * @param name Name of the Lobby
     */
    public Lobby(String name) {
        this.name = name;
        passengers = new LinkedList<>();
    }

    /**
     * Method for adding passengers to the lobby
     */
    public synchronized void passengerCame() {
        Passenger passenger = new Passenger();

        passengers.offerLast(passenger);

        notifyAll();
    }

    /**
     * Method for deleting passengers from the lobby
     *
     * @return passenger, who leave the lobby
     */
    public synchronized Passenger passengerLeave() {
        Passenger passenger = null;

        if (!passengers.isEmpty()) {
            passenger = passengers.pollFirst();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return passenger;
    }
}
