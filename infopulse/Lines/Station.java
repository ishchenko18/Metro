package infopulse.Lines;

import infopulse.people.Passenger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Class, which describe a station
 *
 * @author Ishchenko Vladyslav
 * @since 1.0
 * @version 1.0
 */
public class Station {
    /**
     * ID of the Station
     */
    private int id;

    /**
     * Name of the Station
     */
    private String name;

    /**
     * Is last station on a Line
     */
    private boolean last;

    /**
     * Queue of passengers
     */
    private BlockingQueue<Passenger> passengers;

    /**
     * Lobby on a station
     */
    private Lobby lobby;

    /**
     * Array of escalators
     */
    private Escalator[] escalators;

    /**
     * Semaphore for station
     */
    private Semaphore semaphore;

    /**
     * Constructor of initializing
     *
     * @param id ID of the Station
     * @param name Name of the Station
     * @param last Is last station on a Line
     */
    public Station(int id, String name, boolean last) {
        this.id = id;
        this.name = name;
        this.passengers = new ArrayBlockingQueue<Passenger>(400);
        this.last = last;
        escalators = new Escalator[]{new Escalator(), new Escalator()};
        this.semaphore = new Semaphore(1);
    }

    /**
     * Method for coming to the station
     */
    public void trainCameToStation() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for leaving from the station
     */
    public void trainLeaveStation() {
        semaphore.release();
    }

    /**
     * Method for coming passenger to the station
     *
     * @param passenger passenger, who came to the station
     */
    public void passengerCame(Passenger passenger) {
        System.out.println("Passenger " + passenger + " came to the station: " + name);
        passengers.offer(passenger);
    }

    /**
     * Method for leaving passenger from the station
     *
     * @return passenger, who wanna leave the station
     */
    public Passenger passengerLeaveStation() {
        Passenger passenger = null;
        try {
            passenger = passengers.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Passenger " + passenger + " leave station: " + name);

        return passenger;
    }

    /**
     * Setter for lobby of a station
     *
     * @param lobby lobby of a station
     */
    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    /**
     * Getter for lobby
     *
     * @return lobby of station
     */
    public Lobby getLobby() {
        return lobby;
    }

    /**
     * Getter for escalator
     *
     * @param i number of escalator
     * @return escalator
     * @throws Exception
     */
    public Escalator getEscalators(int i) throws Exception{
        if(i >= 0 && i <= escalators.length) {
            return escalators[i];
        } else {
            throw new Exception("No such escalator");
        }
    }

    /**
     * Method for getting count of passenger on the station
     *
     * @return count of passenger on the station
     */
    public int countOfPassengers() {
        return passengers.size();
    }

    /**
     * Method for checking station on last
     *
     * @return is last station
     */
    public boolean isLast() {
        return last;
    }

    /**
     * Override method toString
     *
     * @return information about station
     */
    @Override
    public String toString() {
        return name;
    }
}
