package pl.michalak.adam.users;

public class PlayersAPI {
    PlayersList playersList;

    public PlayersAPI(){
        this.playersList = new PlayersList(DefaultPlayersNames.PLAYERONE.getValue(), DefaultPlayersNames.PLAYERTWO.getValue());
    }

    public void setPlayerName(int playerNumber, String playerName) {
        playersList.setPlayerName(playerNumber, playerName);
    }
}
