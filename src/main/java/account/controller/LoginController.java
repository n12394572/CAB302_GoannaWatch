package account.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField passwordLogin;

    @FXML
    private CheckBox rememberMe;

    @FXML
    private Button nextButton;


    @FXML
    private void rememberMeClick() {
        ;
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

