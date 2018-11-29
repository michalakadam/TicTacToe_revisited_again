package pl.michalak.adam.users;

import pl.michalak.adam.exceptions.PlayersNamesAreTheSameException;

public class PlayersAPI {
    private final PlayersList playersList;

    public PlayersAPI(){
        this.playersList = new PlayersList(DefaultPlayersNames.PLAYERONE.getValue(), DefaultPlayersNames.PLAYERTWO.getValue());
    }

    public void setPlayerName(int playerNumber, String playerName) throws PlayersNamesAreTheSameException {
        playersList.setPlayerName(playerNumber, playerName);
    }
}
