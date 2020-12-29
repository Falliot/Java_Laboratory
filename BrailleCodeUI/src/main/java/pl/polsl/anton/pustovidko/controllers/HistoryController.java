
package pl.polsl.anton.pustovidko.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;

/**
 * FXML HistoryController class
 *
 * @author Anton Pustovidko
 * @version 1.0
 */
public class HistoryController implements Initializable {

    /**
     * List view for storing the history items
     */
    @FXML
    private ListView<String> listView;
    
    /**
     * Exit button
     */
    @FXML
    private Button exitBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Sets list
     * @param list passed to be set
     */
    public void setItems(ObservableList<String> list) {
        listView.setItems(list);
    }
    
    /**
     * Gets exit button
     * @return exit button
     */
    public Button getButton() {
        return exitBtn;
    }
    
    /**
     * Gets observable list 
     * @return list with items
     */
    public ObservableList<String> getList() {
        return listView.getItems();
    }
}
