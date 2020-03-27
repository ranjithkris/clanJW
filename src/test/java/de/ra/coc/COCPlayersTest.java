import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class COCPlayersTest
{
    private String JWTOKEN;
    private String PLAYER_TAG;

    @Before
    public void setup()
    {
        JWTOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImVhZDM2ZjFmLTZiNjMtNDI4My04Zjg0LTkzMGZjOGZiMzFiYiIsImlhdCI6MTU4NTIzMDM4MCwic3ViIjoiZGV2ZWxvcGVyLzc5Njc2MDRiLTJiNGEtNzE2Yy04Mzg5LTZkM2M3ZGU5MGQxZCIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjk0LjIyMi4yMDUuOTkiXSwidHlwZSI6ImNsaWVudCJ9XX0.DznRgnCbagZ-Oafn3KTUnrtHXNvEMhY3BcRfHYJZ_Ay1SSRLPcm3vHBaQLLpDAdZb6s4qbWhquXSvl9nmOwamw";
        PLAYER_TAG = "%23Y989UU9Y";
    }
    @Test
    public void SimplePlayerTest()
    {
        COCPlayers ob = new COCPlayers(JWTOKEN);

        ob.setPlayerTag(PLAYER_TAG);
        System.out.println(ob.getPlayerClanInformation());
        System.out.println(ob.getPlayerClanName());
        System.out.println(ob.getPlayerClanLevel());
        System.out.println(ob.getPlayerClanSmallBadgeUrl());
        System.out.println(ob.getPlayerClanMediumBadgeUrl());
        System.out.println(ob.getPlayerClanLargeBadgeUrl());
        System.out.println(ob.getPlayerClanTag());

     //   assertTrue(ob.getPlayerClanInformation().equals());
    }
}
