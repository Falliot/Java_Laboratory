package pl.polsl.anton.pustovidko.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Model Class of the program implements conversion from plain text to Braille notation and vise versa
 *
 * @author Anton Pustovidko
 * @version 1.0
 */
public class Model {

    /**
     * A list containing the plain text characters and numbers
     */
    private final List<Character> listChars = Arrays.asList(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            '.', ',', ';', ':', '-', '?', '!', '\'', '(', ')', '/',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' '
    );

    /**
     * A list containing the braille characters. Order corresponds to plainText list.
     */
    private final List<String> braille = Arrays.asList(
            "⠁", "⠃", "⠉", "⠙", "⠑", "⠋", "⠛", "⠓", "⠊", "⠚",
            "⠅", "⠇", "⠍", "⠝", "⠕", "⠏", "⠟", "⠗", "⠎", "⠞",
            "⠥", "⠧", "⠺", "⠭", "⠽", "⠵",
            "⠲", "⠂", "⠆", "⠒", "⠤", "⠦", "⠖", "⠄", "⠐⠣", "⠐⠜", "⠸⠌",
            "⠼⠚", "⠼⠁", "⠼⠃", "⠼⠉", "⠼⠙", "⠼⠑", "⠼⠋", "⠼⠛", "⠼⠓", "⠼⠊", " "
    );

    /**
     * A list containing the braille number notation. Order corresponds to plainText list.
     */
    private final List<String> brailleNum = (Arrays.asList(
            "1", "12", "14", "145", "15", "124", "1245", "125",
            "24", "245", "13", "123", "134", "1345", "135", "1234",
            "12345", "1235", "234", "2345", "136", "1236", "2456", "1346", "13456", "1356",
            "256", "2", "23", "25", "36", "236", "235", "3", "5 126", "5 345", "456 34",
            "3456 245", "3456 1", "3456 12", "3456 14", "3456 145", "3456 15", "3456 124", "3456 1245", "3456 125", "3456 24"
    ));

    /**
     * Map that represent mapping of English characters to Braille symbols
     */
    private final Map<Character, String> charToBraille;

    /**
     * A Map that represent mapping of Braille number characters to English characters
     */
    private final Map<String, Character> brailleNumToChar;

    /**
     * Model Constructor
     */
    public Model() {
        charToBraille = IntStream.range(0, braille.size()).boxed().collect(Collectors.toMap(listChars::get, braille::get));
        brailleNumToChar = IntStream.range(0, brailleNum.size()).boxed().collect(Collectors.toMap(brailleNum::get, listChars::get));
    }

    /**
     * Converts the user input as a plain text into the Braille notation.
     *
     * @param userInput String input to be converted
     * @return converted String
     * @throws BrailleInputException if userInput contains invalid characters
     */
    public String convertToBraille(String userInput) throws BrailleInputException {
        StringBuilder result = new StringBuilder("");
        for (Character letter : userInput.toCharArray()) {
            String letterString = charToBraille.get(letter);
            if (letterString != null) {
                result.append(letterString);
            } else {
                throw new BrailleInputException("Invalid input " + letter);
            }
        }
        return result.toString();

    }

    /**
     * Converts the user input as Braille number notation into plain text.
     *
     * @param userInput String input to be converted
     * @return converted String
     * @throws BrailleInputException if userInput contains invalid characters
     */
    public String convertBrailleNumbers(String userInput) throws BrailleInputException {
        String[] inputSplit = userInput.split(" ");
        StringBuilder result = new StringBuilder("");

        for (int i = 0; i < inputSplit.length; i++) {
            Character character = brailleNumToChar.get(inputSplit[i]);
            
                if (character == null) {
                    if (i+1 < inputSplit.length) {
                    character = brailleNumToChar.get(inputSplit[i] + " " + inputSplit[++i]);
                    if (character == null) {
                        throw new BrailleInputException("Invalid input " + character);
                    }
                } 
                    else {
                throw new BrailleInputException("Invalid input " + inputSplit[i]);
            }
            }
            
            result.append(character).append(" ");
        }
        return result.toString().trim();
    }
}
