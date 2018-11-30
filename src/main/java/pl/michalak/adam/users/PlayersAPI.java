package pl.michalak.adam.users;

import pl.michalak.adam.components.Symbol;
import pl.michalak.adam.exceptions.PlayersNamesAreTheSameException;

/**
 * PlayersAPI is responsible for handling users of the program.
 *
 * @author Adam Michalak
*/
public class PlayersAPI {
    private final PlayersList playersList;

    public PlayersAPI(){
        this.playersList = new PlayersList(DefaultPlayersNames.PLAYERONE.getValue(), DefaultPlayersNames.PLAYERTWO.getValue());
    }


    /**
     * Method that enables changing the player's name.
     *
     * @throws PlayersNamesAreTheSameException when changed player's name is the same as the other player's name.
     * @param playerNumber is a player identifier.
     * @param playerName defines name that is set by this method.
    */
    public void setPlayerName(int playerNumber, String playerName) throws PlayersNamesAreTheSameException {
        playersList.setPlayerName(playerNumber, playerName);
    }

    /**
     * Method that returns playerName specified by playerNumber.
     *
     * @param playerNumber is a player identifier.
     * @return string representation of player's name specified by the playerNumber.
    */
    public String getPlayerName(int playerNumber){
        return playersList.getPlayerName(playerNumber);
    }

    /**
     * Method that returns a symbol that a player plays with.
     *
     * @param playerNumber is a player identifier. Player with playerNumber 1 plays with X, the other one plays with O.
     * @return Symbol that a player specified by the playerNumber is playing with.
     */
    public Symbol getPlayerSymbol(int playerNumber) {
        return playersList.getPlayerSymbol(playerNumber);
    }

    /**
     * Method that adds point(s) to player's score.
     *
     * @param playerNumber is a player identifier.
     * @param pointsAdded specifies amount of points that are going to be added to player's score.
     */
    public void addPointsToPlayersScore(int pointsAdded, int playerNumber) {
        playersList.addPointsToPlayersScore(pointsAdded, playerNumber);
    }

    /**
     * Method that returns amount of points scored by the player.
     *
     * @param playerNumber is a player identifier.
     * @return int that represents amount of points scored by the player.
     */
    public int getPlayersScore(int playerNumber){
        return playersList.getPlayerScore(playerNumber);
    }
}
