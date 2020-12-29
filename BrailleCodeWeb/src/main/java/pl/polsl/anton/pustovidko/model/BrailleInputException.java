/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.anton.pustovidko.model;

/**
 *
 * @author anton
 */
public class BrailleInputException extends Exception {

    /**
     * Non-parameter constructor
     */
    public BrailleInputException() {
    }

    /**
     * Exception class constructor
     *
     * @param message display message
     */
    public BrailleInputException(String message) {
        super(message);
    }
    
}
