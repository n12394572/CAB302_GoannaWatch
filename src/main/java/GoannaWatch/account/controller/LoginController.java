package GoannaWatch.account.controller;

import GoannaWatch.App;
import GoannaWatch.account.model.IAccountDAO;
import GoannaWatch.account.model.Account;
import GoannaWatch.account.model.Session;
import GoannaWatch.account.model.SqliteAccountDAO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Initialises the controller class. This method is automatically called after the .fxml file has been loaded.
 */
public class LoginController {
    //TODO Bind button to enter key
    //TODO Link Signup Page

    private final IAccountDAO accountDAO;
    private boolean rememberMe = false;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label loginFeedbackLabel;

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

    // Package-private constructor allows a test DAO for unit testing without changing normal app behaviour
    LoginController(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    /**
     * Displays an inline validation warning on the login page
     * @param message The validation message to display
     */
    private void showLoginFeedback(String message) {
        loginFeedbackLabel.setText(message);
        loginFeedbackLabel.setVisible(true);
        loginFeedbackLabel.setManaged(true);
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
        loginFeedbackLabel.setVisible(false);
        loginFeedbackLabel.setManaged(false);

        String email = emailTextField.getText() == null
                ? ""
                : emailTextField.getText().trim();
        String password = passwordField.getText() == null
                ? ""
                : passwordField.getText();

        if (email.isBlank() || password.isBlank()) {
            showLoginFeedback("Please enter both email and password.");
            return;
        }

        try {
            Account account = accountDAO.getAccountByEmail(email);

            boolean passwordMatches;

            if (accountDAO instanceof SqliteAccountDAO sqliteAccountDAO) {
                passwordMatches = sqliteAccountDAO.checkPassword(email, password);
            } else {
                passwordMatches = account != null
                        && account.getPassword() != null
                        && account.getPassword().equals(password);
            }

            if (account == null || !passwordMatches) {
                showLoginFeedback("Incorrect email or password.");
                return;
            }

        Session.setCurrentAccount(account);

        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("landing.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

        } catch (SQLException e) {
            showLoginFeedback("Could not access the database. Please try again.");
            System.err.println(e.getMessage());
        }
    }

    /**
     * Searches all accounts for one whose email matches the given address.
     * @param email The email address to search for
     * @return An {@link Optional} containing the matching {@link Account} if one exists.
     */
    //Package-private for account lookup to be uni tested without JavaFX controls to be initialised.
    Optional<Account> findAccountByEmail(String email) {
        List<Account> accounts = accountDAO.getAllAccounts();
        return accounts.stream()
                .filter(a -> a.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @FXML
    private void onSignupRedirectClick() throws IOException {
        //Stage stage = (Stage) signupButton.getScene().getWindow();
        Stage stage = (Stage) signUpLink.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    public void initialize() {
        loginButton.setDefaultButton(true);
    }
}

