package pl.michalak.adam.output;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.testng.Assert.*;

public class DisplayTest {
    private ByteArrayOutputStream byteArrayOutputStream;
    private Display display;

    @BeforeMethod
    public void setup(){
        byteArrayOutputStream = new ByteArrayOutputStream();
        display = new Display(new PrintStream(byteArrayOutputStream));
    }
    @Test
    public void singleWordShouldBePrintedOnScreen(){
        //given
        String whyItFailed = "Message printed isn't equal to message stored in byte array or encoding problem occured";
        String message = "word";
        //when
        display.print(message);
        //then
        try {
            assertEquals(byteArrayOutputStream.toString("UTF-8"), "word");
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
        display.print(message);
        //then
        try {
            assertEquals(byteArrayOutputStream.toString("UTF-8"), "Sentence can be printed on screen as well.");
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
        display.println(message);
        //then
        try {
            assertEquals(byteArrayOutputStream.toString("UTF-8"), "Sentence can be printed on screen as well.\n");
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
        display.println(message);
        //then
        try {
            assertEquals(byteArrayOutputStream.toString("UTF-8"), "Zażółć gęślą jaźń. ~!@#$%^ &*()_+\n");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}