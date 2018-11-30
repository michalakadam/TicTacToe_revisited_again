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
        outputAPI.setScoreBoardPrinter(playersAPI);
        boolean doYouWantToPlayAnotherGame = true;
        while(doYouWantToPlayAnotherGame) {
            this.game = new Game();
            playersAPI.resetPoints();
            boolean doYouWantToPlayAnotherRound = true;
            while (game.getRoundNumber() < 3 && doYouWantToPlayAnotherRound) {
                game.beginNewRound();
                playRound();
                givePlayersPoints();
                printScoreBoard();
                if (game.getRoundNumber() != 3)
                    doYouWantToPlayAnotherRound = askPlayerAboutAnotherRound();
            }
            sumUpGame();
            doYouWantToPlayAnotherGame = askForAnotherGame();
        }
        outputAPI.printFromResourceBundleAndAddNextLine("finishGame");
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
        if(playersAPI.getPlayersScore(1) == playersAPI.getPlayersScore(2))
            outputAPI.printFromResourceBundleAndAddNextLine("drawAnnouncement");
        else {
            outputAPI.print(playersAPI.getPlayerName(roundFlow.determineTheWinner())+" ");
            outputAPI.printFromResourceBundleAndAddNextLine("theWinnerIs");
        }
    }

    private boolean askPlayerAboutAnotherRound(){
        String userAnswer = inputAPI.getStringInputFromPlayer("anotherRound", "Y", "N");
        return userAnswer.equals("Y") || userAnswer.equals("y");
    }

    private boolean askForAnotherGame(){
        String userAnswer = inputAPI.getStringInputFromPlayer("anotherGame", "Y", "N");
        return userAnswer.equals("Y") || userAnswer.equals("y");
    }
}
