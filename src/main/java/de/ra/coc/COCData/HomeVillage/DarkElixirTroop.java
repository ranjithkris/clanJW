package de.ra.coc.COCData.HomeVillage;

import com.sun.xml.internal.fastinfoset.algorithm.IEEE754FloatingPointEncodingAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Dark Elixir troops in home village.
 */
public class DarkElixirTroop {
    /**
     * Constant used to refer the troop Minion in home village.
     */
    public static final String MINION = "Minion";

    /**
     * Constant used to refer the troop Hog Rider in home village.
     */
    public static final String HOG_RIDER = "Hog Rider";

    /**
     * Constant used to refer the troop Valkyrie in home village.
     */
    public static final String VALKYRIE = "Valkyrie";

    /**
     * Constant used to refer the troop Golem in home village.
     */
    public static final String GOLEM = "Golem";

    /**
     * Constant used to refer the troop Witch in home village.
     */
    public static final String WITCH = "Witch";

    /**
     * Constant used to refer the troop Lava Hound in home village.
     */
    public static final String LAVA_HOUND = "Lava Hound";

    /**
     * Constant used to refer the troop Bowler in home village.
     */
    public static final String BOWLER = "Bowler";

    /**
     * Constant used to refer the troop Ice Golem in home village.
     */
    public static final String ICE_GOLEM = "Ice Golem";

    /**
     * This method returns the list of all dark elixir troops in home village.
     * @return Complete list of dark elixir troops in home village.
     */
    public static List<String> getDarkElixirTroopsList() {
        return new ArrayList<String>(){{
            add(MINION);
            add(HOG_RIDER);
            add(VALKYRIE);
            add(GOLEM);
            add(WITCH);
            add(LAVA_HOUND);
            add(BOWLER);
            add(ICE_GOLEM);
        }};
    }
}
