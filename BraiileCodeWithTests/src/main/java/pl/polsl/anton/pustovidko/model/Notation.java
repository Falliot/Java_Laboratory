package pl.polsl.anton.pustovidko.model;

/**
 * Notation Enum
 *
 * @author Anton Pustovidko
 * @version 1.0
 */

public enum Notation {
    /**
     * An enum constant for plain text
     */
    PLAINTEXT(1),
    
    /**
     * An enum constant for Braille notaion
     */
    BRAILLENOTATION(2),
    
    /**
     * An enum default constant, in case user does not select 1 or 2
     */
    ERROR(0);

    /**
     * int for assigning the value to constants
     */
    private final int selection;

    /**
     * Constructor
     *
     * @param selection sets the int field
     */
    Notation(int selection) {
        this.selection = selection;
    }
};
