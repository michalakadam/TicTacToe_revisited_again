package pl.michalak.adam.users;

class Player {
    String name;
    int score;

    Player(String name){
        this.name = name;
        this.score = 0;
    }

    void setPlayerName(String name){
        this.name = name;
    }
}
