package de.ra.exception;

/**
 * This class is a throwable exception to indicate that the given hero name is not
 * yet unlocked by the player.
 */
public class HeroNotUnlockedException extends ClanJWException {
    /**
     * Constructs a new HeroNotUnlockedException with the detailed message.
     * @param heroName Hero name. Example: Barbarian King, Archer Queen etc.
     *                 See the list of all heroes {@link de.ra.coc.COCData.Heroes}
     * @param playerName Player name.
     */
    public HeroNotUnlockedException(String heroName, String playerName) {
        super(heroName + " is not yet unlocked by the player " +
                playerName);
    }
}
