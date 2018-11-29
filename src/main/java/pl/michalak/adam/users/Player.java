package pl.michalak.adam.users;

class Player {
    private String name;
    private int score;

    Player(String name){
        this.name = name;
        this.score = 0;
    }

    void setPlayerName(String name){
        this.name = name;
    }

    public String getPlayerName() {
        return this.name;
    }
}
