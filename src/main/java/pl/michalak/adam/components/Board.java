package pl.michalak.adam.components;

import pl.michalak.adam.exceptions.SlotIsFilledException;

import java.util.ArrayList;
import java.util.List;

class Board {
    private final List<Symbol> piecesOnBoard;

    Board(int boardSize){
        this.piecesOnBoard = new ArrayList<>();
        //boardSize is length of board in one dimension
        for(int i = 0; i < boardSize*boardSize; i++){
            piecesOnBoard.add(Symbol.EMPTY);
        }
    }

    void addSymbolAtSlot(int index, Symbol symbol) throws SlotIsFilledException {
        if (canSymbolBeAddedAtSlot(index))
            piecesOnBoard.set(index, symbol);
        else
            throw new SlotIsFilledException("This slot is already occupied!");
    }

    private boolean canSymbolBeAddedAtSlot(int index) {
        //if slot is empty then return true
        return (piecesOnBoard.get(index).equals(Symbol.EMPTY));
    }

    Symbol getSymbol(int index) {
        return this.piecesOnBoard.get(index);
    }

    int getNumberOfSlots(){
        return this.piecesOnBoard.size();
    }

    int getSideSize(){
        return (int)Math.sqrt(getNumberOfSlots());

    }

    public List<Symbol> getWholeBoard() {
        return this.piecesOnBoard;
    }
}
