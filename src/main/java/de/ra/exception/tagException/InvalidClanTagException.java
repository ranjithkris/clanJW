package de.ra.exception.tagException;

/**
 * This class is a throwable exception to indicated that the given clan tag is invalid.
 */
public class InvalidClanTagException extends InvalidItemTagException {
    /**
     * Constructs a new InvalidClanTagException with the detailed message and reason.
     *
     * @param clanTag Clan tag.
     */
    public InvalidClanTagException(String clanTag) {
        super(clanTag);
    }
}
