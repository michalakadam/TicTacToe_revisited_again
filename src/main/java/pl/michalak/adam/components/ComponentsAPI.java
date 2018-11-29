package pl.michalak.adam.components;

import java.util.List;

public class ComponentsAPI {
    private final Board board;

    public ComponentsAPI(int boardSize){
        this.board = new Board(boardSize);
    }

    public void addSymbolOnBoardAtSlot(int index, Symbol symbol){
        board.addSymbolAtSlot(index, symbol);
    }

    public Symbol getSymbolFromSlot(int index) {
        return board.getSymbol(index);
    }

    public int getBoardSideSize(){
        return board.getSideSize();
    }

    public int getNumberOfSlotsOnBoard(){
        return board.getNumberOfSlots();
    }

    public List<Symbol> getBoardWithPieces(){
        return board.getWholeBoard();
    }
}
