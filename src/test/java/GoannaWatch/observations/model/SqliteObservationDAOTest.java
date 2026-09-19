package GoannaWatch.observations.model;

import GoannaWatch.account.model.Account;
import GoannaWatch.account.model.SqliteAccountDAO;
import GoannaWatch.database.DatabaseInitializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import GoannaWatch.observations.model.Observation;
import GoannaWatch.observations.model.SqliteObservationDAO;
/**
 * Tests saving and reading observations in a temporary database.
 */
public class SqliteObservationDAOTest {

    /**
     * Checks that a saved observation keeps its details and account link.
     *
     * @param folder temporary folder provided by JUnit
     * @throws Exception if database setup or a DAO operation fails
     */
    @Test
    public void testSaveObservation(@TempDir Path folder) throws Exception {
        String testUrl = "jdbc:sqlite:"
                + folder.resolve("test.db").toAbsolutePath();

        String oldUrl = System.getProperty("goannawatch.db.url");

        try {
            System.setProperty("goannawatch.db.url", testUrl);
            DatabaseInitializer.initialize();

            SqliteAccountDAO accountDAO = new SqliteAccountDAO();
            Account account = new Account(
                    "Min",
                    "Test",
                    "min@example.com",
                    "Test123!"
            );

            // The observation needs an account that exists in the database.
            accountDAO.addAccount(account);
            // Read the account back to get its database ID.
            Account savedAccount = accountDAO.getAccountByEmail(
                    "min@example.com"
            );
            assertNotNull(savedAccount);

            LocalDate date = LocalDate.of(2026, 9, 15);
            Observation observation = new Observation(
                    savedAccount,
                    "Brisbane",
                    "Goanna",
                    "No",
                    date
            );

            SqliteObservationDAO dao = new SqliteObservationDAO();
            dao.addObservation(observation);

            // Use the ID assigned when the observation was saved.
            Observation savedObservation = dao.getObservation(
                    observation.getId()
            );

            assertNotNull(savedObservation);
            assertEquals("Brisbane", savedObservation.getLocation());
            assertEquals("Goanna", savedObservation.getAnimalSeen());
            assertEquals(date, savedObservation.getObservedAt());
            assertEquals(
                    savedAccount.getId(),
                    savedObservation.getObserver().getId()
            );

        } finally {
            if (oldUrl == null) {
                System.clearProperty("goannawatch.db.url");
            } else {
                System.setProperty("goannawatch.db.url", oldUrl);
            }
        }
    }
}