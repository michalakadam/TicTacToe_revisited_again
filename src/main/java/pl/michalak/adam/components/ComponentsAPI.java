package pl.michalak.adam.components;

import java.util.List;

public class ComponentsAPI {
    private final Board board;
    private int lastMove;

    public ComponentsAPI(int boardSize){
        this.board = new Board(boardSize);
        this.lastMove = 0;
    }

    public void addSymbolOnBoardAtSlot(int index, Symbol symbol){
        board.addSymbolAtSlot(index-1, symbol);
        saveLastMove(index-1);
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

    private void saveLastMove(int index){
        this.lastMove = index;
    }

    public int getLastMove(){
        return this.lastMove;
    }
}
