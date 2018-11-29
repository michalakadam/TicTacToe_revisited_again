package pl.michalak.adam.settings;

public class PropertiesAPI {
    private final WinningCondition winningCondition;
    private final LocaleWrapper localeWrapper;

    public PropertiesAPI(int winningCondition){
        this.winningCondition = new WinningCondition(winningCondition);
        this.localeWrapper = new LocaleWrapper();
    }

    public void setLocale(String localeCode1, String localeCode2){
        localeWrapper.setLocale(localeCode1, localeCode2);
    }
    public int getWinningConditionForThisGame(){
        return winningCondition.getWinningCondition();
    }
}
