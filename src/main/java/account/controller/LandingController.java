package account.controller;

import account.App;
import account.model.Account;
import account.model.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller class for the Landing view of the App application. This class handles the user interactions in the Landing Page view.
 */
public class LandingController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private Button observationButton;

    /**
     * Initialises the controller class. This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    public void initialize(){
        Account current = Session.getCurrentAccount();
        if (current != null) {
            welcomeLabel.setText("Welcome, " + current.getFullName() + "!");
            emailLabel.setText(current.getEmail());
        }
    }

    /**
     * Handles the action of clicking the logout button. Loads the Welcome page view of the application.
     * @throws IOException If the .fxml file for the welcome view isn't found.
     */
    @FXML
    protected void onLogoutButtonClick() throws IOException {
        Session.clear();
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Handles the action of clicking the "Record an Observation" button. Loads the Observation view of the application.
     * @throws IOException If the .fxml file for the observation view isn't found.
     */
    @FXML
    protected void onObservationButtonClick() throws IOException {
        Stage stage = (Stage) observationButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("observation.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
