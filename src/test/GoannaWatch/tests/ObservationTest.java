package GoannaWatch.tests;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import GoannaWatch.account.model.Account;
import GoannaWatch.observations.model.Observation;

/**
 * Tests the Observation model without using a database.
 */

public class ObservationTest {
    /**
     * Checks that an observation returns the location it was given.
     */
    @Test
    public void testLocation() {
        Account account = new Account(
                "Min",
                "Test",
                "min@example.com",
                "Test123!"
        );

        Observation observation = new Observation(
                account,
                "Brisbane",
                "Goanna",
                LocalDate.of(2026, 9, 15)
        );

        assertEquals("Brisbane", observation.getLocation());
    }
}