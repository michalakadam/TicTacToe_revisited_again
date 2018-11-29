package pl.michalak.adam.users;

import pl.michalak.adam.exceptions.PlayersNamesAreTheSameException;

import java.util.ArrayList;
import java.util.List;

class PlayersList {
    private final List<Player> players;
    PlayersList(String playerOneDefaultName, String playerTwoDefaultName) {
        this.players = new ArrayList<>();
        players.add(new Player(playerOneDefaultName));
        players.add(new Player(playerTwoDefaultName));
    }

    public void setPlayerName(int playerNumber, String playerName) throws PlayersNamesAreTheSameException {
        if(playerNumber == 2 && players.get(0).getName().equals(playerName))
            throw new PlayersNamesAreTheSameException("players' names are exactly the same");
        else if(playerNumber == 1 && players.get(1).getName().equals(playerName))
            throw new PlayersNamesAreTheSameException("players' names are exactly the same");
        else
            players.get(playerNumber-1).setPlayerName(playerName);
    }
}

