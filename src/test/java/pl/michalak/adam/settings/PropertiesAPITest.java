package pl.michalak.adam.settings;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PropertiesAPITest {
    private PropertiesAPI propertiesAPI;

    @BeforeMethod
    public void setUp(){
        int winningCondition = 3;
        propertiesAPI = new PropertiesAPI(winningCondition);
    }

    @Test
    public void shouldReturnWinningCondition(){
        //given
        String whyItFailed = "returned value isn't equal to expected";
        //when
        int winningCondition = propertiesAPI.getWinningConditionForThisGame();
        //then
        assertEquals(winningCondition, 3, whyItFailed);
    }
}
