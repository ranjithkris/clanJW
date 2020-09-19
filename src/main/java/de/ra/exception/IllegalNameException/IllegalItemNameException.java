package de.ra.exception.IllegalNameException;

import de.ra.exception.ClanJWException;

/**
 * This class is a throwable exception to indicated that the given item name is
 * not valid.
 *
 * @author Ranjith Krishnamurthy
 */
public class IllegalItemNameException extends ClanJWException {
    /**
     * Constructs a new IllegalItemNameException with the detailed message.
     *
     * @param itemName Item name. Example: Lightning spell, Wizard etc.
     */
    public IllegalItemNameException(String itemName) {
        super(itemName + " is a illegal item name");
    }
}
