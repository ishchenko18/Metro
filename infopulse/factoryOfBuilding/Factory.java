package infopulse.factoryOfBuilding;

import infopulse.partOfTrain.RailwayCarriage;
import infopulse.partOfTrain.Train;
import infopulse.mainComponents.Depot;
import infopulse.exceptions.DepotNotExistException;
import infopulse.exceptions.NegativeValueException;
import infopulse.exceptions.WrongCreatingTrainException;

import java.util.Random;

/**
 * Pattern Abstract Factory for building new trains and new depots
 *
 * @author Ishchenko Vladyslav
 * @version 1.0
 * @since 1.0
 */
public class Factory {
    /**
     * Method for creating new train
     *
     * @param depot depot with carriages
     * @return created train
     * @throws WrongCreatingTrainException exception, when not enough carriages
     * @throws DepotNotExistException      exception, when depot not exist
     */
    public static Train buildNewTrain(Depot depot) throws DepotNotExistException {
        if (depot != null) {
            Train train = new Train.BuilderOfTrains(depot).createTrain();
            return train;
        } else {
            throw new DepotNotExistException("Depot can't be null");
        }
    }

    /**
     * Method for creating new depot on the line
     *
     * @param spaciousness maximal count of people in the carriage of this depot
     * @return created depot
     * @throws NegativeValueException exception, when spaciousness is negative
     */
    public static Depot buildNewDepotOnTheLine(int spaciousness) throws NegativeValueException {
        Depot depot;
        int countOfSimpleCarriages;
        Random random = new Random();

        if (spaciousness > 0) {
            if (random.nextInt((100) + 1) <= 30) {
                depot = new Depot(new RailwayCarriage(spaciousness, true));
            } else {
                depot = new Depot(new RailwayCarriage(spaciousness, false));
            }

            countOfSimpleCarriages = (int) Math.round(random.nextInt((50) + 1) * 0.7);

            for (int i = 0; i < countOfSimpleCarriages; i++) {
                depot.addNewSimpleRailwayCarriage();
            }

            for (int i = 0; i < Math.round(countOfSimpleCarriages / 0.7) * 0.3; i++) {
                depot.addNewMainRailwayCarriage();
            }

            return depot;
        } else {
            throw new NegativeValueException("Spaciousness of railway carriage should be positive.");
        }
    }
}
