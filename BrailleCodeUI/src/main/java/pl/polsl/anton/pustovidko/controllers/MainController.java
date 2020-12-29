package pl.polsl.anton.pustovidko.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import pl.polsl.anton.pustovidko.model.BrailleInputException;
import pl.polsl.anton.pustovidko.model.Model;

/**
 * FXML MainController class
 *
 * @author Anton Pustovidko
 * @version 1.0
 */
public class MainController implements Initializable {

    /**
    * Model object that performs conversions from plainTest to Braille notation and vise versa
    */
    Model model = new Model();

    /**
     * String for storing the result of the translation 
     */
    String result = "";

    /**
     * String for storing user input
     */
    String userInput = "";

    /**
     * List for storing the history items
     */
    ObservableList<String> list;

    /**
     * Text field for user input in english text
     */
    @FXML
    private TextField englishInputTextField;

    /**
     * Text field for the result of english to Braille translation
     */
    @FXML
    private TextField englishResultTextField;

    /**
     * Text field for user input in Braille number notation
     */
    @FXML
    private TextField brailleResultTextField;

    /**
     * Text field for the result of Braille to english translation
     */
    @FXML
    private TextField brailleInputTextField;

    /**
     * Translate button in English to Braille Tab
     */
    @FXML
    private Button translateEnglishBtn;

    /**
     * Translate button in Braille to English Tab
     */
    @FXML
    private Button translateBrailleBtn;

    /**
     * History menu item
     */
    @FXML
    private MenuItem historyItem;

    /**
     * Exit menu item
     */
    @FXML
    private MenuItem exitItem;

    /**
     * Clear text button in english to Braille Tab
     */
    @FXML
    private Button clearEnglishTextField;

    /**
     * Clear text button in Braille to English Tab
     */
    @FXML
    private Button clearBrailleTextField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Translate from english to Braille method
        translateEnglishBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {

                userInput = englishInputTextField.getText().toLowerCase();
                try {
                    result = model.convertToBraille(userInput);
                    englishResultTextField.setText(result);
                    list.add(userInput + " to Braille code is " + result);
                } catch (BrailleInputException e) {
                    showAlert(e.getMessage());
                }
            }
        });

        // Translate from Braille to english method
        translateBrailleBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                userInput = brailleInputTextField.getText().toLowerCase();

                try {
                    result = model.convertBrailleNumbers(userInput);
                    brailleResultTextField.setText(result);
                    list.add(userInput + " to English is " + result);
                } catch (BrailleInputException e) {
                    showAlert(e.getMessage());
                }
            }
        });

        // Clear text fields
        clearEnglishTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                englishInputTextField.clear();
                englishResultTextField.clear();
            }
        });

        // Clear text fields
        clearBrailleTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                brailleInputTextField.clear();
                brailleResultTextField.clear();
            }
        });

        // exit menu action
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        // set shorcuts
        historyItem.setAccelerator(KeyCombination.keyCombination("Ctrl + H"));
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl + X"));

    }
    /**
     * Alert showing method
     * @param message to be displayed
     */
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error:");
        alert.setContentText(message);

        alert.showAndWait();
    }

    /**
     * Gets menu item 
     * @return history item
     */
    public MenuItem getMenuItem() {
        return historyItem;
    }
    
    /**
     * Sets items 
     * @param list assigns to the list 
     */
    public void setItems(ObservableList<String> list) {
        this.list = list;
    }
}
//125 15 123 123 135 3456 1
