package pl.michalak.adam.input;

import pl.michalak.adam.output.OutputAPI;

class StringInputProvider {
    private final OutputAPI outputAPI;
    private final InputReader inputReader;

    StringInputProvider(OutputAPI outputAPI, InputReader inputReader){
        this.outputAPI = outputAPI;
        this.inputReader = inputReader;
    }

    String getStringFromUser(String message){
        outputAPI.printFromResourceBundle(message);
        return inputReader.getString();
    }

}
