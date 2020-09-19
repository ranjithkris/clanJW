package de.ra.exception.IllegalNameException;

/**
 * This class is a throwable exception to indicated that the given hero name is
 * not valid.
 *
 * @author Ranjith Krishnamurthy
 */
public class IllegalHeroNameException extends IllegalItemNameException {
    /**
     * Constructs a new IllegalHeroNameException with the detailed message.
     *
     * @param heroName Hero name. Example: Barbarian King, Archer Queen etc.
     *                 See the list of all heroes {@link de.ra.coc.COCData.Heroes}
     */
    public IllegalHeroNameException(String heroName) {
        super(heroName);
    }
}
