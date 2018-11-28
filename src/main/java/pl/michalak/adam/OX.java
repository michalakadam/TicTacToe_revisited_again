package pl.michalak.adam;

import pl.michalak.adam.gameflow.GameFlowAPI;

/**
 * Main class of the program.
 *
 * @author Adam Michalak
 */
public class OX {
public static void main(String[] args) {
        GameFlowAPI gameFlowAPI = new GameFlowAPI();
        gameFlowAPI.start();
    }
}
