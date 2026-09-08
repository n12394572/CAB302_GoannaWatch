package account.controller;

import account.model.IAccountDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class LoginController {
    // private final IAccountDAO accountDAO;
    private boolean rememberMe = false;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private CheckBox rememberMeCheck;

    @FXML
    private Button nextButton;

    @FXML
    protected void onRememberMeCheck() {
        rememberMe = rememberMeCheck.isSelected();
    }

    @FXML
    private void onCancelButtonClick() {
        System.out.println("Cancel clicked");
    }

    @FXML
    private void onNextButtonClick() {
        System.out.println("Next clicked");
    }
}

