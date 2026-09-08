package account.controller;

import account.App;
import account.model.IAccountDAO;
import account.model.MockAccountDAO;
import account.model.Account;

import account.model.Session;
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

public class SignupController {

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

    public SignupController(){
        accountDAO = new MockAccountDAO();
    }

    @FXML
    private void onCancelButtonClick() throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

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

    private boolean isEmailAlreadyRegistered(String email) {
        return accountDAO.getAllAccounts().stream()
                .anyMatch(account -> account.getEmail().equalsIgnoreCase(email));
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

