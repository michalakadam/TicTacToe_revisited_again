package pl.michalak.adam.users;

import java.util.ArrayList;
import java.util.List;

class PlayersList {
    List<Player> players;
    PlayersList(String playerOneDefaultName, String playerTwoDefaultName) {
        this.players = new ArrayList<>();
        players.add(new Player(playerOneDefaultName));
        players.add(new Player(playerTwoDefaultName));
    }

    public void setPlayerName(int playerNumber, String playerName) {
        players.get(playerNumber-1).setPlayerName(playerName);
    }
}

