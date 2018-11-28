package pl.michalak.adam.output;

import java.io.PrintStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class OutputAPI {
    private final Display display;
    private final ResourceBundle languageBundle;

    public OutputAPI(PrintStream printStream) {
        this.display = new Display(printStream);
        Locale.setDefault(new Locale("pl"));// TODO: 28.11.18 MOVE THIS LINE TO SETTINGS PACKAGE
        this.languageBundle = ResourceBundle.getBundle("Language");

    }

    public void print(String message) {
        display.print(languageBundle.getString(message));
    }
}
