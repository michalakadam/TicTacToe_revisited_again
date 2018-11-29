package pl.michalak.adam.settings;

class WinningCondition {
    private int winningCondition;

    WinningCondition(int winningCondition){
        this.winningCondition = winningCondition;
    }

    int getWinningCondition(){
        return this.winningCondition;
    }

    public void setWinningCondition(int winningConditionValue) {
        this.winningCondition = winningConditionValue;
    }
}
