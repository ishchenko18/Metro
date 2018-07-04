package infopulse.runnables;

import infopulse.partOfTrain.RailwayCarriage;
import infopulse.lines.Station;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Class describe process of exchanging passengers on the stations
 *
 * @author Ishchcenko Vladyslav
 * @version 1.0
 * @since 1.0
 */
public class ExchangePassengers implements Runnable {
    /**
     * Railway carriage for exchanging of passengers
     */
    private RailwayCarriage carriage;

    /**
     * Station, when will be exchanging
     */
    private Station station;

    /**
     * Check station on the last
     */
    private boolean isLast;

    /**
     * Object, which track exchanging of passengers
     */
    private CountDownLatch countDownLatch;

    /**
     * Constructor of initializing
     *
     * @param carriage       Railway carriage for exchanging of passengers
     * @param station        Station, when will be exchanging
     * @param isLast         Last station or no
     * @param countDownLatch Object, which track exchanging of passengers
     */
    public ExchangePassengers(RailwayCarriage carriage, Station station, boolean isLast, CountDownLatch countDownLatch) {
        this.carriage = carriage;
        this.station = station;
        this.isLast = isLast;
        this.countDownLatch = countDownLatch;
    }

    /**
     * Method, which run a Thread
     */
    @Override
    public void run() {
        Random random = new Random();

        if (!isLast) {
            if (!carriage.isEmpty()) {
                for (int i = 0; i < random.nextInt(carriage.countOfPassengers()); i++) {
                    carriage.passengerLeave();
                }
            }

            while (carriage.getSpaciousness() != carriage.countOfPassengers() && station.countOfPassengers() > 0) {
                carriage.passengerSeat(station.passengerLeaveStation());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            countDownLatch.countDown();
        } else {
            while (!carriage.isEmpty()) {
                carriage.passengerLeave();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            countDownLatch.countDown();
        }
    }
}
