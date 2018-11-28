package pl.michalak.adam.gameflow;

import pl.michalak.adam.output.OutputAPI;
import pl.michalak.adam.settings.PropertiesAPI;

class Introduction {
    OutputAPI outputAPI;
    PropertiesAPI propertiesAPI;
    public Introduction(OutputAPI outputAPI, PropertiesAPI propertiesAPI) {
        this.outputAPI = outputAPI;
        this.propertiesAPI = propertiesAPI;
    }
    public void begin(){
        setGameLanguage();
        outputAPI.printFromResourceBundle("welcome");
    }
    public void setGameLanguage() {
            outputAPI.print("Please select language / Wybierz język: ");
            propertiesAPI.setLocale("pl");
    }
}
