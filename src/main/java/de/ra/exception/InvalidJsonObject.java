package de.ra.exception;

/**
 * This class is a throwable exception to indicated that the JsonObject from the clash of clan server is not valid.
 */
public class InvalidJsonObject extends ClanJWException {
    /**
     * Constructs a new InvalidJsonObject with the detailed message and reason.
     */
    public InvalidJsonObject() {
        super("Invalid Json data.");
    }
}
