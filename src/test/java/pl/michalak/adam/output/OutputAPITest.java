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
    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream printStream;

    @BeforeMethod
    public void setUp(){
        byteArrayOutputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(byteArrayOutputStream);

    }

    @Test
    public void shouldPrintTestMessageInPolish(){
        //given
        String whyItFailed = "ResourceBundle not working or language incorrectly chosen";
        Locale.setDefault(new Locale("pl"));
        OutputAPI outputAPI = new OutputAPI(printStream);
        ResourceBundle languageBundle = ResourceBundle.getBundle("Language");
        String welcomeMessage = "testMessage";
        //when
        outputAPI.printFromResourceBundle(welcomeMessage);
        //then
        try {
            assertEquals(byteArrayOutputStream.toString("UTF-8"), languageBundle.getString("testMessage"), whyItFailed);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldPrintTestMessageInPolishWithFormattingZeroTimes(){
        //given
        String whyItFailed = "ResourceBundle not working or language incorrectly chosen";
        Locale.setDefault(new Locale("pl"));
        OutputAPI outputAPI = new OutputAPI(printStream);
        ResourceBundle languageBundle = ResourceBundle.getBundle("Language");
        String welcomeMessage = "testMessage";
        Locale.setDefault(new Locale("pl"));
        //when
        outputAPI.printFromResourceBundleWithFormatting(welcomeMessage);
        //then
        try {
            assertEquals(byteArrayOutputStream.toString("UTF-8"), languageBundle.getString("testMessage"), whyItFailed);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldPrintTestMessageInPolishWithFormattingOneTime(){
        //given
        String whyItFailed = "ResourceBundle not working or language incorrectly chosen";
        Locale.setDefault(new Locale("pl"));
        OutputAPI outputAPI = new OutputAPI(printStream);
        ResourceBundle languageBundle = ResourceBundle.getBundle("Language");
        String welcomeMessage = "testMessageWithOneFormatting";
        Locale.setDefault(new Locale("pl"));
        //when
        outputAPI.printFromResourceBundleWithFormatting(welcomeMessage, "nie");
        //then
        try {
            assertEquals(byteArrayOutputStream.toString("UTF-8"), "To nie jest wiadomość testowa ", whyItFailed);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldPrintTestMessageInEnglishWithFormattingTwoTimes(){
        //given
        String whyItFailed = "ResourceBundle not working or language incorrectly chosen";
        Locale.setDefault(new Locale("en"));
        OutputAPI outputAPI = new OutputAPI(printStream);
        String welcomeMessage = "testMessageWithDoubleFormatting";
        //when
        outputAPI.printFromResourceBundleWithFormatting(welcomeMessage, "not", "that kind of");
        //then
        try {
            assertEquals(byteArrayOutputStream.toString("UTF-8"), "This is not that kind of test message ", whyItFailed);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}