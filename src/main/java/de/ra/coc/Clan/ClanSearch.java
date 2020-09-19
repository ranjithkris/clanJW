package de.ra.coc.Clan;

import de.ra.coc.InputToJson;
import de.ra.coc.ServerConnection.HttpConnection;
import de.ra.exception.serverConnectionException.COCServerConnectionException;
import de.ra.exception.jsonException.InvalidJsonObject;
import de.ra.exception.tagException.InvalidItemTagException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * ClanSearch class is used to search for clans with different filters.
 */
public class ClanSearch {
    protected String clanNameCriteria = null;
    protected String afterCursor = null;
    protected String beforeCursor = null;
    protected Integer warFrequencyCriteria = -1;
    protected Integer locationIDCriteria = -1;
    protected Integer minMembersCriteria = -1;
    protected Integer maxMembersCriteria = -1;
    protected Integer minClanPointsCriteria = -1;
    protected Integer minClanLevelCriteria = -1;
    protected Integer resultLimit = 20;

    private JSONObject searchResult = null;
    private String JWTOKEN;
    private String completeLink = HttpConnection.API_LINK + HttpConnection.API_VERSION + "/clans?";

    /**
     * Constructs the ClanSearch with the Json Web Token.
     * To get the JSON Web Token see
     * <a href="https://developer.clashofclans.com/">https://developer.clashofclans.com/</a>
     *
     * @param JWToken Json Web Token
     */
    public ClanSearch(String JWToken) {
        JWTOKEN = JWToken;
    }

    protected ClanSearch buildClanSearch() throws UnsupportedEncodingException {
        completeLink = HttpConnection.API_LINK + HttpConnection.API_VERSION + "/clans?";

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

        if (afterCursor != null && beforeCursor == null) {
            if (afterCursor != "")
                param += "after=" + afterCursor + "&";
        } else if (afterCursor == null && beforeCursor != null) {
            if (beforeCursor != "")
                param += "before=" + beforeCursor + "&";
        } else if (afterCursor != null && beforeCursor != null) {
            if (afterCursor != "")
                param += "after=" + afterCursor + "&";
        }

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

        try {
            searchResult = HttpConnection.connectAndGetResults(
                    completeLink,
                    null,
                    JWTOKEN
            );
        } catch (InvalidItemTagException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns the after cursor of the clan search result retrieved from the server.
     *
     * @return After cursor of the clan search result.
     * @throws COCServerConnectionException If the connection to the clash of clan server is failed.
     * @throws JSONException                If fails in processing the JSON.
     * @throws InvalidJsonObject            If the retrieved Json data is invalid.
     */
    public String getAfterCursor() throws COCServerConnectionException, JSONException, InvalidJsonObject {
        if (searchResult == null)
            search();

        if (searchResult.has("paging"))
            if (searchResult.getJSONObject("paging").has("cursors"))
                if (searchResult.getJSONObject("paging").getJSONObject("cursors").has("after"))
                    return (String) searchResult.getJSONObject("paging").getJSONObject("cursors").get("after");
                else
                    return "";

        throw new InvalidJsonObject();
    }

    /**
     * This method returns the before cursor of the clan search result retrieved from the server.
     *
     * @return Before cursor of the clan search result.
     * @throws COCServerConnectionException If the connection to the clash of clan server is failed.
     * @throws JSONException                If fails in processing the JSON.
     * @throws InvalidJsonObject            If the retrieved Json data is invalid.
     */
    public String getBeforeCursor() throws COCServerConnectionException, JSONException, InvalidJsonObject {
        if (searchResult == null)
            search();

        if (searchResult.has("paging"))
            if (searchResult.getJSONObject("paging").has("cursors"))
                if (searchResult.getJSONObject("paging").getJSONObject("cursors").has("before"))
                    return (String) searchResult.getJSONObject("paging").getJSONObject("cursors").get("before");
                else
                    return "";

        throw new InvalidJsonObject();
    }

    /**
     * This method returns the search result retrieved by the search method.
     *
     * @return Search results in JSONArray format.
     * @throws COCServerConnectionException If the connection to the clash of clan server is failed.
     * @throws JSONException                If fails in processing the JSON data.
     */
    public JSONArray getSearchResult() throws JSONException, COCServerConnectionException {
        if (searchResult == null)
            search();

        if (searchResult.has("items")) {
            return searchResult.getJSONArray("items");
        }

        return new JSONArray("[]");
    }
}
