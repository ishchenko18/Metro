package infopulse.Lines;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import infopulse.ComponentsOfTrain.Train;
import infopulse.Depot;
import infopulse.Exceptions.DepotNotExistException;
import infopulse.FactoryOfBuilding.Factory;
import infopulse.people.Driver;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Class, which describe a line of subway
 *
 * @author Ishchenko Vladyslav
 * @version 1.1
 * @since 1.0
 */
@DatabaseTable(tableName = "line")
public class Line {
    /**
     * ID of the line
     */
    @DatabaseField(id = true)
    private int id;

    /**
     * name of the line
     */
    @DatabaseField
    private String name;

    /**
     * Depot on this line
     */
    private Depot depotOnThisLine;

    /**
     * Queue of drivers on the line
     */
    private PriorityBlockingQueue<Driver> drivers;

    /**
     * List of trains on the line
     */
    private ArrayList<Train> trains;

    /**
     * List of stations
     */
    private LinkedList<Station> stations;

    /**
     * Default constructor
     */
    Line() {
        this.drivers = new PriorityBlockingQueue<>(10, Comparator.comparing(Driver::getExperienceOfDriver));
        this.trains = new ArrayList<>();
        this.stations = new LinkedList<>();
    }

    /**
     * Constructor of initializing
     *
     * @param id              ID of the line
     * @param name            Name of the line
     * @param depotOnThisLine Depot on this line
     */
    public Line(int id, String name, Depot depotOnThisLine) {
        this.id = id;
        this.name = name;
        this.depotOnThisLine = depotOnThisLine;
        this.drivers = new PriorityBlockingQueue<>(10, Comparator.comparing(Driver::getExperienceOfDriver));
        this.trains = new ArrayList<>();
        this.stations = new LinkedList<>();
    }

    /**
     * Getter for id of the line
     *
     * @return id of the line
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the name
     *
     * @return name of the station
     */
    public String getName() {
        return name;
    }

    /**
     * Method for adding new driver
     *
     * @param driver driver on the station
     */
    public void addDriver(Driver driver) {
        drivers.put(driver);
    }

    /**
     * Method for taking driver
     *
     * @return chosen driver
     * @throws InterruptedException
     */
    public Driver getDriver() throws InterruptedException {
        return drivers.take();
    }

    /**
     * Getter for a train
     *
     * @param i number of the train
     * @return train
     */
    public Train getTrain(int i) {
        return trains.get(i);
    }

    /**
     * Getter for list of trains
     *
     * @return list of train
     */
    public ArrayList<Train> getTrains() {
        return trains;
    }

    /**
     * Getter for list of stations
     *
     * @return
     */
    public LinkedList<Station> getStations() {
        return stations;
    }

    /**
     * Setter for list of stations
     *
     * @param stations list of stations
     * @since 1.1
     */
    public void setStations(LinkedList<Station> stations) {
        this.stations = stations;
    }

    /**
     * Method for building all possible train on the line
     */
    public void buildAllPossibleTrain() {
        while (depotOnThisLine.checkOnPossibilityCreatingTrain()) {
            try {
                trains.add(Factory.buildNewTrain(depotOnThisLine));
            } catch (DepotNotExistException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method for building a stations
     *
     * @param station adding station
     */
    public void buildStation(Station station) {
        stations.add(station);
    }

    /**
     * Setter for depot on the line
     *
     * @param depotOnThisLine depot on the line
     */
    public void setDepotOnThisLine(Depot depotOnThisLine) {
        this.depotOnThisLine = depotOnThisLine;
    }

    /**
     * Getter for depot on the line
     *
     * @return depot on the line
     */
    public Depot getDepotOnThisLine() {
        return depotOnThisLine;
    }
}
