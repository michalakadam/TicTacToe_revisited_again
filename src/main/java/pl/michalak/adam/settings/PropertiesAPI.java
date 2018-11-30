package pl.michalak.adam.settings;
/**
 * This public API handles game properties
 * like locale, size of the board and winning condition.
 * <p>
 * Locale is required when language of the program is set.
 * Then resource bundle is initialized with the new default locale.
 * Winning condition is length of the winning sequence required to win the game.
 * Board size is length of the board side on which the game will be played.
 * <p/>
 * @author Adam Michalak
*/
public class PropertiesAPI {
    private final WinningCondition winningCondition;
    private final LocaleWrapper localeWrapper;
    private final BoardSize boardSize;

    public PropertiesAPI(){
        this.boardSize = new BoardSize(DefaultGameProperties.BOARDSIZE.getValue());
        this.winningCondition = new WinningCondition(DefaultGameProperties.WINNINGCONDITION.getValue());
        this.localeWrapper = new LocaleWrapper();
    }

    /**
     * This method sets new locale as default.
     * This happens when resource bundle is initialized
     * or language is changed during the intro game phase.
     *
     * @param localeCode1 is a String representation of a language code.
     * @param localeCode2 is a String representation of a country code
    */
    public void setLocale(String localeCode1, String localeCode2){
        localeWrapper.setLocale(localeCode1, localeCode2);
    }

    public void setWinningConditionForThisGame(int winningConditionValue) { winningCondition.setWinningCondition(winningConditionValue);}

    public int getWinningConditionForThisGame(){
        return winningCondition.getWinningCondition();
    }

    public void setBoardSizeForThisGame(int boardSizeValue) { boardSize.setBoardSize(boardSizeValue);}

    public int getBoardSizeForThisGame(){ return boardSize.getBoardSize();}


}
