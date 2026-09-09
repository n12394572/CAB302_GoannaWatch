package observations.controller;

import account.App;
import account.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import observations.model.IObservationDAO;
import observations.model.MockObservationDAO;
import observations.model.Observation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class ObservationController {

    private final IObservationDAO observationDAO;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField animalTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    public ObservationController() {
        observationDAO = new MockObservationDAO();
    }

    @FXML
    private void onSubmitButtonClick(){
        Account currentAccount = Session.getCurrentAccount();
        if (currentAccount == null) {
            showAlert("Must be logged in to record observation.");
            return;
        }

        String location = locationTextField.getText();
        String animalSeen = animalTextField.getText();
        LocalDate observedOn = datePicker.getValue();

        if (observedOn == null) {
            showAlert("Please select a date.");
            return;
        }

        try {
            Observation observation = new Observation(currentAccount, location, animalSeen, observedOn);
            observationDAO.addObservation(observation);

            showAlert("Record Complete!");
            clearForm();
        } catch (InputMismatchException e) {
            showAlert(e.getMessage());
        }

    }

    @FXML
    private void onCancelButtonClick() throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("landing.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    private void clearForm() {
        locationTextField.clear();
        animalTextField.clear();
        datePicker.setValue(null);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
