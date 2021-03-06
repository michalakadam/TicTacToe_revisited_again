package pl.michalak.adam.assessment;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.michalak.adam.components.ComponentsAPI;
import pl.michalak.adam.components.Symbol;
import pl.michalak.adam.exceptions.SlotIsFilledException;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AssessmentAPITest {
    private ComponentsAPI componentsAPI;
    private AssessmentAPI assessmentAPI;

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
        assessmentAPI = new AssessmentAPI(componentsAPI, boardSize);
        //then
        assertFalse(assessmentAPI.checkForWinner(columnStartingIndex, Symbol.X), whyItFailed);
    }

    @Test (dataProvider = "columnsStartingIndexes")
    public void almostFullColumnShouldNotWin(int columnStartingIndex){
        //given
        String whyItFailed = "checkForWinner found not full column to be sufficient winning condition";
        int boardSize = 4;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 3;
        assessmentAPI = new AssessmentAPI(componentsAPI, winningCondition);
        //when
        try {
            componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex+componentsAPI.getBoardSideSize(), Symbol.O);
            componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex+2*componentsAPI.getBoardSideSize(), Symbol.X);
        } catch (SlotIsFilledException e) {
            e.printStackTrace();
        }
        //then
        assertFalse(assessmentAPI.checkForWinner(columnStartingIndex, Symbol.X), whyItFailed);
    }

    @Test (dataProvider = "columnsStartingIndexes")
    public void fullColumnShouldWin(int columnStartingIndex){
        //given
        String whyItFailed = "checkForWinner found full column to be insufficient winning condition";
        int boardSize = 4;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 3;
        assessmentAPI = new AssessmentAPI(componentsAPI, winningCondition);
        //when
        try {
            componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex+componentsAPI.getBoardSideSize(), Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(columnStartingIndex+2*componentsAPI.getBoardSideSize(), Symbol.X);
        } catch (SlotIsFilledException e) {
            e.printStackTrace();
        }
        //then

        assertTrue(assessmentAPI.checkForWinner(columnStartingIndex-1, Symbol.X), whyItFailed);
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
        String whyItFailed = "checkForWinner found not full raw to be sufficient winning condition";
        int boardSize = 6;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 3;
        assessmentAPI = new AssessmentAPI(componentsAPI, winningCondition);
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
        assertFalse(assessmentAPI.checkForWinner(rowStartingIndex-1, Symbol.O), whyItFailed);
    }

    @Test(dataProvider = "rowsStartingIndexes")
    public void fullRowShouldWin(int rowStartingIndex){
        //given
        String whyItFailed = "checkForWinner found full raw to be insufficient winning condition";
        int boardSize = 6;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 4;
        assessmentAPI = new AssessmentAPI(componentsAPI, winningCondition);
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
        assertTrue(assessmentAPI.checkForWinner(rowStartingIndex-1, Symbol.O), whyItFailed);
    }

    @Test
    public void firstDiagonalNotFullShouldNotWin(){
        //given
        String whyItFailed = "checkForWinner found not full first diagonal to be sufficient winning condition";
        int boardSize = 3;
        componentsAPI = new ComponentsAPI(boardSize);
        assessmentAPI = new AssessmentAPI(componentsAPI, boardSize);
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
        assertFalse(assessmentAPI.checkForWinner(4, Symbol.X), whyItFailed);
    }

    @Test
    public void firstDiagonalFullShouldWin(){
        //given
        String whyItFailed = "checkForWinner found full first diagonal not to be sufficient winning condition";
        int boardSize = 7;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 4;
        assessmentAPI = new AssessmentAPI(componentsAPI, winningCondition);
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
        assertTrue(assessmentAPI.checkForWinner(16, Symbol.X), whyItFailed);
    }

    @Test
    public void nonStandardFirstDiagonalSatisfyingWinningConditionShouldWin(){
        //given
        String whyItFailed = "checkForWinner found sufficiently full first diagonal not to be sufficient winning condition";
        int boardSize = 7;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 5;
        assessmentAPI = new AssessmentAPI(componentsAPI, winningCondition);
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
        assertTrue(assessmentAPI.checkForWinner(26, Symbol.X), whyItFailed);
    }

    @Test
    public void secondDiagonalNotFullShouldNotWin(){
        //given
        String whyItFailed = "checkForWinner found not full second diagonal to be sufficient winning condition";
        int boardSize = 3;
        componentsAPI = new ComponentsAPI(boardSize);
        assessmentAPI = new AssessmentAPI(componentsAPI, boardSize);
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
        assertFalse(assessmentAPI.checkForWinner(4, Symbol.X), whyItFailed);
    }

    @Test
    public void secondDiagonalFullShouldWin(){
        //given
        String whyItFailed = "checkForWinner found full second diagonal not to be sufficient winning condition";
        int boardSize = 3;
        componentsAPI = new ComponentsAPI(boardSize);
        assessmentAPI = new AssessmentAPI(componentsAPI, boardSize);
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
        assertTrue(assessmentAPI.checkForWinner(6, Symbol.O), whyItFailed);
    }

    @Test
    public void nonStandardSecondDiagonalSatisfyingWinningConditionShouldWin(){
        //given
        String whyItFailed = "checkForWinner found sufficiently full second diagonal not to be sufficient winning condition";
        int boardSize = 9;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 4;
        assessmentAPI = new AssessmentAPI(componentsAPI, winningCondition);
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
        assertTrue(assessmentAPI.checkForWinner(30, Symbol.O), whyItFailed);
    }

    @Test
    public void consecutiveSlotsFilledShouldNotWin(){
        //given
        String whyItFailed = "wasn't implemented: consecutiveSlotsFilledShouldNotWin";
        int boardSize = 3;
        componentsAPI = new ComponentsAPI(boardSize);
        int winningCondition = 3;
        assessmentAPI = new AssessmentAPI(componentsAPI, winningCondition);
        //when
        try{
            componentsAPI.addSymbolOnBoardAtSlot(1, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(5, Symbol.X);
            componentsAPI.addSymbolOnBoardAtSlot(3, Symbol.X);

        }
        catch(SlotIsFilledException e){
            e.printStackTrace();
        }
        //then
        assertFalse(assessmentAPI.checkForWinner(2, Symbol.X), whyItFailed);
    }

}
