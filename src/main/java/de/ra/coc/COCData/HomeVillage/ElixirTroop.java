package de.ra.coc.COCData.HomeVillage;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Elixir troops in home village.
 */
public class ElixirTroop {
    /**
     * Constant used to refer the troop Barbarian in home village.
     */
    public static final String BARBARIAN = "Barbarian";

    /**
     * Constant used to refer the troop Archer in home village.
     */
    public static final String ARCHER = "Archer";

    /**
     * Constant used to refer the troop Giant in home village.
     */
    public static final String GIANT = "Giant";

    /**
     * Constant used to refer the troop Goblin in home village.
     */
    public static final String GOBLIN = "Goblin";

    /**
     * Constant used to refer the troop Wall Breaker in home village.
     */
    public static final String WALL_BREAKER = "Wall Breaker";

    /**
     * Constant used to refer the troop Balloon in home village.
     */
    public static final String BALLOON = "Balloon";

    /**
     * Constant used to refer the troop Wizard in home village.
     */
    public static final String WIZARD = "Wizard";

    /**
     * Constant used to refer the troop Healer in home village.
     */
    public static final String HEALER = "Healer";

    /**
     * Constant used to refer the troop Dragon in home village.
     */
    public static final String DRAGON = "Dragon";

    /**
     * Constant used to refer the troop P.E.K.K.A in home village.
     */
    public static final String PEKKA = "P.E.K.K.A";

    /**
     * Constant used to refer the troop Baby Dragon in home village.
     */
    public static final String BABY_DRAGON = "Baby Dragon";

    /**
     * Constant used to refer the troop Miner in home village.
     */
    public static final String MINER = "Miner";

    /**
     * Constant used to refer the troop Electro Dragon in home village.
     */
    public static final String ELECTRO_DRAGON = "Electro Dragon";

    /**
     * Constant used to refer the troop Yeti in home village.
     */
    public static final String YETI = "Yeti";

    /**
     * This method returns the list of all elixir troops in home village.
     * @return Complete list of elixir troops in home village.
     */
    public static List<String> getElixirTroopsList() {
        return new ArrayList<String>() {{
            add(BARBARIAN);
            add(ARCHER);
            add(GIANT);
            add(GOBLIN);
            add(WALL_BREAKER);
            add(BALLOON);
            add(WIZARD);
            add(HEALER);
            add(DRAGON);
            add(PEKKA);
            add(BABY_DRAGON);
            add(MINER);
            add(ELECTRO_DRAGON);
            add(YETI);
        }};
    }
}
