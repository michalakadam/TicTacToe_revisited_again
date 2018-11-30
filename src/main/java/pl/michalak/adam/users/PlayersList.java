package pl.michalak.adam.users;

import pl.michalak.adam.components.Symbol;
import pl.michalak.adam.exceptions.PlayersNamesAreTheSameException;

import java.util.ArrayList;
import java.util.List;

class PlayersList {
    private final List<Player> players;
    PlayersList(String playerOneDefaultName, String playerTwoDefaultName) {
        this.players = new ArrayList<>();
        players.add(new Player(playerOneDefaultName, Symbol.X));
        players.add(new Player(playerTwoDefaultName, Symbol.O));
    }

    void setPlayerName(int playerNumber, String playerName) throws PlayersNamesAreTheSameException {
        if(playerNumber == 2 && players.get(0).getPlayerName().equals(playerName))
            throw new PlayersNamesAreTheSameException("players' names are exactly the same");
        else if(playerNumber == 1 && players.get(1).getPlayerName().equals(playerName))
            throw new PlayersNamesAreTheSameException("players' names are exactly the same");
        else
            players.get(playerNumber-1).setPlayerName(playerName);
    }

    String getPlayerName(int playerNumber) {
        return players.get(playerNumber-1).getPlayerName();
    }

    Symbol getPlayerSymbol(int playerNumber) {
        return players.get(playerNumber-1).getPlayerSymbol();
    }

    void addPointsToPlayersScore(int pointsAdded, int playerNumber) {
        players.get(playerNumber-1).addPointsToScore(pointsAdded);
    }

    int getPlayerScore(int playerNumber){
        return players.get(playerNumber-1).getPlayersScore();
    }
}

