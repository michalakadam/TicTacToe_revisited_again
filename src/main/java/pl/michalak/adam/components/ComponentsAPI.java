package pl.michalak.adam.components;
import pl.michalak.adam.exceptions.SlotIsFilledException;
import java.util.List;

/**
 * ComponentsAPI is responsible for handling tic tac toe
 * specific components like board and game pieces.
 * @author Adam Michalak
*/

public class ComponentsAPI {
    private final Board board;
    private int lastMove;

    public ComponentsAPI(int boardSize){
        this.board = new Board(boardSize);
        this.lastMove = 0;
    }


    /**
     * This method adds tic tac toe piece on a specified slot.
     * The exception is thrown where the slot specified by index is already occuppied.
     * Additionally this move is saved as this class' field.
     * It allows AssessmentAPI to work more effectively as it checks only
     * columns, rows and diagonals that consist of the slot that was occupied in last move.
     * @throws SlotIsFilledException when the slot specified by index is already occupied.
     * @param index is location of the slot.
     * @param symbol is a player's specific piece that is going to be placed on the board
    */
    public void addSymbolOnBoardAtSlot(int index, Symbol symbol) throws SlotIsFilledException {
        board.addSymbolAtSlot(index-1, symbol);
        saveLastMove(index-1);
    }

    public Symbol getSymbolFromSlot(int index) {
        return board.getSymbol(index);
    }

    /**
     * This method helps to determine the size of the board.
     * For example if the board's side size is 3 than the board dimensions are 3x3.
     * @return board's side size as integer
    */
    public int getBoardSideSize(){
        return board.getSideSize();
    }

    /**
     * This method helps to determine the amount of slots on the board.
     * For example if the board's dimensions are 4x4 that it has 16 slots.
     * @return total number of slots on the board
     */
    public int getNumberOfSlotsOnBoard(){
        return board.getNumberOfSlots();
    }

    private void saveLastMove(int index){
        this.lastMove = index;
    }

    public int getLastMove(){
        return this.lastMove;
    }
}
