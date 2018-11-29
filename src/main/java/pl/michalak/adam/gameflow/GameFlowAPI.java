package pl.michalak.adam.gameflow;

import pl.michalak.adam.input.InputAPI;
import pl.michalak.adam.output.OutputAPI;
import pl.michalak.adam.settings.PropertiesAPI;
import pl.michalak.adam.users.PlayersAPI;

import java.io.PrintStream;


public class GameFlowAPI {
    PropertiesAPI propertiesAPI;
    OutputAPI outputAPI;
    InputAPI inputAPI;
    PlayersAPI playersAPI;

    public GameFlowAPI(){
        this.propertiesAPI = new PropertiesAPI();
        this.outputAPI = new OutputAPI(new PrintStream(System.out));
        this.inputAPI = new InputAPI(outputAPI);
        this.playersAPI = new PlayersAPI();
    }
    public void start() {
        Intro introduction = new Intro(outputAPI, propertiesAPI, inputAPI, playersAPI);
        introduction.beforeGame();
        outputAPI.print("I'm at the beginning again!");
    }

}
