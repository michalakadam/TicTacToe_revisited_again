package pl.michalak.adam.gameflow;

import pl.michalak.adam.components.ComponentsAPI;
import pl.michalak.adam.input.InputAPI;
import pl.michalak.adam.output.OutputAPI;
import pl.michalak.adam.settings.PropertiesAPI;
import pl.michalak.adam.users.PlayersAPI;

class Game {
    private final PropertiesAPI propertiesAPI;
    private final OutputAPI outputAPI;
    private final InputAPI inputAPI;
    private final PlayersAPI playersAPI;
    private static int roundNumber;

    Game(OutputAPI outputAPI, PropertiesAPI propertiesAPI, InputAPI inputAPI, PlayersAPI playersAPI) {
        this.propertiesAPI = propertiesAPI;
        this.outputAPI = outputAPI;
        this.inputAPI = inputAPI;
        this.playersAPI = playersAPI;
        roundNumber = 0;
    }

    void beginGame(){
        playRound();
    }

    private void playRound(){
        Round round = new Round(propertiesAPI, outputAPI, inputAPI, playersAPI);
        round.beginRound();
    }

}
