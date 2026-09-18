package GoannaWatch.observations.controller;

import GoannaWatch.account.model.Account;
import GoannaWatch.observations.model.Observation;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ObservationControllerTest {

    private Observation createObservation() {
        Account account = new Account(
                "Luke",
                "Smith",
                "luke@example.com",
                "Password1!"
        );

        account.setId(1);

        return new Observation(
                account,
                "Brisbane",
                "Eastern Water Dragon",
                LocalDate.of(2026, 9, 16)
        );
    }

    @Test
    void observationShouldMatchLocationSearch() {
        ObservationController controller = new ObservationController();
        Observation observation = createObservation();

        assertTrue(controller.matchesSearch(observation, "Brisbane"));
    }

    @Test
    void locationSearchShouldIgnoreCase() {
        ObservationController controller = new ObservationController();
        Observation observation = createObservation();

        assertTrue(controller.matchesSearch(observation, "brisbane"));
    }

    @Test
    void observationShouldMatchAnimalSearch() {
        ObservationController controller = new ObservationController();
        Observation observation = createObservation();

        assertTrue(controller.matchesSearch(observation, "Water Dragon"));
    }

    @Test
    void animalSearchShouldIgnoreCase() {
        ObservationController controller = new ObservationController();
        Observation observation = createObservation();

        assertTrue(controller.matchesSearch(observation, "eastern water dragon"));
    }

    @Test
    void nonMatchingSearchShouldReturnFalse() {
        ObservationController controller = new ObservationController();
        Observation observation = createObservation();

        assertFalse(controller.matchesSearch(observation, "Gold Coast"));
    }

    @Test
    void blankSearchShouldMatchObservation() {
        ObservationController controller = new ObservationController();
        Observation observation = createObservation();

        assertTrue(controller.matchesSearch(observation, ""));
    }

    @Test
    void nullSearchShouldMatchObservation() {
        ObservationController controller = new ObservationController();
        Observation observation = createObservation();

        assertTrue(controller.matchesSearch(observation, null));
    }












}