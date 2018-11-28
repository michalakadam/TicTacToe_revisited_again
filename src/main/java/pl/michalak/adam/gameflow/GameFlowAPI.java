package pl.michalak.adam.gameflow;

import pl.michalak.adam.output.OutputAPI;
import pl.michalak.adam.settings.PropertiesAPI;

import java.io.OutputStream;
import java.io.PrintStream;


public class GameFlowAPI {
    PropertiesAPI propertiesAPI;
    OutputAPI outputAPI;

    public GameFlowAPI(){
        propertiesAPI = new PropertiesAPI(3);
        outputAPI = new OutputAPI(new PrintStream(System.out));
    }
    public void start() {
        Introduction introduction = new Introduction(outputAPI, propertiesAPI);
        introduction.begin();


    }

}
