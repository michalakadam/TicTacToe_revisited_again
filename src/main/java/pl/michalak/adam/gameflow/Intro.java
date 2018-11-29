package pl.michalak.adam.gameflow;

import pl.michalak.adam.output.OutputAPI;
import pl.michalak.adam.settings.PropertiesAPI;

class Intro {
    OutputAPI outputAPI;
    PropertiesAPI propertiesAPI;

    Intro(OutputAPI outputAPI, PropertiesAPI propertiesAPI) {
        this.outputAPI = outputAPI;
        this.propertiesAPI = propertiesAPI;
    }
    public void beforeGame(){
        outputAPI.printMenu();

    }
}
