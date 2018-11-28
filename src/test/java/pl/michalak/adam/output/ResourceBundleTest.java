package pl.michalak.adam.output;

import org.testng.annotations.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.testng.Assert.assertEquals;

class ResourceBundleTest {
    @Test
    public void resourceBundleShouldOfferWelcomeInEnglish(){
        //given
        String whyItFailed = "wasn't implemented: shouldPrintWelcomeInEnglish";
        Locale.setDefault(new Locale("en"));
        ResourceBundle languageBundle = ResourceBundle.getBundle("Language");
        //when
        String message = languageBundle.getString("welcome");
        //then
        assertEquals(message, "Welcome in TicTacToe!");
    }

    @Test
    public void resourceBundleShouldOfferWelcomeInPolish(){
        //given
        String whyItFailed = "wasn't implemented: shouldPrintWelcomeInEnglish";
        Locale.setDefault(new Locale("pl"));
        ResourceBundle languageBundle = ResourceBundle.getBundle("Language");
        //when
        String message = languageBundle.getString("welcome");
        //then
        assertEquals(message, "Witaj w grze w kółko i krzyżyk!");
    }
}
