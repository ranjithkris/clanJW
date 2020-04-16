package de.ra.exception;

/**
 * This class is a throwable exception to indicated that the given clan tag is invalid.
 */
public class InvalidClanTagException extends ClanJWException {
    /**
     * Constructs a new InvalidClanTagException with the detailed message and reason.
     * @param clanTag Clan tag.
     */
    public InvalidClanTagException(String clanTag) {
        super("Clan tag " + clanTag + " is invalid");
    }
}
