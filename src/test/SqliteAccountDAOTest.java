package GoannaWatch.tests;

import GoannaWatch.database.DatabaseInitializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import GoannaWatch.account.model.Account;
import GoannaWatch.account.model.SqliteAccountDAO;

/**
 * Tests saving and reading accounts in a temporary database.
 */
public class SqliteAccountDAOTest {

    /**
     * Checks that a saved account can be found by email.
     *
     * @param folder temporary folder provided by JUnit
     * @throws Exception if database setup or an account operation fails
     */
    @Test
    public void testSaveAccount(@TempDir Path folder) throws Exception {
        String testUrl = "jdbc:sqlite:"
                + folder.resolve("test.db").toAbsolutePath();

        String oldUrl = System.getProperty("goannawatch.db.url");

        // Create the tables in the temporary database.
        try {
            System.setProperty("goannawatch.db.url", testUrl);
            DatabaseInitializer.initialize();

            SqliteAccountDAO dao = new SqliteAccountDAO();

            Account account = new Account(
                    "Min",
                    "Test",
                    "min@example.com",
                    "Test123!"
            );

            dao.addAccount(account);

            // Read the account back to check what was saved.
            Account savedAccount = dao.getAccountByEmail("min@example.com");

            assertNotNull(savedAccount);
            assertEquals("Min Test", savedAccount.getFullName());
            assertEquals("min@example.com", savedAccount.getEmail());

            // Restore the setting so it does not affect other tests.
        } finally {
            if (oldUrl == null) {
                System.clearProperty("goannawatch.db.url");
            } else {
                System.setProperty("goannawatch.db.url", oldUrl);
            }
        }
    }
}