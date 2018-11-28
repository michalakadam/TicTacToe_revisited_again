package pl.michalak.adam.output;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class OutputAPI {
    private final Display display;
    private final ResourceBundle languageBundle;

    public OutputAPI(PrintStream printStream) {
        this.display = new Display(printStream);
        this.languageBundle = ResourceBundle.getBundle("Language");

    }

    public void print(String message) { display.print(message);}

    public void printFromResourceBundle(String message) {
        display.print(languageBundle.getString(message));
    }

    public void printFromResourceBundleWithFormatting(String message, Object... params) {
        display.print(MessageFormat.format(languageBundle.getString(message), params));
    }
}
