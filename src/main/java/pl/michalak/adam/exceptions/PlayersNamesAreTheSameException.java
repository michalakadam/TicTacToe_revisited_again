package pl.michalak.adam.exceptions;

/**
 * This exception is thrown when one of the players tries to set their name to the same value as other player's name.
 * @author Adam Michalak
*/
public class PlayersNamesAreTheSameException extends Exception{
    public PlayersNamesAreTheSameException(String message){
        super(message);
    }

}
