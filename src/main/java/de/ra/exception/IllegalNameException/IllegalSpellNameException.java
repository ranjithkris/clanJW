package de.ra.exception.IllegalNameException;

/**
 * This class is a throwable exception to indicated that the given spell name is
 * not valid.
 */
public class IllegalSpellNameException extends IllegalItemNameException {
    /**
     * Constructs a new IllegalSpellNameException with the detailed message.
     *
     * @param spellName Spell name. Example: Lightning spell, Haste spell etc.
     *                  See the list of all spells
     *                  {@link de.ra.coc.COCData.HomeVillage.ElixirSpell}
     *                  {@link de.ra.coc.COCData.HomeVillage.DarkElixirSpell}
     */
    public IllegalSpellNameException(String spellName) {
        super(spellName);
    }
}
