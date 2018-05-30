package infopulse.Runnables;

import infopulse.ComponentsOfTrain.Train;
import infopulse.Lines.Station;
import infopulse.MainComponents.Metro;

/**
 * Class describe moving of Train
 *
 * @author Ishchenko Vladyslav
 * @version 1.0
 * @since 1.0
 */
public class RunTrain implements Runnable {
    /**
     * Train, which will be moving
     */
    private Train train;

    /**
     * Subway, when moving a train
     */
    private Metro metro;

    /**
     * Number of line, when moving a train
     */
    private int numberOfLine;

    /**
     * Constructor of initializing
     *
     * @param train        Train, which will be moving
     * @param metro        Subway, when moving a train
     * @param numberOfLine Number of line
     */
    public RunTrain(Train train, Metro metro, int numberOfLine) {
        this.train = train;
        this.metro = metro;
        this.numberOfLine = numberOfLine;
    }

    /**
     * Method for moving train
     */
    @Override
    public void run() {
        while (true) {
            try {
                train.setDriver(metro.getLinesInSubway(numberOfLine).getDriver());
                int countPassedStations = 0;

                for (Station station : metro.getLinesInSubway(numberOfLine).getStations()) {
                    if (countPassedStations != 0 && station.isLast()) {
                        train.moveTrainOnNextStation(station, true);
                        train.getDriver().upExperience();
                        metro.getLinesInSubway(numberOfLine).addDriver(train.getDriver());
                        train.setDriver(null);
                    } else {
                        train.moveTrainOnNextStation(station, false);
                        countPassedStations++;

                        Thread.sleep(1000);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
