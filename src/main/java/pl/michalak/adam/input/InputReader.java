package pl.michalak.adam.input;

import java.io.InputStream;
import java.util.Scanner;

class InputReader {

    private final Scanner keyboard;

    InputReader(InputStream inputStream){
        this.keyboard = new Scanner(inputStream);
    }
    int getInt(){ return keyboard.nextInt(); }

    String getString(){ return keyboard.next(); }


}
