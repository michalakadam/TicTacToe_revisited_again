package pl.michalak.adam.gameflow;

import pl.michalak.adam.input.InputAPI;
import pl.michalak.adam.output.OutputAPI;
import pl.michalak.adam.settings.PropertiesAPI;

class Intro {
    OutputAPI outputAPI;
    PropertiesAPI propertiesAPI;
    InputAPI inputAPI;

    Intro(OutputAPI outputAPI, PropertiesAPI propertiesAPI, InputAPI inputAPI) {
        this.outputAPI = outputAPI;
        this.propertiesAPI = propertiesAPI;
        this.inputAPI = inputAPI;
    }
    public void beforeGame(){
        outputAPI.printMenu();
        inputAPI.getIntInputFromPlayer("decideInMenu", 1, 3);
    }
}
