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
    private RoundFlow roundFlow;

    GameFlow(OutputAPI outputAPI, PropertiesAPI propertiesAPI, InputAPI inputAPI, PlayersAPI playersAPI) {
        this.propertiesAPI = propertiesAPI;
        this.outputAPI = outputAPI;
        this.inputAPI = inputAPI;
        this.playersAPI = playersAPI;
    }

    void beginGame(){
        this.game = new Game();
        outputAPI.setScoreBoardPrinter(playersAPI);
        boolean doYouWantToPlayAnotherRound = true;
        while(game.getRoundNumber() < 3 && doYouWantToPlayAnotherRound) {
            game.beginNewRound();
            playRound();
            givePlayersPoints();
            printScoreBoard();
            if(game.getRoundNumber() != 3)
                doYouWantToPlayAnotherRound = askPlayerAboutAnotherRound();
        }
        sumUpGame();

    }

    private void playRound(){
        this.roundFlow = new RoundFlow(propertiesAPI, outputAPI, inputAPI, playersAPI);
        roundFlow.beginRound();
    }

    private void givePlayersPoints(){
        if(roundFlow.wasItADraw()){
            playersAPI.addPointsToPlayersScore(1, 1);
            playersAPI.addPointsToPlayersScore(1, 2);
        }
        else
            playersAPI.addPointsToPlayersScore(3, roundFlow.determineTheWinner());
    }

    private void printScoreBoard() {
        outputAPI.printFromResourceBundleAndAddNextLine("scoreBoardHeader");
        outputAPI.printScoreBoard();
    }

    private void sumUpGame() {
        if(roundFlow.wasItADraw())
            outputAPI.printFromResourceBundleAndAddNextLine("drawAnnouncement");
        else {
            outputAPI.print(playersAPI.getPlayerName(roundFlow.determineTheWinner())+" ");
            outputAPI.printFromResourceBundleAndAddNextLine("theWinnerIs");
        }
    }

    boolean askPlayerAboutAnotherRound(){
        return inputAPI.getIntInputFromPlayer("playMore", 1, 2) == 1;
    }
}