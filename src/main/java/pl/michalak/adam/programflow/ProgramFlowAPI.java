package pl.michalak.adam.programflow;

import pl.michalak.adam.input.InputAPI;
import pl.michalak.adam.output.OutputAPI;
import pl.michalak.adam.settings.PropertiesAPI;
import pl.michalak.adam.users.PlayersAPI;

import java.io.PrintStream;

/**
 * This is the class that controls the flow of the program.
 * It wraps two big parts of it: game and intro.
 *
 * @author Adam Michalak
*/
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


    /**
     * This method initializes Intro object with required APIs.
     * Intro object enables user to see and use the menu.
     * Then GameFlow object is initialized with the same parameters.
     * This object is responsible for game UI.
    */
    public void start() {
        Intro introduction = new Intro(outputAPI, propertiesAPI, inputAPI, playersAPI);
        introduction.beforeGame();
        GameFlow gameFlow = new GameFlow(outputAPI, propertiesAPI, inputAPI, playersAPI);
        gameFlow.beginGame();
    }
}
