package pl.michalak.adam.output;

import java.io.PrintStream;

class Display {
    private final PrintStream printStream;

    Display(PrintStream printStream) {
        this.printStream = printStream;
    }


    void print(String message) {
        printStream.print(message);
    }

    void println(String message) {
        printStream.println(message);
    }
}
