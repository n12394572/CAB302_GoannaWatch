package account.controller;

import account.model.IAccountDAO;

import account.model.MockAccountDAO;
import account.model.Account;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController {

    @FXML
    private TextField emailTextField;

    private IAccountDAO accountDAO;
    @FXML
    private TextField passwordTextField;

    @FXML
    private Button submitButton;

    @FXML
    private void onCancelButtonClick() {
        System.out.println("Cancel clicked");
    }

    @FXML
    private void onSubmitButtonClick() {
        System.out.println("Submit clicked");
    }

    public SignupController(){
        accountDAO = new MockAccountDAO();
    }


    @FXML
    private void onAdd() {
        // Default values for a new contact
        final String DEFAULT_FIRST_NAME = "New";
        final String DEFAULT_LAST_NAME = "Account";
        final String DEFAULT_EMAIL = "";
        final String DEFAULT_PHONE = "";
        Account newAccount = new Account(DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL, DEFAULT_PHONE);
        // Add the new contact to the database
        accountDAO.addAccount(newAccount);
    }
}
