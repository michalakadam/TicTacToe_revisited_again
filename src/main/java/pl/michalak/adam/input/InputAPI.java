package pl.michalak.adam.input;

import pl.michalak.adam.output.OutputAPI;

public class InputAPI {
    private final IntInputValidator intInputValidator;
    private final StringInputValidator stringInputValidator;
    private final StringInputProvider stringInputProvider;

    public InputAPI(OutputAPI outputAPI){
        InputReader inputReader = new InputReader(System.in);
        this.stringInputValidator = new StringInputValidator(outputAPI, inputReader);
        this.stringInputProvider = new StringInputProvider(outputAPI, inputReader);
        this.intInputValidator = new IntInputValidator(outputAPI, inputReader, stringInputValidator);
    }

    public int getIntInputFromPlayer(String message, int min, int max){
        return intInputValidator.getIntInputFromPlayer(message, min, max);
    }

    public String getStringInputFromPlayerWithNoStringsAttached(String message){
        return stringInputProvider.getStringFromUser(message);
    }

}
