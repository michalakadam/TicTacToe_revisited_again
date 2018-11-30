package pl.michalak.adam.programflow;

import pl.michalak.adam.input.InputAPI;
import pl.michalak.adam.output.OutputAPI;
import pl.michalak.adam.settings.PropertiesAPI;
import pl.michalak.adam.users.PlayersAPI;

class GameFlow {
    private final PropertiesAPI propertiesAPI;
    private final OutputAPI outputAPI;
    private final InputAPI inputAPI;
    private final PlayersAPI playersAPI;
    private Game game;

    GameFlow(OutputAPI outputAPI, PropertiesAPI propertiesAPI, InputAPI inputAPI, PlayersAPI playersAPI) {
        this.propertiesAPI = propertiesAPI;
        this.outputAPI = outputAPI;
        this.inputAPI = inputAPI;
        this.playersAPI = playersAPI;
    }

    void beginGame(){
        this.game = new Game();
        while(game.getRoundNumber() <= 3)
            game.beginNewRound();
            playRound();

    }

    private void playRound(){
        RoundFlow roundFlow = new RoundFlow(propertiesAPI, outputAPI, inputAPI, playersAPI);
        roundFlow.beginRound();
    }

}
