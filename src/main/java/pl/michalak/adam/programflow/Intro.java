package pl.michalak.adam.programflow;

import pl.michalak.adam.exceptions.PlayersNamesAreTheSameException;
import pl.michalak.adam.input.InputAPI;
import pl.michalak.adam.output.OutputAPI;
import pl.michalak.adam.settings.PropertiesAPI;
import pl.michalak.adam.users.PlayersAPI;

class Intro {
    private final OutputAPI outputAPI;
    private final PropertiesAPI propertiesAPI;
    private final InputAPI inputAPI;
    private final PlayersAPI playersAPI;

    Intro(OutputAPI outputAPI, PropertiesAPI propertiesAPI, InputAPI inputAPI, PlayersAPI playersAPI) {
        this.outputAPI = outputAPI;
        this.propertiesAPI = propertiesAPI;
        this.inputAPI = inputAPI;
        this.playersAPI = playersAPI;
    }
    void beforeGame(){
        mainMenu();
    }

    private void mainMenu(){
        outputAPI.printMainMenu();
        int mainMenuDecision = inputAPI.getIntInputFromPlayer("decideInMainMenu", 1, 4);
        if(mainMenuDecision == 2)
            namePlayers();
        else if(mainMenuDecision == 3)
            changeSettings();
        else if(mainMenuDecision == 4)
            changeLanguage();
    }

    private void namePlayers(){
        outputAPI.printPlayersNamesMenu();
        int playersNamesMenuDecision = inputAPI.getIntInputFromPlayer("decideInPlayersNamesMenu", 1, 3);
        if(playersNamesMenuDecision == 1){
           providePlayerNameThatCannotBeTheSame(playersNamesMenuDecision, "giveNameOfFirstPlayer");
        }
        else if(playersNamesMenuDecision == 2){
            providePlayerNameThatCannotBeTheSame(playersNamesMenuDecision, "giveNameOfSecondPlayer");
        }
        mainMenu();
    }

    private void providePlayerNameThatCannotBeTheSame(int playersNamesMenuDecision, String message){
        String playerName = inputAPI.getStringInputFromPlayerWithNoStringsAttached(message);
        try {
            playersAPI.setPlayerName(playersNamesMenuDecision, playerName);
        }
        catch(PlayersNamesAreTheSameException exception){
            outputAPI.printErrorFromResourceBundleAndAddNextLine("playersNamesAreTheSame");
            providePlayerNameThatCannotBeTheSame(playersNamesMenuDecision, message);
        }
    }

    private void changeLanguage(){
        outputAPI.printLanguageMenu();
        int languageMenuDecision = inputAPI.getIntInputFromPlayer("decideInLanguageMenu", 1, 4);
        if (languageMenuDecision == 1) {
            propertiesAPI.setLocale("en", "US");
            outputAPI.reloadResourceBundleAfterLocaleChange();
            outputAPI.printFromResourceBundleAndAddNextLine("languageChangedConfirmation");
        }
        else if(languageMenuDecision == 2) {
            propertiesAPI.setLocale("pl", "PL");
            outputAPI.reloadResourceBundleAfterLocaleChange();
            outputAPI.printFromResourceBundleAndAddNextLine("languageChangedConfirmation");
        }
        mainMenu();
    }

    private void changeSettings(){
        outputAPI.printGameSettingsMenu();
        int gameSettingsMenuDecision = inputAPI.getIntInputFromPlayer("decideInSettingsMenu", 1, 3);
        if(gameSettingsMenuDecision == 1){
            int customBoardSize = inputAPI.getIntInputFromPlayer("getBoardSize", 3, 9);
            propertiesAPI.setBoardSizeForThisGame(customBoardSize);
        }
        else if(gameSettingsMenuDecision == 2) {
            int maxWinningCondition = propertiesAPI.getBoardSizeForThisGame();
            int customWinningCondition = inputAPI.getIntInputFromPlayer("getWinningCondition", 2, maxWinningCondition);
            propertiesAPI.setWinningConditionForThisGame(customWinningCondition);
        }
        mainMenu();
    }
}
