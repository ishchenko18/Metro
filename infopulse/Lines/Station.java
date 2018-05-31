package infopulse.Lines;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import infopulse.people.Passenger;

import java.io.IOException;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.*;

/**
 * Class, which describe a station
 *
 * @author Ishchenko Vladyslav
 * @since 1.0
 * @version 1.1
 */
@DatabaseTable(tableName = "station")
public class Station {
    /**
     * ID of the Station
     */
    @DatabaseField(id = true)
    private int id;

    /**
     * ID of line, where station locate
     */
    @DatabaseField
    private int lineId;
    /**
     * Name of the Station
     */
    @DatabaseField
    private String name;

    /**
     * Is last station on a Line
     */
    @DatabaseField
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
     * Logger for adding information about transfer of passengers
     */
    private static final Logger logger = Logger.getLogger(Station.class.getName());

    //Start settings for logger
    static {
        logger.setUseParentHandlers(false);

        try {
            FileHandler fileHandler = new FileHandler();

            //Anonymous class Formatter for formatting text of log
            Formatter formatter = new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return record.getMillis() + "  " + record.getSequenceNumber() + "  " + record.getLevel() + "  " + record.getMessage() + "\n";
                }
            };

            fileHandler.setFormatter(formatter);

            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Default Constructor
     *
     * @since 1.1
     */
    Station() {
        this.passengers = new ArrayBlockingQueue<>(400);
        this.escalators = new Escalator[]{new Escalator(), new Escalator()};
        this.semaphore = new Semaphore(1);
    }

    /**
     * Constructor of initializing
     *
     * @param id ID of the Station
     * @param name Name of the Station
     * @param last Is last station on a Line
     * @since 1.1
     */
    public Station(int id, int lineId, String name, boolean last) {
        this.id = id;
        this.lineId = lineId;
        this.name = name;
        this.passengers = new ArrayBlockingQueue<>(400);
        this.last = last;
        this.escalators = new Escalator[]{new Escalator(), new Escalator()};
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
        logger.log(Level.INFO, passenger + " came to the station: " + name);

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

        logger.log(Level.INFO, passenger + " leave station: " + name);

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
     *
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for escalator
     *
     * @param i number of escalator
     * @return escalator
     * @throws Exception exception throws, when the escalator for such index doesn't exist
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
