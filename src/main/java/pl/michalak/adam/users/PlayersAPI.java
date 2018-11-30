package pl.michalak.adam.users;

import pl.michalak.adam.components.Symbol;
import pl.michalak.adam.exceptions.PlayersNamesAreTheSameException;

public class PlayersAPI {
    private final PlayersList playersList;

    public PlayersAPI(){
        this.playersList = new PlayersList(DefaultPlayersNames.PLAYERONE.getValue(), DefaultPlayersNames.PLAYERTWO.getValue());
    }

    public void setPlayerName(int playerNumber, String playerName) throws PlayersNamesAreTheSameException {
        playersList.setPlayerName(playerNumber, playerName);
    }

    public String getPlayerName(int playerNumber){
        return playersList.getPlayerName(playerNumber);
    }

    public Symbol getPlayerSymbol(int playerNumber) {
        return playersList.getPlayerSymbol(playerNumber);
    }
}
