package infopulse.ComponentsOfTrain;


import infopulse.Depot;
import infopulse.Lines.Station;
import infopulse.Runnables.ExchangePassengers;
import infopulse.people.Driver;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

/**
 * This class describe a train in a subway
 *
 * @author Ishchenko Vladyslav
 * @version 1.0
 * @since 1.0
 */
public class Train {
    /**
     * count trains in subway
     */
    public static int countOfTrains = 0;

    /**
     * individual id of train
     */
    private int id;

    /**
     * driver of train
     */
    private Driver driver;

    /**
     * depot for train
     */
    private Depot depotForTrain;

    /**
     * railway carriages in this train
     */
    private LinkedList<RailwayCarriage> railwayCarriages;

    /**
     * default constructor
     *
     * @param builder object of builder
     */
    public Train(BuilderOfTrains builder) {
        this.id = ++Train.countOfTrains;
        this.depotForTrain = builder.depotForTrain;
        this.railwayCarriages = builder.railwayCarriages;
    }

    /**
     * Pattern Builder for building trains
     */
    public static class BuilderOfTrains {
        /**
         * depot for train
         */
        private Depot depotForTrain;

        /**
         * railway carriages in this train
         */
        private LinkedList<RailwayCarriage> railwayCarriages;

        /**
         * Default constructor for Builder
         *
         * @param depotForTrain depot for train
         */
        public BuilderOfTrains(Depot depotForTrain) {
            this.railwayCarriages = new LinkedList<>();
            this.depotForTrain = depotForTrain;
        }

        /**
         * Method for building new train
         *
         * @return created Train
         */
        public Train createTrain() {
            railwayCarriages.addFirst(depotForTrain.getMainRailwayCarriage());

            for (int i = 0; i < 3; i++) {
                if (depotForTrain.getCountOfSimpleCarriages() > 0) {
                    railwayCarriages.add(depotForTrain.getSimpleRailwayCarriage());
                } else {
                    railwayCarriages.add(depotForTrain.getMainRailwayCarriage());
                }
            }

            railwayCarriages.addLast(depotForTrain.getMainRailwayCarriage());

            return new Train(this);
        }
    }

    /**
     * setter for driver of train
     *
     * @param driver driver of a train
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    /**
     * Method move train to a next station
     *
     * @param station station, which will be next for the train
     * @param isLast  is station last
     */
    public void moveTrainOnNextStation(Station station, boolean isLast) {
        CountDownLatch downLatch = new CountDownLatch(railwayCarriages.size());

        System.out.println("Train " + id + " on the station: " + station + " " + driver);

        station.trainCameToStation();

        for (RailwayCarriage carriage : railwayCarriages) {
            new Thread(new ExchangePassengers(carriage, station, isLast, downLatch)).start();
        }

        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        station.trainLeaveStation();
    }

    /**
     * getter for driver of train
     *
     * @return driver of train
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Override method toString
     *
     * @return information about Train
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(this.id + "  \n");

        railwayCarriages.stream().forEach(carriage -> string.append(carriage + "\n"));

        return string.toString();
    }
}
