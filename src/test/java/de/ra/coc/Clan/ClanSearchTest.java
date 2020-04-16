package de.ra.coc.Clan;

import de.ra.exception.*;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ClanSearchTest {
    private String JWTOKEN;

    @Before
    public void setup() {
        File tokenFile = new File(getClass().
                getClassLoader().
                getResource("token.properties").getFile());

        try {
            FileReader tokenFileReader = new FileReader(tokenFile);
            BufferedReader readbuffer = new BufferedReader(tokenFileReader);

            JWTOKEN = readbuffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchClanTest() throws JSONException, ClanJWException, UnsupportedEncodingException {
        ClanSearchFactory myCSF = new ClanSearchFactory(JWTOKEN)
                .setClanNameCriteria("Gods Angel")
                .setResultLimit(3);

        ClanSearch myCS = myCSF.build();

        myCS.search();
        System.out.println(myCS.getSearchResult());
        String cursor = myCS.getBeforeCursor();

        myCS = myCSF.setCursor(ClanSearchFactory.BEFORE, cursor).build();
        myCS.search();
        System.out.println(myCS.getSearchResult());

        myCS = myCSF.setCursor(ClanSearchFactory.AFTER, cursor).build();
        myCS.search();
        System.out.println(myCS.getSearchResult());
    }
}
