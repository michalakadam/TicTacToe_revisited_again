package pl.michalak.adam;

import java.util.ArrayList;
import java.util.List;

class Board {
    private List<Symbol> piecesOnBoard;

    Board(int boardSize){
        this.piecesOnBoard = new ArrayList<>();
        //boardSize is length of board in one dimension
        for(int i = 0; i < boardSize*boardSize; i++){
            piecesOnBoard.add(Symbol.EMPTY);
        }
    }

    void addSymbolAtSlot(int index, Symbol symbol) {
        if (canSymbolBeAddedAtSlot(index))
            piecesOnBoard.set(index, symbol);
    }

    private boolean canSymbolBeAddedAtSlot(int index) {
        //if slot is empty then return true
        return (piecesOnBoard.get(index).equals(Symbol.EMPTY));
    }

    Symbol getSymbol(int index) {
        return this.piecesOnBoard.get(index);
    }


}
