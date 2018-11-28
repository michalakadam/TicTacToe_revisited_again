package pl.michalak.adam.input;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.michalak.adam.output.OutputAPI;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;

public class IntInputValidatorTest {
    InputReader inputReader;
    IntInputValidator intInputValidator;
    OutputAPI outputAPI;
    @BeforeMethod
    public void setUp(){
        outputAPI = new OutputAPI(new PrintStream(System.out));
    }
    @DataProvider
    public static Object[] numbers(){
        return new Object[]{
                -10000,
                -1000,
                -999,
                -100,
                -10,
                -5,
                5,
                10,
                101,
                999,
                1000,
                10000
        };
    }

    @Test(expectedExceptions = NoSuchElementException.class, dataProvider="numbers")
    public void inputShouldBeOutOfRange(int number) {
        //given
        String data = Integer.toString(number);
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        intInputValidator = new IntInputValidator(outputAPI, inputReader);
        //when
        String message = "testMessage";
        int min = -4;
        int max = 4;
        //then
        assertEquals(intInputValidator.getIntInputFromPlayer(message, min, max), number);
    }

    @Test
    public void inputJustInRangeFromTheLeft(){
        //given
        String data = "-4";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        intInputValidator = new IntInputValidator(outputAPI, inputReader);
        //when
        String message = "testMessage";
        int min = -4;
        int max = 4;
        //then
        assertEquals(intInputValidator.getIntInputFromPlayer(message, min, max), -4);
    }

    @Test
    public void inputJustInRangeFromTheRight(){
        //given
        String data = "4";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        intInputValidator = new IntInputValidator(outputAPI, inputReader);
        //when
        String message = "testMessage";
        int min = -4;
        int max = 4;
        //then
        assertEquals(intInputValidator.getIntInputFromPlayer(message, min, max), 4);
    }
    @Test(expectedExceptions = NoSuchElementException.class)
    public void inputEnterShouldThrowException() {
        //given
        String data = "";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        intInputValidator = new IntInputValidator(outputAPI, inputReader);
        //when
        String message = "testMessage";
        int min = -4;
        int max = 4;
        //then
        assertEquals(intInputValidator.getIntInputFromPlayer(message, min, max), 0);
    }
    @Test()
    public void inputSeparatedByWhiteSpaceShouldReadOnlyFirstNumber() {
        //given
        String data = "-2 2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        intInputValidator = new IntInputValidator(outputAPI, inputReader);
        //when
        String message = "testMessage";
        int min = -4;
        int max = 4;
        //then
        assertEquals(intInputValidator.getIntInputFromPlayer(message, min, max), -2);
    }
    @Test(expectedExceptions = NoSuchElementException.class)
    public void inputNotIntThrowsException(){
        //given
        String data = "SomeWordsHereNotInts";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        intInputValidator = new IntInputValidator(outputAPI, inputReader);
        //when
        String message = "testMessage";
        int min = -4;
        int max = 4;
        //then
        assertEquals(intInputValidator.getIntInputFromPlayer(message, min, max), 4);
    }
}