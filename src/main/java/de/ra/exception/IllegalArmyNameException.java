package de.ra.exception;

/**
 * This class is a throwable exception to indicated that the given army name is
 * not valid.
 */
public class IllegalArmyNameException extends Exception{
    /**
     * Constructs a new IllegalArmyNameException with the detailed message.
     * @param armyName Army name. Example: ARCHER, HASTE SPELL etc.
     * @param armyCategory Category of the Army. Example: spells, troops etc.
     */
    public IllegalArmyNameException(String armyName, String armyCategory) {
        super(armyCategory +
                " : " + armyName + " is a illegal " + armyCategory + " name");
    }
}
