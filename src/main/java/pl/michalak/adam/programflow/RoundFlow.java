package pl.michalak.adam.programflow;

import pl.michalak.adam.components.ComponentsAPI;
import pl.michalak.adam.components.Symbol;
import pl.michalak.adam.input.InputAPI;
import pl.michalak.adam.output.OutputAPI;
import pl.michalak.adam.settings.PropertiesAPI;
import pl.michalak.adam.users.PlayersAPI;

class RoundFlow {
    private final PropertiesAPI propertiesAPI;
    private final OutputAPI outputAPI;
    private final InputAPI inputAPI;
    private final PlayersAPI playersAPI;
    private ComponentsAPI componentsAPI;
    private Round round;

    RoundFlow(PropertiesAPI propertiesAPI, OutputAPI outputAPI, InputAPI inputAPI, PlayersAPI playersAPI) {
        this.propertiesAPI = propertiesAPI;
        this.outputAPI = outputAPI;
        this.inputAPI = inputAPI;
        this.playersAPI = playersAPI;
        this. componentsAPI = new ComponentsAPI(propertiesAPI.getBoardSizeForThisGame());
    }


    void beginRound() {
        initializeBoardPrinter();
        round = new Round(componentsAPI, propertiesAPI);
        while(!round.wasThisTheLastTurn() && !round.checkForWinner(componentsAPI.getLastMove(), getPlayersSymbolInThisTurn(getPlayersNumberInThisTurn()))) {
            nextTurn();
        }
        outputAPI.printBoard();
        if(wasItADraw()){
            outputAPI.printFromResourceBundleAndAddNextLine("drawAnnouncement");
        }
        else {
            outputAPI.print(getPlayersNameInThisTurn(determineTheWinner()) + " ");
            outputAPI.printFromResourceBundleAndAddNextLine("theWinnerIs");
        }
    }

    private void initializeBoardPrinter() {
        outputAPI.setBoardPrinter(componentsAPI);
    }

    private void nextTurn(){
        outputAPI.printBoard();
        round.increaseTurnNumber();
        outputAPI.print(getPlayersNameInThisTurn(getPlayersNumberInThisTurn())+", ");
        componentsAPI.addSymbolOnBoardAtSlot(getIndexFromPlayer(), getPlayersSymbolInThisTurn(getPlayersNumberInThisTurn()));
    }

    private int getPlayersNumberInThisTurn(){
        return round.getTurnNumber()%2 == 1 ? 1 : 2;
    }

    private String getPlayersNameInThisTurn(int playerNumber){
        return playersAPI.getPlayerName(playerNumber);
    }

    private Symbol getPlayersSymbolInThisTurn(int playerNumber){
        return playersAPI.getPlayerSymbol(playerNumber);
    }

    private int getIndexFromPlayer(){
        return inputAPI.getIntInputFromPlayer("playerMoves", 1, componentsAPI.getNumberOfSlotsOnBoard());
    }

    boolean wasItADraw(){
        return round.getTurnNumber() == componentsAPI.getNumberOfSlotsOnBoard();
    }

    int determineTheWinner(){
        return getPlayersNumberInThisTurn();
    }
}
