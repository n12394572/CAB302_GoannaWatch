package GoannaWatch.account.controller;

import GoannaWatch.App;
import GoannaWatch.account.model.IAccountDAO;
import GoannaWatch.account.model.MockAccountDAO;
import GoannaWatch.account.model.Account;

import GoannaWatch.account.model.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.InputMismatchException;

/**
 * The controller class for the Signup view of the account.App application. This class handles the user interactions in the Signup view.
 */
public class SignupController {

    //TODO Add password confirmation and some sort of security for password
    //TODO Bind button to enter key
    //TODO Link Login page
    //TODO Functionality for Remember Me checkbox
    //TODO change from Alert to a listener so that it doesn't interrupt user input

    private final IAccountDAO accountDAO;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button loginButton;

    /**
     * Constructs a new SignupController with an AccountManager that  uses an in-memory database to perform CRUD operations on accounts.
     */
    public SignupController(){
        accountDAO = new MockAccountDAO();
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
     * Handles the action of clicking the signup button. Checks that an account doesn't exist with a matching email and loads the landing view of the application.
     * @throws IOException If the .fxml file for the landing view isn't found.
     */
    @FXML
    private void onSubmitButtonClick() throws IOException {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordField.getText();

        if (isEmailAlreadyRegistered(email)){
            showAlert("An account with that email already exists.");
            return;
        }
        try {
            Account newAccount = new Account(firstName, lastName, email, password);
            accountDAO.addAccount(newAccount);
            System.out.println("Account created for " + newAccount.getFullName());

            Session.setCurrentAccount(newAccount);

            Stage stage = (Stage) submitButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("landing.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

        } catch (InputMismatchException | IllegalArgumentException e) {
            showAlert(e.getMessage());
        }


    }

    @FXML
    private void onLoginButtonClick() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Checks if an email is already registered with an account in the database.
     * @param email The email to check if it is already registered.
     * @return The account linked to the email if it already exists, else false.
     */
    private boolean isEmailAlreadyRegistered(String email) {
        return accountDAO.getAllAccounts().stream()
                .anyMatch(account -> account.getEmail().equalsIgnoreCase(email));
    }

    /**
     * Displays an alert dialog box to the user.
     * @param message The text shown in the body of the alert.
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

