package de.ra.exception;

/**
 * This class is a throwable exception to indicated that the given spell name is
 * not valid.
 */
public class IllegalSpellNameException extends Exception{
    /**
     * Constructs a new IllegalSpellNameException with the detailed message.
     * @param spellName Spell name. Example: Lightning spell, Haste spell etc.
     */
    public IllegalSpellNameException(String spellName) {
        super(spellName + " is a illegal spell name");
    }
}
