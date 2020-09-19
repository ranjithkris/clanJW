package de.ra.coc;

import de.ra.coc.COCData.BuilderBase.Troops;
import de.ra.coc.COCData.Heroes;
import de.ra.coc.COCData.HomeVillage.DarkElixirSpell;
import de.ra.coc.COCData.HomeVillage.DarkElixirTroop;
import de.ra.coc.COCData.HomeVillage.ElixirSpell;
import de.ra.coc.COCData.HomeVillage.ElixirTroop;
import de.ra.coc.ServerConnection.HttpConnection;
import de.ra.exception.IllegalNameException.IllegalHeroNameException;
import de.ra.exception.IllegalNameException.IllegalSpellNameException;
import de.ra.exception.IllegalNameException.IllegalTroopNameException;
import de.ra.exception.notUnlockedException.HeroNotUnlockedException;
import de.ra.exception.notUnlockedException.SpellNotUnlockedException;
import de.ra.exception.notUnlockedException.TroopNotUnlockedException;
import de.ra.exception.serverConnectionException.COCServerConnectionException;
import de.ra.exception.tagException.InvalidItemTagException;
import de.ra.exception.tagException.InvalidPlayerTagException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * The COCPlayers class represents the Clash of Clan's Player.
 * This class requires player's tag and
 * the JSON Web Token to connect to the Clash of Clan server to retrieve the
 * Players information. To get the JSON Web Token see <a href="https://developer.clashofclans.com/">https://developer.clashofclans.com/</a>
 */
public class COCPlayers {
    private String JWTOKEN;
    private String PLAYER_TAG;
    private JSONObject PLAYER_INFORMATION;

    /**
     * Initialize newly constructed COCPlayer with the passed JSON Web Token and player's tag and retrieves the players information
     * from the Clash of Clan server.
     * To get the JSON Web Token see
     * <a href="https://developer.clashofclans.com/">https://developer.clashofclans.com/</a>
     *
     * @param JWToken    JSON Web Token.
     * @param PLAYER_TAG Player's tag.
     * @throws COCServerConnectionException If the connection to Clash of Clan server is failed.
     * @throws InvalidPlayerTagException    If the given player tag contains invalid character or not found in Clash of Clans server.
     * @throws UnsupportedEncodingException If the given player tag is failed to encode to avoid taint-style vulnerabilities.
     */
    public COCPlayers(String JWToken, String PLAYER_TAG) throws COCServerConnectionException, InvalidPlayerTagException, UnsupportedEncodingException {
        JWTOKEN = JWToken;
        changePlayerTag(PLAYER_TAG);
    }

    /**
     * This method changes the player's tag that is set previously and retrieves the information
     * of the new player.
     *
     * @param PLAYER_TAG Player's tag.
     * @throws COCServerConnectionException If the connection to Clash of Clan server is failed.
     * @throws InvalidPlayerTagException    If the given player tag contains invalid character or not found in Clash of Clans server.
     * @throws UnsupportedEncodingException If the given player tag is failed to encode to avoid taint-style vulnerabilities.
     */
    public void changePlayerTag(String PLAYER_TAG) throws COCServerConnectionException, UnsupportedEncodingException, InvalidPlayerTagException {
        PolicyFactory myPF = Sanitizers.BLOCKS
                .and(Sanitizers.FORMATTING)
                .and(Sanitizers.IMAGES)
                .and(Sanitizers.LINKS)
                .and(Sanitizers.STYLES)
                .and(Sanitizers.TABLES);

        PLAYER_TAG = myPF.sanitize(PLAYER_TAG);
        PLAYER_TAG = URLEncoder.encode(PLAYER_TAG, "UTF-8");

        try {
            PLAYER_INFORMATION = HttpConnection.connectAndGetResults(
                    HttpConnection.API_LINK + HttpConnection.API_VERSION + "/players/" + PLAYER_TAG,
                    PLAYER_TAG,
                    JWTOKEN
            );
        } catch (InvalidItemTagException e) {
            throw new InvalidPlayerTagException(PLAYER_TAG);
        }
    }

    /**
     * This method returns the players clan information in JSONObject format.
     *
     * @return Player's clan information in JSONObject format. If the player is not in any clan then this method
     * return empty JSONObject.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public JSONObject getPlayerClanInformation() throws JSONException {
        return PLAYER_INFORMATION.has("clan") ? PLAYER_INFORMATION.getJSONObject("clan") : new JSONObject("{}");
    }

    /**
     * This method returns the player's clan name.
     *
     * @return Player's clan name. If the player is not in any clan then this method returns "NotApplicable".
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerClanName() throws JSONException {
        return PLAYER_INFORMATION.has("clan") ? (String) PLAYER_INFORMATION.getJSONObject("clan").get("name") : "NotApplicable";
    }

    /**
     * This method returns the URL to the player's clan small badge.
     *
     * @return URL to the player's clan small badge.
     * If the player is not in any clan then this method returns "NotApplicable".
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerClanSmallBadgeUrl() throws JSONException {
        return PLAYER_INFORMATION.has("clan") ?
                (String) PLAYER_INFORMATION.getJSONObject("clan")
                        .getJSONObject("badgeUrls").get("small") :
                "NotApplicable";
    }

    /**
     * This method returns the URL to the player's clan medium badge.
     *
     * @return URL to the player's clan medium badge.
     * If the player is not in any clan then this method returns "NotApplicable".
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerClanMediumBadgeUrl() throws JSONException {
        return PLAYER_INFORMATION.has("clan") ?
                (String) PLAYER_INFORMATION.getJSONObject("clan")
                        .getJSONObject("badgeUrls").get("medium") :
                "NotApplicable";
    }

    /**
     * This method returns the URL to the player's clan large badge.
     *
     * @return URL to the player's clan large badge.
     * If the player is not in any clan then this method returns "NotApplicable".
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerClanLargeBadgeUrl() throws JSONException {
        return PLAYER_INFORMATION.has("clan") ?
                (String) PLAYER_INFORMATION.getJSONObject("clan")
                        .getJSONObject("badgeUrls").get("large") :
                "NotApplicable";
    }

    /**
     * This method returns the player's clan tag.
     *
     * @return Player's clan tag.
     * If the player is not in any clan then this method returns "NotApplicable".
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerClanTag() throws JSONException {
        return PLAYER_INFORMATION.has("clan") ? (String) PLAYER_INFORMATION.getJSONObject("clan").get("tag") :
                "NotApplicable";
    }

    /**
     * This method returns the player's clan level.
     *
     * @return Player's clan level. If the player is not in any clan then this method returns -1.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerClanLevel() throws JSONException {
        return PLAYER_INFORMATION.has("clan") ? (Integer) PLAYER_INFORMATION.getJSONObject("clan").get("clanLevel") :
                -1;
    }

    /**
     * This method returns the player's name.
     *
     * @return Player's name.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerName() throws JSONException {
        return PLAYER_INFORMATION.has("name") ? (String) PLAYER_INFORMATION.get("name") : "NotGiven";
    }

    /**
     * This method returns the player's experience level.
     *
     * @return Player's experience level.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerExperienceLevel() throws JSONException {
        return PLAYER_INFORMATION.has("expLevel") ? (Integer) PLAYER_INFORMATION.get("expLevel") : -1;
    }

    /**
     * This method returns the player's best trophies in home village.
     *
     * @return Player's best trophies in home village.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerBestTrophies() throws JSONException {
        return PLAYER_INFORMATION.has("bestTrophies") ? (Integer) PLAYER_INFORMATION.get("bestTrophies") : -1;
    }

    /**
     * This method returns the player's best trophies in builder base.
     *
     * @return Player's best trophies in builder base.
     * If the player does not have builder base then this method returns -1.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerBestVersusTrophies() throws JSONException {
        return PLAYER_INFORMATION.has("bestVersusTrophies") ? (Integer) PLAYER_INFORMATION.get("bestVersusTrophies") : -1;
    }

    /**
     * This method returns the number of defence won in home village.
     *
     * @return Number of defence won in home village.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerDefenceWin() throws JSONException {
        return PLAYER_INFORMATION.has("defenseWins") ? (Integer) PLAYER_INFORMATION.get("defenseWins") : 0;
    }

    /**
     * This method returns the player's tag.
     *
     * @return Player's tag.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerTag() throws JSONException {
        return (String) PLAYER_INFORMATION.get("tag");
    }

    /**
     * This method returns the player's current trophies in home village.
     *
     * @return Player's current trophies in home village.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerCurrentTrophies() throws JSONException {
        return PLAYER_INFORMATION.has("trophies") ? (Integer) PLAYER_INFORMATION.get("trophies") : 0;
    }

    /**
     * This method returns the player's current trophies in builder base.
     *
     * @return Player's current trophies in builder base.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerCurrentVersusTrophies() throws JSONException {
        return PLAYER_INFORMATION.has("versusTrophies") ? (Integer) PLAYER_INFORMATION.get("versusTrophies") : 0;
    }

    /**
     * This method returns the player's role in the clan.
     *
     * @return Player's role in the clan. If player is not in any clan then this method returns "NotApplicable"
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerRole() throws JSONException {
        return PLAYER_INFORMATION.has("role") ? (String) PLAYER_INFORMATION.get("role") : "NotApplicable";
    }

    /**
     * This method returns the player's town hall level in home village.
     *
     * @return Player's town hall level in home village.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerTownHallLevel() throws JSONException {
        return PLAYER_INFORMATION.has("townHallLevel") ? (Integer) PLAYER_INFORMATION.get("townHallLevel") : 0;
    }

    /**
     * This method returns the player's builder hall level in builder base.
     *
     * @return Player's builder hall level in builder base.
     * If the player does not have builder hall then this method return 0.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerBuilderHallLevel() throws JSONException {
        return PLAYER_INFORMATION.has("builderHallLevel") ? (Integer) PLAYER_INFORMATION.get("builderHallLevel") : 0;
    }

    /**
     * This method returns the number of battle won in builder base.
     *
     * @return Number of battle won in builder base.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerVersusBattleWin() throws JSONException {
        return PLAYER_INFORMATION.has("versusBattleWinCount") ? (Integer) PLAYER_INFORMATION.get("versusBattleWinCount") : 0;
    }

    /**
     * This method returns the number of attacks won in home village.
     *
     * @return Number of attacks won in home village.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerAttackWin() throws JSONException {
        return PLAYER_INFORMATION.has("attackWins") ? (Integer) PLAYER_INFORMATION.get("attackWins") : 0;
    }

    /**
     * This method returns the number of troops donation by the player.
     *
     * @return Number of troops donation by the player.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerTroopsDonationCount() throws JSONException {
        return PLAYER_INFORMATION.has("donations") ? (Integer) PLAYER_INFORMATION.get("donations") : 0;
    }

    /**
     * This method returns the number of troops received by the other clan member.
     *
     * @return Number of troops received by the other clan member.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerTroopsReceivedCount() throws JSONException {
        return PLAYER_INFORMATION.has("donationsReceived") ? (Integer) PLAYER_INFORMATION.get("donationsReceived") : 0;
    }

    private Integer getIndex(String category, String key) throws JSONException {
        JSONArray troops = PLAYER_INFORMATION.getJSONArray(category);
        for (int index = 0; index < troops.length(); index++) {
            JSONObject troop = (JSONObject) troops.get(index);
            if (troop.get("name").equals(key))
                return index;
        }

        if ("troops".equals(category) && ElixirTroop.getElixirTroopsList().contains(key))
            return 100;

        if ("spells".equals(category) && ElixirSpell.getElixirSpellsList().contains(key))
            return 100;

        if ("spells".equals(category) && DarkElixirSpell.getDarkElixirSpellsList().contains(key))
            return 100;

        if ("troops".equals(category) && DarkElixirTroop.getDarkElixirTroopsList().contains(key))
            return 100;

        if ("troops".equals(category) && Troops.getBuilderBaseTroopsList().contains(key))
            return 100;

        if ("heroes".equals(category) && Heroes.getHeroesList().contains(key))
            return 100;

        return 500;
    }

    /**
     * This method return the player's current level of the given troop name.
     *
     * @param troopName Troop name. Example: ARCHER, Wizard etc.
     *                  See the list of all troops
     *                  {@link de.ra.coc.COCData.HomeVillage.ElixirTroop}
     *                  {@link de.ra.coc.COCData.HomeVillage.DarkElixirTroop}
     *                  {@link de.ra.coc.COCData.BuilderBase.Troops}
     * @return Current level of the troop.
     * @throws JSONException             If the processing of JSONObject is failed.
     * @throws JSONException             If the processing of JSONObject is failed.
     * @throws TroopNotUnlockedException If the provided troop name is not unlocked by the player.
     * @throws IllegalTroopNameException If the provided the troop name is not valid.
     */
    public Integer getPlayerTroopLevel(String troopName) throws JSONException, TroopNotUnlockedException, IllegalTroopNameException {
        return (Integer) getPlayerSingleTroopInfo(troopName).get("level");
    }

    /**
     * This method returns the Player's current troops information in JSONArray format.
     *
     * @return Player's current troops information in JSONArray format.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public JSONArray getPlayerTroopsInformation() throws JSONException {
        return PLAYER_INFORMATION.getJSONArray("troops");
    }

    /**
     * This method return the maximum level of a troop unlocked by the player.
     *
     * @param troopName Troop name. Example: ARCHER, Wizard etc.
     *                  See the list of all troops
     *                  {@link de.ra.coc.COCData.HomeVillage.ElixirTroop}
     *                  {@link de.ra.coc.COCData.HomeVillage.DarkElixirTroop}
     *                  {@link de.ra.coc.COCData.BuilderBase.Troops}
     * @return Maximum level of a troop unlocked by the player.
     * @throws JSONException             If the processing of JSONObject is failed.
     * @throws TroopNotUnlockedException If the provided troop name is not unlocked by the player.
     * @throws IllegalTroopNameException If the provided the troop name is not valid.
     */
    public Integer getPlayerTroopMaxLevel(String troopName) throws JSONException, TroopNotUnlockedException, IllegalTroopNameException {
        return (Integer) getPlayerSingleTroopInfo(troopName).get("maxLevel");
    }

    /**
     * This method returns the village name of the given troop.
     *
     * @param troopName Troop name. Example: ARCHER, Wizard etc.
     *                  See the list of all troops
     *                  {@link de.ra.coc.COCData.HomeVillage.ElixirTroop}
     *                  {@link de.ra.coc.COCData.HomeVillage.DarkElixirTroop}
     *                  {@link de.ra.coc.COCData.BuilderBase.Troops}
     * @return Village name of the given troop.
     * @throws JSONException             If the processing of JSONObject is failed.
     * @throws TroopNotUnlockedException If the provided troop name is not unlocked by the player.
     * @throws IllegalTroopNameException If the provided the troop name is not valid.
     */
    public String getPlayerTroopVillage(String troopName) throws JSONException, TroopNotUnlockedException, IllegalTroopNameException {
        return (String) getPlayerSingleTroopInfo(troopName).get("village");
    }

    /**
     * This method returns the village name of the given troop.
     *
     * @param troopName Troop name. Example: ARCHER, Wizard etc.
     *                  See the list of all troops
     *                  {@link de.ra.coc.COCData.HomeVillage.ElixirTroop}
     *                  {@link de.ra.coc.COCData.HomeVillage.DarkElixirTroop}
     *                  {@link de.ra.coc.COCData.BuilderBase.Troops}
     * @return Village name of the given troop.
     * @throws JSONException             If the processing of JSONObject is failed.
     * @throws TroopNotUnlockedException If the provided troop name is not unlocked by the player.
     * @throws IllegalTroopNameException If the provided the troop name is not valid.
     */
    public String getPlayerTroopName(String troopName) throws JSONException, TroopNotUnlockedException, IllegalTroopNameException {
        return (String) getPlayerSingleTroopInfo(troopName).get("name");
    }

    /**
     * This method returns the complete information of a single troop in JSONObject format.
     *
     * @param troopName Troop name. Example: ARCHER, Wizard etc.
     *                  See the list of all troops
     *                  {@link de.ra.coc.COCData.HomeVillage.ElixirTroop}
     *                  {@link de.ra.coc.COCData.HomeVillage.DarkElixirTroop}
     *                  {@link de.ra.coc.COCData.BuilderBase.Troops}
     * @return Complete information of a single troop.
     * @throws JSONException             If the processing of JSONObject is failed.
     * @throws TroopNotUnlockedException If the provided troop name is not unlocked by the player.
     * @throws IllegalTroopNameException If the provided the troop name is not valid.
     */
    public JSONObject getPlayerSingleTroopInfo(String troopName) throws JSONException, IllegalTroopNameException, TroopNotUnlockedException {
        Integer index = getIndex("troops", troopName);

        if (index == 100)
            throw new TroopNotUnlockedException(troopName,
                    (String) PLAYER_INFORMATION.get("name"));

        if (index == 500)
            throw new IllegalTroopNameException(troopName);

        return (JSONObject) PLAYER_INFORMATION
                .getJSONArray("troops")
                .get(index);
    }

    /**
     * This method returns the complete information of a single spell in JSONObject format.
     *
     * @param spellName Spell name. Example: Lightning spell, Haste spell etc.
     *                  See the list of all spells
     *                  {@link de.ra.coc.COCData.HomeVillage.ElixirSpell}
     *                  {@link de.ra.coc.COCData.HomeVillage.DarkElixirSpell}
     * @return Complete information of a single spell.
     * @throws JSONException             If the processing of JSONObject is failed.
     * @throws SpellNotUnlockedException If the provided troop name is not unlocked by the player.
     * @throws IllegalSpellNameException If the provided the troop name is not valid.
     */
    public JSONObject getPlayerSingleSpellInfo(String spellName) throws JSONException, SpellNotUnlockedException, IllegalSpellNameException {
        Integer index = getIndex("spells", spellName);

        if (index == 100)
            throw new SpellNotUnlockedException(spellName,
                    (String) PLAYER_INFORMATION.get("name"));

        if (index == 500)
            throw new IllegalSpellNameException(spellName);

        return (JSONObject) PLAYER_INFORMATION
                .getJSONArray("spells")
                .get(index);
    }

    /**
     * This method return the player's current level of the given spell name.
     *
     * @param spellName Spell name. Example: Lightning spell, Haste spell etc.
     *                  See the list of all spells
     *                  {@link de.ra.coc.COCData.HomeVillage.ElixirSpell}
     *                  {@link de.ra.coc.COCData.HomeVillage.DarkElixirSpell}
     * @return Current level of the spell.
     * @throws JSONException             If the processing of JSONObject is failed.
     * @throws JSONException             If the processing of JSONObject is failed.
     * @throws SpellNotUnlockedException If the provided spell name is not unlocked by the player.
     * @throws IllegalSpellNameException If the provided the spell name is not valid.
     */
    public Integer getPlayerSpellLevel(String spellName) throws JSONException, SpellNotUnlockedException, IllegalSpellNameException {
        return (Integer) getPlayerSingleSpellInfo(spellName).get("level");
    }

    /**
     * This method returns the Player's current spells information in JSONArray format.
     *
     * @return Player's current spells information in JSONArray format.
     * If no spells are unlocked by the player then this method return empty JSONArray.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public JSONArray getPlayerSpellsInformation() throws JSONException {
        return PLAYER_INFORMATION.getJSONArray("spells");
    }

    /**
     * This method return the maximum level of a spell unlocked by the player.
     *
     * @param spellName Spell name. Example: Lightning spell, Haste spell etc.
     *                  See the list of all spells
     *                  {@link de.ra.coc.COCData.HomeVillage.ElixirSpell}
     *                  {@link de.ra.coc.COCData.HomeVillage.DarkElixirSpell}
     * @return Maximum level of a spell unlocked by the player.
     * @throws JSONException             If the processing of JSONObject is failed.
     * @throws SpellNotUnlockedException If the provided spell name is not unlocked by the player.
     * @throws IllegalSpellNameException If the provided the spell name is not valid.
     */
    public Integer getPlayerSpellMaxLevel(String spellName) throws JSONException, SpellNotUnlockedException, IllegalSpellNameException {
        return (Integer) getPlayerSingleSpellInfo(spellName).get("maxLevel");
    }

    /**
     * This method returns the village name of the given spell.
     *
     * @param spellName Spell name. Example: Lightning spell, Haste spell etc.
     *                  See the list of all spells
     *                  {@link de.ra.coc.COCData.HomeVillage.ElixirSpell}
     *                  {@link de.ra.coc.COCData.HomeVillage.DarkElixirSpell}
     * @return Village name of the given spell.
     * @throws JSONException             If the processing of JSONObject is failed.
     * @throws SpellNotUnlockedException If the provided spell name is not unlocked by the player.
     * @throws IllegalSpellNameException If the provided the spell name is not valid.
     */
    public String getPlayerSpellVillage(String spellName) throws JSONException, SpellNotUnlockedException, IllegalSpellNameException {
        return (String) getPlayerSingleSpellInfo(spellName).get("village");
    }

    /**
     * This method returns the village name of the given spell.
     *
     * @param spellName Spell name. Example: Lightning spell, Haste spell etc.
     *                  See the list of all spells
     *                  {@link de.ra.coc.COCData.HomeVillage.ElixirSpell}
     *                  {@link de.ra.coc.COCData.HomeVillage.DarkElixirSpell}
     * @return Village name of the given spell.
     * @throws JSONException             If the processing of JSONObject is failed.
     * @throws SpellNotUnlockedException If the provided spell name is not unlocked by the player.
     * @throws IllegalSpellNameException If the provided the spell name is not valid.
     */
    public String getPlayerSpellName(String spellName) throws JSONException, SpellNotUnlockedException, IllegalSpellNameException {
        return (String) getPlayerSingleSpellInfo(spellName).get("name");
    }

    /**
     * This method returns the complete information of a single hero in JSONObject format.
     *
     * @param heroName Hero name. Example: Barbarian King, Archer Queen etc.
     *                 See the list of all heroes {@link de.ra.coc.COCData.Heroes}
     * @return Complete information of a single hero.
     * @throws JSONException            If the processing of JSONObject is failed.
     * @throws HeroNotUnlockedException If the provided hero name is not unlocked by the player.
     * @throws IllegalHeroNameException If the provided the hero name is not valid.
     */
    public JSONObject getPlayerSingleHeroInfo(String heroName) throws JSONException, HeroNotUnlockedException, IllegalHeroNameException {
        Integer index = getIndex("heroes", heroName);

        if (index == 100)
            throw new HeroNotUnlockedException(heroName,
                    (String) PLAYER_INFORMATION.get("name"));

        if (index == 500)
            throw new IllegalHeroNameException(heroName);

        return (JSONObject) PLAYER_INFORMATION
                .getJSONArray("heroes")
                .get(index);
    }

    /**
     * This method return the player's current level of the given hero name.
     *
     * @param heroName Hero name. Example: Barbarian King, Archer Queen etc.
     *                 See the list of all heroes {@link de.ra.coc.COCData.Heroes}
     * @return Current level of the hero.
     * @throws JSONException            If the processing of JSONObject is failed.
     * @throws JSONException            If the processing of JSONObject is failed.
     * @throws HeroNotUnlockedException If the provided hero name is not unlocked by the player.
     * @throws IllegalHeroNameException If the provided hero name is not valid.
     */
    public Integer getPlayerHeroLevel(String heroName) throws JSONException, HeroNotUnlockedException, IllegalHeroNameException {
        return (Integer) getPlayerSingleHeroInfo(heroName).get("level");
    }

    /**
     * This method returns the Player's current heroes information in JSONArray format.
     *
     * @return Player's current heroes information in JSONArray format. If heroes are not unlocked by the player
     * then this method return empty JSONArray.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public JSONArray getPlayerHeroesInformation() throws JSONException {
        return PLAYER_INFORMATION.getJSONArray("heroes");
    }

    /**
     * This method return the maximum level of a hero unlocked by the player.
     *
     * @param heroName Hero name. Example: Barbarian King, Archer Queen etc.
     *                 See the list of all heroes {@link de.ra.coc.COCData.Heroes}
     * @return Maximum level of a hero unlocked by the player.
     * @throws JSONException            If the processing of JSONObject is failed.
     * @throws HeroNotUnlockedException If the provided hero name is not unlocked by the player.
     * @throws IllegalHeroNameException If the provided hero name is not valid.
     */
    public Integer getPlayerHeroMaxLevel(String heroName) throws JSONException, HeroNotUnlockedException, IllegalHeroNameException {
        return (Integer) getPlayerSingleHeroInfo(heroName).get("maxLevel");
    }

    /**
     * This method returns the village name of the given hero.
     *
     * @param heroName Hero name. Example: Barbarian King, Archer Queen etc.
     *                 See the list of all heroes {@link de.ra.coc.COCData.Heroes}
     * @return Village name of the given hero.
     * @throws JSONException            If the processing of JSONObject is failed.
     * @throws HeroNotUnlockedException If the provided hero name is not unlocked by the player.
     * @throws IllegalHeroNameException If the provided hero name is not valid.
     */
    public String getPlayerHeroVillage(String heroName) throws JSONException, HeroNotUnlockedException, IllegalHeroNameException {
        return (String) getPlayerSingleHeroInfo(heroName).get("village");
    }

    /**
     * This method returns the village name of the given hero.
     *
     * @param heroName Hero name. Example: Barbarian King, Archer Queen etc.
     *                 See the list of all heroes {@link de.ra.coc.COCData.Heroes}
     * @return Village name of the given hero.
     * @throws JSONException            If the processing of JSONObject is failed.
     * @throws HeroNotUnlockedException If the provided hero name is not unlocked by the player.
     * @throws IllegalHeroNameException If the provided hero name is not valid.
     */
    public String getPlayerHeroName(String heroName) throws JSONException, HeroNotUnlockedException, IllegalHeroNameException {
        return (String) getPlayerSingleHeroInfo(heroName).get("name");
    }

    /**
     * This method returns the player's league name.
     *
     * @return Player's league name. If the player is not assigned to any league, then
     * this method returns a value "League Not Assigned"
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerLeagueName() throws JSONException {
        String name = "League Not Assigned";
        if (PLAYER_INFORMATION.has("league")) {
            if (PLAYER_INFORMATION.getJSONObject("league").has("name")) {
                name = (String) PLAYER_INFORMATION.getJSONObject("league").get("name");
            }
        }
        return name;
    }

    /**
     * This method returns the Tiny badge URL of the league earned by the player.
     *
     * @return Tiny badge URL of the league. If the player is not assigned to any league, then
     * this method returns a value "League Not Assigned"
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerLeagueTinyBadgeUrl() throws JSONException {
        String tinyBadgeURL = "League Not Assigned";
        if (PLAYER_INFORMATION.has("league")) {
            if (PLAYER_INFORMATION.getJSONObject("league").has("iconUrls")) {
                if (PLAYER_INFORMATION.getJSONObject("league").getJSONObject("iconUrls").has("tiny"))
                    tinyBadgeURL = (String) PLAYER_INFORMATION.getJSONObject("league").getJSONObject("iconUrls").get("tiny");
            }
        }
        return tinyBadgeURL;
    }

    /**
     * This method returns the Small badge URL of the league earned by the player.
     *
     * @return Small badge URL of the league. If the player is not assigned to any league, then
     * this method returns a value "League Not Assigned"
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerLeagueSmallBadgeUrl() throws JSONException {
        String smallBadgeURL = "League Not Assigned";
        if (PLAYER_INFORMATION.has("league")) {
            if (PLAYER_INFORMATION.getJSONObject("league").has("iconUrls")) {
                if (PLAYER_INFORMATION.getJSONObject("league").getJSONObject("iconUrls").has("small"))
                    smallBadgeURL = (String) PLAYER_INFORMATION.getJSONObject("league").getJSONObject("iconUrls").get("small");
            }
        }
        return smallBadgeURL;
    }

    /**
     * This method returns the Medium badge URL of the league earned by the player.
     *
     * @return Medium badge URL of the league. If the player is not assigned to any league, then
     * this method returns a value "League Not Assigned"
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerLeagueMediumBadgeUrl() throws JSONException {
        String mediumBadgeURL = "League Not Assigned";
        if (PLAYER_INFORMATION.has("league")) {
            if (PLAYER_INFORMATION.getJSONObject("league").has("iconUrls")) {
                if (PLAYER_INFORMATION.getJSONObject("league").getJSONObject("iconUrls").has("medium"))
                    mediumBadgeURL = (String) PLAYER_INFORMATION.getJSONObject("league").getJSONObject("iconUrls").get("medium");
            }
        }
        return mediumBadgeURL;
    }

    /**
     * This method returns the League ID of the league earned by the player.
     *
     * @return League ID of the league earned by the player. If the player is not assigned to any league, then
     * this method returns a value 0
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerLeagueID() throws JSONException {
        Integer leagueID = 0;
        if (PLAYER_INFORMATION.has("league")) {
            if (PLAYER_INFORMATION.getJSONObject("league").has("id")) {
                leagueID = (Integer) PLAYER_INFORMATION.getJSONObject("league").get("id");
            }
        }
        return leagueID;
    }

    /**
     * This method returns the achievements of player in JSONArray format.
     *
     * @return Achievements of player in JSONArray format. If there is no achievements then this method
     * returns the empty JSONArray.
     * Format of each JSONObject in the returned JSONArray is as below.
     * <pre>
     *     <code>
     *         {
     *             name : "Achievement Name in String type"
     *             completionInfo : "completionInfo in String type"
     *             stars : "stars in Integer type"
     *             village : "village Name in String type"
     *             value : "value in Integer type"
     *             target : "target in Integer type"
     *             info : "info in String type"
     *         }
     *     </code>
     * </pre>
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public JSONArray getPlayerAchievements() throws JSONException {
        return PLAYER_INFORMATION.has("achievements") ?
                PLAYER_INFORMATION.getJSONArray("achievements") : new JSONArray("[]");
    }

    /**
     * This method returns the labels of player in JSONArray format.
     *
     * @return Labels of player in JSONArray format. If there is no achievements then this method
     * returns the empty JSONArray.
     * Format of each JSONObject in the returned JSONArray is as below.
     * <pre>
     *     <code>
     *         {
     *             name : "Label Name in String type"
     *             iconUrls : {
     *                 small : "URL to small icon in String type"
     *                 medium : "URL to medium icon in String type"
     *             }
     *             id : "Label id in Integer type"
     *         }
     *     </code>
     * </pre>
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public JSONArray getPlayerLabels() throws JSONException {
        return PLAYER_INFORMATION.has("labels") ?
                PLAYER_INFORMATION.getJSONArray("labels") : new JSONArray("[]");
    }
}