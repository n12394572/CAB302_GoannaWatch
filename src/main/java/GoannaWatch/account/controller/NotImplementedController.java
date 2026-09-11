package GoannaWatch.account.controller;

import GoannaWatch.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller class for the NotImplemented view of the GoannaWatch application. This class handles the user interactions in the NotImplemented view.
 */
public class NotImplementedController {

    @FXML
    private Button welcomeButton;

    @FXML
    private Button landingButton;

    /**
     * Handles the action of clicking the welcome button. Loads the Welcome view of the application.
     * @throws IOException If the .fxml file for the welcome view isn't found.
     */
    @FXML
    protected void onWelcomeButtonClick() throws IOException {
        Stage stage = (Stage) welcomeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Handles the action of clicking the landing button. Loads the Landing view of the application.
     * @throws IOException If the .fxml file for the landing view isn't found.
     */
    @FXML
    protected void onLandingButtonClick() throws IOException {
        Stage stage = (Stage) welcomeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("landing.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}