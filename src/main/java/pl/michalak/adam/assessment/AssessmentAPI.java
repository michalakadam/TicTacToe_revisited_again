package pl.michalak.adam.assessment;
import pl.michalak.adam.components.ComponentsAPI;
import pl.michalak.adam.components.Symbol;

/**
 * This class is a "judge". It checks the board for the winner.
 *
 * @author Adam Michalak
*/
public class AssessmentAPI {
    WinChecker winChecker;

    public AssessmentAPI(ComponentsAPI componentsAPI, int winningCondition){
        this.winChecker = new WinChecker(componentsAPI, winningCondition);
    }


    /**
     * This method calls an algorithm that checks the board for the winner.
     * @param index determines the slot where last move was made. It helps optimize the algorithm.
     * @param symbol determines type of piece that the last move was made with.
     * @return true if one of the players has the winning combination.
    */
    public boolean checkForWinner(int index, Symbol symbol){
        return winChecker.checkWin(index, symbol);
    }
}
