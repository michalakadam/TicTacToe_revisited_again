package pl.michalak.adam.assessment;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.michalak.adam.components.ComponentsAPI;
import pl.michalak.adam.components.Symbol;
import pl.michalak.adam.exceptions.SlotIsFilledException;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class WinCheckerTest {
    private ComponentsAPI componentsAPI;
    private WinChecker winChecker;

    @DataProvider
    public static Object[] columnsStartingIndexes(){
        return new Object[]{
                1, 2, 3
        };
    }

    @Test(dataProvider = "columnsStartingIndexes")
    public void emptycomponentsAPIShouldNotWin(int columnStartingIndex){
        //given
        String whyItFailed = "apparently win checker returned true even though componentsAPI is empty";
        int boardSize = 7;
        componentsAPI = new ComponentsAPI(boardSize);
        winChecker = new WinChecker(componentsAPI, boardSize);
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
        try {
            componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex+componentsAPI.getBoardSideSize(), Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex+2*componentsAPI.getBoardSideSize(), Symbol.X);
        } catch (SlotIsFilledException e) {
            e.printStackTrace();
        }
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
        try {
            componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex+componentsAPI.getBoardSideSize(), Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex+2*componentsAPI.getBoardSideSize(), Symbol.X);
        } catch (SlotIsFilledException e) {
            e.printStackTrace();
        }
        //then

        assertTrue(winChecker.checkWin(columnStartingIndex-1, Symbol.X), whyItFailed);
    }
    
    @DataProvider
    public static Object[] rowsStartingIndexes(){
        return new Object[]{
                1, 7, 13
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
        try {
            componentsAPI.addSymbolOnBoardAtSlot(rowStartingIndex, Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(rowStartingIndex + 1, Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(rowStartingIndex + 2, Symbol.X);
        }
        catch(SlotIsFilledException e){
            e.printStackTrace();
        }
        //then
        assertFalse(winChecker.checkWin(rowStartingIndex-1, Symbol.O), whyItFailed);
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
        try{
            componentsAPI.addSymbolOnBoardAtSlot(rowStartingIndex, Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(rowStartingIndex+1, Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(rowStartingIndex+2, Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(rowStartingIndex+3, Symbol.O);
        }
        catch(SlotIsFilledException e){
            e.printStackTrace();
        }
        //then
        assertTrue(winChecker.checkWin(rowStartingIndex-1, Symbol.O), whyItFailed);
    }

    @Test
    public void firstDiagonalNotFullShouldNotWin(){
        //given
        String whyItFailed = "checkWin found not full first diagonal to be sufficient winning condition";
        int boardSize = 3;
        componentsAPI = new ComponentsAPI(boardSize);
        winChecker = new WinChecker(componentsAPI, boardSize);
        //when
        try{
            componentsAPI.addSymbolOnBoardAtSlot(1, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(5, Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(9, Symbol.X);
        }
        catch(SlotIsFilledException e){
            e.printStackTrace();
        }
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
        try{
            componentsAPI.addSymbolOnBoardAtSlot(1, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(9, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(17, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(25, Symbol.X);
        }
        catch(SlotIsFilledException e){
            e.printStackTrace();
        }
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
        try{
            componentsAPI.addSymbolOnBoardAtSlot(11, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(19, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(27, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(35, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(43, Symbol.X);
        }
        catch(SlotIsFilledException e){
            e.printStackTrace();
        }
        //then
        assertTrue(winChecker.checkWin(26, Symbol.X), whyItFailed);
    }

    @Test
    public void secondDiagonalNotFullShouldNotWin(){
        //given
        String whyItFailed = "checkWin found not full second diagonal to be sufficient winning condition";
        int boardSize = 3;
        componentsAPI = new ComponentsAPI(boardSize);
        winChecker = new WinChecker(componentsAPI, boardSize);
        //when
        try{
            componentsAPI.addSymbolOnBoardAtSlot(3, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(5, Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(7, Symbol.O);
        }
        catch(SlotIsFilledException e){
            e.printStackTrace();
        }
        //then
        assertFalse(winChecker.checkWin(4, Symbol.X), whyItFailed);
    }

    @Test
    public void secondDiagonalFullShouldWin(){
        //given
        String whyItFailed = "checkWin found full second diagonal not to be sufficient winning condition";
        int boardSize = 3;
        componentsAPI = new ComponentsAPI(boardSize);
        winChecker = new WinChecker(componentsAPI, boardSize);
        //when
        try{
            componentsAPI.addSymbolOnBoardAtSlot(3, Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(5, Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(7, Symbol.O);
        }
        catch(SlotIsFilledException e){
            e.printStackTrace();
        }
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
        try{
            componentsAPI.addSymbolOnBoardAtSlot(7, Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(15, Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(23, Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(31, Symbol.O);
        }
        catch(SlotIsFilledException e){
            e.printStackTrace();
        }
        //then
        assertTrue(winChecker.checkWin(30, Symbol.O), whyItFailed);
    }

    @Test
    public void consecutiveSlotsFilledShouldNotWin(){
        //given
        String whyItFailed = "wasn't implemented: consecutiveSlotsFilledShouldNotWin";
        int boardSize = 3;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 3;
        winChecker = new WinChecker(componentsAPI, winningCondition);
        //when
        for(int i = 1; i <= 7; i++) {
            try{
                componentsAPI.addSymbolOnBoardAtSlot(i, i%2==0 ? Symbol.X : Symbol.O);
            }
            catch(SlotIsFilledException e){
                e.printStackTrace();
            }
        }
        //then
        assertTrue(winChecker.checkWin(0, Symbol.O), whyItFailed);
    }

}
