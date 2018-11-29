package pl.michalak.adam.output;

import org.testng.annotations.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.testng.Assert.assertEquals;

class ResourceBundleTest {
    @Test
    public void resourceBundleShouldOfferWelcomeInEnglish(){
        //given
        String whyItFailed = "ResourceBundle not working or language incorrectly chosen";
        Locale.setDefault(new Locale("en"));
        ResourceBundle languageBundle = ResourceBundle.getBundle("Language");
        //when
        String message = languageBundle.getString("gameName");
        //then
        assertEquals(message, "Welcome in TicTacToe!", whyItFailed);
    }

    @Test
    public void resourceBundleShouldOfferWelcomeInPolish(){
        //given
        String whyItFailed = "ResourceBundle not working or language incorrectly chosen";
        Locale.setDefault(new Locale("pl"));
        ResourceBundle languageBundle = ResourceBundle.getBundle("Language");
        //when
        String message = languageBundle.getString("gameName");
        //then
        assertEquals(message, "Witaj w grze w kółko i krzyżyk!", whyItFailed);
    }
}
