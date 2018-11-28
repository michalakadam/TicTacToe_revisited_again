package pl.michalak.adam.assessment;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.michalak.adam.components.ComponentsAPI;
import pl.michalak.adam.components.Symbol;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class WinCheckerTest {
    private ComponentsAPI componentsAPI;
    private WinChecker winChecker;

    @DataProvider
    public static Object[] columnsStartingIndexes(){
        return new Object[]{
                0, 1, 2
        };
    }

    @Test(dataProvider = "columnsStartingIndexes")
    public void emptycomponentsAPIShouldNotWin(int columnStartingIndex){
        //given
        String whyItFailed = "apparently win checker returned true even though componentsAPI is empty";
        int boardSize = 7;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = boardSize;
        winChecker = new WinChecker(componentsAPI, winningCondition);
        //then
        assertFalse(winChecker.checkWin(columnStartingIndex, Symbol.X), whyItFailed);
    }

    @Test (dataProvider = "columnsStartingIndexes")
    public void almostFullColumnShouldNotWin(int columnStartingIndex){
        //given
        String whyItFailed = "checkWin found not full column to be sufficient winning condition";
        int boardSize = 4;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 3;
        winChecker = new WinChecker(componentsAPI, winningCondition);
        //when
        componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex, Symbol.X);
        componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex+componentsAPI.getBoardSideSize(), Symbol.O);
        componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex+2*componentsAPI.getBoardSideSize(), Symbol.X);
        //then
        assertFalse(winChecker.checkWin(columnStartingIndex, Symbol.X), whyItFailed);
    }

    @Test (dataProvider = "columnsStartingIndexes")
    public void fullColumnShouldWin(int columnStartingIndex){
        //given
        String whyItFailed = "checkWin found full column to be insufficient winning condition";
        int boardSize = 4;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 3;
        winChecker = new WinChecker(componentsAPI, winningCondition);
        //when
        componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex, Symbol.X);
        componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex+componentsAPI.getBoardSideSize(), Symbol.X);
        componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex+2*componentsAPI.getBoardSideSize(), Symbol.X);
        //then
        assertTrue(winChecker.checkWin(columnStartingIndex, Symbol.X), whyItFailed);
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
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 3;
        winChecker = new WinChecker(componentsAPI, winningCondition);
        //when
        componentsAPI.addSymbolOnBoardAtSlot(rowStartingIndex, Symbol.O);
        componentsAPI.addSymbolOnBoardAtSlot(rowStartingIndex+1, Symbol.O);
        componentsAPI.addSymbolOnBoardAtSlot(rowStartingIndex+2, Symbol.X);
        //then
        assertFalse(winChecker.checkWin(rowStartingIndex, Symbol.O), whyItFailed);
    }

    @Test(dataProvider = "rowsStartingIndexes")
    public void fullRowShouldWin(int rowStartingIndex){
        //given
        String whyItFailed = "checkWin found full raw to be insufficient winning condition";
        int boardSize = 6;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 4;
        winChecker = new WinChecker(componentsAPI, winningCondition);
        //when
        componentsAPI.addSymbolOnBoardAtSlot(rowStartingIndex, Symbol.O);
        componentsAPI.addSymbolOnBoardAtSlot(rowStartingIndex+1, Symbol.O);
        componentsAPI.addSymbolOnBoardAtSlot(rowStartingIndex+2, Symbol.O);
        componentsAPI.addSymbolOnBoardAtSlot(rowStartingIndex+3, Symbol.O);
        //then
        assertTrue(winChecker.checkWin(rowStartingIndex, Symbol.O), whyItFailed);
    }

    @Test
    public void firstDiagonalNotFullShouldNotWin(){
        //given
        String whyItFailed = "checkWin found not full first diagonal to be sufficient winning condition";
        int boardSize = 3;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = boardSize;
        winChecker = new WinChecker(componentsAPI, winningCondition);
        //when
        componentsAPI.addSymbolOnBoardAtSlot(0, Symbol.X);
        componentsAPI.addSymbolOnBoardAtSlot(4, Symbol.O);
        componentsAPI.addSymbolOnBoardAtSlot(8, Symbol.X);
        //then
        assertFalse(winChecker.checkWin(4, Symbol.X), whyItFailed);
    }

    @Test
    public void firstDiagonalFullShouldWin(){
        //given
        String whyItFailed = "checkWin found full first diagonal not to be sufficient winning condition";
        int boardSize = 7;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 4;
        winChecker = new WinChecker(componentsAPI, winningCondition);
        //when
        componentsAPI.addSymbolOnBoardAtSlot(0, Symbol.X);
        componentsAPI.addSymbolOnBoardAtSlot(8, Symbol.X);
        componentsAPI.addSymbolOnBoardAtSlot(16, Symbol.X);
        componentsAPI.addSymbolOnBoardAtSlot(24, Symbol.X);
        //then
        assertTrue(winChecker.checkWin(16, Symbol.X), whyItFailed);
    }

    @Test
    public void nonStandardFirstDiagonalSatisfyingWinningConditionShouldWin(){
        //given
        String whyItFailed = "checkWin found sufficiently full first diagonal not to be sufficient winning condition";
        int boardSize = 7;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 5;
        winChecker = new WinChecker(componentsAPI, winningCondition);
        //when
        componentsAPI.addSymbolOnBoardAtSlot(10, Symbol.X);
        componentsAPI.addSymbolOnBoardAtSlot(18, Symbol.X);
        componentsAPI.addSymbolOnBoardAtSlot(26, Symbol.X);
        componentsAPI.addSymbolOnBoardAtSlot(34, Symbol.X);
        componentsAPI.addSymbolOnBoardAtSlot(42, Symbol.X);
        //then
        assertTrue(winChecker.checkWin(26, Symbol.X), whyItFailed);
    }

    @Test
    public void secondDiagonalNotFullShouldNotWin(){
        //given
        String whyItFailed = "checkWin found not full second diagonal to be sufficient winning condition";
        int boardSize = 3;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = boardSize;
        winChecker = new WinChecker(componentsAPI, winningCondition);
        //when
        componentsAPI.addSymbolOnBoardAtSlot(2, Symbol.X);
        componentsAPI.addSymbolOnBoardAtSlot(4, Symbol.O);
        componentsAPI.addSymbolOnBoardAtSlot(6, Symbol.O);
        //then
        assertFalse(winChecker.checkWin(4, Symbol.X), whyItFailed);
    }

    @Test
    public void secondDiagonalFullShouldWin(){
        //given
        String whyItFailed = "checkWin found full second diagonal not to be sufficient winning condition";
        int boardSize = 3;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = boardSize;
        winChecker = new WinChecker(componentsAPI, winningCondition);
        //when
        componentsAPI.addSymbolOnBoardAtSlot(2, Symbol.O);
        componentsAPI.addSymbolOnBoardAtSlot(4, Symbol.O);
        componentsAPI.addSymbolOnBoardAtSlot(6, Symbol.O);
        //then
        assertTrue(winChecker.checkWin(6, Symbol.O), whyItFailed);
    }

    @Test
    public void nonStandardSecondDiagonalSatisfyingWinningConditionShouldWin(){
        //given
        String whyItFailed = "checkWin found sufficiently full second diagonal not to be sufficient winning condition";
        int boardSize = 9;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 4;
        winChecker = new WinChecker(componentsAPI, winningCondition);
        //when
        componentsAPI.addSymbolOnBoardAtSlot(6, Symbol.O);
        componentsAPI.addSymbolOnBoardAtSlot(14, Symbol.O);
        componentsAPI.addSymbolOnBoardAtSlot(22, Symbol.O);
        componentsAPI.addSymbolOnBoardAtSlot(30, Symbol.O);
        //then
        assertTrue(winChecker.checkWin(30, Symbol.O), whyItFailed);
    }

}
