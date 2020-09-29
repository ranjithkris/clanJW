package de.ra.coc.Clan;

import de.ra.coc.ServerConnection.HttpConnection;
import de.ra.exception.serverConnectionException.COCServerConnectionException;
import de.ra.exception.tagException.InvalidClanTagException;
import de.ra.exception.tagException.InvalidItemTagException;
import org.json.JSONException;
import org.json.JSONObject;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * This class represents the Clash of Clan's single Clan.
 * This class requires clan's tag and
 * the JSON Web Token to connect to the Clash of Clan server to retrieve the
 * Clan'S information. To get the JSON Web Token see <a href="https://developer.clashofclans.com/">https://developer.clashofclans.com/</a>
 *
 * @author Ranjith Krishnamurthy
 */
public class Clan {
    private String JWTOKEN;
    private String CLAN_TAG;
    private JSONObject CLAN_INFORMATION;


    /**
     * Initialize newly constructed Clan with the passed JSON Web Token and clan's tag and retrieves the clan's information
     * from the Clash of Clan server.
     * To get the JSON Web Token see
     * <a href="https://developer.clashofclans.com/">https://developer.clashofclans.com/</a>
     *
     * @param JWToken JSON Web Token.
     * @param ClanTag Clan's tag.
     * @throws COCServerConnectionException If the connection to Clash of Clan server is failed.
     * @throws InvalidClanTagException      If the given clan tag contains invalid character or not found in Clash of Clans server.
     * @throws UnsupportedEncodingException If the given player tag is failed to encode to avoid taint-style vulnerabilities.
     */
    public Clan(String JWToken, String ClanTag) throws COCServerConnectionException, UnsupportedEncodingException, InvalidClanTagException {
        JWTOKEN = JWToken;
        CLAN_TAG = ClanTag;
        changeClanTag(CLAN_TAG);
    }

    /**
     * This method changes the clan's tag that is set previously and retrieves the information
     * of the new clan.
     *
     * @param CLAN_TAG Clan's tag.
     * @throws COCServerConnectionException If the connection to Clash of Clan server is failed.
     * @throws InvalidClanTagException      If the given clan tag contains invalid character or not found in Clash of Clans server.
     * @throws UnsupportedEncodingException If the given player tag is failed to encode to avoid taint-style vulnerabilities.
     */
    public void changeClanTag(String CLAN_TAG) throws COCServerConnectionException, UnsupportedEncodingException, InvalidClanTagException {
        PolicyFactory myPF = Sanitizers.BLOCKS
                .and(Sanitizers.FORMATTING)
                .and(Sanitizers.IMAGES)
                .and(Sanitizers.LINKS)
                .and(Sanitizers.STYLES)
                .and(Sanitizers.TABLES);

        CLAN_TAG = myPF.sanitize(CLAN_TAG);
        CLAN_TAG = URLEncoder.encode(CLAN_TAG, "UTF-8");

        try {
            CLAN_INFORMATION = HttpConnection.connectAndGetResults(
                    HttpConnection.API_LINK + HttpConnection.API_VERSION + "/clans/" + CLAN_TAG,
                    CLAN_TAG,
                    JWTOKEN
            );
        } catch (InvalidItemTagException e) {
            throw new InvalidClanTagException(CLAN_TAG);
        }
    }

    /**
     * This method returns the clan's war frequency.
     *
     * @return Clan's war frequency.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public String getWarFrequency() throws JSONException {
        return CLAN_INFORMATION.has("warFrequency") ? (String) CLAN_INFORMATION.get("warFrequency") : "NotApplicable";
    }

    /**
     * This method returns the clan's war league name.
     *
     * @return Clan's war league name.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public String getWarLeagueName() throws JSONException {
        return CLAN_INFORMATION.has("warLeague") ? (String) CLAN_INFORMATION.getJSONObject("warLeague").get("name") : "NotApplicable";
    }

    /**
     * This method returns the clan's war league id.
     *
     * @return Clan's war league id.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public Integer getWarLeagueId() throws JSONException {
        return CLAN_INFORMATION.has("warLeague") ? (Integer) CLAN_INFORMATION.getJSONObject("warLeague").get("id") : -1;
    }

    /**
     * This method returns the number clan war wins.
     *
     * @return Number clan war wins.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public Integer getNumberOfWarWins() throws JSONException {
        return CLAN_INFORMATION.has("warWins") ? (Integer) CLAN_INFORMATION.get("warWins") : -1;
    }

    /**
     * This method returns the clan war win streak.
     *
     * @return Clan war win streak.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public Integer getNumberOfWarWinStreak() throws JSONException {
        return CLAN_INFORMATION.has("warWinStreak") ? (Integer) CLAN_INFORMATION.get("warWinStreak") : -1;
    }

    /**
     * This method returns the clan level.
     *
     * @return Clan level.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public Integer getClanLevel() throws JSONException {
        return CLAN_INFORMATION.has("clanLevel") ? (Integer) CLAN_INFORMATION.get("clanLevel") : -1;
    }

    /**
     * This method returns the required trophies.
     *
     * @return Required trophies.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public Integer getRequiredTrophies() throws JSONException {
        return CLAN_INFORMATION.has("requiredTrophies") ? (Integer) CLAN_INFORMATION.get("requiredTrophies") : -1;
    }

    /**
     * This method returns the clan description.
     *
     * @return Clan description.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public String getClanDescription() throws JSONException {
        return CLAN_INFORMATION.has("description") ? (String) CLAN_INFORMATION.get("description") : "NotApplicable";
    }

    /**
     * This method returns the number of members in clan.
     *
     * @return Number of members in clan.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public Integer getNumberOfMembers() throws JSONException {
        return CLAN_INFORMATION.has("members") ? (Integer) CLAN_INFORMATION.get("members") : -1;
    }

    /**
     * This method returns the clan type.
     *
     * @return Clan type.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public String getClanType() throws JSONException {
        return CLAN_INFORMATION.has("type") ? (String) CLAN_INFORMATION.get("type") : "NotApplicable";
    }

    /**
     * This method returns the clan points
     *
     * @return Clan points
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public Integer getClanPoints() throws JSONException {
        return CLAN_INFORMATION.has("clanPoints") ? (Integer) CLAN_INFORMATION.get("clanPoints") : -1;
    }

    /**
     * This method returns the clan name.
     *
     * @return Clan name.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public String getClanName() throws JSONException {
        return CLAN_INFORMATION.has("name") ? (String) CLAN_INFORMATION.get("name") : "NotApplicable";
    }

    /**
     * This method returns the clan versus points
     *
     * @return Clan versus points
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public Integer getClanVersusPoints() throws JSONException {
        return CLAN_INFORMATION.has("clanVersusPoints") ? (Integer) CLAN_INFORMATION.get("clanVersusPoints") : -1;
    }

    /**
     * This method returns the clan tag.
     *
     * @return Clan tag.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public String getClanTag() throws JSONException {
        return CLAN_INFORMATION.has("tag") ? (String) CLAN_INFORMATION.get("tag") : "NotApplicable";
    }

    /**
     * This method returns isWarLogPublic.
     *
     * @return isWarLogPublic.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public boolean isWarLogPublic() throws JSONException {
        return CLAN_INFORMATION.has("isWarLogPublic") && (boolean) CLAN_INFORMATION.get("isWarLogPublic");
    }

    /**
     * This method returns the number of clan war ties.
     *
     * @return Number of clan war ties.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public Integer getNumberOfClanWarTies() throws JSONException {
        return CLAN_INFORMATION.has("warTies") ? (Integer) CLAN_INFORMATION.get("warTies") : -1;
    }

    /**
     * This method returns the number of clan war losses.
     *
     * @return Number of clan war losses.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public Integer getNumberOfClanWarLosses() throws JSONException {
        return CLAN_INFORMATION.has("warLosses") ? (Integer) CLAN_INFORMATION.get("warLosses") : -1;
    }

    /**
     * This method returns the small clan badge url.
     *
     * @return Small clan badge url.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public String getSmallClanBadgeUrl() throws JSONException {
        return CLAN_INFORMATION.has("badgeUrls") ?
                (String) CLAN_INFORMATION.getJSONObject("badgeUrls").get("small") :
                "NotApplicable";
    }

    /**
     * This method returns the large clan badge url.
     *
     * @return Large clan badge url.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public String getLargeClanBadgeUrl() throws JSONException {
        return CLAN_INFORMATION.has("badgeUrls") ?
                (String) CLAN_INFORMATION.getJSONObject("badgeUrls").get("large") :
                "NotApplicable";
    }

    /**
     * This method returns the medium clan badge url.
     *
     * @return Medium clan badge url.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public String getMediumClanBadgeUrl() throws JSONException {
        return CLAN_INFORMATION.has("badgeUrls") ?
                (String) CLAN_INFORMATION.getJSONObject("badgeUrls").get("medium") :
                "NotApplicable";
    }
}
