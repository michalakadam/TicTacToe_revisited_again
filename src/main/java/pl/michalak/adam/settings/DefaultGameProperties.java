package pl.michalak.adam.settings;

enum DefaultGameProperties {

    BOARDSIZE(3), WINNINGCONDITION(3);

    private final int value;
    DefaultGameProperties(int value){
        this.value = value;
    }

    int getValue(){
        return this.value;
    }
}
