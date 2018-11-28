package pl.michalak.adam;

import pl.michalak.adam.components.Board;
import pl.michalak.adam.components.Symbol;
import pl.michalak.adam.settings.WinningCondition;

class WinChecker {
    Board board;
    WinningCondition winningCondition;

    WinChecker(Board board, WinningCondition winningCondition){
        this.board = board;
        this.winningCondition = winningCondition;
    }


    boolean checkWin(int index, Symbol symbol) {
        //index%board.getSideSize() guarantees that column is checked from the beginning.
        if(countConsecutiveSymbolsInColumn(index%board.getSideSize(), symbol) == this.winningCondition.getWinningCondition()) return true;
        //index - index%board.getSideSize() guarantees that row is checked from the beginning.
        else if(countConsecutiveSymbolsInRow(index - index%board.getSideSize(), symbol) == this.winningCondition.getWinningCondition()) return true;
        //index%(board.getSideSize()+1) guarantees that first diagonal is checked from the beginning.
        else if(countConsecutiveSymbolsInFirstDiagonal(index%(board.getSideSize()+1), symbol) == this.winningCondition.getWinningCondition()) return true;
        //index%(board.getSideSize()-1) guarantees that first diagonal is checked from the beginning.
        //If diagonal is main diagonal therefore index%(board.getSideSize()-1) == 0 and checking will begin at index = 0 which does not belong to this diagonal.
        else if(countConsecutiveSymbolsInSecondDiagonal((index%(board.getSideSize()-1)==0) ? board.getSideSize()-1 : index%(board.getSideSize()-1), symbol) == this.winningCondition.getWinningCondition()) return true;
        return false;
    }

    private int countConsecutiveSymbolsInColumn(int index, Symbol symbol) {
        //index + board.getSideSize() is a location of next slot in the column, eg. for first columne of 3x3 board 0, 3, 6
        if(index + board.getSideSize() >= board.getNumberOfSlots())
            return recursionBaseCaseResult(index, symbol);
        if(board.getSymbol(index).equals(symbol))
            return 1 + countConsecutiveSymbolsInColumn(index+board.getSideSize(), symbol);
        return countConsecutiveSymbolsInColumn(index+board.getSideSize(), symbol);
    }

    private int countConsecutiveSymbolsInRow(int index, Symbol symbol) {
        //index+1 is a location of next slot in the row, eg. for first row of 3x3 board 0, 1, 2
        if((index+1)%board.getSideSize() == 0)
            return recursionBaseCaseResult(index, symbol);
        if(board.getSymbol(index).equals(symbol))
            return 1 + countConsecutiveSymbolsInRow(index+1, symbol);
        return countConsecutiveSymbolsInRow(index+1, symbol);
    }

    //first diagonal is one going from up-left side of the board to bottom-right
    private int countConsecutiveSymbolsInFirstDiagonal(int index, Symbol symbol) {
        //index+board.getSideSize()+1 is a location of next slot in the diagonal, eg. for 3x3 board 0, 4, 8
        if(index+board.getSideSize()+1 >= board.getNumberOfSlots())
            return recursionBaseCaseResult(index, symbol);
        if(board.getSymbol(index).equals(symbol))
            return 1 + countConsecutiveSymbolsInFirstDiagonal(index+board.getSideSize()+1, symbol);
        return countConsecutiveSymbolsInFirstDiagonal(index+board.getSideSize()+1, symbol);
    }

    //second diagonal is one going from up-right side of the board to bottom-left
    private int countConsecutiveSymbolsInSecondDiagonal(int index, Symbol symbol) {
        //index+board.getSideSize()-1 is a location of next slot in the diagonal, eg. for 3x3 board 2, 4, 6
        if(index+board.getSideSize()-1 >= board.getNumberOfSlots())
            return recursionBaseCaseResult(index, symbol);
        if(board.getSymbol(index).equals(symbol))
            return 1 + countConsecutiveSymbolsInSecondDiagonal(index+board.getSideSize()-1, symbol);
        return countConsecutiveSymbolsInSecondDiagonal(index+board.getSideSize()-1, symbol);
    }

    private int recursionBaseCaseResult(int index, Symbol symbol){
        if (board.getSymbol(index).equals(symbol))
            return 1;
        return 0;
    }
}
