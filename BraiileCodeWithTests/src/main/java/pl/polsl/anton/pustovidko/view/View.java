package pl.polsl.anton.pustovidko.view;

import pl.polsl.anton.pustovidko.model.Notation;

import java.util.Scanner;

/**
 * View of the class Implements I/O of the program
 *
 * @author Anton Pustovidko
 * @version 1.0
 */
public class View {

    /**
     * Scanner for reading data from standard input stream
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints the message to for the user and reads the integer from the standard input
     *
     * @return an enum value
     */
    public Notation decisionMethod() {
        print("Please select what operation you would like to do. Select:\n1.Plain text to Braille notation.\n2.Braille notation to plainText.\n");
        int selection = readInt();
         
        switch (selection) {
            case 1:
                return Notation.PLAINTEXT;
            case 2:
                return Notation.BRAILLENOTATION;
            default:
                return Notation.ERROR;
        }
    }

    /**
     * Prints the message and reads user input from standard input
     *
     * @param message String to print
     * @return a String array of user input
     */
    public String readUserInput(String message) {
        System.out.println(message);
        String input = readInput();
        return input;
    }
   
    /**
     * Read String input from standard input
     *
     * @return a String user input
     */
    private String readInput() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }

    /**
     * Read integer input from standard input
     *
     * @return an integer user input
     */
    public int readInt() {
        while (true) {
            if (scanner.hasNextInt()) {
                return Integer.parseInt(scanner.nextLine());
            } else {
                System.out.println("Wrong input, try again.");
                clearInput();
            }
        }
    }

    /**
     * Print message to the standard output
     *
     * @param message String to print
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Clear the input stream in case of scanning failure
     */
    public void clearInput() {
        scanner.nextLine();
    }

}
