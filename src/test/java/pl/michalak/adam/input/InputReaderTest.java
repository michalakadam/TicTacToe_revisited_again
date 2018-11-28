package pl.michalak.adam.input;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.*;

public class InputReaderTest {
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

    @Test(dataProvider="numbers")
    public void wejścieInt(int number) {
        //given
        String data = Integer.toString(number);
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        InputReader inputReader = new InputReader(System.in);
        //then
        assertEquals(inputReader.getInt(), number);
    }
    @DataProvider
    public static Object[] characterSequences(){
        return new Object[]{
                "JAVAACADEMY", "javaacademy", "JavaAcademy",
                "#%*($", "2132341", "221231131", "%#@,#@,@@#"
        };
    }
    @Test(dataProvider="characterSequences")
    public void wejścieString(String data) {
        //given
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        InputReader inputReader = new InputReader(System.in);
        //then
        assertEquals(inputReader.getString(), data);
    }
}
