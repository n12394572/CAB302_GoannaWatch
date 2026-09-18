package GoannaWatch.account.controller;

import GoannaWatch.App;
import GoannaWatch.account.model.Account;

import GoannaWatch.account.model.SqliteAccountDAO;
import java.sql.SQLException;

import GoannaWatch.account.model.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Initialises the controller class. This method is automatically called after the .fxml file has been loaded.
 */
public class LoginController {
    //TODO Bind button to enter key
    //TODO Link Signup Page
    //TODO Change from Alert to something that doesn't create a new window.
    // (Probably just a label or a Message using AtlantaFX)

    private final SqliteAccountDAO accountDAO;
    private boolean rememberMe = false;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox rememberMeCheck;

    //@FXML
    //private Button signupButton;

    @FXML
    private Hyperlink signUpLink;

    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;

    /**
     * Constructs a new LoginController with an AccountManager that uses an in-memory database to perform CRUD operations on accounts.
     */
    public LoginController() {
        accountDAO = new  SqliteAccountDAO();
    }

    /**
     * Handles the action of clicking the remember me checkbox.
     */
    @FXML
    protected void onRememberMeCheck() {
        rememberMe = rememberMeCheck.isSelected();
    }

    /**
     * Handles the action of clicking the cancel button. Loads the welcome view of the application.
     * @throws IOException If the .fxml file for the welcome view isn't found.
     */
    @FXML
    private void onCancelButtonClick() throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Handles the action of clicking the login button. Checks if login details match an existing account and loads the landing view of the application.
     * @throws IOException If the .fxml file for the landing view isn't found.
     * //TODO clear password field if login fails
     */
    @FXML
    private void onNextButtonClick() throws IOException {
        String email = emailTextField.getText().trim();
        String password = passwordField.getText();

        if (email.isBlank() || password.isBlank()) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Missing information",
                    "Please enter both email and password."
            );
            return;
        }

        try {
            Account account = accountDAO.getAccountByEmail(email);

            if (account == null || !accountDAO.checkPassword(email, password)) {
                showAlert(
                        Alert.AlertType.ERROR,
                        "Login failed",
                        "Incorrect email or password."
                );
                return;
            }

            Session.setCurrentAccount(account);

            Stage stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader fxmlLoader =
                    new FXMLLoader(App.class.getResource("landing.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

        } catch (SQLException e) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Database error",
                    "Could not access the database. Please try again."
            );
            System.err.println(e.getMessage());
        }
    }



    @FXML
    private void onSignupRedirectClick() throws IOException {
        //Stage stage = (Stage) signupButton.getScene().getWindow();
        Stage stage = (Stage) signUpLink.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Displays an alert dialog box to the user.
     * @param type The category of alert to display.
     * @param title The text shown in the alert window's title bar.
     * @param message The text shown in the body of the alert.
     *
     * //TODO Change from Alert to a listener.
     */
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        loginButton.setDefaultButton(true);
    }
}

