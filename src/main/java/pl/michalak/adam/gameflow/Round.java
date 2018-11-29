package pl.michalak.adam.gameflow;

import pl.michalak.adam.input.InputAPI;
import pl.michalak.adam.output.OutputAPI;
import pl.michalak.adam.settings.PropertiesAPI;
import pl.michalak.adam.users.PlayersAPI;

class Round {
    private final PropertiesAPI propertiesAPI;
    private final OutputAPI outputAPI;
    private final InputAPI inputAPI;
    private final PlayersAPI playersAPI;
    static int turnCounter;

    Round(PropertiesAPI propertiesAPI, OutputAPI outputAPI, InputAPI inputAPI, PlayersAPI playersAPI) {
        this.propertiesAPI = propertiesAPI;
        this.outputAPI = outputAPI;
        this.inputAPI = inputAPI;
        this.playersAPI = playersAPI;
        this.turnCounter = 0;
    }


    void beginRound() {
        playerMove();
    }

    void playerMove(){

    }
}
