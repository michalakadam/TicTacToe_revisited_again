package pl.michalak.adam.output;

import pl.michalak.adam.components.ComponentsAPI;
import pl.michalak.adam.users.PlayersAPI;
import java.io.PrintStream;

/**
 * OutputAPI handles every object and action related with output.
 * It can print String objects on the console as casual messages or errors.
 * It is responsible for printing various menus.
 * It also provides String objects that represent board and score board.
 *
 * @author Adam Michalak
*/
public class OutputAPI {
    private final ConsolePrinter consolePrinter;
    private final MenuPrinter menuPrinter;
    private final ErrorPrinter errorPrinter;
    private BoardPrinter boardPrinter;
    private ScoreBoardPrinter scoreBoardPrinter;

    public OutputAPI(PrintStream printStream){
        this.consolePrinter = new ConsolePrinter(printStream);
        this.menuPrinter = new MenuPrinter(consolePrinter);
        this.errorPrinter  = new ErrorPrinter(new PrintStream(System.err));
    }


    /**
     * This method uses consolePrinter wrapper to print a String message on the screen.
     * @param message is a String information that is printed on the screen.
    */
    public void print(String message) {
        consolePrinter.print(message);
    }
    
    public void reloadResourceBundleAfterLocaleChange(){
        consolePrinter.reloadResourceBundle();
    }

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

    public void setBoardPrinter(ComponentsAPI componentsAPI) { this.boardPrinter = new BoardPrinter(componentsAPI);}

    public void printBoard() { consolePrinter.print(boardPrinter.boardToString()); }

    public void setScoreBoardPrinter(PlayersAPI playersAPI){
        this.scoreBoardPrinter = new ScoreBoardPrinter(playersAPI);
    }
    public void printScoreBoard() {
        consolePrinter.print(scoreBoardPrinter.printScoreBoard());
    }
}
