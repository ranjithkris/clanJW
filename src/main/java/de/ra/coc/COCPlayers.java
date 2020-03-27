package de.ra.coc;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class COCPlayers
{
    private static String JWTOKEN;
    private static String PLAYER_TAG;
    private static JSONObject PLAYER_INFORMATION;

    private static final String API_LINK = "https://api.clashofclans.com/";
    private static final String API_VERSION = "v1";

    public static final String ARCHER = "archer";

    public COCPlayers(String JWTOKEN)
    {
        COCPlayers.JWTOKEN = JWTOKEN;
    }

    public void setPlayerTag(String PLAYER_TAG)
    {
        COCPlayers.PLAYER_TAG = PLAYER_TAG;
        JSONObject json = null;

        try
        {
            HttpURLConnection connection = (HttpURLConnection) new URL(API_LINK + API_VERSION + "/players/" + PLAYER_TAG).openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("authorization", "Bearer " + JWTOKEN);

            InputStream input;
            int statusCode = connection.getResponseCode();
            System.out.println(statusCode);
            if (statusCode >= 200 && statusCode < 400)
            {
                input = connection.getInputStream();
            }
            else
            {
                input = connection.getErrorStream();
            }

            PLAYER_INFORMATION = InputToJson.getJSONObject(input);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getPlayerClanInformation()
    {
        JSONObject temp = null;
        try
        {
            temp = PLAYER_INFORMATION.getJSONObject("clan");
        }
        catch (JSONException ex)
        {
            System.err.println(ex);
        }

        return temp;
    }

    public String getPlayerClanName()
    {
        String temp = null;
        try
        {
            temp = (String) PLAYER_INFORMATION.getJSONObject("clan")
                    .get("name");
        }
        catch (JSONException ex)
        {
            System.err.println(ex);
        }

        return temp;
    }

    public String getPlayerClanSmallBadgeUrl()
    {
        String temp = null;
        try
        {
            temp = (String) PLAYER_INFORMATION.getJSONObject("clan")
                    .getJSONObject("badgeUrls")
                    .get("small");
        }
        catch (JSONException ex)
        {
            System.err.println(ex);
        }

        return temp;
    }

    public String getPlayerClanMediumBadgeUrl()
    {
        String temp = null;
        try
        {
            temp = (String) PLAYER_INFORMATION.getJSONObject("clan")
                    .getJSONObject("badgeUrls")
                    .get("medium");
        }
        catch (JSONException ex)
        {
            System.err.println(ex);
        }

        return temp;
    }

    public String getPlayerClanLargeBadgeUrl()
    {
        String temp = null;
        try
        {
            temp = (String) PLAYER_INFORMATION.getJSONObject("clan")
                    .getJSONObject("badgeUrls")
                    .get("large");
        }
        catch (JSONException ex)
        {
            System.err.println(ex);
        }

        return temp;
    }

    public String getPlayerClanTag()
    {
        String temp = null;
        try
        {
            temp = (String) PLAYER_INFORMATION.getJSONObject("clan")
                    .get("tag");
        }
        catch (JSONException ex)
        {
            System.err.println(ex);
        }

        return temp;
    }

    public Integer getPlayerClanLevel()
    {
        Integer temp = null;
        try
        {
            temp = (Integer) PLAYER_INFORMATION.getJSONObject("clan")
                    .get("clanLevel");
        }
        catch (JSONException ex)
        {
            System.err.println(ex);
        }

        return temp;
    }
}