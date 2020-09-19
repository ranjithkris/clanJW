package de.ra.coc.ServerConnection;

import de.ra.coc.InputToJson;
import de.ra.exception.serverConnectionException.COCServerConnectionException;
import de.ra.exception.tagException.InvalidItemTagException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class provides the functionality to connect to the Clash of Clan server and retrieves the results.
 *
 * @author Ranjith Krishnamurthy
 */
public class HttpConnection {
    /**
     * Class of Clan server's link
     */
    public static final String API_LINK = "https://api.clashofclans.com/";

    /**
     * Class of Clan API version
     */
    public static final String API_VERSION = "v1";

    /**
     * This method connects to the provided Class of Clan API link and returns the result in JSONObject format.
     *
     * @param link    Complete link.
     * @param tag     Tag. It can be Player tag, Clan tag or null.
     * @param JWTOKEN JWToken.
     * @return Result of the request in JSONObject format.
     * @throws COCServerConnectionException If fails to connect to the Clash of Clan server.
     * @throws InvalidItemTagException      If the given tag is invalid.
     */
    public static JSONObject connectAndGetResults(String link, String tag, String JWTOKEN) throws COCServerConnectionException, InvalidItemTagException {
        //Todo: Before send connection request, sanitize the provided link.

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(link).openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("authorization", "Bearer " + JWTOKEN);

            InputStream input;
            int statusCode = connection.getResponseCode();

            if (statusCode >= 200 && statusCode < 400) {
                input = connection.getInputStream();
            } else if (statusCode == 404) {
                connection.disconnect();
                throw new InvalidItemTagException(tag);
            } else {
                input = connection.getErrorStream();
                JSONObject response = InputToJson.getJSONObject(input);
                String reason = "";
                String message = "";
                if (response.has("reason"))
                    reason = (String) response.get("reason");

                if (response.has("message"))
                    message = (String) response.get("message");

                connection.disconnect();
                throw new COCServerConnectionException(statusCode,
                        reason,
                        message);
            }

            JSONObject jsonObject = InputToJson.getJSONObject(input);
            connection.disconnect();

            return jsonObject;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }
}
