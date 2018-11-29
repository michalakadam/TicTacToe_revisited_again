package pl.michalak.adam.output;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.testng.Assert.*;

public class ConsolePrinterTest {
    private ByteArrayOutputStream byteArrayOutputStream;
    private ConsolePrinter consolePrinter;

    @BeforeMethod
    public void setup(){
        byteArrayOutputStream = new ByteArrayOutputStream();
        consolePrinter = new ConsolePrinter(new PrintStream(byteArrayOutputStream));
    }
    @Test
    public void singleWordShouldBePrintedOnScreen(){
        //given
        String whyItFailed = "Message printed isn't equal to message stored in byte array or encoding problem occured";
        String message = "word";
        //when
        consolePrinter.print(message);
        //then
        try {
            assertEquals(byteArrayOutputStream.toString("UTF-8"), "word", whyItFailed);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void wholeSentenceShouldBePrintedOnScreen(){
        //given
        String whyItFailed = "Message printed isn't equal to message stored in byte array or encoding problem occured";
        String message = "Sentence can be printed on screen as well.";
        //when
        consolePrinter.print(message);
        //then
        try {
            assertEquals(byteArrayOutputStream.toString("UTF-8"), "Sentence can be printed on screen as well.", whyItFailed);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sentenceShouldBePrintedOnScreenFollowedByNextLine(){
        //given
        String whyItFailed = "Message printed isn't equal to message stored in byte array or encoding problem occured";
        String message = "Sentence can be printed on screen as well.";
        //when
        consolePrinter.println(message);
        //then
        try {
            assertEquals(byteArrayOutputStream.toString("UTF-8"), "Sentence can be printed on screen as well.\n", whyItFailed);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void NonStardardCharactersShouldBePrintedOnScreen(){
        //given
        String whyItFailed = "Message printed isn't equal to message stored in byte array or encoding problem occured";
        String message = "Zażółć gęślą jaźń. ~!@#$%^ &*()_+";
        //when
        consolePrinter.println(message);
        //then
        try {
            assertEquals(byteArrayOutputStream.toString("UTF-8"), "Zażółć gęślą jaźń. ~!@#$%^ &*()_+\n", whyItFailed);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}