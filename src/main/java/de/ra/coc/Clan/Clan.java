package de.ra.coc.Clan;

import de.ra.coc.ServerConnection.HttpConnection;
import de.ra.exception.serverConnectionException.COCServerConnectionException;
import de.ra.exception.tagException.InvalidClanTagException;
import de.ra.exception.tagException.InvalidItemTagException;
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
     * @param JWToken  JSON Web Token.
     * @param CLAN_TAG Clan's tag.
     * @throws COCServerConnectionException If the connection to Clash of Clan server is failed.
     * @throws InvalidClanTagException      If the given clan tag contains invalid character or not found in Clash of Clans server.
     * @throws UnsupportedEncodingException If the given player tag is failed to encode to avoid taint-style vulnerabilities.
     */
    public Clan(String JWToken, String CLAN_TAG) throws COCServerConnectionException, UnsupportedEncodingException, InvalidClanTagException {
        JWTOKEN = JWToken;
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
}
