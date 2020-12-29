package pl.polsl.anton.pustovidko.main;

import pl.polsl.anton.pustovidko.controllers.MainController;
import pl.polsl.anton.pustovidko.controllers.HistoryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

/**
 * App class of the program
 *
 * @author Anton Pustovidko
 * @version 1.0
 */
public class App extends Application {

    private Stage window;
    private Scene mainScene, historyScene;

    ObservableList<String> list = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) throws IOException {

        window = stage;

        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = mainLoader.load();

        FXMLLoader historyLoader = new FXMLLoader(getClass().getResource("history.fxml"));
        Parent node = historyLoader.load();

        mainScene = new Scene(root, 600, 400);

        MainController controller = mainLoader.getController();
        HistoryController historyController = historyLoader.getController();

        controller.setItems(historyController.getList());

        MenuItem historyItem = controller.getMenuItem();
        historyItem.setOnAction(e -> window.setScene(historyScene));

        historyScene = new Scene(node, 400, 400);

        Button exitBtn = historyController.getButton();

        exitBtn.setOnAction(e -> window.setScene(mainScene));

        window.setScene(mainScene);
        window.setTitle("Braille Translator");
        window.setResizable(false);
        window.show();
    }
    
     /**
     * Main method of the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
