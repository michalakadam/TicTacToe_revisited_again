package pl.michalak.adam;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class BoardTest {
    Board board;

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
        assertEquals(board.getSymbol(index), Symbol.EMPTY);
    }


    @Test(dataProvider = "slotsOnBoard")
    public void addXSymbolAtAllSlots(int index) {
        //given
        String whyItFailed = "Symbol at the given slot is not X or equals method failed";
        //when
        board.addSymbolAtSlot(index, Symbol.X);
        //then
        assertEquals(board.getSymbol(index), Symbol.X);
    }

    @Test(dataProvider = "slotsOnBoard")
    public void addOSymbolAtAllSlots(int index) {
        //given
        String whyItFailed = "Symbol at the given slot is not O or equals method failed";
        //when
        board.addSymbolAtSlot(index, Symbol.O);
        //then
        assertEquals(board.getSymbol(index), Symbol.O);
    }

    @Test
    public void shouldNotAddPieceAtOccupiedSpot() {
        //given
        String whyItFailed = "Piece was added at slot even though it was occupied";
        //when
        board.addSymbolAtSlot(0, Symbol.O);
        board.addSymbolAtSlot(0, Symbol.X);
        //then
        assertEquals(board.getSymbol(0), Symbol.O);
    }
}
