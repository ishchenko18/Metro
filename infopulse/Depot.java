package infopulse;

import java.util.ArrayList;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import infopulse.ComponentsOfTrain.RailwayCarriage;

/**
 * Class describe a depot of a line
 *
 * @author Ishchenko Vladyslav
 * @version 1.0
 * @since 1.0
 */
@DatabaseTable(tableName = "depot")
public class Depot {
    /**
     * Count of depot in a subway
     */
    public static int countOfDepot = 0;

    /**
     * Number of depot
     */
    @DatabaseField(id = true)
    private int numberOfDepot;

    /**
     * List of simple railway carriages
     */
    private ArrayList<RailwayCarriage> simpleRailwayCarriages;

    /**
     * List of main railway carriages
     */
    private ArrayList<RailwayCarriage> mainRailwayCarriages;

    /**
     *
     */
    Depot() {
        mainRailwayCarriages = new ArrayList<>();
        simpleRailwayCarriages = new ArrayList<>();
    }

    /**
     * Constructor of initializing
     *
     * @param carriage Railway carriage
     */
    public Depot(RailwayCarriage carriage) {
        numberOfDepot = ++countOfDepot;

        mainRailwayCarriages = new ArrayList<>();
        simpleRailwayCarriages = new ArrayList<>();

        if (carriage.isMainCarriage()) {
            mainRailwayCarriages.add(carriage);
        } else {
            simpleRailwayCarriages.add(carriage);
        }
    }

    /**
     * Method for adding new simple railway carriage
     */
    public void addNewSimpleRailwayCarriage() {
        if (!simpleRailwayCarriages.isEmpty()) {
            try {
                simpleRailwayCarriages.add(simpleRailwayCarriages.get(0).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        } else {
            simpleRailwayCarriages.add(new RailwayCarriage(mainRailwayCarriages.get(0).getSpaciousness(), false));
        }
    }

    /**
     * Method for adding new main railway carriage
     */
    public void addNewMainRailwayCarriage() {
        if (!mainRailwayCarriages.isEmpty()) {
            try {
                mainRailwayCarriages.add(mainRailwayCarriages.get(0).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        } else {
            mainRailwayCarriages.add(new RailwayCarriage(simpleRailwayCarriages.get(0).getSpaciousness(), true));
        }
    }

    /**
     * @param simpleRailwayCarriages
     */
    public void setSimpleRailwayCarriages(ArrayList<RailwayCarriage> simpleRailwayCarriages) {
        this.simpleRailwayCarriages = simpleRailwayCarriages;
    }

    /**
     * @param mainRailwayCarriages
     */
    public void setMainRailwayCarriages(ArrayList<RailwayCarriage> mainRailwayCarriages) {
        this.mainRailwayCarriages = mainRailwayCarriages;
    }

    /**
     * Method for getting simple carriage
     *
     * @return simple carriage
     */
    public RailwayCarriage getSimpleRailwayCarriage() {
        RailwayCarriage carriage = simpleRailwayCarriages.get(simpleRailwayCarriages.size() - 1);
        deleteLastSimpleRailwayCarriages();
        return carriage;
    }

    /**
     * Method for getting main carriage
     *
     * @return main carriage
     */
    public RailwayCarriage getMainRailwayCarriage() {
        RailwayCarriage carriage = mainRailwayCarriages.get(mainRailwayCarriages.size() - 1);
        deleteLastMainRailwayCarriages();
        return carriage;
    }

    /**
     * Method for deleting last main carriage from the list
     */
    private void deleteLastMainRailwayCarriages() {
        if (!mainRailwayCarriages.isEmpty()) {
            mainRailwayCarriages.remove(mainRailwayCarriages.size() - 1);
        }
    }

    /**
     * Method for deleting last simple carriage from the list
     */
    private void deleteLastSimpleRailwayCarriages() {
        if (!simpleRailwayCarriages.isEmpty()) {
            simpleRailwayCarriages.remove(simpleRailwayCarriages.size() - 1);
        }
    }

    /**
     * Method for getting count of simple carriage into depot
     *
     * @return Count of simple carriage
     */
    public int getCountOfSimpleCarriages() {
        return simpleRailwayCarriages.size();
    }

    /**
     * Method for getting count of main carriage into depot
     *
     * @return Count of main carriage
     */
    public int getCountOfMainCarriages() {
        return mainRailwayCarriages.size();
    }

    /**
     * Check depot on possibility of creating new train
     *
     * @return Result of checking
     */
    public boolean checkOnPossibilityCreatingTrain() {
        return (mainRailwayCarriages.size() >= 2 && (mainRailwayCarriages.size() + simpleRailwayCarriages.size()) >= 5);
    }

    /**
     * @return
     */
    public int getNumberOfDepot() {
        return numberOfDepot;
    }

    public void print() {
        mainRailwayCarriages.stream().forEach(carriage -> System.out.println(carriage));

        simpleRailwayCarriages.stream().forEach(carriage -> System.out.println(carriage));
    }
}
