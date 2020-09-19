package de.ra.coc.Clan;


import java.io.UnsupportedEncodingException;

/**
 * ClanSearchFactory class is used to build the ClanSearch object with different filters.
 */
public class ClanSearchFactory {
    /**
     * Constant to refer the after cursor position.
     */
    public static final String AFTER = "after";

    /**
     * Constant to refer the before cursor position.
     */
    public static final String BEFORE = "before";

    private String clanNameCriteria = null;
    private String afterCursor = null;
    private String beforeCursor = null;
    private Integer warFrequencyCriteria = -1;
    private Integer locationIDCriteria = -1;
    private Integer minMembersCriteria = -1;
    private Integer maxMembersCriteria = -1;
    private Integer minClanPointsCriteria = -1;
    private Integer minClanLevelCriteria = -1;
    private Integer resultLimit = 20;
    private String JWTOKEN;

    /**
     * Constructs the ClanSearchFactory object with the given Json Web Token to connect to the
     * clash of clan server.
     * To get the JSON Web Token see
     * <a href="https://developer.clashofclans.com/">https://developer.clashofclans.com/</a>
     *
     * @param JWToken Json Wen Token from clash of clan server.
     */
    public ClanSearchFactory(String JWToken) {
        JWTOKEN = JWToken;
    }

    /**
     * This method sets the search criteria with the given clan name.
     *
     * @param clanNameCriteria Clan name.
     * @return returns the ClanSearchFactory.
     */
    public ClanSearchFactory setClanNameCriteria(String clanNameCriteria) {
        this.clanNameCriteria = clanNameCriteria;
        return this;
    }

    /**
     * This method sets the search criteria with the given war frequency.
     *
     * @param warFrequencyCriteria War frequency.
     * @return returns the ClanSearchFactory.
     */
    public ClanSearchFactory setWarFrequencyCriteria(Integer warFrequencyCriteria) {
        this.warFrequencyCriteria = warFrequencyCriteria;
        return this;
    }

    /**
     * This method sets the search criteria with the given location id.
     *
     * @param locationIDCriteria Location id.
     * @return returns the ClanSearchFactory.
     */
    public ClanSearchFactory setLocationIDCriteria(Integer locationIDCriteria) {
        this.locationIDCriteria = locationIDCriteria;
        return this;
    }

    /**
     * This method sets the search criteria with the given minimum members in a clan.
     *
     * @param minMembersCriteria Minimum members in a clan.
     * @return returns the ClanSearchFactory.
     */
    public ClanSearchFactory setMinMembersCriteria(Integer minMembersCriteria) {
        this.minMembersCriteria = minMembersCriteria;
        return this;
    }

    /**
     * This method sets the search criteria with the given maximum members in a clan.
     *
     * @param maxMembersCriteria maximum members in a clan.
     * @return returns the ClanSearchFactory.
     */
    public ClanSearchFactory setMaxMembersCriteria(Integer maxMembersCriteria) {
        this.maxMembersCriteria = maxMembersCriteria;
        return this;
    }

    /**
     * This method sets the search criteria with the given minimum clan points of a clan.
     *
     * @param minClanPointsCriteria Minimum clan points of a clan.
     * @return returns the ClanSearchFactory.
     */
    public ClanSearchFactory setMinClanPointsCriteria(Integer minClanPointsCriteria) {
        this.minClanPointsCriteria = minClanPointsCriteria;
        return this;
    }

    /**
     * This method sets the search criteria with the given minimum clan level.
     *
     * @param minClanLevelCriteria Minimum clan level.
     * @return returns the ClanSearchFactory.
     */
    public ClanSearchFactory setMinClanLevelCriteria(Integer minClanLevelCriteria) {
        this.minClanLevelCriteria = minClanLevelCriteria;
        return this;
    }

    /**
     * This method sets the number of items retrieved by the search.
     *
     * @param resultLimit Number of item retrieved by the search.
     * @return returns the ClanSearchFactory.
     */
    public ClanSearchFactory setResultLimit(Integer resultLimit) {
        this.resultLimit = resultLimit;
        return this;
    }

    /**
     * This method sets the cursor with the given position.
     * Position values are after and before. If invalid positions are passed to this method, then by default it this
     * method will set the position to after. See the valid position values
     * {@link de.ra.coc.Clan.ClanSearchFactory}
     *
     * @param position Position of the cursor
     * @param cursor   Cursor.
     * @return returns the ClanSearchFactory.
     */
    public ClanSearchFactory setCursor(String position, String cursor) {
        if (position == BEFORE) {
            this.afterCursor = null;
            this.beforeCursor = cursor;
        } else {
            this.afterCursor = cursor;
            this.beforeCursor = null;
        }

        return this;
    }

    /**
     * This method generates the ClanSearch object with the given search criteria.
     *
     * @return Returns the ClanSearch object with the search criteria.
     * @throws UnsupportedEncodingException If the given search criteria is failed
     *                                      to encode for the URL.
     */
    public ClanSearch build() throws UnsupportedEncodingException {
        ClanSearch clanSearch = new ClanSearch(JWTOKEN);
        clanSearch.clanNameCriteria = clanNameCriteria;
        clanSearch.warFrequencyCriteria = warFrequencyCriteria;
        clanSearch.locationIDCriteria = locationIDCriteria;
        clanSearch.minMembersCriteria = minMembersCriteria;
        clanSearch.maxMembersCriteria = maxMembersCriteria;
        clanSearch.minClanPointsCriteria = minClanPointsCriteria;
        clanSearch.minClanLevelCriteria = minClanLevelCriteria;
        clanSearch.resultLimit = resultLimit;
        clanSearch.afterCursor = afterCursor;
        clanSearch.beforeCursor = beforeCursor;

        return clanSearch.buildClanSearch();
    }
}
