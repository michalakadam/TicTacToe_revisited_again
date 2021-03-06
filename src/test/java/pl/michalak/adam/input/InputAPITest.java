package pl.michalak.adam.input;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.michalak.adam.output.OutputAPI;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class InputAPITest {
    private InputReader inputReader;
    private InputAPI inputAPI;
    private OutputAPI outputAPI;
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
        inputAPI = new InputAPI(outputAPI);
        //when
        String message = "testMessage";
        int min = -4;
        int max = 4;
        //then
        assertEquals(inputAPI.getIntInputFromPlayer(message, min, max), number);
    }

    @Test
    public void inputJustInRangeFromTheLeft(){
        //given
        String data = "-4";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        inputAPI = new InputAPI(outputAPI);
        //when
        String message = "testMessage";
        int min = -4;
        int max = 4;
        //then
        assertEquals(inputAPI.getIntInputFromPlayer(message, min, max), -4);
    }

    @Test
    public void inputJustInRangeFromTheRight(){
        //given
        String data = "4";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        inputAPI = new InputAPI(outputAPI);
        //when
        String message = "testMessage";
        int min = -4;
        int max = 4;
        //then
        assertEquals(inputAPI.getIntInputFromPlayer(message, min, max), 4);
    }
    @Test(expectedExceptions = NoSuchElementException.class)
    public void inputEnterShouldThrowException() {
        //given
        String data = "";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        inputAPI = new InputAPI(outputAPI);
        //when
        String message = "testMessage";
        int min = -4;
        int max = 4;
        //then
        assertEquals(inputAPI.getIntInputFromPlayer(message, min, max), 0);
    }
    @Test()
    public void inputSeparatedByWhiteSpaceShouldReadOnlyFirstNumber() {
        //given
        String data = "-2 2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        inputAPI = new InputAPI(outputAPI);
        //when
        String message = "testMessage";
        int min = -4;
        int max = 4;
        //then
        assertEquals(inputAPI.getIntInputFromPlayer(message, min, max), -2);
    }
    @Test(expectedExceptions = NoSuchElementException.class)
    public void inputNotIntThrowsException(){
        //given
        String data = "SomeWordsHereNotInts";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        inputAPI = new InputAPI(outputAPI);
        //when
        String message = "testMessage";
        int min = -4;
        int max = 4;
        //then
        assertEquals(inputAPI.getIntInputFromPlayer(message, min, max), 4);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void inputIsWhiteSpaceShouldNotWork() {
        //given
        String data = " ";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        StringInputValidator stringInputValidator = new StringInputValidator(outputAPI, inputReader);
        //when
        String message = "testMessage";
        String option1 = "";
        String option2 = " ";
        //then
        assertEquals(stringInputValidator.getStringInputFromPlayer(message, option1, option2), " ");
    }
    @Test(expectedExceptions = NoSuchElementException.class)
    public void inputIsWhiteEnterShouldNotWork() {
        //given
        String data = "";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        StringInputValidator stringInputValidator = new StringInputValidator(outputAPI, inputReader);
        //when
        String message = "testMessage";
        String option1 = "";
        String option2 = " ";
        //then
        assertEquals(stringInputValidator.getStringInputFromPlayer(message, option1, option2), "");
    }
    @DataProvider
    public static Object[][] characters(){
        return new Object[][]{
                {"X", "O"}, {"123", "Adam"}, {"$#$#", "^$FDDF"}
        };
    }
    @Test(dataProvider = "characters")
    public void inputShouldBeCorrect(String word1, String word2){
        //given
        System.setIn(new ByteArrayInputStream(word1.getBytes()));
        inputReader = new InputReader(System.in);
        StringInputValidator stringInputValidator = new StringInputValidator(outputAPI, inputReader);
        //when
        String message = "testMessage";
        String option1 = word1;
        String option2 = word2;
        //then
        assertEquals(stringInputValidator.getStringInputFromPlayer(message, option1, option2), word1);
    }
    @Test
    public void inputBeginningWithSmallLetterIsCorrect(){
        //given
        String data = "x";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        StringInputValidator stringInputValidator = new StringInputValidator(outputAPI, inputReader);
        //when
        String message = "testMessage";
        String option1 = "X";
        String option2 = "O";
        //then
        assertEquals(stringInputValidator.getStringInputFromPlayer(message, option1, option2), "x");
    }
    @Test
    public void inputBeginningWithCapitalLetterIsCorrect(){
        //given
        String data = "X";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        StringInputValidator stringInputValidator = new StringInputValidator(outputAPI, inputReader);
        //when
        String message = "testMessage";
        String option1 = "x";
        String option2 = "o";
        //then
        assertEquals(stringInputValidator.getStringInputFromPlayer(message, option1, option2), "X");
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void inputThatDoesNotMatchOptionShouldInformAboutIt(){
        //given
        String data = "WrongData";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        inputReader = new InputReader(System.in);
        StringInputValidator stringInputValidator = new StringInputValidator(outputAPI, inputReader);
        //when
        String message = "testMessage";
        String option1 = "x";
        String option2 = "o";
        //then
        assertEquals(stringInputValidator.getStringInputFromPlayer(message, option1, option2), "This line is never reached anyway");
    }

}