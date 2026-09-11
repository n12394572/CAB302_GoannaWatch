package account.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class HomePageController extends Application {

    @FXML
    private Button newObservationButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button mapButton;
    @FXML
    private Button dashboardButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    public void dashboardPress() {
    }

    public void mapPress() {
    }

    public void historyPress() {
    }

    public void newObservationPress() {
    }
}
