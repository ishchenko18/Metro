package infopulse.runnables;

import infopulse.lines.Lobby;
import infopulse.lines.Station;
import infopulse.people.Passenger;

/**
 * Class for running escalator
 *
 * @author Ishchenko Vladyslav
 * @version 1.0
 * @since 1.0
 */
public class RunEscalator implements Runnable {
    /**
     * Station, when escalator work
     */
    private Station station;

    /**
     * Number of escalator
     */
    private int numberEscalator;

    /**
     * Time of thread sleeping
     */
    private int timeSleeping;

    /**
     * Constructor of initialization
     *
     * @param station         Station, when escalator work
     * @param numberEscalator Number of escalator
     * @param timeSleeping    Time of thread sleeping
     */
    public RunEscalator(Station station, int numberEscalator, int timeSleeping) {
        this.station = station;
        this.numberEscalator = numberEscalator;
        this.timeSleeping = timeSleeping;
    }

    /**
     * Method for running of escalator
     */
    @Override
    public void run() {
        try {
            while (true) {
                Passenger passenger = station.getLobby().passengerLeave();

                if (passenger != null) {
                    station.getEscalators(numberEscalator).passengerWantRide(passenger);
                    Thread.sleep(timeSleeping);
                    station.passengerCame(station.getEscalators(numberEscalator).passengerCameToThePlatform());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
