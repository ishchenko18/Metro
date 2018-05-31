package infopulse.Lines;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import infopulse.people.Passenger;

import java.util.LinkedList;

/**
 * Class, which describe a Lobby of station
 *
 * @author Ishchenko Vladyslav
 * @version 1.1
 * @since 1.0
 */
@DatabaseTable(tableName = "lobby")
public class Lobby {
    /**
     * Name of the lobby
     */
    @DatabaseField(id = true)
    private String name;

    /**
     * List of passengers into Lobby
     */
    private LinkedList<Passenger> passengers;

    /**
     * Default constructor
     */
    public Lobby() {
        passengers = new LinkedList<>();
    }

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
