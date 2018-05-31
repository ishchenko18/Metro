package infopulse.people;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Class describe driver
 *
 * @author Ishchenko Vladyslav
 * @author 1.0
 * @since 1.0
 */
@DatabaseTable(tableName = "driver")
public class Driver implements Comparable<Driver>{
    /**
     * id of the driver
     */
    @DatabaseField(id = true)
    private int driverId;

    /**
     * FirstName of the driver
     */
    @DatabaseField
    private String firstName;

    /**
     * Surname of the driver
     */
    @DatabaseField
    private String surname;

    /**
     * Experience of the driver
     */
    @DatabaseField
    private Experience experienceOfDriver;

    /**
     * ID of line, where he work
     */
    @DatabaseField
    private int lineId;


    Driver() {

    }

    /**
     * Constructor of initializing
     *
     * @param driverId           ID of the driver
     * @param firstName          FirstName of the driver
     * @param surname            Surname of the driver
     * @param lineId             ID of the line
     * @param experienceOfDriver Experience of the driver
     */
    public Driver(int driverId, int lineId, String firstName, String surname, Experience experienceOfDriver) {
        this.driverId = driverId;
        this.lineId = lineId;
        this.firstName = firstName;
        this.surname = surname;
        this.experienceOfDriver = experienceOfDriver;
    }

    /**
     * Getter for level of experience
     *
     * @return level of experience
     */
    public int getExperienceOfDriver() {
        if (experienceOfDriver == Experience.Senior) {
            return 1;
        } else if (experienceOfDriver == Experience.Middle) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * Setter for level of experience
     *
     * @param i level of experience
     */
    public void setExperienceOfDriver(int i) {
        if (i == 1) {
            experienceOfDriver = Experience.Senior;
        } else if (i == 2) {
            experienceOfDriver = Experience.Middle;
        } else if (i == 3) {
            experienceOfDriver = Experience.Junior;
        }
    }

    /**
     * Increase level of experience
     */
    public void upExperience() {
        if (this.experienceOfDriver != Experience.Senior) {
            this.setExperienceOfDriver(this.getExperienceOfDriver() - 1);
        }
    }

    /**
     * Method for giving information about driver
     *
     * @return Information about driver
     */
    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", experienceOfDriver=" + experienceOfDriver +
                '}';
    }

    @Override
    public int compareTo(Driver driver) {
        return this.getExperienceOfDriver() - driver.getExperienceOfDriver();
    }
}
