package infopulse.ComponentsOfTrain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import infopulse.people.Passenger;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

/**
 * This class describe a subway carriage
 *
 * @author Ishchenko Vladyslav
 * @since 1.0
 */
@DatabaseTable(tableName = "railwayCarriages")
public class RailwayCarriage implements Cloneable {
    /**
     * count of carriages in subway
     */
    private static int countCarriages = 0;

    /**
     * ID of a carriage
     */
    @DatabaseField(id = true)
    private int id;

    /**
     * maximal count of people in carriage
     */
    @DatabaseField
    private int spaciousness;

    /**
     * possibility to be first or last carriage in a train
     */
    @DatabaseField
    private boolean mainCarriage;

    @DatabaseField
    private int depotId;

    /**
     * List of passengers in a carriage
     */
    private ArrayList<Passenger> passengers;

    /**
     * Default constructor
     */
    RailwayCarriage() {
        ++countCarriages;
        passengers = new ArrayList<>();
    }

    /**
     * Constructor of initializing
     *
     * @param spaciousness maximal count of people in carriage
     * @param mainCarriage possibility to be first or last carriage in a train
     */
    public RailwayCarriage(int spaciousness, boolean mainCarriage) {
        this.id = ++countCarriages;
        this.spaciousness = spaciousness;
        this.mainCarriage = mainCarriage;
        passengers = new ArrayList<>();
    }

    /**
     * method is cloning a carriage
     *
     * @return new carriage
     * @throws CloneNotSupportedException when clone not supported for object
     */
    @Override
    public RailwayCarriage clone() throws CloneNotSupportedException {
        RailwayCarriage carriage = (RailwayCarriage) super.clone();

        carriage.setId(++countCarriages);

        return carriage;
    }

    /**
     * method check type of carriage
     *
     * @return is main carriage
     */
    public boolean isMainCarriage() {
        return mainCarriage;
    }

    /**
     * getter for ID of railway carriage
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * setter for ID of railway carriage
     *
     * @param id ID of railway carriage
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for spaciousness
     *
     * @return spaciousness of carriage
     */
    public int getSpaciousness() {
        return spaciousness;
    }

    /**
     * Method for checking count of people into carriage
     *
     * @return count of passengers into railway carriage
     */
    public int countOfPassengers() {
        return passengers.size();
    }

    /**
     * Method for adding passenger into a carriage
     *
     * @param passenger passenger, who seat into the carriage
     */
    public void passengerSeat(Passenger passenger) {
        System.out.println("Passenger " + passenger + " sat into the railway carriage.");
        passengers.add(passenger);
    }

    /**
     * Method for removing passenger from a carriage
     */
    public void passengerLeave() {
        Random random = new Random();
        passengers.remove(random.nextInt(passengers.size()));
    }

    /**
     * Check passenger into carriage
     *
     * @return is passengers present or no
     */
    public boolean isEmpty() {
        return passengers.isEmpty();
    }


    /**
     * override method toString
     *
     * @return information about carriage
     */
    @Override
    public String toString() {
        return "RailwayCarriage{" +
                "idCarriage=" + id +
                ", spaciousness=" + spaciousness +
                ", mainCarriage=" + mainCarriage +
                '}';
    }
}
