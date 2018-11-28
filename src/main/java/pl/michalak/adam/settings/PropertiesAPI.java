package pl.michalak.adam.settings;

import java.util.Locale;

public class PropertiesAPI {
    private final WinningCondition winningCondition;

    public PropertiesAPI(int winningCondition){
        this.winningCondition = new WinningCondition(winningCondition);
    }

    public void setLocale(String localeCode){
        Locale.setDefault(new Locale(localeCode));
    }
    public int getWinningConditionForThisGame(){
        return winningCondition.getWinningCondition();
    }
}
