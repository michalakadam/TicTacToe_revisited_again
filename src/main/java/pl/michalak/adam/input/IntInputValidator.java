package pl.michalak.adam.input;

import pl.michalak.adam.output.OutputAPI;

import java.text.MessageFormat;
import java.util.InputMismatchException;

class IntInputValidator {
    private final OutputAPI outputAPI;
    private final InputReader inputReader;
    
    IntInputValidator(OutputAPI outputAPI, InputReader inputReader){
        this.outputAPI = outputAPI;
        this.inputReader = inputReader;
    }
    private int checkInputFromPlayer(){
        try {
            return inputReader.getInt();
        }
        catch(InputMismatchException inputMismatchException) {
            outputAPI.printFromResourceBundle("wrongValue");
            inputReader.getString();
            return checkInputFromPlayer();
        }
    }
    private boolean inputIsInRange(int input, int min, int max){
        return input >= min && input <= max;
    }
    public int getIntInputFromPlayer(String message, int min, int max){
        outputAPI.printFromResourceBundle(message);
        int input = checkInputFromPlayer();
        if (inputIsInRange(input, min, max)) {
            return input;
        }
        outputAPI.printFromResourceBundleWithFormatting("numberOutOfRange", min);
        return getIntInputFromPlayer(message, min, max);
    }
}

