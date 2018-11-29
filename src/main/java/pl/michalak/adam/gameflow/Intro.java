package pl.michalak.adam.gameflow;

import pl.michalak.adam.input.InputAPI;
import pl.michalak.adam.output.OutputAPI;
import pl.michalak.adam.settings.PropertiesAPI;

class Intro {
    OutputAPI outputAPI;
    PropertiesAPI propertiesAPI;
    InputAPI inputAPI;

    Intro(OutputAPI outputAPI, PropertiesAPI propertiesAPI, InputAPI inputAPI) {
        this.outputAPI = outputAPI;
        this.propertiesAPI = propertiesAPI;
        this.inputAPI = inputAPI;
    }
    void beforeGame(){
        mainMenu();
    }

    void mainMenu(){
        outputAPI.printMainMenu();
        int mainMenuDecision = inputAPI.getIntInputFromPlayer("decideInMainMenu", 1, 4);
        /*if(mainMenuDecision == 2)
            namePlayers();
        else if(mainMenuDecision == 3)
            changeSettings();
        */if(mainMenuDecision == 4)
            changeLanguage();
    }

    void changeLanguage(){
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
}
