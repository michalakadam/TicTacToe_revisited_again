package pl.michalak.adam.output;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger logger = LogManager.getLogger("Output");

    void print(String message) {
        printStream.print(message);
        logger.info(message);
    }

    void println(String message) {
        printStream.println(message);
        logger.info(message);
    }

    void printFromResourceBundle(String message) {
        printStream.print(languageBundle.getString(message));
        logger.info(languageBundle.getString(message));
    }

    void printFromResourceBundleWithFormatting(String message, Object[] params){
        printStream.print(MessageFormat.format(languageBundle.getString(message), params));
        logger.info(MessageFormat.format(languageBundle.getString(message), params));
    }

    void printFromResourceBundleAndAddNextLine(String message){
        printStream.println(languageBundle.getString(message));
        logger.info(languageBundle.getString(message));
    }

    void reloadResourceBundle(){
        this.languageBundle = ResourceBundle.getBundle("Language");
    }
}
