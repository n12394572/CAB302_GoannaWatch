package account.controller;

import account.model.IAccountDAO;
import account.model.MockAccountDAO;
import account.model.Account;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.InputMismatchException;

public class SignupController {

    private final IAccountDAO accountDAO;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Button submitButton;

    public SignupController(){
        accountDAO = new MockAccountDAO();
    }

    @FXML
    private void onCancelButtonClick() {
        System.out.println("Cancel clicked");
        closeWindow();
    }

    @FXML
    private void onSubmitButtonClick() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        try {
            Account newAccount = new Account(firstName, lastName, email, password);
            accountDAO.addAccount(newAccount);
            System.out.println("Account created for " + newAccount.getFullName());
        } catch (InputMismatchException e) {
            showAlert("Please fill in first and last name.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
