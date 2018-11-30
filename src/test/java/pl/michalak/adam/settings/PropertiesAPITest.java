package pl.michalak.adam.settings;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.Assert.assertEquals;

public class PropertiesAPITest {
    private PropertiesAPI propertiesAPI;

    @BeforeMethod
    public void setUp(){
        propertiesAPI = new PropertiesAPI();
    }

    @Test
    public void shouldReturnDefaultWinningCondition(){
        //given
        String whyItFailed = "returned value isn't equal to expected";
        //when
        int winningCondition = propertiesAPI.getWinningConditionForThisGame();
        //then
        assertEquals(winningCondition, 3, whyItFailed);
    }

    @Test
    public void shouldReturnDefaultBoardSize(){
        //given
        String whyItFailed = "returned value isn't equal to expected";
        //when
        int boardSize = propertiesAPI.getBoardSizeForThisGame();
        //then
        assertEquals(boardSize, 3, whyItFailed);
    }

    @Test
    public void shouldReturnWinningConditionSetByUser(){
        //given
        String whyItFailed = "returned value isn't equal to expected";
        //when
        propertiesAPI.setWinningConditionForThisGame(4);
        int winningCondition = propertiesAPI.getWinningConditionForThisGame();
        //then
        assertEquals(winningCondition, 4, whyItFailed);
    }

    @Test
    public void shouldReturnBoardSizeSetByUser(){
        //given
        String whyItFailed = "returned value isn't equal to expected";
        //when
        propertiesAPI.setBoardSizeForThisGame(4);
        int boardSize = propertiesAPI.getBoardSizeForThisGame();
        //then
        assertEquals(boardSize, 4, whyItFailed);
    }

    @Test
    public void shouldChangeLocaleToEnglish(){
        //given
        String whyItFailed = "Locale wasn't changed to english";
        //when
        propertiesAPI.setLocale("en", "US");
        //then
        assertEquals(Locale.getDefault().getLanguage(), "en", whyItFailed);
    }

    @Test
    public void shouldChangeLocaleToPolish(){
        //given
        String whyItFailed = "Locale wasn't changed to polish";
        //when
        propertiesAPI.setLocale("pl", "PL");
        //then
        assertEquals(Locale.getDefault().getLanguage(), "pl", whyItFailed);
    }
}
