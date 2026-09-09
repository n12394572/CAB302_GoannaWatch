package GoannaWatch.observations.controller;

import GoannaWatch.App;
import GoannaWatch.account.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import GoannaWatch.observations.model.IObservationDAO;
import GoannaWatch.observations.model.MockObservationDAO;
import GoannaWatch.observations.model.Observation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;

/**
 * The controller class for the Observation view of the account.App application. This class handles the user interactions in the Observation view.
 */
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

    /**
     * Initialises the controller class. This method is automatically called after the .fxml file has been loaded.
     */
    public ObservationController() {
        observationDAO = new MockObservationDAO();
    }

    /**
     * Handles the action of clicking the submit button. Checks if the user is logged in then adds new observation.
     */
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

    /**
     * Handles the action of clicking the cancel button. Loads the landing view of the application.
     * @throws IOException If the .fxml file for the landing view isn't found.
     */
    @FXML
    private void onCancelButtonClick() throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("landing.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Clears the input fields of the Observation record.
     */
    private void clearForm() {
        locationTextField.clear();
        animalTextField.clear();
        datePicker.setValue(null);
    }

    /**
     * Displays an alert dialog box to the user.
     * @param message The text shown in the body of the alert.
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
