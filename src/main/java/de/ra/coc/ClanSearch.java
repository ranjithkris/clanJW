package de.ra.coc;

import de.ra.coc.COCPlayers;
import de.ra.coc.InputToJson;
import de.ra.exception.COCServerConnectionException;
import de.ra.exception.InvalidPlayerTagException;
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

public class ClanSearch {
    protected String clanNameCriteria = null;
    protected Integer warFrequencyCriteria = -1;
    protected Integer locationIDCriteria = -1;
    protected Integer minMembersCriteria = -1;
    protected Integer maxMembersCriteria = -1;
    protected Integer minClanPointsCriteria = -1;
    protected Integer minClanLevelCriteria = -1;
    protected Integer resultLimit = -1;

    private static String JWTOKEN;
    private static final String API_LINK = "https://api.clashofclans.com/";
    private static final String API_VERSION = "v1";
    private static String completeLink = API_LINK + API_VERSION + "/clans?";

    /**
     * Constructs the ClanSearch with the Json Web Token.
     * To get the JSON Web Token see
     * <a href="https://developer.clashofclans.com/">https://developer.clashofclans.com/</a>
     *
     * @param JWToken Json Web Token
     */
    public ClanSearch(String JWToken) {
        ClanSearch.JWTOKEN = JWToken;
    }

    protected ClanSearch buildClanSearch() throws UnsupportedEncodingException {
        String param = "";
        if (clanNameCriteria != null && !clanNameCriteria.isEmpty())
            param += "name=" + URLEncoder.encode(clanNameCriteria, "UTF-8") + "&";

        if (warFrequencyCriteria != -1)
            param += "warFrequency=" + warFrequencyCriteria + "&";

        if (locationIDCriteria != -1)
            param += "locationId=" + locationIDCriteria + "&";

        if (minMembersCriteria != -1)
            param += "minMembers=" + minMembersCriteria + "&";

        if (maxMembersCriteria != -1)
            param += "maxMembers=" + maxMembersCriteria + "&";

        if (minClanPointsCriteria != -1)
            param += "minClanPoints=" + minClanPointsCriteria + "&";

        if (minClanLevelCriteria != -1)
            param += "minClanLevel=" + minClanLevelCriteria + "&";

        if (resultLimit != -1)
            param += "limit=" + resultLimit + "&";

        if (param.length() > 0) {
            completeLink += param.substring(0, param.length() - 1);
        }
        return this;
    }

    /**
     * This methods searches the clan with the search criteria set by ClanSearchFactory and
     * retrieves the result from clash of clan server.
     *
     * @throws COCServerConnectionException If fails to connect to clash of clan server.s
     */
    public void search() throws COCServerConnectionException {

        System.out.println(completeLink);
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(completeLink).openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("authorization", "Bearer " + JWTOKEN);

            InputStream input;
            int statusCode = connection.getResponseCode();

            System.out.println("Status code = " + statusCode);
            if (statusCode >= 200 && statusCode < 400) {
                input = connection.getInputStream();
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

            JSONObject jo = InputToJson.getJSONObject(input);
            System.out.println(jo);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
