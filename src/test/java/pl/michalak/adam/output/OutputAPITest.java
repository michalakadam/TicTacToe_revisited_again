package pl.michalak.adam.output;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.testng.Assert.assertEquals;


public class OutputAPITest {
    private OutputAPI displayAPI;
    private ByteArrayOutputStream byteArrayOutputStream;
    PrintStream printStream;

    @BeforeMethod
    public void setUp(){
        byteArrayOutputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(byteArrayOutputStream);
        displayAPI = new OutputAPI(printStream);
    }

    @Test
    public void shouldPrintWelcomeMessageInPolish(){
        //given
        String whyItFailed = "ResourceBundle not working or language incorrectly chosen";
        Locale.setDefault(new Locale("pl"));
        ResourceBundle languageBundle = ResourceBundle.getBundle("Language");
        String welcomeMessage = "welcome";
        //when
        displayAPI.print(welcomeMessage);
        //then
        try {
            assertEquals(byteArrayOutputStream.toString("UTF-8"), languageBundle.getString("welcome"), whyItFailed);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }




}