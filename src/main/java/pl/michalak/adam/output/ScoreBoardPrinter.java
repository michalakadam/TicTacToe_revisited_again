package pl.michalak.adam.output;

import pl.michalak.adam.users.PlayersAPI;

class ScoreBoardPrinter {
    private final PlayersAPI playersAPI;

    ScoreBoardPrinter(PlayersAPI playersAPI) {
        this.playersAPI = playersAPI;
    }

    String printScoreBoard(){
        StringBuilder out = new StringBuilder();
        out.append("\n");
        if(isItADraw()){
            out.append(playersAPI.getPlayerName(1)).append("\t\t").append(playersAPI.getPlayersScore(1));
            out.append("\n");
            out.append(playersAPI.getPlayerName(2)).append("\t\t").append(playersAPI.getPlayersScore(2));
        }
        else{
            out.append("1. ").append(playersAPI.getPlayerName(determineFirstPlayer())).append("\t\t").append(playersAPI.getPlayersScore(determineFirstPlayer()));
            out.append("\n");
            out.append("2. ").append(playersAPI.getPlayerName(determineSecondPlayer())).append("\t\t").append(playersAPI.getPlayersScore(determineSecondPlayer()));
        }
        out.append("\n\n");
        return out.toString();

    }

    boolean isItADraw(){
        return playersAPI.getPlayersScore(1) == playersAPI.getPlayersScore(2);
    }
    int determineFirstPlayer(){
        return playersAPI.getPlayersScore(1) > playersAPI.getPlayersScore(2) ? 1 : 2;
    }

    int determineSecondPlayer(){
        return playersAPI.getPlayersScore(1) < playersAPI.getPlayersScore(2) ? 1 : 2;
    }
}
