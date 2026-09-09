package account.controller;

import account.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller class for the Welcome view of the App application. This class handles the user interactions in the Welcome view.
 */
public class WelcomeController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    /**
     * Handles the action of clicking the login button. Loads the Login view of the application.
     * @throws IOException If the .fxml file for the welcome view isn't found.
     */
    @FXML
    protected void onLoginButtonClick() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Handles the action of clicking the signup button. Loads the Sign-Up view of the application.
     * @throws IOException If the .fxml file for the welcome view isn't found.
     */
    @FXML
    protected void onSignupButtonClick() throws IOException {
        Stage stage = (Stage) signupButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
