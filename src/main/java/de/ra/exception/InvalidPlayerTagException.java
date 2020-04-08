package de.ra.exception;

/**
 * This class is a throwable exception to indicated that the given player tag is invalid.
 */
public class InvalidPlayerTagException extends ClanJWException {
    /**
     * Constructs a new PlayerNotFoundException with the detailed message and reason.
     * @param playerTag Player tag.
     */
    public InvalidPlayerTagException(String playerTag) {
        super("Player tag " + playerTag + " is invalid");
    }
}
