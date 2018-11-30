package pl.michalak.adam.components;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.michalak.adam.exceptions.SlotIsFilledException;

import static org.testng.Assert.assertEquals;

public class BoardTest {
    private Board board;

    @BeforeMethod
    public void setUp(){
        int boardSize = 3;
        board = new Board(boardSize);
    }

    @DataProvider
    public static Object[] slotsOnBoard(){
        return new Object[]{
                0, 1, 2, 3, 4, 5, 6, 7, 8
        };
    }

    @Test(dataProvider = "slotsOnBoard")
    public void initializeBoardWithEmptySlots(int index){
        //given
        String whyItFailed = "One or more of the slots is not empty.";
        //then
        Assert.assertEquals(board.getSymbol(index), Symbol.EMPTY, whyItFailed);
    }

    @Test(dataProvider = "slotsOnBoard")
    public void countBoardSideSize(int index){
        //given
        String whyItFailed = "One or more of the slots is not empty.";
        //then
        assertEquals(board.getSideSize(), 3, whyItFailed);
    }

    @Test(dataProvider = "slotsOnBoard")
    public void addXSymbolAtAllSlots(int index) {
        //given
        String whyItFailed = "Symbol at the given slot is not X or equals method failed";
        //when
        try{
            board.addSymbolAtSlot(index, Symbol.X);
        }
        catch(SlotIsFilledException e){
            e.printStackTrace();
        }
        //then
        assertEquals(board.getSymbol(index), Symbol.X, whyItFailed);
    }

    @Test(dataProvider = "slotsOnBoard")
    public void addOSymbolAtAllSlots(int index) {
        //given
        String whyItFailed = "Symbol at the given slot is not O or equals method failed";
        //when
        try{
            board.addSymbolAtSlot(index, Symbol.O);
        }
        catch(SlotIsFilledException e){
            e.printStackTrace();
        }
        //then
        assertEquals(board.getSymbol(index), Symbol.O, whyItFailed);
    }

    @Test
    public void shouldNotAddPieceAtOccupiedSpot() {
        //given
        String whyItFailed = "Piece was added at slot even though it was occupied";
        //when
        try{
            board.addSymbolAtSlot(0, Symbol.O);
            board.addSymbolAtSlot(0, Symbol.X);
        }
        catch(SlotIsFilledException e){
            e.printStackTrace();
        }
        //then
        assertEquals(board.getSymbol(0), Symbol.O, whyItFailed);
    }
}
