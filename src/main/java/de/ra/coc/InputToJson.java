package de.ra.coc;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class retrieves the JSONObject from the InputStream connected to the Clash of Clan server.
 *
 * @author Ranjith Krishnamurthy
 */
public class InputToJson {
    /**
     * This method retrieves the JSONObject from the InputStream connected to the Clash of Clan server.
     *
     * @param input InputStream connected to Clash of Clan server.
     * @return JSONObject.
     */
    public static JSONObject getJSONObject(InputStream input) {
        JSONObject json = null;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        StringBuilder response = new StringBuilder();

        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!(response.length() == 0)) {
            try {
                json = new JSONObject(response.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return json;
    }
}
