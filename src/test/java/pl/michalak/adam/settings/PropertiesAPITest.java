package pl.michalak.adam.settings;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.Locale;

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

    @Test
    public void defaultLocaleShouldBePolish(){
        //given
        String whyItFailed = "default locale on this machine is not polish";
        //then
        assertEquals(Locale.getDefault().getCountry(), "PL");
    }

    @Test
    public void shouldChangeLocaleToEnglish(){
        //given
        String whyItFailed = "Locale wasn't changed to english";
        //when
        propertiesAPI.setLocale("en", "US");
        //then
        assertEquals(Locale.getDefault().getLanguage(), "en");
    }
}
