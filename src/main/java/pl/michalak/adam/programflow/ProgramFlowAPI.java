package pl.michalak.adam.programflow;

import pl.michalak.adam.input.InputAPI;
import pl.michalak.adam.output.OutputAPI;
import pl.michalak.adam.settings.PropertiesAPI;
import pl.michalak.adam.users.PlayersAPI;

import java.io.PrintStream;


public class ProgramFlowAPI {
    private final PropertiesAPI propertiesAPI;
    private final OutputAPI outputAPI;
    private final InputAPI inputAPI;
    private final PlayersAPI playersAPI;

    public ProgramFlowAPI(){
        this.propertiesAPI = new PropertiesAPI();
        this.outputAPI = new OutputAPI(new PrintStream(System.out));
        this.inputAPI = new InputAPI(outputAPI);
        this.playersAPI = new PlayersAPI();
    }
    public void start() {
        Intro introduction = new Intro(outputAPI, propertiesAPI, inputAPI, playersAPI);
        introduction.beforeGame();
        playGame();
    }

    private void playGame(){
        GameFlow gameFlow = new GameFlow(outputAPI, propertiesAPI, inputAPI, playersAPI);
        gameFlow.beginGame();
    }

}
