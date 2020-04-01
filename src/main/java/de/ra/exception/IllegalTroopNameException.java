package de.ra.exception;

/**
 * This class is a throwable exception to indicated that the given troop name is
 * not valid.
 */
public class IllegalTroopNameException extends Exception{
    /**
     * Constructs a new IllegalTroopNameException with the detailed message.
     * @param troopName Troop name. Example: ARCHER, Wizard etc.
     *                  See the list of all troops
     *                  {@link de.ra.coc.COCData.HomeVillage.ElixirTroop}
     *                  {@link de.ra.coc.COCData.HomeVillage.DarkElixirTroop}
     *                  {@link de.ra.coc.COCData.BuilderBase.Troops}
     */
    public IllegalTroopNameException(String troopName) {
        super(troopName + " is a illegal troop name");
    }
}
