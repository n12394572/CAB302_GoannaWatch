package account.controller;

import account.App;
import account.model.Account;
import account.model.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LandingController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Button logoutButton;

    @FXML
    public void initialize(){
        Account current = Session.getCurrentAccount();
        if (current != null) {
            welcomeLabel.setText("Welcome, " + current.getFullName() + "!");
            emailLabel.setText(current.getEmail());
        }
    }

    @FXML
    protected void onLogoutButtonClick() throws IOException {
        Session.clear();
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
