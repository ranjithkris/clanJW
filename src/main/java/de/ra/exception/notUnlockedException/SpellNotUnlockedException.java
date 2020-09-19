package de.ra.exception.notUnlockedException;

/**
 * This class is a throwable exception to indicate that the given spell name is not
 * yet unlocked by the player.
 */
public class SpellNotUnlockedException extends ItemNotUnlockedException {
    /**
     * Constructs a new SpellNotUnlockedException with the detailed message.
     *
     * @param spellName  Spell name. Example: Lightning spell, Haste spells etc.
     *                   See the list of all spells
     *                   {@link de.ra.coc.COCData.HomeVillage.ElixirSpell}
     *                   {@link de.ra.coc.COCData.HomeVillage.DarkElixirSpell}
     * @param playerName Player name.
     */
    public SpellNotUnlockedException(String spellName, String playerName) {
        super(spellName, playerName);
    }
}
