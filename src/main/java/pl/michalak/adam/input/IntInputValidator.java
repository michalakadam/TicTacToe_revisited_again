package pl.michalak.adam.input;

import pl.michalak.adam.output.OutputAPI;

import java.util.InputMismatchException;

class IntInputValidator {
    private final OutputAPI outputAPI;
    private final InputReader inputReader;
    private final StringInputValidator stringInputValidator;

    IntInputValidator(OutputAPI outputAPI, InputReader inputReader, StringInputValidator stringInputValidator){
        this.outputAPI = outputAPI;
        this.inputReader = inputReader;
        this.stringInputValidator = stringInputValidator;
    }
    private int checkInputFromPlayer(String message){
        try {
            return inputReader.getInt();
        }
        catch(InputMismatchException inputMismatchException) {
            String wrongAnswer = inputReader.getString();
            if(wrongAnswer.equals("q"))
                exitValidation(message);
            else
                outputAPI.printErrorFromResourceBundle("wrongValue");
            return checkInputFromPlayer(message);
        }
    }
    private void exitValidation(String message){
        String areYouSure = stringInputValidator.getStringInputFromPlayer("validateUserExitWish", "Y", "N");
        if(areYouSure.equals("Y") || areYouSure.equals("y"))
            System.exit(0);
        else
            outputAPI.printFromResourceBundleAndAddNextLine("changedMyMind");
        outputAPI.printFromResourceBundle(message);
    }
    private boolean inputIsInRange(int input, int min, int max){
        return input >= min && input <= max;
    }
    int getIntInputFromPlayer(String message, int min, int max){
        outputAPI.printFromResourceBundle(message);
        int input = checkInputFromPlayer(message);
        if (inputIsInRange(input, min, max)) {
            return input;
        }
        outputAPI.printErrorFromResourceBundleWithFormatting("numberOutOfRange", min, max);
        return getIntInputFromPlayer(message, min, max);
    }
}

