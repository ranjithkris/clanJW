package de.ra.coc;

import de.ra.exception.IllegalTroopNameException;
import de.ra.exception.TroopNotUnlockedException;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class COCPlayersTest {
    private String JWTOKEN;
    private String PLAYER_TAG;

    @Before
    public void setup() {
        File tokenFile = new File(getClass().
                getClassLoader().
                getResource("token.properties").getFile());

        try {
            FileReader tokenFileReader = new FileReader(tokenFile);
            BufferedReader readbuffer = new BufferedReader(tokenFileReader);

            JWTOKEN = readbuffer.readLine();
            PLAYER_TAG = readbuffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void PlayersClanInformation() throws JSONException {
        COCPlayers player1 = new COCPlayers(JWTOKEN, PLAYER_TAG);

        //Check for getPlayerClanInformation() method.
        assertEquals("{\"name\":\"Gods Angels\",\"clanLevel\":4,\"badgeUrls\":{\"small\":\"https://" +
                        "api-assets.clashofclans.com/badges/70/a5u2rx86Xo5Mcsapiq0o_NPv0leRzDVDeeyUbSfGjds.png\"," +
                        "\"large\":\"https://api-assets.clashofclans.com/badges/512/a5u2rx86Xo5Mcsapiq0o_NPv0leRzDVDe" +
                        "eyUbSfGjds.png\",\"medium\":\"https://api-assets.clashofclans.com/badges/200/a5u2rx86Xo5Mcsapi" +
                        "q0o_NPv0leRzDVDeeyUbSfGjds.png\"},\"tag\":\"#VQU8PYGY\"}",
                player1.getPlayerClanInformation().toString());

        //Check for getPlayerClanName() method;
        assertEquals("Gods Angels", player1.getPlayerClanName());

        //Check for getPlayerClanLevel() method.
        assertEquals(4, (int) player1.getPlayerClanLevel());

        //Check for getPlayerClanSmallBadgeUrl() method.
        assertEquals("https://api-assets.clashofclans.com/badges/70/a5u2rx86Xo5Mcsapiq0o_" +
                "NPv0leRzDVDeeyUbSfGjds.png", player1.getPlayerClanSmallBadgeUrl());

        //Check for getPlayerClanMediumBadgeUrl() method.
        assertEquals("https://api-assets.clashofclans.com/badges/200/a5u2rx86Xo5Mcsapiq0o_" +
                "NPv0leRzDVDeeyUbSfGjds.png", player1.getPlayerClanMediumBadgeUrl());

        //Check for getPlayerClanLargeBadgeUrl() method.
        assertEquals("https://api-assets.clashofclans.com/badges/512/a5u2rx86Xo5Mcsapiq0o_" +
                "NPv0leRzDVDeeyUbSfGjds.png", player1.getPlayerClanLargeBadgeUrl());

        //Check for getPlayerClanTag() method.
        assertEquals("#VQU8PYGY", player1.getPlayerClanTag());
    }

    @Test
    public void PlayersProfile() throws JSONException, TroopNotUnlockedException, IllegalTroopNameException {
        COCPlayers player1 = new COCPlayers(JWTOKEN, PLAYER_TAG);

        System.out.println(player1.getPlayerName());
        System.out.println(player1.getPlayerExperienceLevel());
        System.out.println(player1.getPlayerBestTrophies());
        System.out.println(player1.getPlayerBestVersusTrophies());
        System.out.println(player1.getPlayerDefenceWin());
        System.out.println(player1.getPlayerTag());
        System.out.println(player1.getPlayerCurrentTrophies());
        System.out.println(player1.getPlayerCurrentVersusTrophies());
        System.out.println(player1.getPlayerRole());
        System.out.println(player1.getPlayerTownHallLevel());
        System.out.println(player1.getPlayerBuilderHallLevel());
        System.out.println(player1.getPlayerVersusBattleWin());
        System.out.println(player1.getPlayerAttackWin());
        System.out.println(player1.getPlayerTroopsDonationCount());
        System.out.println(player1.getPlayerTroopsReceivedCount());
        System.out.println(player1.getPlayerTroopLevel(COCPlayers.ARCHER));
        System.out.println(player1.getPlayerTroopMaxLevel(COCPlayers.ARCHER));
        System.out.println(player1.getPlayerTroopInformation());
    }
}
