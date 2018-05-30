package infopulse.people;

/**
 * Class describe a passenger in a Subway
 *
 * @author Ishchenko Vladyslav
 * @version 1.0
 * @since 1.0
 */
public class Passenger {
    /**
     * Count of the passengers
     */
    public static int countPassenger = 0;

    /**
     * ID of the passenger
     */
    private int id;

    /**
     * FirstName of the passenger
     */
    private String firstName;

    /**
     * Surname of the passenger
     */
    private String surname;

    /**
     * Constructor of initializing
     *
     * @param id        ID of the passenger
     * @param firstName FirstName of the passenger
     * @param surname   Surname of the passenger
     */
    public Passenger(int id, String firstName, String surname) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
    }

    /**
     * Default constructor
     */
    public Passenger() {
        this.id = ++countPassenger;
        firstName = "Unknown";
        surname = "Unknown";
    }

    /**
     * Method for giving information about passenger
     *
     * @return information about passenger
     */
    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
