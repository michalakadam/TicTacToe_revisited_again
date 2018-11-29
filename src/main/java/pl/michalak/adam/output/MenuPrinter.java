package pl.michalak.adam.output;

class MenuPrinter {
    private final ConsolePrinter consolePrinter;

    MenuPrinter(ConsolePrinter consolePrinter){
        this.consolePrinter = consolePrinter;
    }

    void printMainMenu(){
        consolePrinter.print("\n");
        consolePrinter.printFromResourceBundleAndAddNextLine("gameName");
        consolePrinter.println("\t=======================");
        consolePrinter.printFromResourceBundleAndAddNextLine("choosePlay");
        consolePrinter.printFromResourceBundleAndAddNextLine("namePlayers");
        consolePrinter.printFromResourceBundleAndAddNextLine("changeGameSettings");
        consolePrinter.printFromResourceBundleAndAddNextLine("changeLanguage");
        consolePrinter.print("\n");
    }

    public void printLanguageMenu() {
        consolePrinter.print("\n");
        consolePrinter.printFromResourceBundleAndAddNextLine("languageMenuTitleAndLanguages");
        consolePrinter.printFromResourceBundleAndAddNextLine("goBackToMainMenu");
        consolePrinter.print("\n");
    }

    public void printGameSettingsMenu() {
        consolePrinter.print("\n");
        consolePrinter.printFromResourceBundleAndAddNextLine("settingsMenuTitle");
        consolePrinter.printFromResourceBundleAndAddNextLine("boardSizeSet");
        consolePrinter.printFromResourceBundleAndAddNextLine("winningConditionSet");
        consolePrinter.printFromResourceBundleAndAddNextLine("goBackToMainMenu");
        consolePrinter.print("\n");
    }

    public void printPlayersNamesMenu() {
        consolePrinter.print("\n");
        consolePrinter.printFromResourceBundleAndAddNextLine("playersNamesMenuTitle");
        consolePrinter.printFromResourceBundleAndAddNextLine("playerOneName");
        consolePrinter.printFromResourceBundleAndAddNextLine("playerTwoName");
        consolePrinter.printFromResourceBundleAndAddNextLine("goBackToMainMenu");
        consolePrinter.print("\n");
    }
}
