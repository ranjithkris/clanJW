package de.ra.exception;

/**
 * This class is a throwable exception to indicate that the given army name is not
 * yet unlocked by the player.
 */
public class ArmyNotUnlockedException extends Exception{
    /**
     * Constructs a new ArmyNotUnlockedException with the detailed message.
     * @param armyName Army name. Example: ARCHER, HASTE SPELL etc.
     * @param armyCategory Category of the item. Example: spells, troops etc.
     * @param playerName Player name.
     */
    public ArmyNotUnlockedException(String armyName, String armyCategory, String playerName) {
        super(armyCategory + " : " +
                armyName + " is not yet unlocked by the player " +
                playerName);
    }
}
