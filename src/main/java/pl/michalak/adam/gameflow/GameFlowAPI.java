package pl.michalak.adam.gameflow;

import pl.michalak.adam.input.InputAPI;
import pl.michalak.adam.output.OutputAPI;
import pl.michalak.adam.settings.PropertiesAPI;

import java.io.PrintStream;


public class GameFlowAPI {
    PropertiesAPI propertiesAPI;
    OutputAPI outputAPI;
    InputAPI inputAPI;

    public GameFlowAPI(){
        this.propertiesAPI = new PropertiesAPI(3);
        this.outputAPI = new OutputAPI(new PrintStream(System.out));
        this.inputAPI = new InputAPI(outputAPI);
    }
    public void start() {
        Intro introduction = new Intro(outputAPI, propertiesAPI, inputAPI);
        introduction.beforeGame();
        outputAPI.print("I'm at the beginning again!");
    }

}
