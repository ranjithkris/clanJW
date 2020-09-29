package de.ra.coc.Clan;

import de.ra.exception.ClanJWException;
import org.json.JSONException;
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
    public void myClan() throws ClanJWException, UnsupportedEncodingException, JSONException {
        Clan myClan = new Clan(JWTOKEN, "#VQU8PYGY");

        System.out.println("Description = " + myClan.getClanDescription());
        System.out.println("Clan level = " + myClan.getClanLevel());
        System.out.println("Number of war wins = " + myClan.getNumberOfWarWins());
        System.out.println("War streaks = " + myClan.getNumberOfWarWinStreak());
        System.out.println("Required Trophies = " + myClan.getRequiredTrophies());
        System.out.println("War frequency = " + myClan.getWarFrequency());
        System.out.println("War league Id = " + myClan.getWarLeagueId());
        System.out.println("War league name = " + myClan.getWarLeagueName());
        System.out.println("War league name = " + myClan.getNumberOfMembers());
        System.out.println("Clan Type = " + myClan.getClanType());
        System.out.println("Clan Points = " + myClan.getClanPoints());
        System.out.println("Clan Name = " + myClan.getClanName());
        System.out.println("Clan Versus Points = " + myClan.getClanVersusPoints());
        System.out.println("Clan Tag = " + myClan.getClanTag());
        System.out.println("Is War Log Public = " + myClan.isWarLogPublic());
        System.out.println("Clan War Ties = " + myClan.getNumberOfClanWarTies());
    }
}
