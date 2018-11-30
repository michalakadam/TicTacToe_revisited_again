package pl.michalak.adam.assessment;

import pl.michalak.adam.components.ComponentsAPI;
import pl.michalak.adam.components.Symbol;

public class AssessmentAPI {
    WinChecker winChecker;

    public AssessmentAPI(ComponentsAPI componentsAPI, int winningCondition){
        this.winChecker = new WinChecker(componentsAPI, winningCondition);
    }

    public boolean checkForWinner(int index, Symbol symbol){
        return winChecker.checkWin(index, symbol);
    }
}
