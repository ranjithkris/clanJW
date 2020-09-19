package de.ra.coc.Clan;

import de.ra.exception.ClanJWException;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class ClanTest {
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
    public void myClan() throws ClanJWException, UnsupportedEncodingException {
        Clan clan1 = new Clan(JWTOKEN, "#Y989UU9Y");

    }
}
