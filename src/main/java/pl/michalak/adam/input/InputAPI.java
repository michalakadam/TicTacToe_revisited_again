package pl.michalak.adam.input;

import pl.michalak.adam.output.OutputAPI;

public class InputAPI {
    InputReader inputReader;
    IntInputValidator intInputValidator;
    StringInputValidator stringInputValidator;

    public InputAPI(OutputAPI outputAPI){
        this.inputReader = new InputReader(System.in);
        this.stringInputValidator = new StringInputValidator(outputAPI, inputReader);
        this.intInputValidator = new IntInputValidator(outputAPI, inputReader, stringInputValidator);
    }

    public int getIntInputFromPlayer(String message, int min, int max){
        return intInputValidator.getIntInputFromPlayer(message, min, max);
    }

    public String getStringInputFromPlayer(String message, String option1, String option2){
        return stringInputValidator.getStringInputFromPlayer(message, option1, option2);
    }

}
