package de.ra.exception.tagException;

import de.ra.exception.ClanJWException;

/**
 * This class is a throwable exception to indicated that the given tag is invalid.
 */
public class InvalidItemTagException extends ClanJWException {
    /**
     * Constructs a new InvalidItemTagException with the detailed message and reason.
     *
     * @param tag tag.
     */
    public InvalidItemTagException(String tag) {
        super(tag + " is invalid tag");
    }
}