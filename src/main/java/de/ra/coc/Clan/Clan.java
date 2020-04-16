package de.ra.coc.Clan;

import de.ra.coc.InputToJson;
import de.ra.exception.COCServerConnectionException;
import de.ra.exception.InvalidClanTagException;
import org.json.JSONException;
import org.json.JSONObject;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * This class represents the Clash of Clan's single Clan.
 * This class requires clan's tag and
 * the JSON Web Token to connect to the Clash of Clan server to retrieve the
 * Clan'S information. To get the JSON Web Token see <a href="https://developer.clashofclans.com/">https://developer.clashofclans.com/</a>
 */
public class Clan {
    private static String JWTOKEN;
    private static String CLAN_TAG;
    private static JSONObject CLAN_INFORMATION;

    private static final String API_LINK = "https://api.clashofclans.com/";
    private static final String API_VERSION = "v1";

    /**
     * Initialize newly constructed Clan with the passed JSON Web Token and clan's tag and retrieves the clan's information
     * from the Clash of Clan server.
     * To get the JSON Web Token see
     * <a href="https://developer.clashofclans.com/">https://developer.clashofclans.com/</a>
     *
     * @param JWTOKEN    JSON Web Token.
     * @param CLAN_TAG Clan's tag.
     * @throws COCServerConnectionException If the connection to Clash of Clan server is failed.
     * @throws InvalidClanTagException    If the given clan tag contains invalid character or not found in Clash of Clans server.
     * @throws UnsupportedEncodingException If the given player tag is failed to encode to avoid taint-style vulnerabilities.
     */
    public Clan(String JWTOKEN, String CLAN_TAG) throws COCServerConnectionException, UnsupportedEncodingException, InvalidClanTagException {
        Clan.JWTOKEN = JWTOKEN;
        changeClanTag(CLAN_TAG);
    }

    /**
     * This method changes the clan's tag that is set previously and retrieves the information
     * of the new clan.
     *
     * @param CLAN_TAG Clan's tag.
     * @throws COCServerConnectionException If the connection to Clash of Clan server is failed.
     * @throws InvalidClanTagException    If the given clan tag contains invalid character or not found in Clash of Clans server.
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
        Clan.CLAN_TAG = URLEncoder.encode(CLAN_TAG, "UTF-8");

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(API_LINK + API_VERSION + "/clans/" + Clan.CLAN_TAG).openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("authorization", "Bearer " + JWTOKEN);

            InputStream input;
            int statusCode = connection.getResponseCode();

            if (statusCode >= 200 && statusCode < 400) {
                input = connection.getInputStream();
            } else if (statusCode == 404) {
                throw new InvalidClanTagException(CLAN_TAG);

            } else {
                input = connection.getErrorStream();
                JSONObject response = InputToJson.getJSONObject(input);
                System.out.println(response);
                String reason = "";
                String message = "";
                if (response.has("reason"))
                    reason = (String) response.get("reason");

                if (response.has("message"))
                    message = (String) response.get("message");

                throw new COCServerConnectionException(statusCode,
                        reason,
                        message);
            }

            CLAN_INFORMATION = InputToJson.getJSONObject(input);
            System.out.println(CLAN_INFORMATION);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
