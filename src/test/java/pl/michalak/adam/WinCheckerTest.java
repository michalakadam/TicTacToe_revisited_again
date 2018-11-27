package pl.michalak.adam;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class WinCheckerTest {
    Board board;
    WinningCondition winningCondition;
    WinChecker winChecker;

    @DataProvider
    public static Object[] columnsStartingIndexes(){
        return new Object[]{
                0, 1, 2
        };
    }

    @Test(dataProvider = "columnsStartingIndexes")
    public void emptyBoardShouldNotWin(int columnStartingIndex){
        //given
        String whyItFailed = "apparently win checker returned true even though board is empty";
        int boardSize = 7;
        board = new Board(boardSize);
        winningCondition = new WinningCondition(boardSize);
        winChecker = new WinChecker(board, winningCondition);
        //then
        assertFalse(winChecker.checkWin(columnStartingIndex, Symbol.X), whyItFailed);
    }

    @Test (dataProvider = "columnsStartingIndexes")
    public void almostFullColumnShouldNotWin(int columnStartingIndex){
        //given
        String whyItFailed = "checkWin found not full column to be sufficient winning condition";
        int boardSize = 4;
        board = new Board(boardSize);
        winningCondition = new WinningCondition(3);
        winChecker = new WinChecker(board, winningCondition);
        //when
        board.addSymbolAtSlot(columnStartingIndex, Symbol.X);
        board.addSymbolAtSlot(columnStartingIndex+board.getSideSize(), Symbol.O);
        board.addSymbolAtSlot(columnStartingIndex+2*board.getSideSize(), Symbol.X);
        //then
        assertFalse(winChecker.checkWin(columnStartingIndex, Symbol.X), whyItFailed);
    }

    @Test (dataProvider = "columnsStartingIndexes")
    public void fullColumnShouldWin(int columnStartingIndex){
        //given
        String whyItFailed = "checkWin found full column to be insufficient winning condition";
        int boardSize = 4;
        board = new Board(boardSize);
        winningCondition = new WinningCondition(3);
        winChecker = new WinChecker(board, winningCondition);
        //when
        board.addSymbolAtSlot(columnStartingIndex, Symbol.X);
        board.addSymbolAtSlot(columnStartingIndex+board.getSideSize(), Symbol.X);
        board.addSymbolAtSlot(columnStartingIndex+2*board.getSideSize(), Symbol.X);
        //then
        assertTrue(winChecker.checkWin(columnStartingIndex, Symbol.X), whyItFailed);
    }

    @Test
    public void threeInAColumnShouldWinOnBigBoard(){

    }
    
    @DataProvider
    public static Object[] rowsStartingIndexes(){
        return new Object[]{
                0, 6, 12
        };
    }
    
    @Test(dataProvider = "rowsStartingIndexes")
    public void rowAlmostFullShouldNotWin(int rowStartingIndex){
        //given
        String whyItFailed = "checkWin found not full raw to be sufficient winning condition";
        int boardSize = 6;
        board = new Board(boardSize);
        winningCondition = new WinningCondition(3);
        winChecker = new WinChecker(board, winningCondition);
        //when
        board.addSymbolAtSlot(rowStartingIndex, Symbol.O);
        board.addSymbolAtSlot(rowStartingIndex+1, Symbol.O);
        board.addSymbolAtSlot(rowStartingIndex+2, Symbol.X);
        //then
        assertFalse(winChecker.checkWin(rowStartingIndex, Symbol.O), whyItFailed);
    }

    @Test(dataProvider = "rowsStartingIndexes")
    public void fullRowShouldWin(int rowStartingIndex){
        //given
        String whyItFailed = "checkWin found full raw to be insufficient winning condition";
        int boardSize = 6;
        board = new Board(boardSize);
        winningCondition = new WinningCondition(4);
        winChecker = new WinChecker(board, winningCondition);
        //when
        board.addSymbolAtSlot(rowStartingIndex, Symbol.O);
        board.addSymbolAtSlot(rowStartingIndex+1, Symbol.O);
        board.addSymbolAtSlot(rowStartingIndex+2, Symbol.O);
        board.addSymbolAtSlot(rowStartingIndex+3, Symbol.O);
        //then
        assertTrue(winChecker.checkWin(rowStartingIndex, Symbol.O), whyItFailed);
    }

    @Test
    public void firstDiagonalNotFullShouldNotWin(){
        //given
        String whyItFailed = "checkWin found not full first diagonal to be sufficient winning condition";
        int boardSize = 3;
        board = new Board(boardSize);
        winningCondition = new WinningCondition(boardSize);
        winChecker = new WinChecker(board, winningCondition);
        //when
        board.addSymbolAtSlot(0, Symbol.X);
        board.addSymbolAtSlot(4, Symbol.O);
        board.addSymbolAtSlot(8, Symbol.X);
        //then
        assertFalse(winChecker.checkWin(4, Symbol.X), whyItFailed);
    }

    @Test
    public void firstDiagonalFullShouldWin(){
        //given
        String whyItFailed = "checkWin found full first diagonal not to be sufficient winning condition";
        int boardSize = 7;
        board = new Board(boardSize);
        winningCondition = new WinningCondition(4);
        winChecker = new WinChecker(board, winningCondition);
        //when
        board.addSymbolAtSlot(0, Symbol.X);
        board.addSymbolAtSlot(8, Symbol.X);
        board.addSymbolAtSlot(16, Symbol.X);
        board.addSymbolAtSlot(24, Symbol.X);
        //then
        assertTrue(winChecker.checkWin(16, Symbol.X), whyItFailed);
    }

    @Test
    public void nonStandardFirstDiagonalSatisfyingWinningConditionShouldWin(){
        //given
        String whyItFailed = "checkWin found sufficiently full first diagonal not to be sufficient winning condition";
        int boardSize = 7;
        board = new Board(boardSize);
        winningCondition = new WinningCondition(5);
        winChecker = new WinChecker(board, winningCondition);
        //when
        board.addSymbolAtSlot(10, Symbol.X);
        board.addSymbolAtSlot(18, Symbol.X);
        board.addSymbolAtSlot(26, Symbol.X);
        board.addSymbolAtSlot(34, Symbol.X);
        board.addSymbolAtSlot(42, Symbol.X);
        //then
        assertTrue(winChecker.checkWin(26, Symbol.X), whyItFailed);
    }

    @Test
    public void secondDiagonalNotFullShouldNotWin(){
        //given
        String whyItFailed = "checkWin found not full second diagonal to be sufficient winning condition";
        int boardSize = 3;
        board = new Board(boardSize);
        winningCondition = new WinningCondition(boardSize);
        winChecker = new WinChecker(board, winningCondition);
        //when
        board.addSymbolAtSlot(2, Symbol.X);
        board.addSymbolAtSlot(4, Symbol.O);
        board.addSymbolAtSlot(6, Symbol.O);
        //then
        assertFalse(winChecker.checkWin(4, Symbol.X), whyItFailed);
    }

    @Test
    public void secondDiagonalFullShouldWin(){
        //given
        String whyItFailed = "checkWin found full second diagonal not to be sufficient winning condition";
        int boardSize = 3;
        board = new Board(boardSize);
        winningCondition = new WinningCondition(boardSize);
        winChecker = new WinChecker(board, winningCondition);
        //when
        board.addSymbolAtSlot(2, Symbol.O);
        board.addSymbolAtSlot(4, Symbol.O);
        board.addSymbolAtSlot(6, Symbol.O);
        //then
        assertTrue(winChecker.checkWin(6, Symbol.O), whyItFailed);
    }

    @Test
    public void nonStandardSecondDiagonalSatisfyingWinningConditionShouldWin(){
        //given
        String whyItFailed = "checkWin found sufficiently full second diagonal not to be sufficient winning condition";
        int boardSize = 9;
        board = new Board(boardSize);
        winningCondition = new WinningCondition(4);
        winChecker = new WinChecker(board, winningCondition);
        //when
        board.addSymbolAtSlot(6, Symbol.O);
        board.addSymbolAtSlot(14, Symbol.O);
        board.addSymbolAtSlot(22, Symbol.O);
        board.addSymbolAtSlot(30, Symbol.O);
        //then
        assertTrue(winChecker.checkWin(30, Symbol.O), whyItFailed);
    }

}
