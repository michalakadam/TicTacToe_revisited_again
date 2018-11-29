package pl.michalak.adam.output;

import pl.michalak.adam.components.ComponentsAPI;

class MenuPrinter {
    ConsolePrinter consolePrinter;

    MenuPrinter(ConsolePrinter consolePrinter){
        this.consolePrinter = consolePrinter;
    }

    void printMenu(){
        consolePrinter.printFromResourceBundleAndAddNextLine("gameName");
        consolePrinter.println("\t==========================\n");
        consolePrinter.printFromResourceBundleAndAddNextLine("choosePlay");
        consolePrinter.printFromResourceBundleAndAddNextLine("changeSettings");
        consolePrinter.printFromResourceBundleAndAddNextLine("changeLanguage");
        consolePrinter.print("\n");
        consolePrinter.printFromResourceBundle("decideInMenu");

    }
}
