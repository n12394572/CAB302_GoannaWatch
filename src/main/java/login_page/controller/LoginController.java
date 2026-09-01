package login_page.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class LoginController {

    @FXML
    private TextArea termsAndConditions;

    @FXML
    private CheckBox rememberMe;

    @FXML
    private Button nextButton;

    @FXML
    private void onAgreeCheckBoxClick() {
        nextButton.setDisable(!rememberMe.isSelected());
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

