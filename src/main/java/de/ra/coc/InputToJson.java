package de.ra.coc;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputToJson {
    protected static JSONObject getJSONObject(InputStream input) {
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

        System.out.println(response);
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
