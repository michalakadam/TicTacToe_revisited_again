package pl.michalak.adam.input;

import pl.michalak.adam.output.OutputAPI;

class StringInputValidator {
    private final OutputAPI outputAPI;
    private final InputReader inputReader;

    StringInputValidator(OutputAPI outputAPI, InputReader inputReader){
        this.outputAPI = outputAPI;
        this.inputReader = inputReader;
    }


    private boolean checkStringInput(String expected, String actual){
        //case does not matter in comparison
        return expected.toLowerCase().equals(actual.toLowerCase());
    }

    String getStringInputFromPlayer(String message, String option1, String option2){
        outputAPI.printFromResourceBundle(message);
        String answer = inputReader.getString();
        if(checkStringInput(option1, answer) || checkStringInput(option2, answer))
            return answer;
        else if(answer.equals("q") || answer.equals("Q")){
            String areYouSure = getStringInputFromPlayer("validateUserExitWish", "Y", "N");
            if(areYouSure.equals("Y") || areYouSure.equals("y"))
                System.exit(0);
            else
                outputAPI.printFromResourceBundleAndAddNextLine("changedMyMind");
        }
        outputAPI.printErrorFromResourceBundleWithFormatting("insertCorrectAnswer", option1, option2);
        return getStringInputFromPlayer(message, option1, option2);
    }
}
