package pl.michalak.adam.exceptions;

/**
 * This exception is thrown when a player wants to put their piece in a slot that is already occupied.
 * @author Adam Michalak
*/
public class SlotIsFilledException extends Exception {
    public SlotIsFilledException(String message){
        super(message);
    }
}
