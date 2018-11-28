package pl.michalak.adam.settings;

public class PropertiesAPI {
    WinningCondition winningCondition;

    public PropertiesAPI(int winningCondition){
        this.winningCondition = new WinningCondition(winningCondition);
    }

    public int getWinningConditionForThisGame(){
        return winningCondition.getWinningCondition();
    }
}
