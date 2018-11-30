package pl.michalak.adam.users;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.michalak.adam.components.Symbol;
import pl.michalak.adam.exceptions.PlayersNamesAreTheSameException;

import static org.testng.Assert.*;

public class PlayersAPITest {
    private PlayersAPI playersAPI;

    @BeforeMethod
    public void setUp(){
        this.playersAPI = new PlayersAPI();
    }

    @Test
    public void playerDefaultNamesShouldWork(){
        //given
        String whyItFailed = "players default names differ from those stated in enum";
        //when
        String playerOneName = playersAPI.getPlayerName(1);
        String playerTwoName = playersAPI.getPlayerName(2);
        //then
        assertTrue(playerOneName.equals("X"), whyItFailed);
        assertTrue(playerTwoName.equals("O"), whyItFailed);
    }

    @Test
    public void playerNameCanBeChanged(){
        //given
        String whyItFailed = "players name wasn't changed";
        //when
        try {
            playersAPI.setPlayerName(1, "testName");
        }
        catch(PlayersNamesAreTheSameException e){
            e.printStackTrace();
        }
        //then
        assertTrue(playersAPI.getPlayerName(1).equals("testName"), whyItFailed);
    }

    @Test(expectedExceptions = PlayersNamesAreTheSameException.class)
    public void testCaseMethod() throws PlayersNamesAreTheSameException {
        //given
        String whyItFailed = "error wasn't thrown";
        //when
        playersAPI.setPlayerName(1, "testName");
        playersAPI.setPlayerName(2, "testName");
        //then
        assertFalse(playersAPI.getPlayerName(1).equals(playersAPI.getPlayerName(2)), whyItFailed);
    }

    @Test
    public void playersScoreCanBeIncreased(){
        //given
        String whyItFailed = "Player's score was not increased";
        //when
        playersAPI.addPointsToPlayersScore(1, 1);

        //then
        assertEquals(playersAPI.getPlayersScore(1), 1, whyItFailed);
    }

    @Test
    public void playersSymbolShouldBeReturned(){
        //given
        String whyItFailed = "Players' symbol wasn't returned correctly";
        //then
        assertEquals(playersAPI.getPlayerSymbol(1), Symbol.X, whyItFailed);
    }
}