package account.controller;

import account.model.IAccountDAO;
import account.model.Account;
import account.model.MockAccountDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class LoginController {
    private final IAccountDAO accountDAO;
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

    public LoginController() {
        accountDAO = new MockAccountDAO();
    }

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
        String email = emailTextField.getText() == null ? "" : emailTextField.getText().trim();
        String password = passwordTextField.getText() == null ? "" : passwordTextField.getText().trim();

        if (email.isBlank() || password.isBlank()) {
            showAlert(Alert.AlertType.WARNING, "Missing information", "Please enter both email or password.");
        }

        Optional<Account> matchedAccount = findAccountByEmail(email);

        if (matchedAccount.isEmpty() || !matchedAccount.get().getPassword().equals(password)){
            showAlert(Alert.AlertType.ERROR, "Login failed", "Incorrect email or password.");
            return;
        }

        Account account = matchedAccount.get();
        System.out.println("Logging in as " + account.getFullName() + (rememberMe ? "(remember me enabled)" : ""));
    }

    private Optional<Account> findAccountByEmail(String email) {
        List<Account> accounts = accountDAO.getAllAccounts();
        return accounts.stream()
                .filter(a -> a.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeWindow(Button anyButtonOnScene) {
        Stage stage = (Stage) anyButtonOnScene.getScene().getWindow();
        stage.close();
    }
}

