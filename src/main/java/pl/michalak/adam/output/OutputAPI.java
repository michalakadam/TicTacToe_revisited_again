package pl.michalak.adam.output;

import pl.michalak.adam.components.ComponentsAPI;

import java.io.PrintStream;

public class OutputAPI {
    private final ConsolePrinter consolePrinter;
    private final MenuPrinter menuPrinter;
    private final ErrorPrinter errorPrinter;
    private BoardPrinter boardPrinter;

    public OutputAPI(PrintStream printStream){
        this.consolePrinter = new ConsolePrinter(printStream);
        this.menuPrinter = new MenuPrinter(consolePrinter);
        this.errorPrinter  = new ErrorPrinter(new PrintStream(System.err));
    }

    public void print(String message) { consolePrinter.print(message);}

    public void printFromResourceBundle(String message) {
        consolePrinter.printFromResourceBundle(message);
    }

    public void printFromResourceBundleAndAddNextLine(String message) {consolePrinter.printFromResourceBundleAndAddNextLine(message);}

    public void printFromResourceBundleWithFormatting(String message, Object... params) {
        consolePrinter.printFromResourceBundleWithFormatting(message, params);
    }

    public void printErrorFromResourceBundle(String errorMessage){ errorPrinter.printFromResourceBundle(errorMessage);}

    public void printErrorFromResourceBundleAndAddNextLine(String errorMessage){ errorPrinter.printFromResourceBundleAndAddNextLine(errorMessage);}

    public void printErrorFromResourceBundleWithFormatting(String errorMessage, Object... params){errorPrinter.printFromResourceBundleWithFormatting(errorMessage, params);}
    public void printMainMenu(){
        menuPrinter.printMainMenu();
    }

    public void printPlayersNamesMenu() { menuPrinter.printPlayersNamesMenu(); }

    public void printGameSettingsMenu() { menuPrinter.printGameSettingsMenu(); }

    public void printLanguageMenu() { menuPrinter.printLanguageMenu();}

    public void reloadResourceBundleAfterLocaleChange(){
        consolePrinter.reloadResourceBundle();
    }

    public void setBoardPrinter(ComponentsAPI componentsAPI) { this.boardPrinter = new BoardPrinter(componentsAPI);}

    public void printBoard() { consolePrinter.print(boardPrinter.boardToString()); }
}
