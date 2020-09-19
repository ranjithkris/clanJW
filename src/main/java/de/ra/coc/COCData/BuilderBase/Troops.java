package de.ra.coc.COCData.BuilderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the troops in builder base.
 *
 * @author Ranjith Krishnamurthy
 */
public class Troops {
    /**
     * Constant used to refer the troop Raged Barbarian in builder base.
     */
    public static final String RAGED_BARBARIAN = "Raged Barbarian";

    /**
     * Constant used to refer the troop Sneaky Archer in builder base.
     */
    public static final String SNEAKY_ARCHER = "Sneaky Archer";

    /**
     * Constant used to refer the troop Boxer Giant in builder base.
     */
    public static final String BOXER_GIANT = "Boxer Giant";

    /**
     * Constant used to refer the troop Beta Minion in builder base.
     */
    public static final String BETA_MINION = "Beta Minion";

    /**
     * Constant used to refer the troop Bomber in builder base.
     */
    public static final String BOMBER = "Bomber";

    /**
     * Constant used to refer the troop Baby Dragon in builder base.
     */
    public static final String BABY_DRAGON = "Baby Dragon";

    /**
     * Constant used to refer the troop Cannon Cart in builder base.
     */
    public static final String CANNON_CART = "Cannon Cart";

    /**
     * Constant used to refer the troop Night Witch in home village.
     */
    public static final String NIGHT_WITCH = "Night Witch";

    /**
     * Constant used to refer the troop Drop Ship in builder base.
     */
    public static final String DROP_SHIP = "Drop Ship";

    /**
     * Constant used to refer the troop Super P.E.K.K.A in builder base.
     */
    public static final String SUPER_PEKKA = "Super P.E.K.K.A";

    /**
     * Constant used to refer the troop Hog Glider in builder base.
     */
    public static final String HOG_GLIDER = "Hog Glider";

    /**
     * This method returns the list of all troops in builder base.
     *
     * @return Complete list of troops in builder base.
     */
    public static List<String> getBuilderBaseTroopsList() {
        return new ArrayList<String>() {{
            add(RAGED_BARBARIAN);
            add(SNEAKY_ARCHER);
            add(BOXER_GIANT);
            add(BETA_MINION);
            add(BOMBER);
            add(BABY_DRAGON);
            add(CANNON_CART);
            add(NIGHT_WITCH);
            add(DROP_SHIP);
            add(SUPER_PEKKA);
            add(HOG_GLIDER);
        }};
    }
}
