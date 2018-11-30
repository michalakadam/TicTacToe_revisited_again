package pl.michalak.adam.input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.michalak.adam.output.OutputAPI;

/**
 * This object handles everything that is related to user input.
 *
*/
public class InputAPI {
    private final IntInputValidator intInputValidator;
    private final StringInputValidator stringInputValidator;
    private final StringInputProvider stringInputProvider;

    public InputAPI(OutputAPI outputAPI){
        InputReader inputReader = new InputReader(System.in);
        this.stringInputValidator = new StringInputValidator(outputAPI, inputReader);
        this.stringInputProvider = new StringInputProvider(outputAPI, inputReader);
        this.intInputValidator = new IntInputValidator(outputAPI, inputReader, stringInputValidator);
    }

    private static final Logger logger = LogManager.getLogger("Input");

    /**
     * This method enables user to provide int value and checks it for validity.
     * @param message is a code of text stored in resource bundle.
     * @param min is a minimum value that is correct for this inquiry.
     * @param max is a maximum value that is correct for this inquiry.
     * @return validated user input as an int.
    */
    public int getIntInputFromPlayer(String message, int min, int max){
        int userAnswer = intInputValidator.getIntInputFromPlayer(message, min, max);
        logger.info(userAnswer);
        return userAnswer;
    }

    /**
     * This method enables user to provide string answer for an inquiry.
     * @param message is a code of text stored in resource bundle.
     * @return user input as a string.
     */
    public String getStringInputFromPlayerWithNoStringsAttached(String message){
        String userAnswer = stringInputProvider.getStringFromUser(message);
        logger.info(userAnswer);
        return userAnswer;
    }
}
