package de.ra.exception.serverConnectionException;

import de.ra.exception.ClanJWException;

/**
 * This class is a throwable exception to indicated that the connection to the
 * Clash of Clan server is failed.
 *
 * @author Ranjith Krishnamurthy
 */
public class COCServerConnectionException extends ClanJWException {
    /**
     * Constructs a new COCServerConnectionException with the detailed message and reason.
     *
     * @param statusCode Status code from the Clash of Clan server.
     * @param reason     Reason for the connection fail.
     * @param message    Detail message for connection fail.
     */
    public COCServerConnectionException(int statusCode, String reason, String message) {
        super("Failed to Connect to the Clash of Clans server with +" +
                statusCode + " status code.\n" +
                "Reason = " + reason +
                "\nMessage = " + message);
    }
}
