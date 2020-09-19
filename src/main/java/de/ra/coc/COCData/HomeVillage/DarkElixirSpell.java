package de.ra.coc.COCData.HomeVillage;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Dark Elixir spells in home village.
 *
 * @author Ranjith Krishnamurthy
 */
public class DarkElixirSpell {
    /**
     * Constant used to refer the Poison Spell in home village.
     */
    public static final String POISON_SPELL = "Poison Spell";

    /**
     * Constant used to refer the Earthquake Spell in home village.
     */
    public static final String EARTHQUAKE_SPELL = "Earthquake Spell";

    /**
     * Constant used to refer the Haste Spell in home village.
     */
    public static final String HASTE_SPELL = "Haste Spell";

    /**
     * Constant used to refer the Skeleton Spell in home village.
     */
    public static final String SKELETON_SPELL = "Skeleton Spell";

    /**
     * Constant used to refer the Bat Spell in home village.
     */
    public static final String BAT_SPELL = "Bat Spell";

    /**
     * This method returns the list of all dark elixir spells in home village.
     *
     * @return Complete list of dark elixir spells in home village.
     */
    public static List<String> getDarkElixirSpellsList() {
        return new ArrayList<String>() {{
            add(POISON_SPELL);
            add(EARTHQUAKE_SPELL);
            add(HASTE_SPELL);
            add(SKELETON_SPELL);
            add(BAT_SPELL);
        }};
    }
}
