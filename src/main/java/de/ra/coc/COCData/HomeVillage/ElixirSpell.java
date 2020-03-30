package de.ra.coc.COCData.HomeVillage;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Elixir spells in home village.
 */
public class ElixirSpell {
    /**
     * Constant used to refer the Lightning Spell in home village.
     */
    public static final String LIGHTNING_SPELL = "Lightning Spell";

    /**
     * Constant used to refer the Healing Spell in home village.
     */
    public static final String HEALING_SPELL = "Healing Spell";

    /**
     * Constant used to refer the Rage Spell in home village.
     */
    public static final String RAGE_SPELL = "Rage Spell";

    /**
     * Constant used to refer the Jump Spell in home village.
     */
    public static final String JUMP_SPELL = "Jump Spell";

    /**
     * Constant used to refer the Freeze Spell in home village.
     */
    public static final String FREEZE_SPELL = "Freeze Spell";

    /**
     * Constant used to refer the Clone Spell in home village.
     */
    public static final String CLONE_SPELL = "Clone Spell";

    /**
     * This method returns the list of all elixir spells in home village.
     * @return Complete list of elixir spells in home village.
     */
    public static List<String> getElixirSpellsList() {
        return new ArrayList<String>() {{
            add(LIGHTNING_SPELL);
            add(HEALING_SPELL);
            add(RAGE_SPELL);
            add(JUMP_SPELL);
            add(FREEZE_SPELL);
            add(CLONE_SPELL);
        }};
    }
}
