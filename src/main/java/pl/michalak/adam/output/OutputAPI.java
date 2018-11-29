package pl.michalak.adam.output;

import java.io.PrintStream;

public class OutputAPI {
    private final ConsolePrinter consolePrinter;
    private final MenuPrinter MenuPrinter;

    public OutputAPI(PrintStream printStream) {
        this.consolePrinter = new ConsolePrinter(printStream);
        this.MenuPrinter = new MenuPrinter(consolePrinter);
    }

    public void print(String message) { consolePrinter.print(message);}

    public void println(String message) { consolePrinter.println(message); }

    public void printFromResourceBundle(String message) {
        consolePrinter.printFromResourceBundle(message);
    }

    public void printFromResourceBundleAndAddNextLine(String message) {consolePrinter.printFromResourceBundleAndAddNextLine(message);}

    public void printFromResourceBundleWithFormatting(String message, Object... params) {
        consolePrinter.printFromResourceBundleWithFormatting(message, params);
    }

    public void printMainMenu(){
        MenuPrinter.printMainMenu();
    }

    public void printLanguageMenu() { MenuPrinter.printLanguageMenu();}

    public void reloadResourceBundleAfterLocaleChange(){
        consolePrinter.reloadResourceBundle();
    }
}
