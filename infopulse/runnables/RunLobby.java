package infopulse.runnables;

import infopulse.lines.Station;

/**
 * Class describe work of Lobby
 *
 * @author Ishchenko Vladyslav
 * @version 1.0
 * @since 1.0
 */
public class RunLobby implements Runnable {
    /**
     * Station, when lobby work
     */
    private Station station;

    /**
     * Time of thread sleeping
     */
    private long timeSleeping;

    /**
     * Constructor of initializing
     *
     * @param station      Station, when lobby work
     * @param timeSleeping Time of thread sleeping
     */
    public RunLobby(Station station, long timeSleeping) {
        this.station = station;
        this.timeSleeping = timeSleeping;
    }

    /**
     * Method for running of Lobby
     */
    @Override
    public void run() {
        while (true) {
            station.getLobby().passengerCame();

            try {
                Thread.sleep(timeSleeping);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
