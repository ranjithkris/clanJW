package de.ra.exception;

/**
 * This class is a top level throwable exception to catch all the clanJW related exception.
 */
public class ClanJWException extends Exception {
    /**
     * Constructs a new ClanJWException with the detailed message and reason. This is the top level
     * exception that catches all the exception related to clanJW.
     *
     * @param message Detail message for connection fail.
     */
    public ClanJWException(String message) {
        super(message);
    }
}
