package pl.michalak.adam.settings;

class PropertiesAPI {
    private final WinningCondition winningCondition;

    public PropertiesAPI(int winningCondition){
        this.winningCondition = new WinningCondition(winningCondition);
    }

    public int getWinningConditionForThisGame(){
        return winningCondition.getWinningCondition();
    }
}
