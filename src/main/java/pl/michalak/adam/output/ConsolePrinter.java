package pl.michalak.adam.output;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.ResourceBundle;

class ConsolePrinter {
    private final PrintStream printStream;
    private ResourceBundle languageBundle;

    ConsolePrinter(PrintStream printStream) {
        this.printStream = printStream;
        this.languageBundle = ResourceBundle.getBundle("Language");
    }


    void print(String message) {
        printStream.print(message);
    }

    void println(String message) {
        printStream.println(message);
    }

    void printFromResourceBundle(String message) { printStream.print(languageBundle.getString(message));
    }

    void printFromResourceBundleWithFormatting(String message, Object[] params){
        printStream.print(MessageFormat.format(languageBundle.getString(message), params));
    }

    void printFromResourceBundleAndAddNextLine(String message){
        printStream.println(languageBundle.getString(message));
    }

    void reloadResourceBundle(){
        this.languageBundle = ResourceBundle.getBundle("Language");
    }
}
