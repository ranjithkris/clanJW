package de.ra.exception.tagException;

/**
 * This class is a throwable exception to indicated that the given player tag is invalid.
 *
 * @author Ranjith Krishnamurthy
 */
public class InvalidPlayerTagException extends InvalidItemTagException {
    /**
     * Constructs a new PlayerNotFoundException with the detailed message and reason.
     *
     * @param playerTag Player tag.
     */
    public InvalidPlayerTagException(String playerTag) {
        super("Player tag " + playerTag + " is invalid");
    }
}
