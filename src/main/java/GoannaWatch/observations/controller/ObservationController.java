package GoannaWatch.observations.controller;

import GoannaWatch.App;
import GoannaWatch.account.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import GoannaWatch.observations.model.IObservationDAO;
import GoannaWatch.observations.model.MockObservationDAO;
import GoannaWatch.observations.model.Observation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;

/**
 * The controller class for the Observation view of the GoannaWatch application. This class handles the user interactions in the Observation view.
 */
public class ObservationController {

    private final IObservationDAO observationDAO;

    @FXML
    private ListView<Observation> observationsListView;

    @FXML
    private CheckBox showMineOnlyCheck;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField animalTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private VBox observationContainer;

    /**
     * Initialises the controller class. This method is automatically called after the .fxml file has been loaded.
     */
    public ObservationController() {
        observationDAO = new MockObservationDAO();
    }

    /**
     * Selects a contact in the list view and updates the text fields with the observation's information
     * @param observation The observation to select.
     */
    private void selectObservation(Observation observation) {
        observationsListView.getSelectionModel().select(observation);
        locationTextField.setText(observation.getLocation());
        animalTextField.setText(observation.getAnimalSeen());
        datePicker.setValue(observation.getObservedAt());
    }

    /**
     * Renders a cell in the observations list view by setting the text to the observation's details.
     * @param listView The list view to render the cell for.
     * @return The rendered cell.
     */
    private ListCell<Observation> renderCell(ListView<Observation> listView) {
        return new ListCell<>() {
            /**
             * Handles the event when an observation is selected in the list view.
             * @param mouseEvent The event to handle.
             */
            private void onObservationSelected(MouseEvent mouseEvent) {
                ListCell<Observation> clickedCell = (ListCell<Observation>) mouseEvent.getSource();
                Observation selected = clickedCell.getItem();
                if (selected != null) selectObservation(selected);
            }

            /**
             * Updates the item in the cell by setting the text to the observation's details.
             * @param observation The observation to update the cell with.
             * @param empty Whether the cell is empty.
             */
            @Override
            protected void updateItem(Observation observation, boolean empty) {
                super.updateItem(observation, empty);
                if (empty || observation == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onObservationSelected);
                } else {
                    setText(observation.getAnimalSeen() + " at " + observation.getLocation()
                    + " (" + observation.getObservedAt() + ")");
                    super.setOnMouseClicked(this::onObservationSelected);
                }
            }
        };
    }

    /**
     * Synchronises the observation list view with the observations in the database.
     */
    private void syncObservations() {
        observationsListView.getItems().clear();

        List<Observation> observations;
        if (showMineOnlyCheck.isSelected()) {
            Account currentAccount = Session.getCurrentAccount();
            observations = (currentAccount != null)
                    ? observationDAO.getObservationsByAccount(currentAccount)
                    : List.of();
        } else {
            observations = observationDAO.getAllObservations();
        }

        boolean hasObservation = !observations.isEmpty();
        if (hasObservation) {
            observationsListView.getItems().addAll(observations);
        }
        observationContainer.setVisible(hasObservation);
    }

    @FXML
    public void initialize() {
        observationsListView.setCellFactory(this::renderCell);
        syncObservations();

        observationsListView.getSelectionModel().selectFirst();
        Observation firstObservation = observationsListView.getSelectionModel().getSelectedItem();
        if (firstObservation != null) {
            selectObservation(firstObservation);
        }
    }

    @FXML
    private void onShowMineOnlyCheck() {
        syncObservations();
    }

    @FXML
    private void onEditConfirm() {
        Observation selected = observationsListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }
        try {
            selected.setLocation(locationTextField.getText());
            selected.setAnimalSeen(animalTextField.getText());
            selected.setObservedAt(datePicker.getValue());
            observationDAO.updateObservation(selected);
            syncObservations();
        } catch (InputMismatchException e) {
            showAlert(e.getMessage());
        }
    }

    @FXML
    private void onDelete() {
        Observation selected = observationsListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            observationDAO.deleteObservation(selected);
            syncObservations();
        }
    }

    @FXML
    private void onAdd() {
        Account currentAccount = Session.getCurrentAccount();
        if (currentAccount == null) {
            showAlert("You must be logged in to record an observation.");
            return;
        }

        final String DEFAULT_LOCATION = "New Location";
        final String DEFAULT_ANIMAL = "Unknown";
        final LocalDate DEFAULT_DATE = LocalDate.now();

        Observation newObservation = new Observation(currentAccount, DEFAULT_LOCATION, DEFAULT_ANIMAL, DEFAULT_DATE);
        observationDAO.addObservation(newObservation);
        syncObservations();

        selectObservation(newObservation);
        locationTextField.requestFocus();
    }

    @FXML
    private void onCancel() {
        Observation selected = observationsListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selectObservation(selected);
        }
    }

    @FXML
    private void onBackButtonClick() throws IOException {
        Stage stage = (Stage) observationsListView.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("landing.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
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
