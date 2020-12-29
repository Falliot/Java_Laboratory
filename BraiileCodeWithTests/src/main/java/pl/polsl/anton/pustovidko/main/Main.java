package pl.polsl.anton.pustovidko.main;

import pl.polsl.anton.pustovidko.model.BrailleInputException;
import pl.polsl.anton.pustovidko.view.View;
import pl.polsl.anton.pustovidko.model.Model;
import pl.polsl.anton.pustovidko.model.Notation;


/**
 * Main Class of the program
 *
 * @author Anton Pustovidko
 * @version 1.0
 */
public class Main {

    /**
     * Main method of the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        View view = new View();

        Model model = new Model();
     
        // number of parameters passed to the program
        int numberOfParameters = args.length;
        
        StringBuilder greetings = new StringBuilder(" ");
        
        // write all the parameters passed to the program
        for (int i = 0; i < numberOfParameters; i++) {
           greetings.append(args[i]).append(" ");
        }
        
        view.print(greetings.toString().trim());

        String result = "";
        boolean restart = true;
        
        while (restart == true) {
            try {
                Notation decision = view.decisionMethod();
                    switch(decision) {
                        case PLAINTEXT:
                             result = model.convertToBraille(view.readUserInput("Enter text"));
                        restart = false;
                        break;
                        case BRAILLENOTATION:
                             result = model.convertBrailleNumbers(view.readUserInput("Paste text in Braille notation").toLowerCase());
                        restart = false;
                        break;
                        default: 
                            view.print("Please provide a correct number\n");
                }
            } catch (BrailleInputException exception) {
                view.print(exception.getMessage());
                result = "";
                restart = true;
            }
        }

        view.print(result);

    }
}//125 15 123 123 135 3456 1
