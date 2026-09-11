package account.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class SignupController extends Application {


    @FXML
    private ChoiceBox<String> userType = new ChoiceBox<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    public void userTypeChoice() {
        userType.getItems().setAll("regular user", "expert user");
    }

    public void loginRedirectClick() {
    }
}
