package pl.michalak.adam.users;

enum DefaultPlayersNames {
    PLAYERONE("X"), PLAYERTWO("O");

    private final String name;
    DefaultPlayersNames(String name){
        this.name = name;
    }

    String getValue(){
        return this.name;
    }
}
