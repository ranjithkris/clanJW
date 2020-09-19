package de.ra.exception.notUnlockedException;

import de.ra.exception.ClanJWException;

/**
 * This class is a throwable exception to indicate that the given item name is not
 * yet unlocked by the player.
 */
public class ItemNotUnlockedException extends ClanJWException {
    /**
     * Constructs a new ItemNotUnlockedException with the detailed message.
     *
     * @param itemName   Item name. Example: Barbarian King, Lightning spell etc.
     * @param playerName Player name.
     */
    public ItemNotUnlockedException(String itemName, String playerName) {
        super(itemName + " is not yet unlocked by the player " + playerName);
    }
}