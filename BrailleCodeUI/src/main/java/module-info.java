/**
 * Default package with javafx configurations
 */
module pl.polsl.anton.pustovidko.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens pl.polsl.anton.pustovidko.controllers to javafx.fxml;
    exports pl.polsl.anton.pustovidko.main;
}
