package pl.polsl.anton.pustovidko.test;

import pl.polsl.anton.pustovidko.model.Model;
import pl.polsl.anton.pustovidko.model.BrailleInputException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test Class for Model
 *
 * @author Anton Pustovidko
 * @version 1.0
 */
public class ModelTest {

    /**
         * Model object that performs conversions from plainTest to Braille notation and vise versa
         */

    Model model = new Model();

    /**
     * A test method to check the correctness of convertToBraille method
     * @throws BrailleInputException if userInput contains invalid characters
     */
    @Test
    public void testTextToBrailleConversion() throws BrailleInputException {
        String input = "hello";
        String invalidInput = "*&";
        String correctConversion = "⠓⠑⠇⠇⠕";
        try {
            assertEquals(correctConversion, model.convertToBraille(input), "Conversion is correct");
        } catch (pl.polsl.anton.pustovidko.model.BrailleInputException e) {

        }
        try {
            model.convertToBraille(invalidInput);
            fail("Incorect conversion, exception was expected");
        } catch (pl.polsl.anton.pustovidko.model.BrailleInputException e) {
        }
    }

    ;
    
    /**
     * A test method that checks whether the convertBrailleNumbers works properly  
     * @throws BrailleInputException if userInput contains invalid characters
     */
     @Test
    public void testBrailleToTextConversion() throws BrailleInputException {
        String input = "125 15 123 123 135 3456 1";
        String wrongConversion = "hello world";
        assertNotEquals(model.convertBrailleNumbers(input), wrongConversion, "Conversion is wrong");
    }

    ;
    
    /**
     * A Parameterized Test that check the correctness of convertBrailleNumbers method
     * @param input Braille number notation string
     * @param expected correct conversion 
     * @throws BrailleInputException if input contains invalid characters 
     */
     @org.junit.jupiter.params.ParameterizedTest
    @CsvSource({
        "125 15 123 123 135,h e l l o",
        "245 1 1236 1 2345 15 234 2345 24 1345 1245 24 234 14 135 135 123,j a v a t e s t i n g i s c o o l",
        "3456 1 3456 12 3456 14 3456 145 3456 15,1 2 3 4 5"})
    void plainTextToBraille(String input, String expected) throws BrailleInputException {
        String actualValue = model.convertBrailleNumbers(input);
        assertEquals(expected, actualValue, "Obtained results cover not only capital letters");
    }
    
     /**
     * A simple test that throws exception when the input contains invalid characters
     */
    @Test
    public void simpleTestOfException() {
        try {
            model.convertBrailleNumbers("&*");
            fail("An exception should be thrown when the input contains invalid characters.");
        } catch (BrailleInputException e) {
        }
    }
}
