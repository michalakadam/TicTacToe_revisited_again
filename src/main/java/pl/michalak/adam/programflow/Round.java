package pl.michalak.adam.programflow;

import pl.michalak.adam.assessment.AssessmentAPI;
import pl.michalak.adam.components.ComponentsAPI;
import pl.michalak.adam.components.Symbol;
import pl.michalak.adam.settings.PropertiesAPI;

class Round {
    private int turnNumber;
    private final int winningCondition;
    private final int MAXNUMBEROFTURNS;
    private final AssessmentAPI assessmentAPI;

    Round(ComponentsAPI componentsAPI, PropertiesAPI propertiesAPI) {
        this.turnNumber = 0;
        this.winningCondition = propertiesAPI.getWinningConditionForThisGame();
        this.assessmentAPI = new AssessmentAPI(componentsAPI, winningCondition);
        MAXNUMBEROFTURNS = componentsAPI.getNumberOfSlotsOnBoard();
    }

    boolean wasThisTheLastTurn(){
        return turnNumber == MAXNUMBEROFTURNS;
    }

    int getTurnNumber(){
        return this.turnNumber;
    }

    void increaseTurnNumber() {
        this.turnNumber++;
    }

    boolean checkForWinner(int lastMove, Symbol playersSymbolInThisTurn) {
        //when number of moves is less than winning condition it is not possible to win
        if(turnNumber < winningCondition*2-1)
            return false;
        return assessmentAPI.checkForWinner(lastMove, playersSymbolInThisTurn);
    }
}
