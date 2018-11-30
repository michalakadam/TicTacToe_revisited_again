package pl.michalak.adam.programflow;

class Game {
    private int roundNumber;

    Game(){
        this.roundNumber = 0;
    }

    void beginNewRound(){
        this.roundNumber++;
    }

    int getRoundNumber(){
        return this.roundNumber;
    }
}
