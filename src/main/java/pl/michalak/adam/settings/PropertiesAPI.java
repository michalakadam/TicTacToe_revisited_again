package pl.michalak.adam.settings;

public class PropertiesAPI {
    private final WinningCondition winningCondition;
    private final LocaleWrapper localeWrapper;
    private final BoardSize boardSize;

    public PropertiesAPI(){
        this.boardSize = new BoardSize(DefaultGameProperties.BOARDSIZE.getValue());
        this.winningCondition = new WinningCondition(DefaultGameProperties.WINNINGCONDITION.getValue());
        this.localeWrapper = new LocaleWrapper();
    }

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
