package de.ra.coc;

import de.ra.coc.COCData.Heroes;
import de.ra.coc.COCData.HomeVillage.ElixirSpell;
import de.ra.coc.COCData.HomeVillage.ElixirTroop;
import de.ra.exception.*;
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
    public void PlayersClanInformation() throws JSONException, COCServerConnectionException, UnsupportedEncodingException, InvalidPlayerTagException {
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
    public void PlayersProfile() throws JSONException, SpellNotUnlockedException, TroopNotUnlockedException,
            IllegalSpellNameException, IllegalTroopNameException, COCServerConnectionException, HeroNotUnlockedException, IllegalHeroNameException, UnsupportedEncodingException, InvalidPlayerTagException {
        COCPlayers player1 = new COCPlayers(JWTOKEN, PLAYER_TAG);

        System.out.println("Player Name = " + player1.getPlayerName());
        System.out.println("Experience Level = " + player1.getPlayerExperienceLevel());
        System.out.println("Best Trophies" + player1.getPlayerBestTrophies());
        System.out.println("Best Versus Trophies = " + player1.getPlayerBestVersusTrophies());
        System.out.println("Defence Won = " + player1.getPlayerDefenceWin());
        System.out.println("Player Tag = " + player1.getPlayerTag());
        System.out.println("Current Trophies = " + player1.getPlayerCurrentTrophies());
        System.out.println("Current Versus trophies = " + player1.getPlayerCurrentVersusTrophies());
        System.out.println("Player Role = " + player1.getPlayerRole());
        System.out.println("TownHall level = " + player1.getPlayerTownHallLevel());
        System.out.println("Builder Hall level = " + player1.getPlayerBuilderHallLevel());
        System.out.println("Versus Battle Won = " + player1.getPlayerVersusBattleWin());
        System.out.println("Attack Won = " + player1.getPlayerAttackWin());
        System.out.println("Troop donation count = " + player1.getPlayerTroopsDonationCount());
        System.out.println("Troop received count = " + player1.getPlayerTroopsReceivedCount());

        System.out.println("Archer Level = " + player1.getPlayerTroopLevel(ElixirTroop.ARCHER));
        System.out.println("Archer Max level = " + player1.getPlayerTroopMaxLevel(ElixirTroop.ARCHER));
        System.out.println("Archer village name = " + player1.getPlayerTroopVillage(ElixirTroop.ARCHER));
        System.out.println("Archer troop name = " + player1.getPlayerTroopName(ElixirTroop.ARCHER));
        System.out.println("Baby Dragon Information = " + player1.getPlayerSingleTroopInfo(ElixirTroop.BABY_DRAGON));
        System.out.println("Complete troops information = " + player1.getPlayerTroopsInformation());

        System.out.println("Freeze Spell Level = " + player1.getPlayerSpellLevel(ElixirSpell.FREEZE_SPELL));
        System.out.println("Freeze Spell Max level = " + player1.getPlayerSpellMaxLevel(ElixirSpell.FREEZE_SPELL));
        System.out.println("Freeze Spell village name = " + player1.getPlayerSpellVillage(ElixirSpell.FREEZE_SPELL));
        System.out.println("Freeze Spell name = " + player1.getPlayerSpellName(ElixirSpell.FREEZE_SPELL));
        System.out.println("Healing Spell Information = " + player1.getPlayerSingleSpellInfo(ElixirSpell.HEALING_SPELL));
        System.out.println("Complete Spells information = " + player1.getPlayerSpellsInformation());

        System.out.println("Barbarian King Level = " + player1.getPlayerHeroLevel(Heroes.BARBARIAN_KING));
        System.out.println("Barbarian King Max level = " + player1.getPlayerHeroMaxLevel(Heroes.BARBARIAN_KING));
        System.out.println("Barbarian King village name = " + player1.getPlayerHeroVillage(Heroes.BARBARIAN_KING));
        System.out.println("Barbarian King troop name = " + player1.getPlayerHeroName(Heroes.BARBARIAN_KING));
        System.out.println("Archer Queen Information = " + player1.getPlayerSingleHeroInfo(Heroes.ARCHER_QUEEN));
        System.out.println("Complete Heroes information = " + player1.getPlayerHeroesInformation());
    }
}
