package infopulse.people;

/**
 * Class describe driver
 *
 * @author Ishchenko Vladyslav
 * @author 1.0
 * @since 1.0
 */
public class Driver {
    /**
     * id of the driver
     */
    private int driverId;

    /**
     * FirstName of the driver
     */
    private String firstName;

    /**
     * Surname of the driver
     */
    private String surname;

    /**
     * Experience of the driver
     */
    private Experience experienceOfDriver;

    /**
     * Constructor of initializing
     *
     * @param driverId           id of the driver
     * @param firstName          FirstName of the driver
     * @param surname            Surname of the driver
     * @param experienceOfDriver Experience of the driver
     */
    public Driver(int driverId, String firstName, String surname, Experience experienceOfDriver) {
        this.driverId = driverId;
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
}
