package de.ra.coc.COCData;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the heroes in home village.
 *
 * @author Ranjith Krishnamurthy
 */
public class Heroes {
    /**
     * Constant used to refer the Barbarian King in home village.
     */
    public static final String BARBARIAN_KING = "Barbarian King";

    /**
     * Constant used to refer the Archer Queen in home village.
     */
    public static final String ARCHER_QUEEN = "Archer Queen";

    /**
     * Constant used to refer the Grand Warden in home village.
     */
    public static final String GRAND_WARDEN = "Grand Warden";

    /**
     * Constant used to refer the Royal Champion in home village.
     */
    public static final String ROYAL_CHAMPION = "Royal Champion";

    /**
     * Constant used to refer the Royal Champion in home village.
     */
    public static final String BATTLE_MACHINE = "Battle Machine";

    /**
     * This method returns the list of all heroes in home village.
     *
     * @return Complete list of heroes in home village.
     */
    public static List<String> getHeroesList() {
        return new ArrayList<String>() {{
            add(BARBARIAN_KING);
            add(ARCHER_QUEEN);
            add(GRAND_WARDEN);
            add(ROYAL_CHAMPION);
            add(BATTLE_MACHINE);
        }};
    }
}