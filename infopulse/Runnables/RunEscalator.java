package infopulse.Runnables;

import infopulse.Lines.Lobby;
import infopulse.Lines.Station;
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
     * Lobby for the escalator
     */
    private Lobby lobby;

    /**
     * Time of thread sleeping
     */
    private int timeSleeping;

    /**
     * Constructor of initialization
     *
     * @param station         Station, when escalator work
     * @param lobby           Lobby for the escalator
     * @param numberEscalator Number of escalator
     * @param timeSleeping    Time of thread sleeping
     */
    public RunEscalator(Station station, Lobby lobby, int numberEscalator, int timeSleeping) {
        this.station = station;
        this.lobby = lobby;
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
                Passenger passenger = lobby.passengerLeave();

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
