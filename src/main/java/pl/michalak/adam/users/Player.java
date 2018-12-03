package pl.michalak.adam.users;

import pl.michalak.adam.components.Symbol;

class Player {
    private String name;
    private int score;
    private final Symbol symbol;

    Player(String name, Symbol symbol){
        this.name = name;
        this.score = 0;
        this.symbol = symbol;
    }

    void setPlayerName(String name){
        this.name = name;
    }

    String getPlayerName() {
        return this.name;
    }

    Symbol getPlayerSymbol() {
        return this.symbol;
    }

    void addPointsToScore(int pointsAdded) {
        this.score += pointsAdded;
    }

    int getPlayersScore(){
        return this.score;
    }

    void resetPoints() {
        this.score = 0;
    }
}
