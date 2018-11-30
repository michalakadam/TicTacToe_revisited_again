package pl.michalak.adam.assessment;

import pl.michalak.adam.components.ComponentsAPI;
import pl.michalak.adam.components.Symbol;

class WinChecker {
    private final ComponentsAPI componentsAPI;
    private final int winningCondition;

    WinChecker(ComponentsAPI componentsAPI, int winningCondition){
        this.componentsAPI = componentsAPI;
        this.winningCondition = winningCondition;
    }


    boolean checkWin(int index, Symbol symbol) {
        //index%componentsAPI.getBoardSideSize() guarantees that column is checked from the beginning.
        if(countConsecutiveSymbolsInColumn(index%componentsAPI.getBoardSideSize(), symbol) == this.winningCondition) return true;
        //index - index%componentsAPI.getBoardSideSize() guarantees that row is checked from the beginning.
        else if(countConsecutiveSymbolsInRow(index - index%componentsAPI.getBoardSideSize(), symbol) == this.winningCondition) return true;
        //index%(componentsAPI.getBoardSideSize()+1) guarantees that first diagonal is checked from the beginning.
        else if(countConsecutiveSymbolsInFirstDiagonal(index%(componentsAPI.getBoardSideSize()+1), symbol) == this.winningCondition) return true;
        //index%(componentsAPI.getBoardSideSize()-1) guarantees that first diagonal is checked from the beginning.
        //If diagonal is main diagonal therefore index%(componentsAPI.getBoardSideSize()-1) == 0 and checking will begin at index = 0 which does not belong to this diagonal.
        else{
            if (index % (componentsAPI.getBoardSideSize() - 1) == 0 || componentsAPI.getBoardSideSize() == 3) {
                if (countConsecutiveSymbolsInSecondDiagonal(componentsAPI.getBoardSideSize() - 1, symbol) == winningCondition)
                    return true;
            } else {
                if (countConsecutiveSymbolsInSecondDiagonal(index % (componentsAPI.getBoardSideSize() - 1), symbol) == this.winningCondition)
                    return true;
            }
        }
        return false;
    }

    private int countConsecutiveSymbolsInColumn(int index, Symbol symbol) {
        //index + componentsAPI.getBoardSideSize() is a location of next slot in the column, eg. for first columne of 3x3 componentsAPI 0, 3, 6
        if(index + componentsAPI.getBoardSideSize() >= componentsAPI.getNumberOfSlotsOnBoard())
            return recursionBaseCaseResult(index, symbol);
        if(componentsAPI.getSymbolFromSlot(index).equals(symbol))
            return 1 + countConsecutiveSymbolsInColumn(index+componentsAPI.getBoardSideSize(), symbol);
        return countConsecutiveSymbolsInColumn(index+componentsAPI.getBoardSideSize(), symbol);
    }

    private int countConsecutiveSymbolsInRow(int index, Symbol symbol) {
        //index+1 is a location of next slot in the row, eg. for first row of 3x3 componentsAPI 0, 1, 2
        if((index+1)%componentsAPI.getBoardSideSize() == 0)
            return recursionBaseCaseResult(index, symbol);
        if(componentsAPI.getSymbolFromSlot(index).equals(symbol))
            return 1 + countConsecutiveSymbolsInRow(index+1, symbol);
        return countConsecutiveSymbolsInRow(index+1, symbol);
    }

    //first diagonal is one going from up-left side of the componentsAPI to bottom-right
    private int countConsecutiveSymbolsInFirstDiagonal(int index, Symbol symbol) {
        //index+componentsAPI.getBoardSideSize()+1 is a location of next slot in the diagonal, eg. for 3x3 componentsAPI 0, 4, 8
        if(index+componentsAPI.getBoardSideSize() +1 >= componentsAPI.getNumberOfSlotsOnBoard())
            return recursionBaseCaseResult(index, symbol);
        if(componentsAPI.getSymbolFromSlot(index).equals(symbol))
            return 1 + countConsecutiveSymbolsInFirstDiagonal(index+componentsAPI.getBoardSideSize()+1, symbol);
        return countConsecutiveSymbolsInFirstDiagonal(index+componentsAPI.getBoardSideSize()+1, symbol);
    }

    //second diagonal is one going from up-right side of the componentsAPI to bottom-left
    private int countConsecutiveSymbolsInSecondDiagonal(int index, Symbol symbol) {
        //index+componentsAPI.getBoardSideSize()-1 is a location of next slot in the diagonal, eg. for 3x3 componentsAPI 2, 4, 6
        if(index+componentsAPI.getBoardSideSize()-1 >= componentsAPI.getNumberOfSlotsOnBoard())
            return recursionBaseCaseResult(index, symbol);
        if(componentsAPI.getSymbolFromSlot(index).equals(symbol))
            return 1 + countConsecutiveSymbolsInSecondDiagonal(index+componentsAPI.getBoardSideSize()-1, symbol);
        return countConsecutiveSymbolsInSecondDiagonal(index+componentsAPI.getBoardSideSize()-1, symbol);
    }

    private int recursionBaseCaseResult(int index, Symbol symbol){
        if (componentsAPI.getSymbolFromSlot(index).equals(symbol))
            return 1;
        return 0;
    }
}
