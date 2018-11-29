package pl.michalak.adam.settings;

class BoardSize {
    private int boardSize;
    BoardSize(int boardSize) {
        this.boardSize =  boardSize;
    }

    void setBoardSize(int boardSize){
        this.boardSize = boardSize;
    }

    int getBoardSize(){
        return this.boardSize;
    }
}
