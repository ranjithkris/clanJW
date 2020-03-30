package de.ra.exception;

/**
 * This class is a throwable exception to indicate that the given spell name is not
 * yet unlocked by the player.
 */
public class SpellNotUnlockedException extends Exception{
    /**
     * Constructs a new SpellNotUnlockedException with the detailed message.
     * @param spellName Spell name.
     * @param playerName Player name.
     */
    public SpellNotUnlockedException(String spellName, String playerName) {
        super("Troop " +
                spellName + " is not yet unlocked by the player " +
                playerName);
    }
}
