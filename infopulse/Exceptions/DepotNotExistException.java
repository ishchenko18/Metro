package infopulse.Exceptions;

/**
 * Describe exception, when depot not exist
 *
 * @author Ishchenko Vladyslav
 * @since 1.0
 * @version 1.0
 * @exception Exception
 */
public class DepotNotExistException extends Exception {
    public DepotNotExistException(String message) {
        super(message);
    }
}
