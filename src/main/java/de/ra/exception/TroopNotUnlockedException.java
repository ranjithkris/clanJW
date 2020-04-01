package de.ra.exception;

/**
 * This class is a throwable exception to indicate that the given troop name is not
 * yet unlocked by the player.
 */
public class TroopNotUnlockedException extends Exception{
    /**
     * Constructs a new TroopNotUnlockedException with the detailed message.
     * @param troopName Troop name. Example: ARCHER, WIZARD etc.
     *                  @param troopName Troop name. Example: ARCHER, Wizard etc.
     *                  See the list of all troops
     *                  {@link de.ra.coc.COCData.HomeVillage.ElixirTroop}
     *                  {@link de.ra.coc.COCData.HomeVillage.DarkElixirTroop}
     *                  {@link de.ra.coc.COCData.BuilderBase.Troops}
     * @param playerName Player name.
     */
    public TroopNotUnlockedException(String troopName, String playerName) {
        super(troopName + " is not yet unlocked by the player " +
                playerName);
    }
}
